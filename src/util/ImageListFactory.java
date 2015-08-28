package util;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.image.BufferedImage;
import java.util.*;

import javax.swing.DefaultListModel;
/*
 * 获取图片的一个集合
 */
public class ImageListFactory {
	public static List<BufferedImage> Textureimg;
	public static String[] TextureName;
	public static DefaultListModel TextureModel;//纹理list中的模型
	
	public static Map<String,DefaultListModel> StampModel;//印章中的list模型
	public static String[] StampFolderName;//印章文件夹名称 
	public static int StampFolderCount;//印章文件夹个数
	public static Map<String,String[]> StampFileName;
	//public static  DefaultListModel ttt;
	public static List<Color[]> gradientColor;
	public static DefaultListModel gradientMode;
	/*
	 * 返回一个指定大小的略缩图
	 */
	public static Image getThumbnail(Image img,int width,int height){
		BufferedImage temp = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = temp.getGraphics();
		g.drawImage(img,0, 0, width, height, null);
		g.dispose();
		return temp;
	}
	public static BufferedImage getThumbnail(BufferedImage img,int width,int height){
		BufferedImage temp = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = temp.getGraphics();
		g.drawImage(img,0, 0, width, height, null);
		g.dispose();
		return temp;
	}
}