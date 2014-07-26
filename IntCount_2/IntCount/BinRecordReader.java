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
public class BinRecordReader extends RecordReader<LongWritable, Text> {

  private long start;
  private long pos;
  private long end;
  private FSDataInputStream in;
  private LongWritable key = null;
  private Text value = null;

  public void initialize(InputSplit genericSplit,
                         TaskAttemptContext context) throws IOException {
    FileSplit split = (FileSplit) genericSplit;
    Configuration job = context.getConfiguration();
    start = split.getStart();
    end = start + split.getLength();
    final Path file = split.getPath();
    FileSystem fs = file.getFileSystem(job);
    this.in = fs.open(split.getPath());
    in.seek(start);
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
      value.set(Integer.toString(in.readInt()));
      pos = in.getPos();
      return true;
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
