package test;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;

public class CropTest3 extends Applet
{
	private Image im;
	private Image cropped;
	URL url;
	public void init()
	{
		MediaTracker mt = new MediaTracker(this);//建立一个媒体跟踪器对象
		
		try {
			url = new URL("file:c:/1.jpg");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//

		//实现完全加载图像
		try 
		{
			im = createImage((ImageProducer)url.getContent());//
			mt.addImage(im, 0);
			mt.waitForID(0);
			}
		catch(Exception e)
		{
			e.printStackTrace();
			}
		//----------------------
		ImageFilter filter = new CropImageFilter(110,5,100,100);//获取图片指定位置和大小

		FilteredImageSource fis = new FilteredImageSource(im.getSource(), filter);
		cropped = createImage(fis); 
		}
	public void paint(Graphics g)
	{
		g.drawImage(im,0,0,this);
		g.drawImage(cropped,im.getWidth(this)+20,0,this);
		}
}

