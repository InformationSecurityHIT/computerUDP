package UI;

import DataBase.DBBean;
import MyUI.*;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;


import gnu.io.SerialPort;
import serialException.NoSuchPort;
import serialException.NotASerialPort;
import serialException.PortInUse;
import serialException.SerialPortParameterFailure;
import tem.SerialTool;
import twaver.TWaverUtil;
import withAndroidCamera.withCamera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class uimain {
    //    private withCamera wCamera = new withCamera(8888);
    private Executor executor = Executors.newCachedThreadPool();
    private ShowTime st = new ShowTime();
    private SerialPort sport;
    public JFrame frame;

    //    public Webcam webcam;
    public JLabel vein_image_panel=new JLabel();//1&2
    public ImageIcon imageShow;


    public DBBean db;
    public RoundedBorder btnNewButton = new RoundedBorder("        识别静脉                       ->");
    public RoundedBorder btnNewButton_1 = new RoundedBorder("        录入静脉                       ->");
    public RoundedBorder btnNewButton_2 = new RoundedBorder("        查看记录                       ->");
    public RoundedBorder btnNewButton_3 = new RoundedBorder("        隐私保护                       ->");
    public ImageIcon imagetoshow1 = new ImageIcon("data_before\\match\\menu_set.png");
    public ImageIcon imagetoshow2 = new ImageIcon("data_before\\match\\face.png");
    public JLabel pi1 = new JLabel(imagetoshow1);
    //    public ImageIcon imagetoshow2 = new ImageIcon("data_before\\match\\menu2.png");
    public JLabel pi2 = new JLabel(imagetoshow1);
    //    public ImageIcon imagetoshow3 = new ImageIcon("data_before\\match\\menu3.png");
    public JLabel pi3 = new JLabel(imagetoshow1);
    //    public ImageIcon imagetoshow4 = new ImageIcon("data_before\\match\\menu4.png");
    public JLabel pi4 = new JLabel(imagetoshow1);
    public JLabel label_face = new JLabel(imagetoshow2);
    public JPanel title_red = new JPanel();
    //@TODO sxz
//    private ImageIcon tmpIcon = new ImageIcon("data_before\\match\\07.png");
//    private ImageIcon leftDownIcon = change(tmpIcon,0.95);
    private JLabel leftDownLabel = new JLabel();
    JPanel p1 = new JPanel();//左侧
    JPanel p2;//右侧
    JPanel title = new JPanel();//上侧
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JLabel hello = new JLabel();
    JLabel title_node = new JLabel();
    //private ShowTime st = new ShowTime();

    public uimain(DBBean db) {
        this.db = db;
    }

//    public class update_label implements Runnable {
//        @Override
//        public void run() {
//            while (true) {
//                imageShow = new ImageIcon("image_from_android\\0.png");
//                label = new JLabel(imageShow);
//            }
//        }
//    }

    /**
     * 初始化左侧菜单栏
     */
    public void begin() throws Exception {

//        wCamera.submit("image_from_android\\0.png");
//        this.executor.execute(new update_label());
        frame = new JFrame();
        p2 = new JPanel();
        vein_image_panel.setIcon(new ImageIcon("image_from_android/black.jpg"));
        vein_image_panel.setBackground(Color.BLACK);
        vein_image_panel.setBounds(300,100,700,500);
//        Thread thread = new Thread() {
//
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(2);
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                    frame.repaint();
//                }
//            }
//        };

//        thread.start();
        frame.getContentPane().setFont(new Font("等线", Font.PLAIN, 16));
        //@TODO sxz 去除装饰
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰风格
        frame.setBackground(new Color(245, 245, 245));
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setBounds(100, 100, 1800, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);//去掉标题栏
        //AWTUtilities.setWindowOpaque(frame, false);//JFrame设置为透明
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        leftDownLabel.setLayout(null);
//        leftDownLabel.setIcon(leftDownIcon);
//        leftDownLabel.setBounds(20, 430, leftDownIcon.getIconWidth(), leftDownIcon.getIconHeight());
        title.setBounds(0, 0, 1800, 100);
        title.setLayout(null);
        title.setBackground(new Color(254, 254, 254));
//        p1.setBackground(new Color(245,245,245));
        p1.setLayout(null);
//        p4.setBackground(new Color(244,204,204));
        p4.setBackground(new Color(254, 254, 254));
//        p4.setBounds(5, 5, 230, 850);
        p4.setLayout(null);
//        p4.setBorder(BorderFactory.createRaisedBevelBorder());
//        p4.setBorder(new MyBorder());
        p2.setBounds(0, 0, 1500, 850);
        p2.setLayout(null);
        p3.setBounds(0, 0, 1500, 850);
        p3.setBackground(new Color(250, 250, 250));
        p3.setLayout(null);
        p2.add(p3);

        //p3.add(st);
//        p4.add(leftDownLabel);
        p1.add(p4);
        pi1.setBounds(20, 137, 25, 25);
        pi1.setOpaque(false);
        p4.add(pi1);
        pi2.setBounds(20, 197, 25, 25);
        pi2.setOpaque(false);
        p4.add(pi2);
        pi3.setBounds(20, 257, 25, 25);
        pi3.setOpaque(false);
        p4.add(pi3);
        pi4.setBounds(20, 317, 25, 25);
        pi4.setOpaque(false);
        p4.add(pi4);
        label_face.setBounds(115, 10, 70, 70);
        label_face.setOpaque(false);
        p4.add(label_face);
        hello.setBounds(95, 90, 150, 20);
        hello.setOpaque(false);
        hello.setForeground(new Color(102, 102, 102));
        p4.add(hello);
        hello.setFont(new Font("等线", Font.PLAIN, 18));
        hello.setText("您好！管理员");
        title_node.setBounds(20, 0, 400, 75);
        title_node.setOpaque(false);
        title_node.setForeground(new Color(254, 254, 254));
        title.add(title_node);
        title_node.setFont(new Font("等线", Font.BOLD, 24));
        title_node.setText("静脉识别之多用途高效身份认证系统");
        st.setBounds(1500, 40, 300, 30);
        st.setOpaque(false);
        title.add(st);
//        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("等线", Font.PLAIN, 18));
        btnNewButton.setBackground(new Color(254, 254, 254));
        btnNewButton.setBounds(0, 120, 300, 60);
        btnNewButton.setOpaque(false);
        btnNewButton.setForeground(new Color(102, 102, 102));
        btnNewButton_1.setOpaque(false);
        btnNewButton_2.setOpaque(false);
        btnNewButton_3.setOpaque(false);
//btnNewButton.setBorder(BorderFactory.createRaisedBevelBorder());
//btnNewButton_1.setBorder(BorderFactory.createRaisedBevelBorder());
//btnNewButton_2.setBorder(BorderFactory.createRaisedBevelBorder());
//btnNewButton_3.setBorder(BorderFactory.createRaisedBevelBorder());
        p4.add(btnNewButton);
//        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFont(new Font("等线", Font.PLAIN, 18));
        btnNewButton_1.setForeground(new Color(102, 102, 102));
        btnNewButton_1.setBackground(new Color(254, 254, 254));
        btnNewButton_1.setBounds(0, 180, 300, 60);
        p4.add(btnNewButton_1);
//        btnNewButton_2.setForeground(Color.WHITE);
        btnNewButton_2.setFont(new Font("等线", Font.PLAIN, 18));
        btnNewButton_2.setBackground(new Color(254, 254, 254));
        btnNewButton_2.setForeground(new Color(102, 102, 102));
        btnNewButton_2.setBounds(0, 240, 300, 60);
        p4.add(btnNewButton_2);
//        btnNewButton_3.setForeground(Color.WHITE);
        btnNewButton_3.setFont(new Font("等线", Font.PLAIN, 18));
        btnNewButton_3.setBackground(new Color(254, 254, 254));
        btnNewButton_3.setForeground(new Color(102, 102, 102));
        btnNewButton_3.setBounds(0, 300, 300, 60);
        p4.add(btnNewButton_3);
        title.add(title_red);
        title_red.setBackground(new Color(228, 84, 47));
        title_red.setBounds(0, 0, 420, 75);
        //p4.add(btnNewButton_4);

//        webcam = Webcam.getWebcams().get(0);//从摄像头获取数据
//        webcam.setViewSize(WebcamResolution.VGA.getSize());

        //@TODO wrl
//        imageShow = new ImageIcon("image_from_android\\0.png");
//        label = new JLabel(imageShow);


//        panel = new RecPanel2(webcam);
//        panel.setFPSDisplayed(true);
//        panel.setDisplayDebugInfo(true);
//        panel.setImageSizeDisplayed(true);
//        panel.setMirrored(false);
//        //@DZH
//        panel.setOpaque(false);

        openPort();
//        p.add(p1);
//        p.add(p2);
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, p4, p2);
        JSplitPane sp1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, title, sp);
        //sp.setLayout(null);
        sp1.setOpaque(false);//Panel设置为透明
        sp.setOpaque(false);//Panel设置为透明
        sp.setDividerLocation(300);
        sp1.setDividerLocation(75);
        sp.setDividerSize(0);//删除分割线
        sp1.setDividerSize(0);//删除分割线

