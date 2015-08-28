package tool;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import canvas.Canvas;

public class ArrowTool extends AbstractTool{
	private static DrawTool tool = null;
	private Path2D.Double shaftPath = new Path2D.Double();
	private Path2D.Double headPath = new Path2D.Double();
	private Line2D.Double line  = new Line2D.Double();
	ArrayList<Point2D.Double> points = new ArrayList<Point2D.Double>();
	private ArrowTool(Canvas canvas){
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new ArrowTool(canvas);
		}
		return tool;
	}
	@Override
	public void draw(Graphics g, int x1, int y1, int x2, int y2) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setColor(AbstractTool.color);
		g2.setStroke(new BasicStroke(20.0f));
		g2.drawLine(x1, y1, x2, y2);
		double angle = Math.PI / 8;
		double lineAngle = Math.atan2(y2 -y1, x2 - x1);
		double x = x2 + Math.cos(lineAngle) * 20.0/2;
		double y = y2 + Math.sin(lineAngle) * 20.0/2;
		double angle1 = lineAngle + Math.PI + angle;
		double topx = x + Math.cos(angle1) * 20.0;
		double topy = y + Math.sin(angle1) * 20.0;
		double angle2 = lineAngle + Math.PI - angle;
		double botx = x + Math.cos(angle2) * 20.0;
		double boty = y + Math.sin(angle2) * 20.0;
		drawArrowHead(g2, topx, topy, x1, y1, botx, boty);
		//getCanvas().repaint();
	}
	/*public void mouseDragged(MouseEvent e) {
		//super.mouseDragged(e);
		// 获取图片的Graphics对象
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setColor(AbstractTool.color);
		g2.setStroke(new BasicStroke(20.0f));
		points.add(new Point2D.Double(e.getX(), e.getY()));
		Point2D.Double batch0 = points.get(0);
		Point2D.Double batch1 = points.get(points.size()-1);
		double x1 = batch0.x;
		double y1 = batch0.y;
		double x2 = batch1.x;
		double y2 = batch1.y;
		int mode = 1;
		double angle = Math.PI / 8;
		//shaftPath.moveTo(x1, y1);
		//shaftPath.lineTo(x1 + (x2 - x1), y1 + (y2 - y1));
		//g2.fill(shaftPath);
		line.setLine(x1, y1,x1 + (x2 - x1), y1 + (y2 - y1));
		g2.draw(line);
		double lineAngle = Math.atan2(y2 -y1, x2 - x1);
		if(mode ==1 || mode ==3){
			double x = x2 + Math.cos(lineAngle) * 20.0/2;
			double y = y2 + Math.sin(lineAngle) * 20.0/2;
			double angle1 = lineAngle + Math.PI + angle;
			double topx = x + Math.cos(angle1) * 20.0;
			double topy = y + Math.sin(angle1) * 20.0;
			double angle2 = lineAngle + Math.PI - angle;
			double botx = x + Math.cos(angle2) * 20.0;
			double boty = y + Math.sin(angle2) * 20.0;
			drawArrowHead(g2, topx, topy, x1, y1, botx, boty);
		}
		getCanvas().repaint();
		//setPressX(e.getX());
		//setPressY(e.getY());
	}**/
	
	@Override
	public void mouseReleased(MouseEvent e) {
		points.clear();
		shaftPath.reset();
		headPath.reset();
		super.mouseReleased(e);
	}
	public void drawArrowHead(Graphics2D g2,double x0,double y0,double x1,double y1,double x2,double y2){//箭头头部
		headPath.moveTo(x0, y0);
		headPath.lineTo(x1, y1);
		headPath.lineTo(x2, y2);
		double cpx = (x0 + x1 + x2) / 3;
		double cpy = (y0 + y1 + y2) / 3;
		headPath.quadTo(cpx, cpy, x0, y0);
	//	headPath.curveTo(x0, y0, x1, y1, x2, y2);
		headPath.closePath();
		g2.fill(headPath);
		headPath.reset();
	}
}
