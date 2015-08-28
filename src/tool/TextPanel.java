package tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import twaver.base.A.E.d;

import canvas.Canvas;

public class TextPanel extends JPanel{
	private Color rectColor = Color.BLACK;
	private int pressX,pressY,pressWidth,pressHeight,pressLocX,pressLocY;
	private Canvas canvas;
	public Canvas getCanvas() {
		return canvas;
	}


	private boolean resize = true;//是否可以改变大小
	private boolean right = true;//是否是右边
	private boolean drawBorder = true;
	private TextArea text;
	private String drawText = "";
	//private JPopupMenu popup = new JPopupMenu();
	public TextPanel(int x,int y,Canvas canvas){
		this.canvas = canvas;
		setSize(500,300);
		setLocation(x, y);
		setOpaque(false);
		setLayout(null);
		setMinimumSize(new Dimension(20, 20));
		mouse m = new mouse();
		addMouseListener(m);
		addMouseMotionListener(m);
		text = new TextArea(this,canvas);
		text.setSize(getWidth()-60, getHeight()-60);
		text.setLocation(30, 30);
		text.setVisible(false);
		add(text);
		/*JMenuItem jm = new JMenuItem("移除");
		popup.add(jm);
		add(popup);*/
		//text.setSize(width, height)
	}


	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		DrawBorder(g2);
		
	}


	private class mouse extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {
			text.setVisible(true);
			super.mouseClicked(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			text.setVisible(true);
			//if(text.getText() != null) text.setVisible(true);
			if(resize){
				if(right)
					setSize(pressWidth+e.getXOnScreen()-pressX, pressHeight);
				if(!right)
					setSize(pressWidth,pressHeight + e.getYOnScreen() - pressY);
			}
			if(!resize)
				setLocation(pressLocX + e.getXOnScreen() - pressX, pressLocY + e.getYOnScreen() - pressY);
			text.setSize(getWidth()-60, getHeight()-60);
			text.setLocation(30, 30);
			super.mouseDragged(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseMoved(e);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			if(!text.isVisible())//当文本框不可见时
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			setToolTipText("按下拖动，双击编辑");
			super.mouseEntered(arg0);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(!text.isVisible())//当文本框不可见时
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			text.setVisible(false);
			drawBorder = true;
			pressX = e.getXOnScreen();
			pressY = e.getYOnScreen();
			pressWidth = getWidth();
			pressHeight = getHeight();
			pressLocX = getLocation().x;
			pressLocY = getLocation().y;
			if((e.getX()>=getWidth()-20 && e.getX()<=getWidth()) && //右边
					e.getY()>=0 && e.getY()<=20	){
				right = true;
				resize = true;
			}
			else if((e.getX()>=0 && e.getX()<=20) &&//左边
					(e.getY()>=getHeight()-20 &&e.getY()<=getHeight())){
				right = false;
				resize = true;
			}
			else
				resize = false;
			super.mousePressed(e);
		}
		
	}
	private void DrawBorder(Graphics2D g2){
		if(drawBorder){
			g2.setColor(new Color(60,60,60));
			g2.fillRect(getWidth()-20, 0, 20, 20);
			g2.fillRect(0, getHeight()-20, 20, 20);
			g2.setColor(new Color(123,123,255));
			g2.setStroke(new BasicStroke(1.6f));
			g2.drawLine(10, 10, getWidth()-10, 10);
			g2.drawLine(10, 10, 10, getHeight()-10);
			g2.drawLine(10, getHeight()-10, getWidth()-10, getHeight()-10);
			g2.drawLine(getWidth()-10, 10, getWidth()-10, getHeight()-10);
		}
	}
	public String getDrawText() {
		return drawText;
	}


	public void setDrawText(String drawText) {
		this.drawText = drawText;
		repaint();
	}
	public boolean isDrawBorder() {
		return drawBorder;
	}


	public void setDrawBorder(boolean drawBorder) {
		this.drawBorder = drawBorder;
		if(!drawBorder)
			setVisible(false);
		repaint();
	}
	public TextArea getText() {
		return text;
	}


	public void setText(TextArea text) {
		this.text = text;
	}
	public boolean isResize() {
		return resize;
	}


	public void setResize(boolean resize) {
		this.resize = resize;
	}
}
