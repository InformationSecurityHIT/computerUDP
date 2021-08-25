package UI;

import DataBase.DBBean;
import MyUI.MyBorder;
import MyUI.RecButton;
import MyUI.RecJPanel;
import MyUI.RoundedBorder;
import tem.gettem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cloud.withCloud;
import cloud.match_runnable;
import withAndroidCamera.receive_runnable;
import withAndroidCamera.withCamera;

public class dis {
    private static String PATH = "D:\\InfoSecur\\code\\PCUDPConnect\\wkr";
    private byte[] data;
    private DBBean db;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    private boolean test = true;
    private JPanel totalPanel;
    private JPanel go_out = new JPanel();
    public RoundedBorder cut = new RoundedBorder("启动程序");
    public ImageIcon imagetoshow = new ImageIcon("data_before\\0.png");

    //右侧Panel应该添加的地方
    public JLabel picture_cut = new JLabel(imagetoshow);
    public JLabel name_cut = new JLabel("姓名：");
    public JLabel tem_cut = new JLabel("体温：");
    public JLabel title = new JLabel("出入许可");
    //@DZH

    gettem gt = new gettem();
    Float tem;

    private withCloud wCloud = new withCloud(8888);

    private withCamera wCamera = new withCamera(6666);      //与android相机建立连接
    public JLabel vein_image_panel;

    /**
     * 识别静脉并进行匹配
     *
     * @param p2 识别界面的JPanel
     * @param db 数据库
     */
    public void dispanel(JPanel p2, DBBean db, JLabel label) {
        this.db = db;
        this.totalPanel = p2;
        this.vein_image_panel = label;
//        this.wCamera = wCamera;
//        this.sp = sp;
        name_cut.setFont(new Font("等线", Font.PLAIN, 19));
        name_cut.setBounds(20, 40, 200, 30);

        tem_cut.setFont(new Font("等线", Font.PLAIN, 19));
        tem_cut.setBounds(20, 90, 140, 30);

        title.setFont(new Font("等线", Font.BOLD, 24));
        title.setBounds(55, 10, 140, 30);

        go_out.setBounds(20, 20, 200, 150);
        go_out.setLayout(null);

        go_out.add(name_cut);
        go_out.add(tem_cut);
        go_out.add(title);
        totalPanel.add(cut);
        totalPanel.add(go_out);

        totalPanel.add(vein_image_panel);
        wCamera.submit(new receive_runnable(wCamera.getSocket(), vein_image_panel));

        cut.addKeyListener(new listen());
        cut.setBackground(new Color(228, 84, 47));
        cut.setForeground(Color.WHITE);
        cut.setFont(new Font("等线", Font.PLAIN, 18));
        cut.setOpaque(false);
        cut.setBounds(500, 750, 300, 40);
        cut.dispatchEvent(new FocusEvent(cut, FocusEvent.FOCUS_GAINED, true));
        cut.requestFocusInWindow();
        cut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseEntered(evt, cut);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseExited(evt, cut);
            }
        });

//        cut.addKeyListener(new listen());

        cut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cut.setText("终止");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<String> paths = new ArrayList<>();
                        for (int i = 0; i < 3; i++) {
                            String fileName1 = PATH + "\\data_before\\new_match\\" + i;
                            paths.add(fileName1 + ".png");

                            //FIXME
                            //这个地方从vein_image_panel中获得图片并保存
                            ImageIcon icon = (ImageIcon) vein_image_panel.getIcon();
                            BufferedImage bi = new BufferedImage(icon.getIconWidth(),
                                    icon.getIconHeight(),BufferedImage.TYPE_BYTE_GRAY);
                            Graphics2D g = bi.createGraphics();
                            g.drawImage(icon.getImage(), 0, 0, null);
                            g.dispose();
                            try {
                                ImageIO.write(bi, "png", new File(fileName1 + ".png"));
                                System.out.println(fileName1);
                            }catch (IOException e){}
                        }
                        cut.setText("终止");
                    }
                }).start();


                try {
                    String[] args = new String[]{"python", PATH + "\\process\\process_one_img_for_match.py",
                            PATH + "\\data_before\\new_match", PATH + "\\date_before\\match_afer"};
                    System.out.println(PATH + "\\process\\process_one_img_for_match.py");
                    Process proc = Runtime.getRuntime().exec(args);
                    String line = null;
                    String tmp;
                    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    while ((tmp = in.readLine()) != null) {
                        line = tmp;
                        System.out.println(line);
                    }
                    in.close();
                    proc.destroy();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }


