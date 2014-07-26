import java.io.DataOutputStream;
import java.io.IOException;


public class Big {
	private static byte[] bigInt=new byte[92];
	public Big(int data){
		bigInt[88]=(byte)(data>>24);
		bigInt[89]=(byte)(data>>16);
		bigInt[90]=(byte)(data>>8);
		bigInt[91]=(byte)(data>>0);
	}
	public Big(String str){
	
	}
	public void write(DataOutputStream fileOut){
		try {
			fileOut.write(bigInt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
