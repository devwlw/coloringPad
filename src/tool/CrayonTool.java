package tool;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 蜡笔工具
 */
import canvas.Canvas;

public class CrayonTool extends AbstractTool{
	private static DrawTool tool = null;
	int i = 0;
	List<Point> loc = new ArrayList<Point>();
	public static boolean change = true;
	private CrayonTool(Canvas canvas) {
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if(tool==null){
			tool = new CrayonTool(canvas);
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
		for(int i = 0; i<loc.size();i++){
			g2.fillRect((int)(e.getX()-AbstractTool.Stroke/2+loc.get(i).x), (int)(e.getY() - AbstractTool.Stroke/2+loc.get(i).y), 1, 1);
		}
		getCanvas().repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	@Override
	public void mousePressed(MouseEvent e) {
		Graphics g = getCanvas().getImg().getGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
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
		
		for(int i = 0; i<loc.size();i++){
			g2.fillRect((int)(e.getX()-AbstractTool.Stroke/2+loc.get(i).x), (int)(e.getY() - AbstractTool.Stroke/2+loc.get(i).y), 1, 1);
		}
		getCanvas().repaint();
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(change){
			loc.clear();
			AbstractTool.crayonList.clear();
			AbstractTool.crayonList = loc = getDrawPoints();
			change = false;
		}
		super.mouseMoved(e);
	}
	public List<Point> getDrawPoints(){
		List<Point> l = new ArrayList<Point>();
			int r = ((int)AbstractTool.Stroke)/2 ==0 ? 1 : ((int)AbstractTool.Stroke)/2;//半径或者矩形边长一半
			if(AbstractTool.LineStyle == BasicStroke.CAP_ROUND){//在圆形范围内做点
				while(l.size() <= Math.PI*(r*r)/2){
					int width = (int) (Math.random()*r*2);//宽度取小于直径
					int temp = width;
					if(width > r) temp = width - r;//高度应为宽度对应弦			
					int height = (int) (Math.random()*(2* Math.sqrt(2*r*temp - temp*temp)));
					if(width > r) height = (int)(Math.random()*(2*Math.sqrt(r*r-temp*temp)));
					int blank;
					blank = (int) ((2*r - 2* Math.sqrt(2*r*temp - temp*temp))/2);
					if(width > r){
						blank = (int) (r- Math.sqrt(r*r - temp*temp));
					}
					l.add(new Point(width,blank+height));
					//g2.fillRect((int)(e.getX()-AbstractTool.Stroke/2+width), (int)(e.getY() - AbstractTool.Stroke/2+blank+height), 1, 1);

				}
			}
			else{
				for(int j = 0; j< 4*r*r;j++){//取点数为面积的一半
					int width = (int) (Math.random()*(2*r));
					int height = (int) (Math.random()*(2*r));
					l.add(new Point(width,height));
				}
			}
			return l;
	}
}
