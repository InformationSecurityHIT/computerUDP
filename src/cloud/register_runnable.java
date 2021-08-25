package cloud;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.util.List;

public class register_runnable implements Runnable{
    private Socket socket = null;
    private DataOutputStream dataOutput;
    private String command;
    private List<String> paths;
    public register_runnable(String name, List<String> paths, Socket socket){
        try {

            this.socket = socket;
            this.dataOutput = new DataOutputStream(this.socket.getOutputStream());
            this.command = "register_" + name;
            this.paths = paths;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        send_message(this.command,this.dataOutput);
        for(int i=0;i<paths.size();i++){
            send_image(this.dataOutput,this.paths.get(i));
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
}
