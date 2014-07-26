import java.io.*;
public class BinReader {
	private static int key;
	private static int value;
	private static DataInputStream fileIn;
	private static int total=0,count=0;

	public static void main(String[] args){
		File dirpath=new File("D:\\outputbin");
		for(String filepath:dirpath.list()){
			try{
				fileIn = new DataInputStream(new FileInputStream(dirpath+"\\"+filepath));
				total+=fileIn.available()/12;
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
		System.out.println("Total:"+total);
		System.out.println("Count:"+count);
	}
}