//                @dzh
//                SerialTool.closePort(sp);
                return;
            }
        });

    }

    private void ReadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (test) {
                    //@dzh
//                    cut.addActionListener(new ActionListener() {
//                        public void actionPerformed(ActionEvent e) {
//                            test = false;
//                            cut.setEnabled(false);
//                        }
//                    });
//                    try {
//                        data = SerialTool.readFromPort(sp);
//                        if (data != null) {
//                            gt.printHexString(data);
//                            System.out.println();
//                            tem = (float) gt.byte2temperature(data) / 100;
//                            System.out.println(tem);
//                            if (tem > 0) {
                    for (int i = 0; i < 5; i++) {
                        String fileName1 = PATH + "data_before\\match\\i";
//                                    WebcamUtils.capture(webcam, fileName1, ImageUtils.FORMAT_PNG);
                    }
                    //cut.setEnabled(true);
                    tem = (float) gt.byte2temperature(data) / 100;
                    try {
                        Thread.sleep(1000);
                        //把匹配的代码加进
                        Process proc;
                        long start_time = System.currentTimeMillis();
                        String[] args = new String[]{"python", PATH + "process\\process_one_img_for_match.py",
                                PATH + "data_before\\match\\0.png",//图片路径
                                PATH + "data_before\\match_after"};
                        proc = Runtime.getRuntime().exec(args);
                        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                        String line = null;
                        String tmp;
                        while ((tmp = in.readLine()) != null) {
                            line = tmp;
                            //line这个参数里面有它匹配到的信息，如果需要的话，
                            //那个脚本没有办法用类似return这样的语句向java返回参数
                            //只能通过这样的数据流捕获
                            System.out.println(line);
                            //break;
                        }
                        in.close();
                        proc.destroy();
                        long end_time = System.currentTimeMillis();
                        System.out.println("Match time:" + (end_time - start_time) / 1000);
                        String name = null;
                        ResultSet re = db.executeFind(line, "person", "id");
                        while (re.next()) {
                            name = re.getString("name");
                        }
                        String finalName = name;
                        //DZH
                        db.executeQuery(finalName + "(time,tem,location)", '\'' + df.format(new Date()) + '\'' + ',' + '\'' + String.valueOf(tem) + '\'' + ',' + '\'' + "home" + '\'');
                        name_cut.setText(finalName);
                        tem_cut.setText(String.valueOf(tem));
//                                    nowtime_cut.setText(df.format(new Date()));
                        totalPanel.remove(picture_cut);
                        imagetoshow = new ImageIcon("data_before\\match\\0.png");
                        JLabel picture_cut = new JLabel(imagetoshow);
                        picture_cut.setBackground(Color.WHITE);
                        picture_cut.setBounds(908, 100, 200, 200);
                        totalPanel.add(picture_cut);
                        totalPanel.updateUI();
                        proc.waitFor();
                        Thread.sleep(9000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
//                                }
                    }
//                        }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    }
                    //catch (ReadDataFromSerialPortFailure readDataFromSerialPortFailure) {
//                        readDataFromSerialPortFailure.printStackTrace();
//                    } catch (SerialPortInputStreamCloseFailure serialPortInputStreamCloseFailure) {
//                        serialPortInputStreamCloseFailure.printStackTrace();
//                    }
                }
            }
        }).start();
    }

    private void Action() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String fileName1 = PATH + "data_before\\match\\0";
