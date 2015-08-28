package tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import canvas.Canvas;

/*
 * 茸毛工具(若下次的鼠标轨迹和这次的不同，将作出很多直线)
 */
public class FurTool extends AbstractTool{
	private static DrawTool tool = null;
	private ArrayList<Point> path = new ArrayList<Point>();
	private int deameter = 4;
	private int spread = 500;
	private FurTool(Canvas canvas){
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new FurTool(canvas);
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
		g2.setStroke(new BasicStroke(AbstractTool.Stroke));
		if(AbstractTool.color != null){//如果颜色不为空
			g2.setColor(AbstractTool.color);
		}
		else if(AbstractTool.isColorRandom == true){//若是随机颜色
			tempColor = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
			g2.setColor(tempColor);
		}
		else if(AbstractTool.texture != null){//若纹理不为空
			g2.setPaint(AbstractTool.texture);
		}
		else
			g2.setPaint(AbstractTool.gradient);
		g2.drawLine(getPressX(), getPressY(), e.getX(), e.getY());
		path.add(new Point(e.getX(),e.getY()));
		for(int i = 0; i<path.size();i++){
			int dx = path.get(i).x - path.get(path.size()-1).x;
			int dy = path.get(i).y - path.get(path.size()-1).y;
			int d = dx * dx + dy*dy;
			if(d<spread){
				g2.drawLine(e.getX() + (dx * deameter),e.getY()+(dy * deameter),
						e.getX() - (dx * deameter), e.getY() - (dy * deameter));
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
	
}
