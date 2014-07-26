import java.io.*;
public class BinReader {
	private static int key;
	private static int value;
	private static DataInputStream fileIn;

	public static void main(String[] args){
		File dirpath=new File("D:\\outputbin");
		for(String filepath:dirpath.list()){
			try{
				fileIn = new DataInputStream(new FileInputStream(dirpath+"\\"+filepath));
				while(fileIn.available()>0){
					key=fileIn.readInt();
					fileIn.readInt();
					value=fileIn.readInt();
					System.out.println("key:"+key+"\t"+"value:"+value);
				}
				fileIn.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
