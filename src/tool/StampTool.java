package tool;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import util.ImageCosur;
import util.ImageListFactory;

import canvas.Canvas;

public class StampTool extends AbstractTool{
	private static DrawTool tool = null;
	private Graphics g;
	private Graphics2D g2;
	public static BufferedImage image;
	//private int first,last;//图片缩放的分子和分母
	private int count = 1;
	private ImageIcon stampIcon;
	
	private int angle = 1;//图片旋转角度
	private boolean max = true;//从小到达还是从大到小
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	private int multiple = 7;//图片缩小的倍数
	private StampTool(Canvas canvas) {
		super(canvas);
		// TODO Auto-generated constructor stub
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new StampTool(canvas);
		}

		return tool;
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);
		g = getCanvas().getImg().getGraphics();
		g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, AbstractTool.stampOpactity));//根据Stamp中的值，来设置透明值
		if(AbstractTool.stamp !=null){//如果印章图片不为空
		if((count++ % stampGap) ==0){//图片间隔，每隔stampGap次，图片复位
			image = AbstractTool.stamp;//设置图片为印章图片
			//setDefaultCursor(Toolkit.getDefaultToolkit().createCustomCursor(image,
				//	new Point(image.getWidth()/2,image.getHeight()/2), "mycursor"));
			AffineTransform old = g2.getTransform();//保存2D仿射变换
			g2.rotate(Math.PI/angle++,e.getX(),e.getY());//没两张图片相隔Math.PI/angle++幅度
			if(max){//如果图片为最大，则让它缩小一定倍数
				image = ImageListFactory.getThumbnail(image, (multiple)*image.getWidth()/8, (multiple--)*image.getHeight()/8);
				if(0 == multiple)
					max = false;
			}
			else{//当图片为最小时，放大一定倍数
				image = ImageListFactory.getThumbnail(image, (++multiple)*image.getWidth()/8,(multiple)*image.getHeight()/8);
				if(7 == multiple)
					max = true;
			}
			if(stampAngle==angle) {
				angle = 1;}//复位旋转次数
			g2.drawImage(image,e.getX()-image.getWidth()/2,e.getY()-image.getHeight()/2,image.getWidth(),image.getHeight(),null);	
			g2.setTransform(old);//设置久的2D仿射变换，防止其影响下一步的作图
			getCanvas().repaint();
		}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		count = 1;//初始化
		angle = 1;
		super.mouseReleased(e);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		if(AbstractTool.stamp != null){
			g = getCanvas().getImg().getGraphics();
			g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, AbstractTool.stampOpactity));
			//if(AbstractTool.stamp !=null){
			//setFirstLast();
			image = AbstractTool.stamp;
			//image = ImageListFactory.getThumbnail(AbstractTool.stamp,first*AbstractTool.stamp.getWidth()/last,first*AbstractTool.stamp.getHeight()/last);
			g2.drawImage(image,e.getX()-image.getWidth()/2,e.getY()-image.getHeight()/2,image.getWidth(),image.getHeight(),null);
			getCanvas().repaint();
		}
	}
}
