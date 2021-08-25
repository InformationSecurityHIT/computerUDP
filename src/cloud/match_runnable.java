package cloud;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.util.List;

//匹配的时候需要发送match命令，发送几张图片，最后需要接收
public class match_runnable  implements Runnable{
    private Socket socket = null;
    private DataOutputStream dataOutput;
    private DataInputStream dataInput;
    private String command;
    private List<String>paths;
    private String match_result = null;
    public match_runnable(List<String> paths,Socket socket) {
//        try {
//            this.socket = socket;
//            this.command = "match";
//            this.dataOutput = new DataOutputStream(this.socket.getOutputStream());
//            this.dataInput = new DataInputStream(this.socket.getInputStream());
//            this.paths = paths;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    @Override
    public void run() {
        try{
            System.out.println("开始发送数据...");
            send_message(this.command,this.dataOutput);
            for(int i=0;i<this.paths.size();i++){
                System.out.println("发送第"+i+"张图片");
                send_image(this.dataOutput,this.paths.get(i));
            }
            while(true){
                int size = this.dataInput.readInt();
                if(size<=0) continue;
                byte[] data = new byte[size];
                int len = 0;
                //将二进制数据写入data数组
                while (len < size) {len += dataInput.read(data, len, size - len); }
                String temp = parse_command(data);
                if(temp.equals("continue")) continue;
                this.match_result = temp;
                System.out.println("成功接收数据");
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String parse_command(byte[] data){
        return new String(data);
    }
    private void send_message(String message, DataOutputStream dataOutput){
        try {
            byte[] tmp = message.getBytes();
            dataOutput.writeInt(tmp.length);
            dataOutput.write(tmp, 0, tmp.length);
            dataOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void send_image(DataOutputStream dataOutput,String path){
        try{
            File f = new File(path);
            byte[] tmp = Files.readAllBytes(f.toPath());
            dataOutput.writeInt(tmp.length);
            dataOutput.write(tmp, 0, tmp.length);
            dataOutput.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getResult(){
        return this.match_result;
    }
}
