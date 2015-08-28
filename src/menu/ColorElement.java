package menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tool.AbstractTool;

public class ColorElement extends JLabel{
	private int colorX = 0;//鼠标x
	private int value;//颜色值 
	private String name= "";//显示的字符串
	private GradientPaint gPaint = null;
	private Rectangle rec;

	public ColorElement(int width,int height){
		setSize(width, height);
		setOpaque(false);
		setLayout(null);
	//	setLocation(50, l+=60);
		rec = new Rectangle(6, getHeight());
		setValue((int)(Math.random()*255));
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		if(gPaint!=null){
			g2.setPaint(gPaint);
			g2.fillRect(0,0,getWidth(),getHeight());
			g2.setPaint(null);
			g2.setColor(Color.WHITE);
				if(getValue() >200)
					g2.setColor(new Color(42,42,42));
			g2.drawRect((int)(colorX - rec.getWidth()/2), 0, (int)rec.getWidth(), (int)rec.getHeight()-1);
			if((int)(colorX - rec.getWidth()/2)>getWidth())//超过最大边界
				g2.drawRect(getWidth()-(int)rec.getWidth()-1, 0, (int)rec.getWidth(), (int)rec.getHeight()-1);
			if((int)(colorX - rec.getWidth()/2) < 0)//最小边界
				g2.drawRect(0, 0, (int)rec.getWidth(), (int)rec.getHeight()-1);
			
			
			Font f = new Font("微软雅黑",Font.PLAIN,24);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			g2.setFont(f);
			g2.drawString(name, getWidth()/2-((name.length()*f.getSize())/2), 26);
		}
		
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		this.colorX =getWidth() - (getWidth()*value)/255;
		//System.out.println("value  ---- x   "+value+"   "+colorX);
		repaint();
	}
	public void setColorX(int x) {
		this.colorX = x;
		this.value = 255 - (255*x)/getWidth();
		if(this.value <0)
			this.value = 0;
		if(this.value >255)
			this.value = 255;
		repaint();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		repaint();
	}
	public GradientPaint getgPaint() {
		return gPaint;
	}
	public void setgPaint(GradientPaint gPaint) {
		this.gPaint = gPaint;
		repaint();
	}
}
