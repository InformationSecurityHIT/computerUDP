package withAndroidCamera;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

//从android那个地方接收发过来的图片，存在某个路径下
public class receive_runnable implements Runnable{
    private DatagramSocket socket = null;
    private DatagramPacket packet = null;
    private JLabel vein_image_panel = null;


    public receive_runnable(DatagramSocket socket, JLabel vein_image_panel){
        this.socket = socket;
        this.packet = new DatagramPacket(new byte[100000], 100000);
        this.vein_image_panel = vein_image_panel;
    }
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        System.out.println("开始接收数据...");
        while (true){
            try {
                this.socket.receive(this.packet);
                byte[] data = packet.getData();
//                System.out.println(packet.getAddress().getHostAddress()+data.length);
                ImageIcon image = new ImageIcon(ImageIO. read(new ByteArrayInputStream(data)));
                image.setImage(image.getImage().getScaledInstance(700,500, Image.SCALE_DEFAULT ));
                vein_image_panel.setIcon(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void parse_image(byte[] data,String path){
        try (FileOutputStream fileOutputStream =
                     new FileOutputStream(new File(path))){
            fileOutputStream.write(data);
            System.out.println("success"+data.length);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
