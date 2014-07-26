import java.io.*;


public class Big {
	private static byte[] buffer=new byte[88];
	public Big(){
	}
	public static void read(FileInputStream fileIn) throws IOException{
		int ch1=0,ch2=0,ch3=0,ch4=0,tmp=0;
	    fileIn.read(buffer);
	    ch1=fileIn.read();
	    ch2=fileIn.read();
	    ch3=fileIn.read();
	    ch4=fileIn.read();
		tmp=(ch1<<24)+(ch2<<16)+(ch3<<8)+(ch4<<0);
		System.out.println(Integer.toString(tmp));
	}
}
