import java.io.DataOutputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/** An {@link OutputFormat} that writes plain text files. */
public class BinOutputFormat<K, V> extends FileOutputFormat<K, V> {
  protected static class BinRecordWriter<K, V>
    extends RecordWriter<K, V> {
    protected DataOutputStream out;
    private final int keyValueSeparator=0xFFFFFFFF;

    public BinRecordWriter(DataOutputStream out) {
      this.out = out;
    }

    private void writeObject(Object o) throws IOException {
      if (o instanceof Text){
    	  Text to=(Text)o;
    	  String str=to.toString();
    	  char[] a=str.toCharArray();
    	  int result=0;
    	  for(int i=a.length-1,j=1;i>=0;i--,j*=10){
    		  result += (a[i]-'0')*j;
    	  }
    	  out.writeInt(result);
      }
      if (o instanceof IntWritable) {
        IntWritable to = (IntWritable) o;
        out.writeInt(to.get());
      }

    }

    public synchronized void write(K key, V value)
      throws IOException {

      boolean nullKey = key == null || key instanceof NullWritable;
      boolean nullValue = value == null || value instanceof NullWritable;
      if (nullKey && nullValue) {
        return;
      }
      if (!nullKey) {
        writeObject(key);
      }
      if (!(nullKey || nullValue)) {
        out.writeInt(keyValueSeparator);
      }
      if (!nullValue) {
        writeObject(value);
      }
    }

    public synchronized 
    void close(TaskAttemptContext context) throws IOException {
      out.close();
    }
  }

  public RecordWriter<K, V> 
         getRecordWriter(TaskAttemptContext job
                         ) throws IOException, InterruptedException {
    Configuration conf = job.getConfiguration();
    String extension = "";
    Path file = getDefaultWorkFile(job, extension);
    FileSystem fs = file.getFileSystem(conf);
    FSDataOutputStream fileOut = fs.create(file, false);
	return new BinRecordWriter<K, V>(fileOut);
  }
}

