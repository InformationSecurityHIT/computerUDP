import DataBase.DBBean;
import UI.uimain;

import javax.swing.*;
import java.awt.*;


public class main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
//                    JFrame frame = new JFrame();
//                    frame.addKeyListener(new listen());
//                    frame.getContentPane().setFont(new Font("等线", Font.PLAIN, 16));
//                    //@TODO sxz 去除装饰
//                    frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰风格
//                    frame.setBackground(new Color(245, 245, 245));
//                    frame.getContentPane().setBackground(new Color(245, 245, 245));
//                    frame.setBounds(100, 100, 1800, 900);
//                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    frame.setUndecorated(true);//去掉标题栏
//                    //AWTUtilities.setWindowOpaque(frame, false);//JFrame设置为透明
//                    frame.setLocationRelativeTo(null);
//                    frame.setVisible(true);
                    uimain ui = new uimain(new DBBean(),"");
                    ui.begin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
