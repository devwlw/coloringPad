package menu;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;

import mainface.MainFace;
import util.FontFactory;
import util.ImageButton;
import util.ImageListFactory;
import util.Info;

public class SetMenu extends Pull{
	private MainFace frame1;
	private ImageButton button;
	private BufferedImage file,empty,help,exit;
	private label o,e,h,c;//打开，清空，帮助，关闭
	public SetMenu(MainFace frame,ImageButton button,MenuContainer mc){
		super(200,220,button,mc);
		this.frame1 = frame;
		this.button = button;
		try {
			file = ImageIO.read(new File("icon/File.png"));
			file = ImageListFactory.getThumbnail(file,20,20);
			help = ImageIO.read(new File("icon/Help.png"));
			help = ImageListFactory.getThumbnail(help,20,20);
			empty = ImageIO.read(new File("icon/null.png"));
			empty = ImageListFactory.getThumbnail(empty,20,20);
			exit = ImageIO.read(new File("icon/exit.png"));
			exit = ImageListFactory.getThumbnail(exit,20,20);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMouseListener(new MouseAdapter() {
		});
		getClose().setVisible(false);
		o = new label(file,"打开新文件");
		h = new label(help,"帮助&关于");
		e = new label(empty,"清空");
		c = new label(exit, "退出");
		o.setLocation(16, 16);
		add(o);
		e.setLocation(16, 16+47);
		add(e);
		h.setLocation(16, 16+47+47);
		add(h);
		c.setLocation(16, 16+47+47+47);
		add(c);
	}
	private class label extends JLabel{
		BufferedImage img;
		GradientPaint paint = null;
		
		// = new GradientPaint(0, 0,new Color(56,56,56) ,
		//			0, getHeight(),new Color(28,28,28));
		private Color color = Color.WHITE;
		private String str1; 
		private int count = 0;
		private helpMenu hm;
		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public label(BufferedImage img,String str){
			this.img = img;
			this.str1 = str;
			setSize(187, 41);
			setOpaque(false);
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					paint = new GradientPaint(0, 0,new Color(53,106,243) ,0, getHeight(),new Color(38,75,170));
					repaint();
					super.mouseEntered(e);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					paint = null;
					repaint();
					super.mouseExited(e);
				}
				@Override
				public void mousePressed(MouseEvent e) {
					if(str1 =="打开新文件"){
						frame1.getCanvas().getService().open(frame1.getCanvas());
					}
					else if(str1 == "帮助&关于"){
						System.out.println("帮助");
						hm = new helpMenu(label.this);
						if(hm.isVisible() ==false){
							hm.setLocation((Info.MAXWIDTH - hm.getWidth())/2,(Info.MAXHEIGHT - hm.getHeight())/2);
							hm.setVisible(true);
						}	
					}
					else if(str1 == "清空"){
						Graphics g = frame1.getCanvas().getImg().getGraphics();
						g.setColor(Color.WHITE);
						g.fillRect(0, 0, frame1.getCanvas().getImg().getWidth(),frame1.getCanvas().getImg().getHeight());
						frame1.getCanvas().repaint();
					}
					else{
						System.exit(0);
					}
					super.mousePressed(e);
				}
			});
		}

		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.GRAY);
			g2.drawRect(1, 1, getWidth()-15, getHeight()-8);
			if(paint!=null){
				g2.setPaint(paint);
				g.fillRect(1, 1, getWidth()-15, getHeight()-8);
			}
			g2.drawImage(img, 3,10, img.getWidth(),img.getHeight(),null);
			g2.setFont(FontFactory.getSongFont(15));
			g2.setColor(Color.WHITE);
			g2.drawString(str1, 30, 27);
		}
		public Color getColor(){
			return color;
		}
		public void setColor(Color color){
			this.color = color;
		}
		public GradientPaint getPaint() {
			return paint;
		}

		public void setPaint(GradientPaint paint) {
			this.paint = paint;
			repaint();
		}
	}
	private class helpMenu extends JDialog{
		private Im im;
		public helpMenu(label lb){
			setSize(400, 400);
			setOpaque(false);
			setUndecorated(true);
			setLayout(null);
			setVisible(false);
			AWTUtilities.setWindowOpaque(this,false);
			setModal(true);
			im = new Im(this,lb);
			im.setLocation(0, 0);
			add(im);
			
		}
		public Im getIm() {
			return im;
		}
		public void setIm(Im im) {
			this.im = im;
		}
		private class Im extends JPanel{
			private ImageButton close = null;
			private helpMenu hm1;
			private label lb1;
			public Im(helpMenu hm,label lb){
				this.hm1 = hm;
				this.lb1 = lb;
				setSize(400, 400);
				setLayout(null);
				setOpaque(false);
				close = new ImageButton("icon/menu_close.png");
				close.setLocation(getWidth()-40,10);
				add(close);
				close.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {
						close.switchPic(close.getImageName()+"1.png");
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						close.switchPic(close.getImageName()+".png");
					}
					@Override
					public void mousePressed(MouseEvent e) {
						hm1.setVisible(false);
						lb1.setCount(0);
					}
				});

			}
			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
				GradientPaint paint = new GradientPaint(0, 0,new Color(56,56,56) ,
						0, getHeight(),new Color(28,28,28));
				g2.setPaint(paint);
				g2.fillRect(0, 0, getWidth(), getHeight());
				try {
					g2.setFont(FontFactory.getPassionFont(25.0f));
				} catch (FontFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				g2.setColor(Color.WHITE);
				g2.drawString("C o l o r i n g  P a d   V1.0", 50, 50);
				try {
					g2.setFont(FontFactory.getOstrichFont(20.0f));
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g2.drawString("汪林巍    735254599@qq.com",50, 150);
				try {
					g.setFont(FontFactory.getOstrichFont(15.0f));
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g2.drawString("由于时间不足以及某些技术不到位，程序功能还不尽", 50, 250);
				g2.drawString("人意，将在V2.0中完善程序的功能", 50, 300);
			}
			public ImageButton getClose() {
				return close;
			}
			public void setClose(ImageButton close) {
				this.close = close;
			}
		}
		
		}
	}
