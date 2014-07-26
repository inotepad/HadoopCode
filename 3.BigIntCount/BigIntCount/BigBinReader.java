import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.io.Text;

/**
 * A class that provides a line reader from an input stream.
 */
public class BigBinReader {
  private InputStream in;
  private byte[] buffer=new byte[92];
  
  public BigBinReader(InputStream in) {
    this.in = in;
  }

  public void close() throws IOException {
    in.close();
  }
  
  public boolean readBigBin(Text str) throws IOException {
	int newSize=0,bufferLength=0,ch1=0,ch2=0,ch3=0,ch4=0,tmp=0;
    str.clear();
    while(bufferLength<92){
    	newSize=in.read(buffer,bufferLength,92-bufferLength);
    	if(newSize<0){
    		return false;
    	}
    	bufferLength+=newSize;
    }
    
    ch1=buffer[88];
    if(ch1<0){	ch1=(ch1<<24)>>>24;}
    ch2=buffer[89];
    if(ch2<0){	ch2=(ch2<<24)>>>24;}
    ch3=buffer[90];
    if(ch3<0){	ch3=(ch3<<24)>>>24;}
    ch4=buffer[91];
    if(ch4<0){	ch4=(ch4<<24)>>>24;}

    tmp=(ch1<<24)+(ch2<<16)+(ch3<<8)+(ch4<<0);
    str.set(Integer.toString(tmp));
    return true;
  }
}
