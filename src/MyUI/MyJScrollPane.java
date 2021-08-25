package MyUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MyJScrollPane extends JScrollPane {
    public MyJScrollPane(JTable table){
        super(table);
//        setFocusable(false);
    }
    @Override
    public void paint(Graphics g) {
        int fieldX = 0;
        int fieldY = 0;
        int fieldWeight = getSize().width;
        int fieldHeight = getSize().height;
        RoundRectangle2D rect = new RoundRectangle2D.Double(fieldX, fieldY, fieldWeight, fieldHeight, 0, 0);
        g.setClip(rect);
        super.paint(g);
    }
}
