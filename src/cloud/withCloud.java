package cloud;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class withCloud {
    private String host = "192.168.43.246";//云服务器地址
    private int port = 9999;
    private Socket withCloudSocket = null;
    private Executor executor = Executors.newCachedThreadPool();

    public withCloud(int port){
        try {
            this.port = port;
            System.out.println("发起连接请求");
            this.withCloudSocket = new Socket(host, this.port);

//            executor.execute(new SendImageRunnable(snedStringClientSocket, grabber));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void submit(Runnable x){
        this.executor.execute(x);
    }
    public Socket getSocket(){
        return this.withCloudSocket;
    }
}

