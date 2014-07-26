import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class BufBinWriter {
	private static DataOutputStream fileOut;
	public static void main(String[] args) {
		try{
			fileOut = new DataOutputStream(new FileOutputStream("D:\\inputbinfile"));
			BufDataOutputStream bufFileOut=new BufDataOutputStream(fileOut);
			//count=1024,num=262144
			for(int count=0;count<1024;count++){
				for(int num=0;num<262144;num++){
					bufFileOut.write(num);
				}
			}
			bufFileOut.close();
			fileOut.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
