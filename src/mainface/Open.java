package mainface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import tool.AbstractTool;
import tool.DrawTool.*;
import twaver.TWaverUtil;
import util.FontFactory;
import util.ImageListFactory;
import util.Info;

public class Open implements Info{
	private boolean load = false;//载入资源是否完成
	/**
	 这个类用于启动swing线程
	 */
	//private static MainFace frame1;
	//private static int width1;
	//private static int height1;
	private static void run(final MainFace frame,final int width, final int height){
		
		SwingUtilities.invokeLater(new Runnable() {	
			@Override
			public void run() {
				frame.setSize(width,height);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setUndecorated(true);//取消窗体装饰
				frame.setLayout(null);
				frame.setMinimumSize(new Dimension(MINWIDTH, MINHEIGHT));
				frame.setVisible(true);
				try {
					frame.setIconImage(ImageIO.read(new File("icon/icon1.png")));
					frame.setTitle("ColoringPad v1.0");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		/*frame1 = Frame;
		width1 = width;
		height1 = height;*/
	}
		public static void initSource(){//载入资源
			long t = System.currentTimeMillis()/1000;
		WelcomePage wp = new WelcomePage();
		wp.setVisible(true);
		wp.setInfo("读取纹理文件....");
		initTextureImg();
		wp.setInfo("构造纹理模型");
		initTextureListMode();
		wp.setInfo("读取字体文件...");
		initFont();
		wp.setInfo("读取印章文件");
		initStampImg();
		wp.setInfo("构造印章模型");
		initStampMode();
		wp.setInfo("构造渐变模型");
		initTextureGradientMode();
		System.out.println("耗时:"+(System.currentTimeMillis()/1000 - t));
		wp.setInfo("读取完毕,正在进入");
		try {
			TimeUnit.MILLISECONDS.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wp.setVisible(false);
		try {
			run(new MainFace(), DEFAULTWIDTH, DEFAULTHEIGHT);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	private static void initTextureImg(){//载入纹理图片
		File f = new File("media/texture");//载入存放纹理图片的文件夹
		List<BufferedImage> img = new ArrayList<BufferedImage>();//保存纹理图片的List
		String[] name = new String[100];//保存每个纹理图片的路径数组
		int i ;
		for(i =0;i<f.list().length;i++){
				try {
					img.add( ImageIO.read(new File("media/texture/"+f.list()[i]))  );//载入图片(File的list方法不会列出根目录，所以要手动加上)
					name[i] = "media/texture/"+f.list()[i];//保存图片路径
				} catch (IOException e) {
					System.out.println("文件错误:" +"media/texture/"+f.list()[i]);
				}

		}
		//开始构造纹理list
		ImageListFactory.Textureimg = img;//保存到ImageListFactory
		ImageListFactory.TextureName = name;
	}
	private static void initTextureListMode(){//初始化填充纹理菜单列表的mode
		DefaultListModel model = new DefaultListModel();
		BufferedImage bi = null;
		for(int j = 0;j<ImageListFactory.Textureimg.size();j++){
			bi = ImageListFactory.Textureimg.get(j);//获得纹理图片
			model.addElement(new ImageIcon(ImageListFactory.getThumbnail(bi,55,55)));//向model添加图片，大小为 55*55
		}
		ImageListFactory.TextureModel = model;//保存model
	}
	private static void initFont(){//保存字体文件
		File font = new File("Font");//读取字体文件夹
		FontFactory.fontName = font.list();//保存所有字体路径
		Map<String,Font> ma =  new HashMap<String, Font>();
		for(String temp : FontFactory.fontName){//循环保存字体文件
			Font fo = null;
			File file;
			FileInputStream fi;
			file = new File("Font/"+temp);
			try {
				fi = new FileInputStream(file);
				fo = Font.createFont(Font.TRUETYPE_FONT,fi);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch(IOException e){
				
			}
			catch(FontFormatException e){
				
			}
			ma.put(temp, fo);
			FontFactory.fontMap = ma;
			
		}
	}
	private static void initStampImg(){//保存印章图片
		File f = new File("media/draw");
		String[] count = f.list();//文件夹下的文件
		String[] folder = new String[50];//文件夹下的文件夹名称
		String[] name;//每个文件夹的下的文件
		File temp;
		int i = 0;
		HashMap<String,String[]> FileName = new HashMap<String, String[]>();
		for(String t : count){
			temp = new File("media/draw/"+t);
			if(!temp.isFile()){//如果是文件夹则保存文件夹名称
				folder[i++] = "media/draw/"+t;//文件夹
				name = temp.list();//文件夹内的文件
				FileName.put("media/draw/"+t, name);
				System.out.println(t+"   "+name.length);
			}
		}
		ImageListFactory.StampFolderCount = i;
		ImageListFactory.StampFolderName = folder;
		ImageListFactory.StampFileName = FileName;
	}
	@SuppressWarnings("unchecked")
	private static void initStampMode(){//初始化印章模型
		DefaultListModel model = new DefaultListModel();
		Image bi = null;
		HashMap<String,DefaultListModel> list = new HashMap<String,DefaultListModel>();
		int i;
		String abs = new File(".").getAbsolutePath().replace(".","").replace("\\", "/");//获得程序所在文件夹的绝对路径
		for(i  = 0;i<ImageListFactory.StampFolderCount;i++){
			model = new DefaultListModel();
			for(String temp : ImageListFactory.StampFileName.get(ImageListFactory.StampFolderName[i])){
				try {
					
					bi = TWaverUtil.getImage("file:/"+abs +ImageListFactory.StampFolderName[i]+"/"+temp);
					//通过TwaverUtil来加载图片，以提高效率
					model.addElement(new ImageIcon(ImageListFactory.getThumbnail(bi,55,55)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}//第二个for
			list.put(ImageListFactory.StampFolderName[i],model);//键：文件夹 值：文件夹内的图片路径
		}//第一个for
		ImageListFactory.StampModel = list;//保存
	}
	private static void initTextureGradientMode(){
		BufferedImage temp;
		DefaultListModel mode = new DefaultListModel();
		LinearGradientPaint imagePaint = null;//线性颜色渐变模式
		float[] po = new float[]{0.0f,1.0f/6,2.0f/6f,3.0f/6,4.0f/6,5.0f/6,1.0f};//定义填充线性颜色渐变的各个分量
		ArrayList<Color[]> colorList = new ArrayList<Color[]>();//颜色数组
		int i = 0;
		Color[] one = null;
		while(i<200){
			i++;
			one = new Color[]{new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255)),
					new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255)),
					new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255)),
					new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255)),
					new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255)),
					new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255)),
					new Color((int)(Math.random() * 255),(int)(Math.random() * 255),(int)(Math.random() * 255))};
			//随机生成7个颜色，保存在颜色数组中
			imagePaint = new LinearGradientPaint(0,0,25,25,	po,one
					);//构造填充线性颜色渐变
			
			temp = new BufferedImage(25,25,BufferedImage.TYPE_INT_RGB);//声明一个大小25*25的图片，用来填充渐变模型中的list
			Graphics gra = temp.getGraphics();
			Graphics2D g2 = (Graphics2D)gra;
			g2.setPaint(imagePaint);
			gra.fillRect(0, 0, 25, 25);
			mode.addElement(new ImageIcon(temp));
			colorList.add(one);
		}
		ImageListFactory.gradientMode = mode;
		ImageListFactory.gradientColor = colorList;
	}
}
