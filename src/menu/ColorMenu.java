package menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import mainface.MainFace;
import tool.AbstractTool;
import util.ImageButton;

public class ColorMenu extends Pull{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8400816560503534122L;
	private MainFace frame;
	private ImageButton button;
	private MenuContainer mc;
	private ColorElement r,g,b;// RGB颜色面板
	private final int WIDTH = 510;
	private Color selectedColor;//当前选中颜色
	private GradientPaint Rp,Gp,Bp;
	private ColorLabel cl;
	private ColorRadomPanel rcp;//随机颜色面板
	private ColorChangeButton ccb;//换一组按钮
	public ColorMenu(MainFace frame, ImageButton button,MenuContainer mc){
		super(700,600,button,mc);
		this.frame = frame;
		this.button = button;
		this.mc = mc;
		setLayout(null);
		r = new ColorElement(WIDTH,40);
		g = new ColorElement(WIDTH,40);
		b = new ColorElement(WIDTH,40);
		Rp = new GradientPaint(0, 0, new Color(255,g.getValue(),b.getValue()),510,0,new Color(0,g.getValue(),b.getValue()));
		Gp = new GradientPaint(0, 0, new Color(r.getValue(),255,b.getValue()),510,0,new Color(r.getValue(),0,b.getValue()));
		Bp = new GradientPaint(0, 0, new Color(r.getValue(),g.getValue(),255),510,0,new Color(r.getValue(),g.getValue(),0));
		r.setgPaint(Rp);
		g.setgPaint(Gp);
		b.setgPaint(Bp);
		r.setLocation(90, 70);
		g.setLocation(90, 140);
		b.setLocation(90,210);
		add(r);
		add(g);
		add(b);
		rcp = new ColorRadomPanel(19*22,19*13,this);
		rcp.setLocation(90, b.getLocation().y+b.getHeight()+20);
		add(rcp);
		addRGBMouseLinstener();
		selectedColor = new Color(r.getValue(),g.getValue(),b.getValue());
		
		cl = new ColorLabel(125, 125);
		cl.setLocation(495+50,rcp.getLocation().y+60);
		cl.setBackColor(selectedColor);
		add(cl);
		
		ccb = new ColorChangeButton(rcp);
		ccb.setBackColor(selectedColor);
		ccb.setLocation(200,525);
		add(ccb);
		}
	@Override
	public void paintComponent(Graphics arg0) {
		Graphics2D g2 = (Graphics2D)arg0;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setColor(new Color(r.getValue(),g.getValue(),b.getValue()));
		g2.fillRect(0, 0, getWidth(),getWidth());
		
		
		Font f = new Font("微软雅黑",Font.PLAIN,24);
		g2.setColor(Color.WHITE);
			if(r.getValue()>200 || g.getValue()>200 || b.getValue() >200)
				g2.setColor(new Color(42,42,42));
		g2.setFont(f);
		g2.drawString(""+r.getValue(),30, r.getLocation().y+26);
		g2.drawString(""+g.getValue(),30, g.getLocation().y+26);
		g2.drawString(""+b.getValue(),30, b.getLocation().y+26);
		
		cl.setBackColor(selectedColor);
		ccb.setBackColor(selectedColor);
	}
	private void addRGBMouseLinstener(){
		class Red extends MouseAdapter{
			@Override
			public void mouseDragged(MouseEvent e) {
				r.setColorX(e.getX());
				if(e.getX() <0 || e.getX()>r.getWidth()) return;
				Gp = new GradientPaint(0, 0, new Color(r.getValue(),255,b.getValue()),510,0,new Color(r.getValue(),0,b.getValue()));
				g.setgPaint(Gp);
				Bp = new GradientPaint(0, 0, new Color(r.getValue(),g.getValue(),255),510,0,new Color(r.getValue(),g.getValue(),0));
				b.setgPaint(Bp);
				setSelectedColor(new Color(r.getValue(),g.getValue(),b.getValue()));
				//repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				r.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				r.setName("Red");
				super.mouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				r.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				r.setName("");
				super.mouseExited(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				r.setColorX(e.getX());
				Gp = new GradientPaint(0, 0, new Color(r.getValue(),255,b.getValue()),510,0,new Color(r.getValue(),0,b.getValue()));
				g.setgPaint(Gp);
				Bp = new GradientPaint(0, 0, new Color(r.getValue(),g.getValue(),255),510,0,new Color(r.getValue(),g.getValue(),0));
				b.setgPaint(Bp);
				setSelectedColor(new Color(r.getValue(),g.getValue(),b.getValue()));
				//repaint();
				
			}		
		};
		class Green extends MouseAdapter{
			@Override
			public void mouseDragged(MouseEvent e) {
				g.setColorX(e.getX());
				if(e.getX() <0 || e.getX()>g.getWidth()) return;
				Rp = new GradientPaint(0, 0, new Color(255,g.getValue(),b.getValue()),510,0,new Color(0,g.getValue(),b.getValue()));
				r.setgPaint(Rp);
				Bp = new GradientPaint(0, 0, new Color(r.getValue(),g.getValue(),255),510,0,new Color(r.getValue(),g.getValue(),0));
				b.setgPaint(Bp);
				setSelectedColor(new Color(r.getValue(),g.getValue(),b.getValue()));
			//	repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				g.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				g.setName("Green");
				super.mouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				g.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				g.setName("");
				super.mouseExited(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				g.setColorX(e.getX());
				Rp = new GradientPaint(0, 0, new Color(255,g.getValue(),b.getValue()),510,0,new Color(0,g.getValue(),b.getValue()));
				r.setgPaint(Rp);
				Bp = new GradientPaint(0, 0, new Color(r.getValue(),g.getValue(),255),510,0,new Color(r.getValue(),g.getValue(),0));
				b.setgPaint(Bp);
				setSelectedColor(new Color(r.getValue(),g.getValue(),b.getValue()));
			//	repaint();
				//rcp.repaint();
			}
		}
		class Blue extends MouseAdapter{
			@Override
			public void mouseDragged(MouseEvent e) {
				b.setColorX(e.getX());
				if(e.getX() <0 || e.getX()>b.getWidth()) return;
				Rp = new GradientPaint(0, 0, new Color(255,g.getValue(),b.getValue()),510,0,new Color(0,g.getValue(),b.getValue()));
				r.setgPaint(Rp);
				Gp = new GradientPaint(0, 0, new Color(r.getValue(),255,b.getValue()),510,0,new Color(r.getValue(),0,b.getValue()));
				g.setgPaint(Gp);
				setSelectedColor(new Color(r.getValue(),g.getValue(),b.getValue()));
				repaint();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				b.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				b.setName("Blue");
				super.mouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				b.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				b.setName("");
				super.mouseExited(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				b.setColorX(e.getX());
				Rp = new GradientPaint(0, 0, new Color(255,g.getValue(),b.getValue()),510,0,new Color(0,g.getValue(),b.getValue()));
				r.setgPaint(Rp);
				Gp = new GradientPaint(0, 0, new Color(r.getValue(),255,b.getValue()),510,0,new Color(r.getValue(),0,b.getValue()));
				g.setgPaint(Gp);
				setSelectedColor(new Color(r.getValue(),g.getValue(),b.getValue()));
				repaint();
			}
		}
		Red redLintener = new Red();
		Green greenLinstener = new Green();
		Blue blueLinstener = new Blue();
		r.addMouseListener(redLintener);
		r.addMouseMotionListener(redLintener);
		g.addMouseListener(greenLinstener);
		g.addMouseMotionListener(greenLinstener);
		b.addMouseListener(blueLinstener);
		b.addMouseMotionListener(blueLinstener);
	}
	public Color getSelectedColor() {
		return selectedColor;
	}
	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
		AbstractTool.color = selectedColor;
		repaint();
	}
	public ColorElement getR() {
		return r;
	}
	public void setR(ColorElement r) {
		this.r = r;
	}
	public ColorElement getG() {
		return g;
	}
	public void setG(ColorElement g) {
		this.g = g;
	}
	public ColorElement getB() {
		return b;
	}
	public void setB(ColorElement b) {
		this.b = b;
	}
}
