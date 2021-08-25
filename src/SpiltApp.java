import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SpiltApp {
	
	public SpiltApp() {
		// TODO Auto-generated constructor stub
	}
	public static void coreSpiltF() throws Exception{
		String standardString;
		File file = new File("D:\\java\\source\\SpiltImage\\Database_1");
		File file2 = new File("D:\\java\\source\\SpiltImage\\train");
			file2.mkdir();
		File[] allFiles = file.listFiles();
		System.out.println(allFiles.length);
		for(int i=1;i<=155;i++) {
			Integer tmp = i;
			String tmpString = tmp.toString();
			List<File> fileList = new ArrayList<File>();
			
			
			for(int j=1;j<allFiles.length;j++) {
				String tt = allFiles[j].getName();
				String[] ttt1 = tt.split("L");
				String[] ttt2 = tt.split("R");
				boolean isMatch1 = Pattern.matches(tmpString, ttt1[0]);
				boolean isMatch2 = Pattern.matches(tmpString, ttt2[0]);
				if(isMatch1==true) {
					fileList.add(allFiles[j]);
				}else if(isMatch2==true) {
					fileList.add(allFiles[j]);
				}
			}
			System.out.println(i+"\t"+fileList.size());
			File file1 = new File("D:\\java\\source\\SpiltImage\\train\\"+tmpString);
			if(!file1.exists()) {
				file1.mkdir();
			}
			
			for(int k=0;k<fileList.size();k++) {
				FileInputStream fis = new FileInputStream(fileList.get(k));
				String destFileName = fileList.get(k).getName().replaceAll("L1|L2|L3|R1|R2|R3", "\\."+k);
				FileOutputStream fos = new FileOutputStream(new File(file1, destFileName));
				
				copy(fis, fos);
				fis.close();
				fos.close();
			}
			

			

			



			
		}
	}
	public static void main(String args[]) throws Exception{
		coreSpiltF();
	}
	public static void copy(InputStream in, OutputStream out) throws Exception {
		byte[] buf = new byte[1024];

		int len = 0;

		/*读取文件内容并写入文件字节流中*/

		while((len = in.read(buf))!=-1) {
			out.write(buf, 0, len);
		}

		

	}
}
