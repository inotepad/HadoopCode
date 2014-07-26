import java.io.*;

public class BinWriter {
	private static DataOutputStream fileOut;

	public static void main(String[] args) {
		try{
			fileOut = new DataOutputStream(new FileOutputStream("D:\\inputbinfile"));
			for(int count=0;count<10;count++){
				for(int num=0;num<10;num++){
					fileOut.writeInt(num);
				}
			}
			fileOut.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
