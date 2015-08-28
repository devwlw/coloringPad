package tool;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import canvas.Canvas;

/*
 **网状工具(和chrome类似，不过只会在距当前最近的线条处做线) 
 */
public class WebTool extends AbstractTool{
	private static DrawTool tool = null;
	public static int spread = 30*100;
	//private double rand = 0;
	private ArrayList<Point> path = new ArrayList<Point>();
	private WebTool(Canvas canvas){
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new WebTool(canvas);
		}
		return tool;
	}
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		// 获取图片的Graphics对象
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setStroke(new BasicStroke(AbstractTool.Stroke,AbstractTool.LineStyle,BasicStroke.JOIN_ROUND));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, AbstractTool.brushOpactity));
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
		
		g2.drawLine(getPressX(), getPressY(), e.getX(), e.getY());
		path.add(new Point(e.getX(),e.getY()));
		for(int i = 0;i<path.size();i++){
			double dx = path.get(i).x - path.get(path.size()-1).x;
			double dy = path.get(i).y - path.get(path.size()-1).y;
			double d = dx*dx + dy*dy;
		//	System.out.println("rand"+rand);
			if(d<spread){
				g2.draw(new Line2D.Double(path.get(path.size()-1).x,path.get(path.size()-1).y,
										  path.get(i).x,path.get(i).y));
			}
		}
		setPressX(e.getX());
		setPressY(e.getY());
		getCanvas().repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		path.clear();
		super.mouseReleased(e);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		//rand = Math.random();
		super.mousePressed(e);
	}
	
}
