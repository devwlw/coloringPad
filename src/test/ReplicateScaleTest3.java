package test;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;
import java.awt.*;
import java.awt.image.*;

public class ReplicateScaleTest3 extends Applet {
	private Image im;
	private Image big, small;
	private int imw, imh;
	URL url;
	public void init() {
		MediaTracker mt = new MediaTracker(this);
		//url = getClass().getResource("1.jpg");
		try {
			url = new URL("file:c:1.jpg");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(url);
		try {
			im = createImage((ImageProducer)url.getContent());
			mt.addImage(im, 0);
			mt.waitForID(0);
			}
		catch(Exception e) { e.printStackTrace(); } 
		imw = im.getWidth(this);
		imh = im.getHeight(this);

		ImageFilter bigFilter = 
				new ReplicateScaleFilter(imw*2, imh*2);

		ImageFilter smallFilter = 
				new ReplicateScaleFilter(imw/2, imh/2);
		FilteredImageSource bigSource = 
				new FilteredImageSource(im.getSource(), bigFilter);

		FilteredImageSource smallSource = 
				new FilteredImageSource(im.getSource(), smallFilter);

		big = createImage(bigSource); 
		small = createImage(smallSource); 
		}
	public void paint(Graphics g) {
		g.drawImage(im,0,0,this);
		g.drawImage(small,imw,0,this);
		g.drawImage(big,imw,imh/2,this);
		}
}

