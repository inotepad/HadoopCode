
import java.io.*;


public class BigBinReader {
	private static FileInputStream fileIn;
	private static Big tmp;
	private static long pos=0;
	public static void main(String[] args) {
		try{
			fileIn = new FileInputStream("D:\\inputbinfile");
			tmp=new Big();
			while(fileIn.available()>0){
				tmp.read(fileIn);
				pos+=92;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(pos);
	}
}
