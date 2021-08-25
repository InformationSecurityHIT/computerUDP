package MyUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;


public class RecJPanel extends JPanel {


	private static final long serialVersionUID = 1L;




	public RecJPanel() {
		super();
		setOpaque(false);
		//setSize(300,600);
		//setBackground(new Color(0,24,147));
		//JLabel nameLabel = new JLabel("圆角面板", JLabel.CENTER);
		//nameLabel.setForeground(Color.white);
		//nameLabel.setBounds(0, 0, 80, 30);
		//nameLabel.setAlignmentY(0.1f);
		//add(nameLabel);
	}

	@Override
	public void paint(Graphics g) {
		int fieldX = 0;
		int fieldY = 0;
		int fieldWeight = getSize().width;
		int fieldHeight = getSize().height;
		RoundRectangle2D rect = new RoundRectangle2D.Double(fieldX, fieldY, fieldWeight, fieldHeight, 50, 50);

		g.setClip(rect);
		super.paint(g);
	}

	protected void paintComponent(Graphics g) {
		Image image=new ImageIcon("data_before\\match\\background2.jpg").getImage();
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
//	public static void main(String[] args) {
//		JFrame frame = new JFrame("圆角面板");
//		frame.setLayout(null);
//		frame.setBounds(500, 300, 500, 500);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		RecJPanel panel = new RecJPanel();
//		panel.setLocation(0, 0);
//		frame.add(panel);
//		frame.setVisible(true);
//	}

