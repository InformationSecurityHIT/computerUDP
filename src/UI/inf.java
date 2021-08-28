package UI;

import DataBase.DBBean;
import MyUI.*;
//import com.github.sarxos.webcam.Webcam;
//import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class inf {
    public RoundedBorder save = new RoundedBorder("保存");
    public JLabel label_in = new JLabel("\u59D3\u540D");
    public JLabel phone = new JLabel("\u8054\u7CFB\u65B9\u5F0F");
    public JLabel provence = new JLabel("省份");
    public JLabel city = new JLabel("城市");
    public JLabel street = new JLabel("区县");
    public JLabel home = new JLabel("详细地址");
    public JLabel id_number = new JLabel("身份证号");
    public JLabel secret = new JLabel("密码");

    public JTextField name_in = new JTextField();
    public JTextField idnumber_in = new JTextField();
    public JTextField tel_in = new JTextField();
    public JTextField provence_in = new JTextField();
    public JTextField city_in = new JTextField();
    public JTextField street_in = new JTextField();
    public JTextField home_in = new JTextField();
    public JTextField secret_in = new JTextField();
    JCheckBox power = new JCheckBox("管理员权限");
    //    private Webcam webcam;
    private JPanel p2, basic = new JPanel(), other = new JPanel();
    private DBBean db;
    private JLabel label;
    String tel = null;
    String name = null;

    boolean if_power;

    public ImageIcon imagetoshow6 = new ImageIcon("data_before\\match\\acbd.png");
    public JLabel face = new JLabel(imagetoshow6);

