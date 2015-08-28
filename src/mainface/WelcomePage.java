package mainface;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.FontFactory;
import util.Info;

import com.sun.awt.AWTUtilities;

public class WelcomePage extends JFrame {
	private Image globe;
	private BufferedImage g;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private File file;
	private int count;//文件夹中的图片个数
	private String[] images;
	private int index;//图片索引
	private float fontSize;//字体大小
	private String info = null;//显示的信息
	public WelcomePage() {
		//super()
		setUndecorated(true);
		setAlwaysOnTop(true);
		file = new File("media/welcome");
		images = file.list();
		count = images.length;
		index = (int)(Math.random()*100)/(100/count);
		if(0 == index)
			index = 1;
		try {
			setIconImage(ImageIO.read(new File("icon/icon1.png")));
			g = ImageIO.read(new File("media/welcome/"+images[index-1]));
			//if(g.getWidth()>400)
				fontSize = 25.0f;
		//	else
			//	fontSize = 35.0f;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		globe = tk.getImage("media/welcome/"+images[index-1]);	
		setSize(g.getWidth(),g.getHeight());
		setLocation(Info.MAXWIDTH/2 - getWidth()/2,Info.MAXHEIGHT/2 - getHeight()/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(globe,0);
			mt.waitForID(0);
			}
		catch(Exception e) { e.printStackTrace(); }
		add(new in());
		}
	
	class in extends JPanel{
		public in(){
			setSize(g.getWidth(),g.getHeight());
			setLocation(0, 0);
			setLayout(null);
			setOpaque(false);
		}

		@Override
		public void paintComponent(Graphics g) {
			Font f = null;
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
			g.drawImage(globe,0,0,this);
			try {
				f = FontFactory.getLobsterFont(fontSize);
			} catch (FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//f = f.deriveFont(Font.BOLD, fontSize);
			g.setFont(f);
			g.setColor(Color.WHITE);
			g.drawString("C  o  l  o  r  i  n  g   P a d   v1.0",getWidth()/15, getHeight()/8);
			g.setFont(new Font("微软雅黑",Font.PLAIN,15));
			//g.setColor(Color.RED);
			g.drawString(info, getWidth()/13, getHeight()/5);
		}

		
	}
	public String getInfo(){
		return info;
	}
	public void setInfo(String info){
		this.info = info;
		repaint();
	}
}


