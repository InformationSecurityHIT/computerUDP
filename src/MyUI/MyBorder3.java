package MyUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 *
 */
public class MyBorder3 implements Border {

    private int thickness_ = 3;
    private Color white = new Color(217,217,217);
    private Color gray = Color.WHITE;
    private Color black = Color.WHITE;

    public void paintBorder(Component c, Graphics g, int x, int y, int width,
                            int height) {
        Color oldColor = g.getColor();
        int i;

        for (i = 0; i < thickness_; i++) {
            g.setColor(new Color(245,245,245));
            g.drawRoundRect(x + i, y + i, width-i-i , height-i-i ,50,50); //White Rectangle
//            g.drawArc(x + i, y + i, 50, 50,270,90);
        }
//        for (i = 0; i < thickness_; i++) {
//            g.setColor(new Color(255,255,255));
//
////            g.drawArc(x + i+width-50, y + i, 50, 50,0,90);
////            g.drawArc(x + i, y + i+height-50, 50, 50,180,90);
//            g.drawLine(x + i, y + i, (width - x) - (i * 2), y + i); //Top Outer Edge
//            g.drawLine(x + i, y + i, x + i, (height - y) - (i * 2));  //Left Outer Edge
//        }
//        for (i = thickness_/2; i < thickness_; i++) {
//            g.setColor(new Color(238,238,238));
//            g.drawArc(x + i, y + i, 50, 50,90,90);
//            g.drawLine(x + i, y + i, (width - x) - (i * 2), y + i); //Top Outer Edge
//            g.drawLine(x + i, y + i, x + i, (height - y) - (i * 2));  //Left Outer Edge
//        }
        g.setColor(oldColor);
    }

    public int getThickness() {
        return thickness_;
    }

    public void setThickness(int i) {
        thickness_ = i;
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(thickness_, thickness_, thickness_, thickness_);
    }

}
