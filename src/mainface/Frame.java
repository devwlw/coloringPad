package mainface;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//这个类用于填充程序边框
import javax.swing.JLabel;
/*
 * 程序边框
 */
public class Frame extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	private TexturePaint texture;
	private File file;
	public Frame(String path) throws IOException{
		file = new File(path);
		img = ImageIO.read(file);
		Rectangle rect = new Rectangle(img.getWidth(), img.getHeight());
		texture = new TexturePaint(img, rect);
		setLayout(null);
		setOpaque(false);
	}
	public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(texture);
        g2.fillRect(0,0,getWidth(),getHeight());
        //super.paintComponent(g);
    }
}
