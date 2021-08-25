import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class sss{
	public sss(){
		
	}
	public static void printOutPath(String allString) throws Exception{
		
		File file1 = new File("D:\\path.txt");
		file1.createNewFile();
		 try {
	           BufferedWriter out = new BufferedWriter(new FileWriter("D:\\path.txt"));
	           out.write(allString);
	           out.close();
	           
	       } catch (IOException e) {
	       }
	}
	public static String createString() {
		File file = new File("D:\\train");
		File[] allFiles = file.listFiles();
		String allString = new String();
		for(int i=0;i<allFiles.length;i++) {
			File[] intoAllFiles = allFiles[i].listFiles();
			System.out.println(allFiles[i].getName());
			for(int j=0;j<intoAllFiles.length;j++) {
				allString = allString + intoAllFiles[j].getPath()+"|"+allFiles[i].getName()+"\n";
			}
		}
		return allString;
	}
	public static void main(String args[]) throws Exception{
		
		printOutPath(createString());
	}
	
	
	
	
	
	
}