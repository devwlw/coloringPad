package tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.concurrent.TimeUnit;

import canvas.Canvas;

public class SpirographTooL extends AbstractTool implements Runnable{
	private static DrawTool tool = null;
	//螺旋的  固定半径，移动半径，移动分量,旋转弧度
	public static int fix = 99;
	public static int radius = 3;
	public static int offset = 100;
	public static int radians = 10;
	//public static int speed = 10;
	//public static int distance = 400;
	int x = 0;
	int y = 0;
	int prevx = 0;
	int prevy = 0;
	double s = .004 * Math.sqrt(radius);	// controls the step size; low values of r (e.g. 2) make jagged pattern, so s will be small for low value of r; the effect is not directly proportinal, but sqrt (arbitrary)
	double t;
	double rSum, rDiff, exprResult;
	
	Thread thread;
	MouseEvent e;
	boolean isDraw = true;
	private SpirographTooL(Canvas canvas){
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new SpirographTooL(canvas);
		}
		return tool;
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		isDraw = false;
		reSet();
		super.mouseReleased(e);
	}
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		// 获取图片的Graphics对象
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setColor(AbstractTool.color);
		g2.setStroke(new BasicStroke(AbstractTool.Stroke));
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		this.e = e;
		//isDraw = true;
		// thread = new Thread(this);
		// thread.start();
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setColor(AbstractTool.color);
		g2.setStroke(new BasicStroke(0.1f));
	/*	Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setColor(AbstractTool.color);
		g2.setStroke(new BasicStroke(0.1f));
		int x = 0;
		int y = 0;
		int prevx = 0;
		int prevy = 0;

		double s = .004 * Math.sqrt(radius);	// controls the step size; low values of r (e.g. 2) make jagged pattern, so s will be small for low value of r; the effect is not directly proportinal, but sqrt (arbitrary)
		double t;
		double rSum, rDiff, exprResult;
		rSum  = fix + radius;
		rDiff = fix - radius;
		int temp = 0;
		while(temp++ <=offset){
		for(t=0; t<=radians; t+=s){
			prevx = x;
			prevy = y;		
			exprResult = (rSum * t) / (double) radius;
				x = (int)((rSum)*Math.cos((double)t) - (temp)*Math.cos(exprResult) );
				y = (int)((rSum)*Math.sin((double)t) - (temp)*Math.sin(exprResult) );
				g2.drawLine(e.getX()+prevx, e.getY()+prevy, e.getX()+x, e.getY()+y);*/
				//getCanvas().repaint();
			//	exprResult = (rDiff * t) / (double) radius;
			///	x = (int)((rDiff)*Math.cos((double)t) + (offset)*Math.cos(exprResult));
			//	y = (int)((rDiff)*Math.sin((double)t) - (offset)*Math.sin(exprResult));
			//g.drawLine(prevx, prevy, x, y); // line
		//}//for
	//	System.out.println("画下了");
		
	//	}
		
	}
	@Override
	public void run() {	
		int temp = 0;
		while (isDraw ){
			try {
				while(temp++ <=offset){
				for(t=0; t<=radians; t+=s){
					prevx = x;
					prevy = y;	
					exprResult = (rSum * t) / (double) radius;
					x = (int)((rSum)*Math.cos((double)t) - (temp)*Math.cos(exprResult) );
					y = (int)((rSum)*Math.sin((double)t) - (temp)*Math.sin(exprResult) );
					draw();
					if(!isDraw) break;	
				}
				Thread.sleep(5);
			}
			} catch (InterruptedException e){
			}
		}
	}
	public void draw(){
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setColor(AbstractTool.color);
		g2.setStroke(new BasicStroke(0.1f));
		g2.drawLine(e.getX()+prevx, e.getY()+prevy, e.getX()+x, e.getY()+y);
		getCanvas().repaint();
	}
	public void reSet(){
		x = 0;
		y = 0;
		prevx = 0;
		prevy = 0;
		s = .004 * Math.sqrt(radius);	// controls the step size; low values of r (e.g. 2) make jagged pattern, so s will be small for low value of r; the effect is not directly proportinal, but sqrt (arbitrary)
		rSum  = fix + radius;
		rDiff = fix - radius;
		int temp = 0;
		//prevx = x;
	//	prevy = y;		
		//exprResult = (rSum * t) / (double) radius;
		//x = (int)((rSum)*Math.cos((double)t) - (temp)*Math.cos(exprResult) );
	//	y = (int)((rSum)*Math.sin((double)t) - (temp)*Math.sin(exprResult) );
	}

}
