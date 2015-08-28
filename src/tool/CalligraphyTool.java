package tool;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import canvas.Canvas;
/*
 * 笔迹工具
 */
public class CalligraphyTool extends AbstractTool{
	private static DrawTool tool = null;
	Path2D path = new Path2D.Double();
	private CalligraphyTool(Canvas canvas){
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if(tool==null){
			tool = new CalligraphyTool(canvas);
		}
		return tool;
	}
	@Override
	public void mouseDragged(MouseEvent e) {

		// 获取图片的Graphics对象
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
	            RenderingHints.VALUE_STROKE_NORMALIZE );//抗锯齿提示符
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
		//AffineTransform old = g2.getTransform();
		g2.translate((-1*AbstractTool.Stroke)/2, 0);
		path.moveTo(getPressX(), getPressY());
		path.lineTo(getPressX()+AbstractTool.Stroke,  getPressY() - AbstractTool.Stroke/2);
		path.lineTo(e.getX()+AbstractTool.Stroke, e.getY() - AbstractTool.Stroke/2);
		path.lineTo(e.getX(), e.getY());
		path.closePath();
		g2.fill(path);
		//g2.drawLine(getPressX(), getPressY(), e.getX(), e.getY());
		setPressX(e.getX());
		setPressY(e.getY());
		//g2.setTransform(old);
		getCanvas().repaint();
		super.mouseDragged(e);
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		path.reset();
		super.mouseReleased(e);
	}
}
