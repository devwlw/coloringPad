package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import mainface.MainFace;


import util.Info;

/*
 * 倒数第二个面板
 */
public class FunctionPanel extends JPanel implements Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private TexturePaint texture;
	private File file;
	private Rectangle rect;
	private FunctionButton text,brush,save,eraser,set,color,textu,stamp,gradient;
	private FunctionButton back,forward;//后退和前进按钮
	private MainFace frame;
	public FunctionPanel(String path,MainFace frame) throws IOException{
		this.frame = frame;
		file = new File(path);
		image = ImageIO.read(file);
		setSize(DEFAULTWIDTH-1, image.getHeight()+2);
		rect = new Rectangle(0,0,image.getWidth(),image.getHeight());
		texture = new TexturePaint(image,rect);
		text = new TextButton("icon/text.png",frame);
		brush = new BrushButton("icon/paint.png",frame);
		eraser = new EraserButton("icon/eraser.png",frame);
		save = new SaveButton("icon/save.png",frame);
		set = new SetButton("icon/menu.png",frame);
		color = new ColorButton("icon/color.png",frame);
		textu = new TextureButton("icon/texture.png", frame);
		stamp = new StampButton("icon/stamp.png",frame);
		gradient = new GradientButton("icon/gradient.png", frame);
		
		back = new BackButton("icon/back.png",frame);
		forward = new ForwardButton("icon/forward.png", frame);
		save.setLocation(45, 4);
		back.setLocation(85,4);
		forward.setLocation(125, 4);
		set.setLocation(5, 4);
		eraser.setLocation(getWidth()-45, 4);
		brush.setLocation(getWidth()-85, 4);
		stamp.setLocation(getWidth()-165, 4);
		text.setLocation(getWidth()-125, 4);
		color.setLocation(getWidth()-325, 4);
		textu.setLocation(getWidth()-285, 4);	
		gradient.setLocation(getWidth()-365, 4);
		add(text);
		add(brush);
		add(eraser);
		add(save);
		add(back);
		add(forward);
		add(set);
		add(color);
		add(textu);
		add(stamp);
		add(gradient);
		setLayout(null);
		setOpaque(false);
		class WC extends ComponentAdapter{

			@Override
			public void componentResized(ComponentEvent arg0) {
				eraser.setLocation(getWidth()-45, 4);
				brush.setLocation(getWidth()-85, 4);
				text.setLocation(getWidth()-125, 4);
				color.setLocation(getWidth()-325, 4);
				textu.setLocation(getWidth()-285, 4);
				stamp.setLocation(getWidth()-165, 4);
				gradient.setLocation(getWidth()-365, 4);
			}
		}
		addComponentListener(new WC());
	}
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(texture);
		g2.fillRect(0,0,getWidth(),getHeight());
		g2.setColor(Color.WHITE);
		g2.drawLine(textu.getLocation().x+textu.getWidth()+5, 5, 
				textu.getLocation().x+textu.getWidth()+5, getHeight()-5);
	//	g2.drawLine(getWidth()-330+textu.getWidth(), 6, getWidth()-330+textu.getWidth(), getHeight()-6);
		super.paintComponent(g);
	}
	
}