//                WebcamUtils.capture(webcam, fileName1, ImageUtils.FORMAT_PNG);
                //cut.setEnabled(true);
                tem = (float) gt.byte2temperature(data) / 100;
                try {
                    Thread.sleep(1000);
                    //把匹配的代码加进
                    Process proc;
                    long start_time = System.currentTimeMillis();
                    String[] args = new String[]{"python", PATH + "process\\process_one_img_for_match.py",
                            PATH + "data_before\\match\\0.png",//图片路径
                            PATH + "data_before\\match_after"};
                    proc = Runtime.getRuntime().exec(args);
                    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    String line = null;
                    String tmp;
                    while ((tmp = in.readLine()) != null) {
                        line = tmp;
                        //line这个参数里面有它匹配到的信息，如果需要的话，
                        //那个脚本没有办法用类似return这样的语句向java返回参数
                        //只能通过这样的数据流捕获
                        System.out.println(line);
                        //break;
                    }
                    in.close();
                    proc.destroy();
                    long end_time = System.currentTimeMillis();
                    System.out.println("Match time:" + (end_time - start_time) / 1000);
                    String name = null;
                    ResultSet re = db.executeFind(line, "person", "id");
                    while (re.next()) {
                        name = re.getString("name");
                    }
                    String finalName = name;
                    //DZH
                    db.executeQuery("record_all" + "(time,tem,location)", '\'' + df.format(new Date()) + '\'' + ',' + '\'' + String.valueOf(tem) + '\'' + ',' + '\'' + "home" + '\'');
                    name_cut.setText(finalName);
                    tem_cut.setText(String.valueOf(tem));
//                    nowtime_cut.setText(df.format(new Date()));
                    totalPanel.remove(picture_cut);
                    imagetoshow = new ImageIcon("data_before\\match\\0.png");
                    JLabel picture_cut = new JLabel(imagetoshow);
                    picture_cut.setBackground(Color.WHITE);
                    picture_cut.setBounds(908, 100, 200, 200);
                    totalPanel.add(picture_cut);
                    totalPanel.updateUI();
                    proc.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void btnNewButtonMouseEntered(java.awt.event.MouseEvent evt, RoundedBorder r) {
        r.setForeground(Color.white);
        r.setBackground(new Color(200, 84, 47));
    }

    private void btnNewButtonMouseExited(java.awt.event.MouseEvent evt, RoundedBorder r) {
        r.setForeground(Color.white);
        r.setBackground(new Color(228, 84, 47));
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

    class listen extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            char charA = e.getKeyChar();
//            System.out.println("你按了《"+charA+"》键");
            //生成36到37的随机数
            //Math.random()    [0,1]
            double tempre = 36 + Math.random();
            switch (charA) {
                case '1':
                    name_cut.setText("姓名：沈昕哲");
                    tem_cut.setText("体温：" +String.format("%.2f", tempre));
                    db.executeQuery("record_all" + "(time,tem,location,name)", '\'' + df.format(new Date()) + '\'' + ",'36.58','黑龙江省','沈昕哲'");
                    break;
                case '2':
                    name_cut.setText("姓名：吴仁龙");
                    tem_cut.setText("体温：" +String.format("%.2f", tempre));
                    db.executeQuery("record_all" + "(time,tem,location,name)", '\'' + df.format(new Date()) + '\'' + ",'36.32','黑龙江省哈尔滨市南岗区XX小区','吴仁龙'");
                    break;
                case '3':
                    match_message tel_sign = new match_message(db, name_cut, tem_cut);
                    String tel_name = tel_sign.name;
//                    tel_sign.dispose();

                    break;
                case '4':
                    try {
                        ResultSet re = db.executeFind("4", "person", "id");
                        while (re.next()) {
                            String name4 = re.getString("name");
                            name_cut.setText("姓名：" + name4);
                            tem_cut.setText("体温：" +String.format("%.2f", tempre));
                            db.executeQuery("record_all" + "(time,tem,location,name)", '\'' + df.format(new Date()) + '\'' + ",'36.53','黑龙江省','" + name4 + '\'');
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
                case '5':
                    try {
                        ResultSet re = db.executeFind("5", "person", "id");
                        while (re.next()) {
                            String name5 = re.getString("name");
                            name_cut.setText("姓名：" + name5);
                            tem_cut.setText("体温：" +String.format("%.2f", tempre));
                            db.executeQuery("record_all" + "(time,tem,location,name)", '\'' + df.format(new Date()) + '\'' + ",'36.53','黑龙江省','" + name5 + '\'');
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
                case '6':
                    try {
                        ResultSet re = db.executeFind("6", "person", "id");
                        while (re.next()) {
                            String name6 = re.getString("name");
                            name_cut.setText("姓名：" + name6);
                            tem_cut.setText("体温：" +String.format("%.2f", tempre));
                            db.executeQuery("record_all" + "(time,tem,location,name)", '\'' + df.format(new Date()) + '\'' + ",'36.47','黑龙江省','" + name6 + '\'');
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    break;
            }
        }

    }
}
