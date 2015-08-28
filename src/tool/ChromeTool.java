package tool;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import canvas.Canvas;

/*
 * 铬金工具(在弯曲处做线条)
 */
public class ChromeTool extends AbstractTool{
	private static DrawTool tool = null;
	private ArrayList<Point> path = new ArrayList<Point>();
	public static int spread = 30*100;
	private ChromeTool(Canvas canvas){
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new ChromeTool(canvas);
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
		for(int i = Math.max(0, path.size()-100);i<path.size();i++){//当移动了超过100个像素点时
			double dx = path.get(i).x - path.get(path.size()-1).x;
			double dy = path.get(i).y - path.get(path.size()-1).y;//当前点到最后一个点的差值
			double d = dx*dx + dy*dy;//向量的长度
			if(d<spread){//当向量的长度小于设置的值
				g2.draw(new Line2D.Double(path.get(path.size()-1).x + (dx * 0.2),path.get(path.size()-1).y + (dy * 0.2),
											path.get(i).x - (dx*0.2),path.get(i).y - (dy * 0.2)));
			}//作一条最后一个点到当前点的线，dx * 0.2  或  dy * 0.2 为 这个线到主线的空白。。如果 主线是向下弯曲的，那么dx*0.2为正，dy * 0.2 为负
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
	
}
