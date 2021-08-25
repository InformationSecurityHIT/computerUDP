package MyUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RecButton extends JButton {
    private static final long serialVersionUID = 1L;
    public RecButton(String s) {
        super(s);
        setOpaque(true);
    }
//    @Override
//    public void paintComponent(Graphics g) {
//        if (getModel().isArmed()) {
//            g.setColor(new Color(255, 121, 64));//按下后按钮变成绿色
//        } else {
//            g.setColor(getBackground());
//        }
//        // 这个调用会画一个标签和焦点矩形。
//        super.paintComponent(g);
//    }
    @Override
    public void paint(Graphics g) {
        int fieldX = 0;
        int fieldY = 0;
        int fieldWeight = getSize().width;
        int fieldHeight = getSize().height;
        if (getModel().isArmed()) {
            g.setColor(new Color(255,255,255));
        } else {
            g.setColor(getBackground());
        }
        RoundRectangle2D rect = new RoundRectangle2D.Double(fieldX, fieldY, fieldWeight, fieldHeight, 50, 50);

        g.setClip(rect);
        super.paint(g);
    }
}
