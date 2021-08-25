package MyUI;
import java.awt.*;
import javax.swing.*;

public class BackgroundPanel extends JPanel {
    private static final long serialVersionUID = -6352788025440244338L;
    protected void paintComponent(Graphics g) {
        Image image=new ImageIcon("data_before\\match\\background6.jpg").getImage();
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
}
            }
