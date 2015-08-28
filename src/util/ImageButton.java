package util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
//这个类用于将图片替换为按钮
public class ImageButton extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected BufferedImage image;
	private String name;
	private int count = 0;

	public ImageButton(String path){//构造函数接受文件路径
		File file = new File(path);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContentAreaFilled(false);  
		setBorder(null);
		setBorderPainted(false);
		setSize(image.getWidth(),image.getHeight());
		name = path.split("(.png)")[0];
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		g2.drawImage(image, 0, 0, null);	
	}
	public void switchPic(String path){//切换图片
		File file = new File(path);
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(path.endsWith(".png"))
			name = path.split("(.png)")[0];
		if(path.endsWith("1.png"))
			name = path.split("(1.png)")[0];
		if(path.endsWith("2.png"))
			name = path.split("(2.png)")[0];
		repaint();
	}
	@Override
	public int getHeight() {
		return image.getHeight();
	}
	@Override
	public int getWidth() {
		return image.getWidth();
	}
	public String getImageName(){
		return name;
	}
	public void setImageName(String name){
		this.name = name;
}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}