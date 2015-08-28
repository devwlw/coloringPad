package tool;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import canvas.Canvas;

public class EraserTool extends AbstractTool{
	private static DrawTool tool = null;
	private EraserTool(Canvas canvas) {
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new EraserTool(canvas);
		}
		return tool;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mouseDragged(e);
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//¿¹¾â³ÝÌáÊ¾·û
		g2.setStroke(new BasicStroke(AbstractTool.Stroke,AbstractTool.LineStyle,BasicStroke.JOIN_ROUND));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, AbstractTool.brushOpactity));
		g2.setColor(Color.WHITE);
		g2.drawLine(getPressX(), getPressY(), e.getX(), e.getY());
		setPressX(e.getX());
		setPressY(e.getY());
		getCanvas().repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//¿¹¾â³ÝÌáÊ¾·û
		g2.setStroke(new BasicStroke(AbstractTool.Stroke,AbstractTool.LineStyle,BasicStroke.JOIN_ROUND));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, AbstractTool.brushOpactity));
		g2.setColor(Color.WHITE);
		g2.drawLine(e.getX(), e.getY(), e.getX(), e.getY());
		getCanvas().repaint();
	}
	
}