//    private withCamera wCamera;


    public ImageIcon change(ImageIcon image, double i) {//  i 为放缩的倍数

        int width = (int) (image.getIconWidth() * i);
        int height = (int) (image.getIconHeight() * i);
        Image img = image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);//第三个值可以去查api是图片转化的方式
        ImageIcon image2 = new ImageIcon(img);

        return image2;

    }

    /**
     * @param p2 输入界面JPanel
     * @param db 数据库
     */
    public inf(JPanel p2, DBBean db, JLabel label) {
//        this.webcam = webcam;
        this.p2 = p2;
        this.db = db;
        this.label = label;
    }

    /**
     * 录入时输入个人信息界面
     */
    public void initialize_in() {
        /**
         * @dzh at 2021/7/19
         */
        p2.add(basic);
        p2.add(other);
        basic.setBounds(350, 50, 400, 450);
        basic.setBackground(new Color(254, 254, 254));
        basic.setLayout(null);
        basic.setBorder(BorderFactory.createTitledBorder("基本信息"));
        other.setBounds(800, 50, 400, 450);
        other.setLayout(null);
        other.setBackground(new Color(254, 254, 254));
        other.setBorder(BorderFactory.createTitledBorder("位置信息"));

        save.setBackground(new Color(228, 84, 47));
        save.setForeground(Color.WHITE);
        save.setFont(new Font("等线", Font.PLAIN, 18));
        save.setOpaque(false);
        save.setBounds(1120, 750, 300, 40);
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseEntered(evt, save);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewButtonMouseExited(evt, save);
            }
        });
        p2.add(save);

        label_in.setFont(new Font("等线", Font.PLAIN, 18));
        label_in.setBackground(Color.WHITE);
        label_in.setBounds(30, 200, 80, 25);
        basic.add(label_in);
        name_in.setFont(new Font("等线", Font.PLAIN, 18));
        name_in.setBackground(Color.WHITE);
        name_in.setBounds(110, 200, 270, 25);
        name_in.setOpaque(false);
        basic.add(name_in);
        phone.setFont(new Font("等线", Font.PLAIN, 18));
        phone.setBackground(Color.WHITE);
        phone.setBounds(30, 250, 80, 25);
        basic.add(phone);
        tel_in.setFont(new Font("等线", Font.PLAIN, 18));
        tel_in.setBackground(Color.WHITE);
        tel_in.setBounds(110, 250, 270, 25);
        tel_in.setOpaque(false);
        basic.add(tel_in);
        id_number.setFont(new Font("等线", Font.PLAIN, 18));
        id_number.setBackground(Color.WHITE);
        id_number.setBounds(30, 300, 80, 25);
        basic.add(id_number);
        idnumber_in.setFont(new Font("等线", Font.PLAIN, 18));
        idnumber_in.setBackground(Color.WHITE);
        idnumber_in.setBounds(110, 300, 270, 25);
        idnumber_in.setOpaque(false);
        basic.add(idnumber_in);
        secret.setFont(new Font("等线", Font.PLAIN, 18));
        secret.setBackground(Color.WHITE);
        secret.setBounds(30, 350, 80, 25);
        basic.add(secret);
        secret_in.setFont(new Font("等线", Font.PLAIN, 18));
        secret_in.setBackground(Color.WHITE);
        secret_in.setBounds(110, 350, 270, 25);
        secret_in.setOpaque(false);
        basic.add(secret_in);
        face.setBounds(250, 30, 120, 120);
        face.setLayout(null);
        basic.add(face);


        provence.setFont(new Font("等线", Font.PLAIN, 18));
        provence.setBackground(Color.WHITE);
        provence.setBounds(30, 200, 80, 25);
        other.add(provence);
        provence_in.setFont(new Font("等线", Font.PLAIN, 18));
        provence_in.setBackground(Color.WHITE);
        provence_in.setBounds(110, 200, 270, 25);
        provence_in.setOpaque(false);
        other.add(provence_in);
        city.setFont(new Font("等线", Font.PLAIN, 18));
        city.setBackground(Color.WHITE);
        city.setBounds(30, 250, 80, 25);
        other.add(city);
        city_in.setFont(new Font("等线", Font.PLAIN, 18));
        city_in.setBackground(Color.WHITE);
        city_in.setBounds(110, 250, 270, 25);
        city_in.setOpaque(false);
        other.add(city_in);
        street.setFont(new Font("等线", Font.PLAIN, 18));
        street.setBackground(Color.WHITE);
        street.setBounds(30, 300, 80, 25);
        other.add(street);
        street_in.setFont(new Font("等线", Font.PLAIN, 18));
        street_in.setBackground(Color.WHITE);
        street_in.setBounds(110, 300, 270, 25);
        street_in.setOpaque(false);
        other.add(street_in);
        home.setFont(new Font("等线", Font.PLAIN, 18));
        home.setBackground(Color.WHITE);
        home.setBounds(30, 350, 80, 25);
        other.add(home);
        home_in.setFont(new Font("等线", Font.PLAIN, 18));
        home_in.setBackground(Color.WHITE);
        home_in.setBounds(110, 350, 270, 25);
        home_in.setOpaque(false);
        other.add(home_in);
        power.setFont(new Font("等线", Font.PLAIN, 18));
        power.setBackground(Color.WHITE);
        power.setBounds(30, 150, 270, 25);
        power.setOpaque(false);
        other.add(power);
        power.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // 获取事件源（即复选框本身）
                JCheckBox checkBox = (JCheckBox) e.getSource();
                if_power = checkBox.isSelected();
            }
        });
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
    }

    private void action() {
        String name_in0 = name_in.getText();
        String tel_in0 = tel_in.getText();
        String home_in0 = provence_in.getText() + city_in.getText() + street_in.getText() + home_in.getText();
        String idnumber_in0 = idnumber_in.getText();
        String secret_in0 = secret_in.getText();
        if (if_power)
            db.executeInsert("person(name,tel,home_location,id_number,secret,power,face)", '\'' + name_in0 + '\'' + ',' + '\'' + tel_in0 + '\'' + ',' + '\'' + home_in0 + '\'' + ',' + '\'' + idnumber_in0 + '\'' + ',' + '\'' + secret_in0 + '\'' + ",1,1");
        else
            db.executeInsert("person(name,tel,home_location,id_number,secret,power,face)", '\'' + name_in0 + '\'' + ',' + '\'' + tel_in0 + '\'' + ',' + '\'' + home_in0 + '\'' + ',' + '\'' + idnumber_in0 + '\'' + ',' + '\'' + secret_in0 + '\'' + ",0,1");

        ResultSet re = db.executeFindMAXID("person", "id");
        try {
            while (re.next()) {
                tel = re.getString("tel");
//                name = re.getString("name");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(tel);
        input in = new input(label, p2, tel);
        in.inpanel();
        in.take_picture();
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
