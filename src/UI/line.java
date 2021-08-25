package UI;

import javax.swing.*;
import java.awt.*;

public class line extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawLine(840, 0, 840, 900);
        g.drawLine(0, 0, 50, 50);
        g.drawString("Banner", 0, 40);
    }
}
