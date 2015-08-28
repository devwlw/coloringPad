package tool;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import canvas.Canvas;
/*
 * 纸带工具
 */
public class StreamerTool extends AbstractTool{
	private static DrawTool tool = null;
	private double vectorx,vectory;
	private Object[] finger = {};
	float minThickness = 0.25f;
	float thicknessFactor = 0.12f;
	float smoothingFactor = 0.42f;//0-1
	float thicknessSmoothingFactor = 0.42f;
	float tipTaperFactor = 0.2f;
	private StreamerTool(Canvas canvas) {
		super(canvas);
		
	}
	public static DrawTool getInstance(Canvas canvas) {
		if(tool == null){
			tool = new StreamerTool(canvas);
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
		vectorx = e.getX() - getPressX();
		vectory = e.getY() - getPressY();
		if(!(Math.abs(vectorx) > 5 || Math.abs(vectory) > 5)) return;
		//g2.drawArc(e.getX(), arg2, arg3, arg4, arg5)
	}
}
