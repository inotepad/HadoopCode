import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

/**
 * Treats keys as offset in file and value as line. 
 */
public class BigBinRecordReader extends RecordReader<LongWritable, Text> {

  private long start;
  private long pos;
  private long end;
  private FSDataInputStream fileIn;
  private BigBinReader in;
  private LongWritable key = null;
  private Text value = null;

  public void initialize(InputSplit genericSplit,
                         TaskAttemptContext context) throws IOException {
	long remainder=0;
    FileSplit split = (FileSplit) genericSplit;
    Configuration job = context.getConfiguration();
    start = split.getStart();
    end = start + split.getLength();
    final Path file = split.getPath();
    FileSystem fs = file.getFileSystem(job);
    this.fileIn = fs.open(split.getPath());
    this.in=new BigBinReader(fileIn);
    remainder=start % 92;
    if(remainder !=0){
    	start +=(92-remainder);
    }
    fileIn.seek(start);
    this.pos = start;
  }
  
  public boolean nextKeyValue() throws IOException {
    if (key == null) {
    	key = new LongWritable();
    }
    key.set(pos);
    if (value == null) {
    	value = new Text();
    }
    if (pos < end) {
    	if(in.readBigBin(value)){
    		pos+=92;
    		return true;}
    	else{return false;}
    }else{
    	return false;
    }

  }

  @Override
  public LongWritable getCurrentKey() {
    return key;
  }

  @Override
  public Text getCurrentValue() {
    return value;
  }

  /**
   * Get the progress within the split
   */
  public float getProgress() {
    if (start == end) {
      return 0.0f;
    } else {
      return Math.min(1.0f, (pos - start) / (float)(end - start));
    }
  }
  
  public synchronized void close() throws IOException {
    if (in != null) {
      in.close(); 
    }
  }
}
