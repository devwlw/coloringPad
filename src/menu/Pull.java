package menu;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import mainface.MainFace;

import util.FaceUtil;
import util.FontFactory;
import util.ImageButton;

public class Pull extends JPanel{
	private MenuContainer mc;
	private ImageButton close = null;

	private Thread cl = null;
	private int xoff = 7,yoff = 7;//·¢É¢±ß¿òµÄ¿í¶È
	private int x=0,y=0;
	private ImageButton button1;
	public Pull(int width,int height,ImageButton button,MenuContainer mc){
		this.button1 = button;
		this.mc = mc;
		setLocation(0, 0);
		setSize(width, height);
		setOpaque(false);
		setLayout(null);
		close = new ImageButton("icon/menu_close.png");
		addButtonLinster();
		close.setLocation(getWidth()-40,10);
		add(close);
	}
	private void addButtonLinster(){
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				button1.setCount(0);
				x = mc.getLocationOnScreen().x;
				y = mc.getLocationOnScreen().y;		
				cl = new Thread(new Runnable() {	
					
					@Override
					public void run() {
						for(int i = x;i>=mc.getWidth()-2*mc.getWidth();){
							mc.setLocation(i,y);
							try {
								TimeUnit.MILLISECONDS.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(x<300){
								i-=15;
							}
							else
								i-= (x/100)*10;
							if(mc.getLocationOnScreen().x < -200){
								mc.setVisible(false);
								cl.interrupt();
								return;
							}
						}
					}
				});
				cl.start();
			}
		});
		close.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				close.switchPic(close.getImageName()+"1.png");
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				close.switchPic(close.getImageName()+".png");
			}
			
		});
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2;
		g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);//¿¹¾â³ÝÌáÊ¾·û
		GradientPaint paint = new GradientPaint(0, 0,new Color(56,56,56) ,
			0, getHeight(),new Color(28,28,28));
		g2.setPaint(paint);
		g2.setFont(FontFactory.getSongFont(17));
		g2.fillRect(xoff, yoff, getWidth()-2*xoff, getHeight()-2*yoff);
		//FaceUtil.createShadowBorder(g2,getWidth(),getHeight(),xoff,yoff);
	}
	public ImageButton getClose() {
		return close;
	}
	public void setClose(ImageButton close) {
		this.close = close;
	}
}
