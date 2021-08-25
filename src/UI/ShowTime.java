package UI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class is a simple JFrame implementation to explain how to
 * display time dynamically on the JSwing-based interface.
 * @author Edison
 *
 */
public class ShowTime extends JPanel{
	/*
	 * Variables 
	 */
	private JPanel timePanel;
	private JLabel timeLabel;
	private JLabel displayArea;
	private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private String time;
	private int ONE_SECOND = 1000;
  
 	public ShowTime(){
 		timePanel = new JPanel();
 		timePanel.setBackground(new Color(254,254,254));
 		
 		timeLabel = new JLabel("当前时间：");
 		timeLabel.setFont(new Font("等线", Font.PLAIN, 18));
 		displayArea = new JLabel();
 		displayArea.setFont(new Font("等线", Font.PLAIN, 18));
 		displayArea.setBackground(new Color(254,254,254));
 		
  
 		configTimeArea();
  
 		timePanel.add(timeLabel);
 		timePanel.add(displayArea);
 		this.add(timePanel);

 		this.setBounds(1500, 30, 300, 30);
 	}
  
 	/**
 	 * This method creates a timer task to update the time per second
 	 */
 	private void configTimeArea() {
 		Timer tmr = new Timer();
 		tmr.scheduleAtFixedRate(new JLabelTimerTask(),new Date(), ONE_SECOND);
 	}
  
 	/**
 	 * Timer task to update the time display area
 	 *
 	 */
 	protected class JLabelTimerTask extends TimerTask{
 		SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
 		@Override
 		public void run() {
 			time = dateFormatter.format(Calendar.getInstance().getTime());
 			displayArea.setText(time);
 		}
 	}
  
 	
}/* 何问起 hovertree.com */