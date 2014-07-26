import java.io.*;
public class BigBinWriter {
	private static DataOutputStream fileOut;
	private static Big tmp;
	public static void main(String[] args) {
		try{
			fileOut = new DataOutputStream(new FileOutputStream("D:\\inputbinfile"));
			//1024*12000
			for(int count=0;count<1024;count++){
				for(int num=0;num<12000;num++){
					tmp=new Big(num);
					tmp.write(fileOut);
				}
			}
			fileOut.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