//        AWTUtilities.setWindowOpaque(frame, false);//JFrame设置为透明

//        frame.add(sp);
        frame.add(sp1);

        //###########################################################
        //更新匹配的界面
        //##############################################################
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        //p3.add(st);
                        p3.updateUI();
//                        label.setBounds(0, 5, 640, 480);//摄像头
//                        label.setBorder(new MyBorder());
                        p3.removeAll();
//                        new dis().dispanel(wCamera, p3, db, sport);
                        new dis().dispanel(p3, db, vein_image_panel);
//                        p3.add(label);
                    }
                });
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        p3.updateUI();
                        p3.removeAll();
//                        label.setBounds(0, 5, 640, 480);//摄像头
//                        input in = new input(webcam,panel,p2,"200");
//                        in.inpanel();
//                        in.take_picture();
//                        new inf(wCamera, p3, db, label).initialize_in();
                        new inf(p3, db, vein_image_panel).initialize_in();
                    }
                });
            }
        });
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        p3.updateUI();
                        p3.removeAll();
                        new search(db).searpanel(p3);
                    }
                });
            }
        });
        btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseEntered(evt, btnNewButton);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseExited(evt, btnNewButton);
            }
        });
        btnNewButton_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseEntered(evt, btnNewButton_1);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseExited(evt, btnNewButton_1);
            }
        });
        btnNewButton_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseEntered(evt, btnNewButton_2);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseExited(evt, btnNewButton_2);
            }
        });
        btnNewButton_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseEntered(evt, btnNewButton_3);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseExited(evt, btnNewButton_3);
            }
        });
    }

    private void openPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    long start_time = System.currentTimeMillis();
                    sport = SerialTool.openPort("COM4", 9600);
                    long end_time = System.currentTimeMillis();
                    System.out.println(end_time - start_time);
                } catch (SerialPortParameterFailure serialPortParameterFailure) {
                    serialPortParameterFailure.printStackTrace();
                } catch (NotASerialPort notASerialPort) {
                    notASerialPort.printStackTrace();
                } catch (NoSuchPort noSuchPort) {
                    noSuchPort.printStackTrace();
                } catch (PortInUse portInUse) {
                    portInUse.printStackTrace();
                }
            }
        }).start();
    }

    private void btnNewButtonMouseEntered(java.awt.event.MouseEvent evt, RoundedBorder r) {
        r.setForeground(Color.WHITE);
        r.setBackground(new Color(228, 84, 47));
    }

    private void btnNewButtonMouseExited(java.awt.event.MouseEvent evt, RoundedBorder r) {
        r.setForeground(new Color(102, 102, 102));
        r.setBackground(new Color(254, 254, 254));
    }
}
