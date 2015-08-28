package mainface;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import util.FontFactory;
import util.Info;
public class TopPanel extends JPanel implements Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	private Font f;
	private Color FocusColor = Color.WHITE;
	private MainFace frame;
	public TopPanel(MainFace frame){
		this.frame = frame;
		setOpaque(false);
		setLayout(null);
		setLocation(1, 1);
		setSize(DEFAULTWIDTH, 23);
		try {
			img = ImageIO.read(new File("icon/icon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent arg0) {
				//FocusColor = new Color(220,220,220);
				FocusColor = SystemColor.controlHighlight;
				//System.out.println("lost");
				repaint();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				FocusColor =  Color.WHITE;
				repaint();
				//System.out.println("have");
			}
		});
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		Graphics2D g2 = (Graphics2D) arg0;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//¿¹¾â³ÝÌáÊ¾·û
		try {
			f =  FontFactory.getPassionFont(15.0f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.setColor(FocusColor);
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.BLACK);
		g2.setFont(f);
		g2.drawString("   C o l o r i n g  P a d  V1.0", 28, 17);
		g2.drawImage(img,7,3,null);
		g2.drawLine(getWidth()-110, 0,getWidth()-110,getHeight());
		super.paintComponent(arg0);
	}

}
