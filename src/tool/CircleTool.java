package tool;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import canvas.Canvas;

public class CircleTool extends AbstractTool{
	private Canvas canvas;
	private int max = 200,diameter = 1;
	private static DrawTool tool = null;
	private boolean min = true;
	public CircleTool(Canvas canvas) {
		super(canvas);
		this.canvas = canvas;
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new CircleTool(canvas);
		}
		return tool;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	           RenderingHints.VALUE_ANTIALIAS_ON);//¿¹¾â³ÝÌáÊ¾·û
		g2.setStroke(new BasicStroke(AbstractTool.Stroke,AbstractTool.LineStyle,BasicStroke.JOIN_ROUND));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER , AbstractTool.brushOpactity));
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
		g2.drawOval(e.getX()-diameter/2, e.getY()-diameter/2,diameter, diameter);
		if(min == false){
			diameter -= 1;
			if(diameter <=2 ){
				min = true;
			}
		}
		else{
			diameter += 1;
			if(diameter >=max ){
				min = false;
			}
		}
		getCanvas().repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);
		diameter = 1;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//¿¹¾â³ÝÌáÊ¾·û
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
		g2.drawOval(e.getX()-diameter/2, e.getY()-diameter/2,diameter, diameter);	
		getCanvas().repaint();
	}
	
}
