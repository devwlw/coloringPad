package tool;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import util.ImageCosur;

import canvas.Canvas;
import canvas.Service;

public class AbstractTool implements DrawTool{
	private Canvas canvas = null;// 画板
	public static int drawWidth = 0;//画板宽
	public static int drawHeight = 0;//画板高
	private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);//默认的鼠标指针
	private int pressX = -1;//鼠标按下的坐标
	private int pressY = -1;
	public static ImageCosur cursorIcon = new ImageCosur();//印章图标

	public static boolean isColorRandom = false;
	public static Color color = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));//颜色
	public static TexturePaint texture = null;//纹理
	public static float Stroke = 0.1f;//画笔大小
	public static float brushOpactity = 1.0f;//画刷透明度
	public static int LineStyle = BasicStroke.CAP_ROUND;//默认线条样式
	public static List<Point> crayonList = new ArrayList<Point>();
	public static LinearGradientPaint gradient = null;
	public static Color[] gradientColor = null;
	
	public static BufferedImage stamp;//印章图片
	public static float stampOpactity = 0.5f;
	public static int stampGap = 10;//印章作图时每个图像的间隔
	public static int stampAngle = 5;
	protected Color tempColor;

	public float getStroke() {
		return Stroke;
	}
	public void setStroke(float stroke) {
		Stroke = stroke;
	}
	public float getBrushOpactity() {
		return brushOpactity;
	}
	public void setBrushOpactity(float opactity) {
		this.brushOpactity = opactity;
	}
	/*public float getDiameter() {
		return diameter;
	}
	public void setDiameter(float diameter) {
		this.diameter = diameter;
	}*/

	//private float diameter;//直径
	public AbstractTool(Canvas canvas){
		this.canvas= canvas;
		AbstractTool.drawWidth = canvas.getWidth()-1;
		AbstractTool.drawHeight = canvas.getHeight()-1;
		canvas.add(cursorIcon);
	}
	public AbstractTool(Canvas canvas, String path) {
		this(canvas);
		// 创建工具鼠标图型
		this.defaultCursor = Service.createCursor(path);
	}
	public AbstractTool(Canvas canvas,BufferedImage image){
		this(canvas);
		this.defaultCursor = Toolkit.getDefaultToolkit().createCustomCursor(image,
				new Point(10,10), "mycursor");
	}
	public int getPressX() {
		return pressX;
	}
	public void setPressX(int pressX) {
		this.pressX = pressX;
	}
	public int getPressY() {
		return pressY;
	}
	public void setPressY(int pressY) {
		this.pressY = pressY;
	}

	public Canvas getCanvas() {
		return canvas;
	}
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
	public Cursor getDefaultCursor() {
		return defaultCursor;
	}
	public void setDefaultCursor(Cursor defaultCursor) {
		this.defaultCursor = defaultCursor;
		canvas.setCursor(defaultCursor);
	}
	protected ImageCosur getCursorIcon() {
		return cursorIcon;
	}
	protected void setCursorIcon(ImageCosur stampIcon) {
		this.cursorIcon = stampIcon;
	}
	public void mouseDragged(MouseEvent e) {
		Graphics g = getCanvas().getGraphics();
		createShape(e, g);
		//draw(g, getPressX(), getPressY(), e.getX(), e.getY());
		/*if(canvas.getTool() instanceof StampTool  && stampIconImage!=null){
			ImageIcon ii = new ImageIcon(stampIconImage);
			cursorIcon.setSize(stampIconImage.getWidth(), stampIconImage.getHeight());
			cursorIcon.setLocation(e.getX()-stampIconImage.getWidth()/2,e.getY()-stampIconImage.getHeight()/2);
			cursorIcon.setIcon(ii);
		}*/
		//Graphics2D g2 = (Graphics2D)getCanvas().getGraphics();
		//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SrcOver, AbstractTool.brushOpactity));//不透明度
		cursorIcon.setVisible(false);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(canvas.getTool() instanceof StampTool){
			if(stamp!=null){
				BufferedImage icon = new BufferedImage(stamp.getWidth(), stamp.getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D iconG2 = (Graphics2D)icon.getGraphics();
				iconG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,stampOpactity));
				iconG2.drawImage(stamp, 0, 0, icon.getWidth(), icon.getHeight(), null);
				ImageIcon ii = new ImageIcon(icon);
				cursorIcon.setSize(stamp.getWidth(), stamp.getHeight());
				cursorIcon.setLocation(e.getX()-stamp.getWidth()/2,e.getY()-stamp.getHeight()/2);
				cursorIcon.setIcon(ii);
				cursorIcon.setVisible(true);
			}
		}
		if(canvas.getTool() instanceof PencilTool || canvas.getTool() instanceof EraserTool){
			BufferedImage icon;
			if(Stroke<=1)
				icon = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
			else
				icon = new BufferedImage(Math.round(Stroke),Math.round(Stroke),BufferedImage.TYPE_INT_ARGB);
			Graphics2D iconG2 = (Graphics2D)icon.getGraphics();
			iconG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,brushOpactity));
			iconG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
			if(AbstractTool.color != null){
				iconG2.setColor(AbstractTool.color);
			}
			else if(AbstractTool.isColorRandom == true){
				iconG2.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			}
			else if(AbstractTool.texture != null){
				iconG2.setPaint(AbstractTool.texture);
			}
			else
				iconG2.setPaint(AbstractTool.gradient);
			iconG2.setStroke(new BasicStroke(Stroke,LineStyle,BasicStroke.JOIN_ROUND));
			if(canvas.getTool() instanceof EraserTool){
				iconG2.setStroke(new BasicStroke(Stroke,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				iconG2.setColor(Color.WHITE);
				iconG2.drawLine(icon.getWidth()/2,icon.getHeight()/2, icon.getWidth()/2,icon.getHeight()/2);
			}
				
			iconG2.drawLine(icon.getWidth()/2,icon.getHeight()/2, icon.getWidth()/2,icon.getHeight()/2);
			ImageIcon ii = new ImageIcon(icon);
			cursorIcon.setSize(icon.getWidth(), icon.getHeight());
			cursorIcon.setLocation(e.getX() - icon.getWidth()/2, e.getY() - icon.getHeight()/2);
			cursorIcon.setIcon(ii);
			cursorIcon.setVisible(true);
		}
		if(canvas.getTool() instanceof CrayonTool){
			BufferedImage icon;
			if(Stroke<=1)
				icon = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
			else
				icon = new BufferedImage(Math.round(Stroke),Math.round(Stroke),BufferedImage.TYPE_INT_ARGB);
			
			Graphics2D iconG2 = (Graphics2D)icon.getGraphics();
			iconG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,brushOpactity));
			iconG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
			if(AbstractTool.color != null){
				iconG2.setColor(AbstractTool.color);
			}
			else if(AbstractTool.isColorRandom == true){
				iconG2.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			}
			else if(AbstractTool.texture != null){
				iconG2.setPaint(AbstractTool.texture);
			}
			else
				iconG2.setPaint(AbstractTool.gradient);
			iconG2.setStroke(new BasicStroke(Stroke,LineStyle,BasicStroke.JOIN_ROUND));
			for(int i = 0; i<crayonList.size();i++){
				iconG2.fillRect(crayonList.get(i).x, crayonList.get(i).y, 1, 1);
			}
			ImageIcon ii = new ImageIcon(icon);
			cursorIcon.setSize(icon.getWidth(), icon.getHeight());
			cursorIcon.setLocation(e.getX() - icon.getWidth()/2, e.getY() - icon.getHeight()/2);
			cursorIcon.setIcon(ii);
			cursorIcon.setVisible(true);
		}
		if(canvas.getTool() instanceof CalligraphyTool){
			BufferedImage icon;
			
			if(Stroke<=1)
				icon = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
			else
				icon = new BufferedImage(Math.round(Stroke),Math.round(Stroke)/2,BufferedImage.TYPE_INT_ARGB);
			System.out.println("大小   "+icon.getWidth()+"   "+icon.getHeight());
			Graphics2D iconG2 = (Graphics2D)icon.getGraphics();
			iconG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,brushOpactity));
			iconG2.setStroke(new BasicStroke(2.0f));
			iconG2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
			if(AbstractTool.color != null){
				iconG2.setColor(AbstractTool.color);
				System.out.println("颜色");
			}
			else if(AbstractTool.isColorRandom == true){
				iconG2.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
			}
			else if(AbstractTool.texture != null){
				iconG2.setPaint(AbstractTool.texture);
			}
			else
				iconG2.setPaint(AbstractTool.gradient);
		//	iconG2.drawLine(e.getX() - (int)AbstractTool.Stroke/2, e.getY() + (int)AbstractTool.Stroke/4,
			//		e.getX()+(int)AbstractTool.Stroke/2, e.getY() - (int)AbstractTool.Stroke/4);
			iconG2.drawLine(0, icon.getHeight(), icon.getWidth(), 0);
			ImageIcon ii = new ImageIcon(icon);
			cursorIcon.setSize(icon.getWidth(), icon.getHeight());
			cursorIcon.setLocation(e.getX() - icon.getWidth()/2, e.getY() - icon.getHeight()/2);
			cursorIcon.setIcon(ii);
			cursorIcon.setVisible(true);
		}
		canvas.add(cursorIcon);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Graphics g = getCanvas().getImg().getGraphics();
		createShape(e, g);
		// 把pressX与pressY设置为初始值
		setPressX(-1);
		setPressY(-1);
		// 重绘
		getCanvas().repaint();
		
	}
	private void createShape(MouseEvent e,Graphics g){
		g.drawImage(getCanvas().getImg(), 0, 0,
				AbstractTool.drawWidth, AbstractTool.drawHeight, null);
		// 设置颜色
		g.setColor(AbstractTool.color);
		//getCanvas().getImg().setIsSaved(false);
		// 画图形
		draw(g, getPressX(), getPressY(), e.getX(), e.getY());
		getCanvas().repaint();
	}
	public void draw(Graphics g, int x1, int y1, int x2, int y2) {
		//getCanvas().repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		setPressX(e.getX());
		setPressY(e.getY());	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
