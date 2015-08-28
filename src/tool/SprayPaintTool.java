package tool;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import canvas.Canvas;

/*
 * 喷漆工具(随着按下的时间颜色逐渐加深)
 */
public class SprayPaintTool extends AbstractTool{
	private static DrawTool tool = null;
	private SprayPaintTool(Canvas canvas){
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new SprayPaintTool(canvas);
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
	}
}
