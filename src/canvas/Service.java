package canvas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tool.AbstractTool;

import mainface.MainFace;


/*
 * 画图的实现类
 */


public class Service {
	/**
	 * 创建鼠标自定义图形
	 * 
	 * @param path
	 *            String 图形路径
	 * @return Cursor;
	 */
	public static List<BufferedImage> canvasImg = new ArrayList<BufferedImage>();
	public static int count = 0;//保存了多少张画板图片
	public static int action = 1;
	private ImageFileChooser fileChooser;
	public static Cursor createCursor(String path) {
		Image cursorImage = Toolkit.getDefaultToolkit().createImage(path);
		return Toolkit.getDefaultToolkit().createCustomCursor(cursorImage,
				new Point(10,10), "mycursor");
	}
	public void repaint(Graphics g, BufferedImage bufferedImage) {
		int drawWidth = bufferedImage.getWidth();
		int drawHeight = bufferedImage.getHeight();
		// 把缓冲的图片绘画出来
		g.drawImage(bufferedImage, 0, 0, drawWidth, drawHeight, null);
	}
	public Dimension getScreenSize() {
		Toolkit dt = Toolkit.getDefaultToolkit();
		return dt.getScreenSize();
	}
	public void initDrawSpace(Canvas canvas) {
		// 获取画图对象
		Graphics g = canvas.getImg().getGraphics();
		// 获取画布的大小
		Dimension d = canvas.getPreferredSize();
		// 获取宽
		int drawWidth = (int) d.getWidth();
		// 获取高
		int drawHeight = (int) d.getHeight();
		// 绘画区
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, drawWidth, drawHeight);
	}
	public void save(boolean b, final Canvas canvas) {
		new Thread(new Runnable() {		
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					 fileChooser = new ImageFileChooser();
			 } catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException | UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				if (fileChooser.showSaveDialog(canvas) == ImageFileChooser.APPROVE_OPTION) {
					// 获取当前路径
					File currentDirectory = fileChooser.getCurrentDirectory();
					// 获取文件名
					String fileName = fileChooser.getSelectedFile().getName();
					// 获取后缀名
					String suf = fileChooser.getSuf();
					// 组合保存路径
					String savePath = currentDirectory + "\\" + fileName + "."
							+ suf;
					try {
						// 将图片写到保存路径
						ImageIO.write(canvas.getImg(), suf, new File(
								savePath));
					} catch (java.io.IOException ie) {
						ie.printStackTrace();
					}
				}
			}
		}).start();
			
	}

	/**
	 * 打开图片
	 * 
	 * @param frame
	 *            ImageFrame
	 * @return void
	 */
	public void open(final Canvas canvas) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				 try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						 fileChooser = new ImageFileChooser();
				 } catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException | UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						}
				// save(false, canvas);
						if (fileChooser.showOpenDialog(canvas) == ImageFileChooser.APPROVE_OPTION) {
							// 获取选择的文件
								File file = fileChooser.getSelectedFile();
							// 设置当前文件夹
								fileChooser.setCurrentDirectory(file);
							BufferedImage image = null;
							try {
									// 从文件读取图片
								image = ImageIO.read(file);
								} catch (java.io.IOException e) {
									return;
								}
								// 宽，高
								int width = image.getWidth();
								int height = image.getHeight();
								AbstractTool.drawWidth = width;
								AbstractTool.drawHeight = height;
								// 创建一个MyImage
								BufferedImage myImage = new BufferedImage(width, height,
										BufferedImage.TYPE_INT_RGB);
								// 把读取到的图片画到myImage上面
								myImage.getGraphics().drawImage(image, 0, 0, width, height, null);
								canvas.setImg(myImage);
								// repaint
								canvas.repaint();
								// 重新设置viewport
							}
			}
		}).start();
	}	
}
