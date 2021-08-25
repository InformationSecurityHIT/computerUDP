package MyUI;

import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MyJTableHeader extends JTableHeader {
    @Override
    public void paint(Graphics g) {
        int fieldX = 0;
        int fieldY = 0;
        int fieldWeight = getSize().width;
        int fieldHeight = getSize().height;
//        if (getModel().isArmed()) {
//            g.setColor(new Color(255,255,255));
//        } else {
//            g.setColor(getBackground());
//        }
        RoundRectangle2D rect = new RoundRectangle2D.Double(fieldX, fieldY, fieldWeight, fieldHeight, 50, 50);

        g.setClip(rect);
        super.paint(g);
    }
}
