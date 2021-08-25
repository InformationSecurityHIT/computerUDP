package UI;

import MyUI.MyBorder;
import MyUI.RecButton;
import MyUI.RoundedBorder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cloud.*;
import withAndroidCamera.*;

public class input {

    private static String PATH = "D:\\InfoSecur\\code\\PCUDPConnect\\wkr";
    private RoundedBorder cut2 = new RoundedBorder("开始拍照");
    //    private Webcam webcam;
    private ImageIcon imagetoshow;
    private JLabel TipsPicture_label;
    private JLabel Tips_text;
    private JLabel id_text;
    private JLabel label;
    private JPanel p2;
    private String finalId;

    private withCloud wCloud = new withCloud(8888);
    //    private withCamera wCamera;
    private Executor executor = Executors.newCachedThreadPool();

    private withCamera wCamera = new withCamera(6666);      //与android相机建立连接
    public JLabel vein_image_panel;

    /**
     * 构造函数
     *
     * @param p2      界面JPanel
     * @param finalId 当前用户的ID
     */
    public input(JLabel label, JPanel p2, String finalId) {
//        this.wCamera = wCamera;
        this.vein_image_panel = label;
        this.p2 = p2;
        this.finalId = finalId;
    }

    /**
     * 录入静脉界面
     */
    public void inpanel() {
        p2.updateUI();
        p2.removeAll();
//        p2.add(label);
//        label_cut.setBackground(Color.BLACK);
//        label_cut.setFont(new Font("等线", Font.PLAIN, 50));
//        label_cut.setBounds(100, 80, 640, 100);
//        p2.add(label_cut);
        //cut2.setBackground(new Color(0, 153, 153));
        cut2.setBackground(new Color(228, 84, 47));
        cut2.setForeground(Color.WHITE);
        cut2.setFont(new Font("等线", Font.PLAIN, 18));
        cut2.setOpaque(false);
        cut2.setBounds(500, 750, 300, 40);
        cut2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseEntered(evt, cut2);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseExited(evt, cut2);
            }
        });
        p2.add(cut2);
        //        @DZH
//        picture_cut2.setBackground(new Color(205, 213, 253));

//        p2.add(label);


        vein_image_panel.setIcon(new ImageIcon("image_from_android/black.jpg"));
        vein_image_panel.setBackground(Color.BLACK);
        vein_image_panel.setBounds(300, 100, 700, 500);
        p2.add(vein_image_panel);
        wCamera.submit(new receive_runnable(wCamera.getSocket(), vein_image_panel));


    }

    private void btnNewButtonMouseEntered(java.awt.event.MouseEvent evt, RecButton r) {
        // TODO add your handling code here:
        r.setForeground(Color.black);
    }

    private void btnNewButtonMouseExited(java.awt.event.MouseEvent evt, RecButton r) {
        // TODO add your handling code here:
        r.setForeground(Color.white);
    }

    /**
     * 拍照并存储静脉图像
     */
    public void take_picture() {
//        imagetoshow = new ImageIcon("gesture\\normal.jpg");
//        TipsPicture_label = new JLabel(imagetoshow);
//        TipsPicture_label.setBackground(Color.WHITE);
//        TipsPicture_label.setBounds(800, 150, 400, 400);
//
        Tips_text = new JLabel("准备静脉录入");
        Tips_text.setFont(new Font("等线", Font.PLAIN, 24));
        Tips_text.setBounds(600, 10, 200, 30);

        id_text = new JLabel("您的ID为：" + finalId);
        id_text.setFont(new Font("等线", Font.PLAIN, 24));
        id_text.setBounds(600, 40, 200, 30);
//
////        p2.add(TipsPicture_label);
        p2.add(Tips_text);
        p2.add(id_text);
        cut2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseEntered(evt, cut2);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseExited(evt, cut2);
            }
        });
        cut2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
    }

    private void action() {
        cut2.setEnabled(false);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> paths = new ArrayList<>();
//                finalId = "wrll";
                File file1 = new File("data_before\\" + finalId);
                File file2 = new File("data_after\\" + finalId);
                if (!file1.exists()) file1.mkdir();
                if (!file2.exists()) file2.mkdir();
                for (int j = 0; j < 2; j++) {
                    Tips_text.setText("拍照中...");
                    for (int i = 0; i < 3; i++) {
                        String fileName = "data_before\\" + finalId + "\\" + finalId + "_" + (5*j + i);
                        paths.add(fileName+".png");
                        //FIXME
                        //这个地方从vein_image_panel中获得图片并保存
                        ImageIcon icon = (ImageIcon) vein_image_panel.getIcon();
                        BufferedImage bi = new BufferedImage(icon.getIconWidth(),
                                icon.getIconHeight(),BufferedImage.TYPE_BYTE_GRAY);
                        Graphics2D g = bi.createGraphics();
                        g.drawImage(icon.getImage(), 0, 0, null);
                        g.dispose();
                        try {
                            ImageIO.write(bi, "png", new File(fileName + ".png"));
                            //System.out.println(fileName);
                        }catch (IOException e){}
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        wCamera.save_image(fileName+".png");
//                        WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_PNG);
//                        copy2path("D:\\Lab\\Info_Security_53\\dzh\\wkr\\image_from_android\\0.png",
//                                fileName+".png");
                    }
//                    try {
////                        Tips_text.setText("赶紧换手！！！");
////                        Thread.sleep(10000);
//                    } catch (InterruptedException interruptedException) {
//                        interruptedException.printStackTrace();
//                    }
                }
                Tips_text.setText("录入结束");
//                System.out.println("拍照完成");

                try {
                    System.out.println(PATH + "process\\process_all_imgs_for_register.py");
                    //调用脚本去处理录入图片
                    String[] args_register = new String[]{"python", PATH + "\\process\\process_all_imgs_for_register.py",
                            PATH + "\\data_before\\" + finalId,
                            PATH + "\\data_after\\" + finalId,
                            PATH + "\\keyPoint\\" + finalId};
                    Process proc_register = Runtime.getRuntime().exec(args_register);
                    String line = null;
                    String tmp;
                    BufferedReader in = new BufferedReader(new InputStreamReader(proc_register.getInputStream()));
                    while ((tmp = in.readLine()) != null) {
                        line = tmp;
                        System.out.println(line);
                    }
                    in.close();
                    proc_register.destroy();
                }catch (IOException e){}
                //executor.execute(new register_runnable(finalId.toString(), paths, wCloud.getSocket()));
//                wCloud.submit(new register_runnable(finalId.toString(),paths,wCloud.getSocket()));
            }
        });
        th.start();
        cut2.setEnabled(true);
    }

    public void copy2path(String source_path, String dest_path) {
        try {
            File result = new File(source_path);//需要复制到的路径，以及图片的新命名+格式
            FileInputStream input = new FileInputStream(dest_path);//需要复制的原图的路径+图片名+ .png(这是该图片的格式)
            FileOutputStream out = new FileOutputStream(result);
            byte[] buffer = new byte[1000000];//一个容量，相当于打水的桶，可以自定义大小
            int hasRead = 0;
            while ((hasRead = input.read(buffer)) > 0) {
                out.write(buffer, 0, hasRead);//0：表示每次从0开始
            }
            input.close();//关闭
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void btnNewButtonMouseEntered(java.awt.event.MouseEvent evt, RoundedBorder r) {
        r.setForeground(Color.white);
        r.setBackground(new Color(200, 84, 47));
    }

    private void btnNewButtonMouseExited(java.awt.event.MouseEvent evt, RoundedBorder r) {
        r.setForeground(Color.white);
        r.setBackground(new Color(228, 84, 47));
    }
}
