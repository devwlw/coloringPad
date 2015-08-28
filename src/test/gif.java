package test;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

public class gif extends JFrame {
	Image globe;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public static void main(String args[]) {
		JFrame f = new gif();
		f.setVisible(true);
		}
	public gif() {
		super("globe");
		setSize(800, 800);
		setUndecorated(true);
		globe = tk.getImage("media/welcome/1.gif");
		
		try {
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(globe,0);
			mt.waitForID(0);
			}
		catch(Exception e) { e.printStackTrace(); }
		}
	/*public void addNotify() {
		super.addNotify(); // this instantiates the peer

		Insets insets = getInsets();
		Dimension scrnsz = tk.getScreenSize();
		Dimension globesz = new Dimension(globe.getWidth(this), 
				globe.getHeight(this));

		setBounds((scrnsz.width/2) - (globesz.width/2),
				(scrnsz.height/2) - (globesz.height/2),
				globesz.width + insets.left + insets.right, 
				globesz.height + insets.top + insets.bottom);
		}*/
	public void paint(Graphics g) {
		Insets insets = getInsets();
		g.drawImage(globe,insets.left,insets.top,this);
		}
	public void update(Graphics g) {
		paint(g);
		}
}

