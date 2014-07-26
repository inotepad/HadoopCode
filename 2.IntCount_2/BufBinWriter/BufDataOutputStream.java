import java.io.DataOutputStream;
import java.io.IOException;
public class BufDataOutputStream {
	final static int BUFSIZE=1024*512;
	private static  byte[] bufData=new byte[BUFSIZE];
	private static  int pos=0;
	private  DataOutputStream bufFileOut;
	public BufDataOutputStream(DataOutputStream fileOut) {
		this.bufFileOut=fileOut;
	}
	public void write(int data){
		if(pos+4<BUFSIZE){
			bufData[pos]=(byte)(data>>24);
			bufData[pos+1]=(byte)(data>>16);
			bufData[pos+2]=(byte)(data>>8);
			bufData[pos+3]=(byte)(data>>0);
			pos+=4;
		}else{
			try {
				bufFileOut.write(bufData);
				pos=0;
				bufData=new byte[BUFSIZE];
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void close(){
		try {
			bufFileOut.write(bufData,0,pos);
			pos=0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
