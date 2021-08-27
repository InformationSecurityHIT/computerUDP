package UI;

import DataBase.DBBean;
import MyUI.NewScrollBarUI;
import MyUI.RoundedBorder;
import com.mysql.cj.util.StringUtils;
import org.python.core.util.StringUtil;
import search.returnVector;
import search.table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class search {
    JTable table0;
    public JTextField search = new JTextField();
    JScrollPane jScrollPane;
    public RoundedBorder sea = new RoundedBorder("搜索");
    private DBBean db;
//    private TableColumn column;

    public search(DBBean db) {
        this.db = db;
    }

    public ImageIcon imagetoshow1 = new ImageIcon("data_before\\match\\search1.png");
    public JLabel pi1 = new JLabel(imagetoshow1);
    public ImageIcon imagetoshow2 = new ImageIcon("data_before\\match\\search2.png");
    public JLabel pi2 = new JLabel(imagetoshow2);
    public ImageIcon imagetoshow3 = new ImageIcon("data_before\\match\\search3.png");
    public JLabel pi3 = new JLabel(imagetoshow3);
    public ImageIcon imagetoshow4 = new ImageIcon("data_before\\match\\search4.png");
    public JLabel pi4 = new JLabel(imagetoshow4);

    /**
     * 搜索界面
     *
     * @param p2 界面的JPanel
     */
    public void searpanel(JPanel p2) {
        table0 = new table(returnVector.getHeadName(db, "record_all"), db).returnAllData_table("record_all");
        jScrollPane = new JScrollPane(table0);//滚动条
        table0.setFont(new Font("等线", Font.PLAIN, 18));
        table0.setRowHeight(27);
        table0.setOpaque(false);
//        table0.setBackground(new Color(247, 247, 247));
        table0.setShowGrid(false);

        search.setText("请输入用户姓名");
        search.setForeground(new Color(100, 100, 100));
        search.setFont(new Font("等线", Font.PLAIN, 18));
        search.setBounds(1182, 22, 150, 30);
        search.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
            }
            @Override
            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub
                if (search.getForeground().equals(new Color(0, 0, 0))) {
                } else {
                    search.setText("");
                    search.setForeground(new Color(0, 0, 0));
                }
            }
        });
        search.setOpaque(false);

        sea.setBounds(1350, 22, 100, 30);
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
        p2.add(sea);
        p2.add(search);

        pi1.setBounds(150, 77, 20, 20);
        pi1.setOpaque(false);
        p2.add(pi1);
        pi2.setBounds(520, 77, 20, 20);
        pi2.setOpaque(false);
        p2.add(pi2);
        pi3.setBounds(880, 77, 20, 20);
        pi3.setOpaque(false);
        p2.add(pi3);
        pi4.setBounds(1260, 77, 20, 20);
        pi4.setOpaque(false);
        p2.add(pi4);
        jScrollPane.setBounds(10, 70, 1490, 750);
        jScrollPane.setOpaque(false);
        jScrollPane.getViewport().setOpaque(false);
//        jScrollPane.setBorder(new MyBorder3());
        jScrollPane.getVerticalScrollBar().setUI(new NewScrollBarUI());
        p2.add(jScrollPane);


        sea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name_sea = search.getText();
                p2.remove(jScrollPane);
                table0 = new table(returnVector.getHeadName(db, "record_all"), db).returnData_table("record_all", name_sea, "name");
                table0.setFont(new Font("等线", Font.PLAIN, 18));
                table0.setRowHeight(27);
                table0.setOpaque(false);
                table0.setShowGrid(false);
//                table0.setBackground(new Color(247, 247, 247));

                jScrollPane = new JScrollPane(table0);
                jScrollPane.setBounds(10, 70, 1490, 750);
                jScrollPane.setOpaque(false);
                jScrollPane.getViewport().setOpaque(false);
//                jScrollPane.setBorder(new MyBorder3());
                jScrollPane.getVerticalScrollBar().setUI(new NewScrollBarUI());
                p2.add(jScrollPane);
            }
        });
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
