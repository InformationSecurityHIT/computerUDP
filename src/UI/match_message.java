package UI;

import DataBase.DBBean;
import MyUI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class match_message {
    JFrame frame= new JFrame();
    private JTextField search = new JTextField();
    private RoundedBorder sea = new RoundedBorder("确定");
    private JLabel title = new JLabel("请输入手机号码");
    private DBBean db;
    public static String name ="";
    public JLabel name_label,tem_label;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式


    public match_message(DBBean db,JLabel name_label,JLabel tem_label) {
        this.db = db;
        this.name_label=name_label;
        this.tem_label=tem_label;
        paintUI();
    }

    public static void main(String[] args) {
    }


    public void paintUI() {

        frame.setUndecorated(true); // 去掉窗口的装饰
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰风格
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setBounds(800, 300, 390, 250);
        frame.getContentPane().setBackground(new Color(254, 254, 254));
        frame.setLayout(null);

        title.setFont(new Font("等线", Font.PLAIN, 23));
        title.setBounds(120, 20, 200, 30);


        search.setText("请输入手机号码");
        search.setForeground(new Color(100, 100, 100));
        search.setFont(new Font("等线", Font.PLAIN, 18));
        search.setBounds(70, 80, 250, 40);
        search.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (search.getForeground().equals(new Color(0, 0, 0))) {
                } else {
                    search.setText("");
                    search.setForeground(new Color(0, 0, 0));
                }
            }
        });
        search.setOpaque(false);


        sea.setBounds(70, 150, 250, 40);
        sea.setFont(new Font("等线", Font.PLAIN, 18));
        sea.setBackground(new Color(228, 84, 47));
        sea.setForeground(Color.WHITE);
        sea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseEntered(evt, sea);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseExited(evt, sea);
            }
        });
        sea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String tel = search.getText();
                    ResultSet re = db.executeFind(tel, "person", "tel");
                    name="";
                    while (re.next())
                        name = re.getString("name");
                    System.out.println(name);
                    if(name!="") {
                        name_label.setText("姓名：" + name);
                        tem_label.setText("体温：36.61");
                        db.executeQuery("record_all" + "(time,tem,location,name)", '\'' + df.format(new Date()) + '\'' + ",'36.61','黑龙江省','" + name + '\'');
                    }else{
                        name_label.setText("姓名：无匹配");
                        tem_label.setText("体温：36.48");
                    }
                    frame.dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        frame.add(sea);
        frame.add(title);
        frame.add(search);
        frame.setVisible(true);
    }

    public void dispose(){
        frame.dispose();
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
