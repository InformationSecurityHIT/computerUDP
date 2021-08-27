package UI;

import DataBase.DBBean;
import MyUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sign_up {
    JFrame frame;
    JPanel p2 = new JPanel();
    public ImageIcon imagetoshow1 = new ImageIcon("data_before\\match\\id.png");
    public JLabel pi1 = new JLabel(imagetoshow1);
    public ImageIcon imagetoshow2 = new ImageIcon("data_before\\match\\secret.png");
    public JLabel pi2 = new JLabel(imagetoshow2);
    public JTextField name_in = new JTextField();
    public JPasswordField secret_in = new JPasswordField();
    public RoundedBorder save = new RoundedBorder("登录");
    public JLabel title = new JLabel("智慧静脉");
    public JLabel name = new JLabel("ID");
    public JLabel secret = new JLabel("密码");
    DBBean db = new DBBean();
    public ImageIcon bg = new ImageIcon("data_before\\match\\background.jpeg");
    public JLabel back = new JLabel(bg);

    public static void main(String[] args) {
        new sign_up();
        return;
    }

    public sign_up() {
        frame = new JFrame();
        secret_in.setEchoChar('*');
        frame.setUndecorated(true); // 去掉窗口的装饰
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰风格
        p2.setBackground(new Color(254, 254, 254));
//        frame.setTitle("\u624B\u80CC\u9759\u8109\u8BC6\u522B\u4E0E\u6D4B\u6E29\u8F6F\u4EF6");
        frame.getContentPane().setBackground(new Color(254, 254, 254));
        frame.setBounds(100, 100, 1800, 900);
        p2.setBounds(0, 0, 1800, 900);

        frame.setVisible(true);
        p2.setLayout(null);
        frame.setLayout(null);
        frame.add(p2);
        back.setBounds(0, 0, 1800, 900);
        back.setOpaque(false);

        pi1.setBounds(615, 310, 30, 30);
        pi1.setOpaque(false);
        pi2.setBounds(615, 370, 30, 30);
        pi2.setOpaque(false);
        name_in.setFont(new Font("等线", Font.PLAIN, 18));
        name_in.setBackground(Color.WHITE);
        name_in.setBounds(700, 313, 430, 25);
//        name_in.setOpaque(false);
        p2.add(name_in);
        secret_in.setFont(new Font("等线", Font.PLAIN, 18));
        secret_in.setBackground(Color.WHITE);
        secret_in.setBounds(700, 373, 430, 25);
//        secret_in.setOpaque(false);
        p2.add(secret_in);
        save.setBackground(new Color(228, 84, 47));
        save.setForeground(Color.WHITE);
        save.setFont(new Font("等线", Font.PLAIN, 18));
        save.setOpaque(false);
        save.setBounds(750, 550, 300, 40);
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseEntered(evt, save);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseExited(evt, save);
            }
        });
        title.setFont(new Font("等线", Font.PLAIN, 50));
        title.setBounds(800, 200, 400, 50);
        name.setFont(new Font("等线", Font.PLAIN, 18));
        name.setBounds(650, 307, 100, 40);
        secret.setFont(new Font("等线", Font.PLAIN, 18));
        secret.setBounds(650, 367, 100, 40);
        p2.add(title);
        p2.add(name);
        p2.add(secret);
        p2.add(pi1);
        p2.add(pi2);
        p2.add(save);
        p2.add(back);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
//                            System.out.println("******************************");
                    String id = name_in.getText();
                    String sec = secret_in.getText();
                    ResultSet re = db.executeFind(id, "person", "id");
//                            System.out.println(re);
                    while (re.next())
                        if (re.getString("secret").equals(sec)&&re.getString("power").equals("1")) {
                            frame.dispose();
                            uimain ui = new uimain(db);
                            ui.begin();
                        }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    private void action() {
        String name = name_in.getText();
        String secret = secret_in.getText();
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
