package tool;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import util.FontFactory;

import canvas.Canvas;

public class PencilTool extends AbstractTool{
	//private  BufferedImage img = null;
	int i = 0;

	private static DrawTool tool = null;
	//Path2D path = new Path2D.Double();
	private PencilTool(Canvas canvas) {
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new PencilTool(canvas);
		}
		return tool;
	}
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		// 获取图片的Graphics对象
		
		Graphics g = getCanvas().getImg().getGraphics();//获得图片的作图对象
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setStroke(new BasicStroke(AbstractTool.Stroke,AbstractTool.LineStyle,BasicStroke.JOIN_ROUND));//根据菜单的值来设置画笔大小和风格
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, AbstractTool.brushOpactity));//不透明度
		
		if(AbstractTool.color != null){//如果颜色不为空
			g2.setColor(AbstractTool.color);
		}
		else if(AbstractTool.isColorRandom == true){//若是随机颜色
			if(i++ %30 ==0){
				tempColor = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
			}
			g2.setColor(tempColor);
		}
		else if(AbstractTool.texture != null){//若纹理不为空
			g2.setPaint(AbstractTool.texture);
		}
		else
			g2.setPaint(AbstractTool.gradient);
		//g2.drawLine(e.getX(), e.getY(),e.getX(), e.getY());
		g2.drawLine(getPressX(), getPressY(), e.getX(), e.getY());
		//画线，坐标为上次的鼠标坐标和这次的
		setPressX(e.getX());//设置上次画线的坐标为这次的
		setPressY(e.getY());
		getCanvas().repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		if(AbstractTool.color != null){
			g2.setColor(AbstractTool.color);
		}
		else if(AbstractTool.isColorRandom == true){
			g2.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		}
		else if(AbstractTool.texture != null){
			g2.setPaint(AbstractTool.texture);
		}
		else
			g2.setPaint(AbstractTool.gradient);
		g2.setStroke(new BasicStroke(AbstractTool.Stroke,AbstractTool.LineStyle,BasicStroke.JOIN_ROUND));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER , AbstractTool.brushOpactity));
		g2.drawLine(e.getX(),e.getY(), e.getX(), e.getY());	
		//按下的那下，只需要画条当前点到当前点的线即可
		getCanvas().repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		//path.reset();
		super.mouseReleased(e);
	}

}
