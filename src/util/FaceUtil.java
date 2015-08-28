package util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.Random;

/**
 * @author Bleach
 *
 */
public class FaceUtil {
	/**
	 * @param g2 希望添加阴影边框的容器的作图对象
	 * @param width 组件宽
	 * @param height 组件高
	 * @param xoff 边框宽度
	 * @param yoff 边框高度
	 */
	public static void createShadowBorder(Graphics2D g2,int width,int height,int xoff,int yoff){
		BufferedImage leftImg,rightImg,topImg,bottomImg;
		Graphics g;
		Graphics2D img;
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		topImg = new BufferedImage(width, yoff, BufferedImage.TYPE_INT_RGB);
		g = topImg.getGraphics();
		img = (Graphics2D) g;
		GradientPaint top = new GradientPaint(0,yoff,Color.BLACK,0,0,new Color(34,34,34));
		img.setPaint(top);
		img.fillRect(0, 0, width, yoff);
		topImg = FaceUtil.getGaussianBlurFilter(5,true).filter(topImg, null);
		topImg = FaceUtil.getGaussianBlurFilter(5, false).filter(topImg, null);
		g2.drawImage(topImg, 0, 0, null);
		
		bottomImg = new BufferedImage(width, yoff, BufferedImage.TYPE_INT_RGB);
		g = bottomImg.getGraphics();
		img = (Graphics2D) g;
		GradientPaint bottom = new GradientPaint(0,0,Color.BLACK,0,yoff,new Color(34,34,34));
		img.setPaint(bottom);
		img.fillRect(0, 0, width, yoff);
		bottomImg = FaceUtil.getGaussianBlurFilter(5,true).filter(bottomImg, null);
		bottomImg = FaceUtil.getGaussianBlurFilter(5, false).filter(bottomImg, null);
		g2.drawImage(bottomImg, 0,height-yoff, null);
		
		leftImg = new BufferedImage(xoff, height, BufferedImage.TYPE_INT_RGB);
		g = leftImg.getGraphics();
		img = (Graphics2D) g;
		GradientPaint left = new GradientPaint(xoff,0,Color.BLACK,0,0,new Color(34,34,34));
		img.setPaint(left);
		img.fillRect(0, 0, xoff, height);
		leftImg = FaceUtil.getGaussianBlurFilter(5,true).filter(leftImg, null);
		leftImg = FaceUtil.getGaussianBlurFilter(5, false).filter(leftImg, null);
		g2.drawImage(leftImg, 0,0, null);
		
		rightImg = new BufferedImage(xoff, height, BufferedImage.TYPE_INT_RGB);
		g = rightImg.getGraphics();
		img = (Graphics2D) g;
		GradientPaint right = new GradientPaint(0,0,Color.BLACK,xoff,0,new Color(34,34,34));
		img.setPaint(right);
		img.fillRect(0, 0, xoff, height);
		rightImg = FaceUtil.getGaussianBlurFilter(5,true).filter(rightImg, null);
		rightImg = FaceUtil.getGaussianBlurFilter(5, false).filter(rightImg, null);
		g2.drawImage(rightImg, width-xoff,0, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}
	
	/**
	 * @param radius 算法内核半径
	 * @param horizontal 垂直还是水平内核
	 * @return 一个带有高斯模糊的滤镜
	 */
	public static ConvolveOp getGaussianBlurFilter(int radius,
            boolean horizontal) {
        if (radius < 1) {
            throw new IllegalArgumentException("Radius must be >= 1");
        }
        
        int size = radius * 2 + 1;
        float[] data = new float[size];
        
        float sigma = radius / 3.0f;
        float twoSigmaSquare = 2.0f * sigma * sigma;
        float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
        float total = 0.0f;
        
        for (int i = -radius; i <= radius; i++) {
            float distance = i * i;
            int index = i + radius;
            data[index] = (float) Math.exp(-distance / twoSigmaSquare) / sigmaRoot;
            total += data[index];
        }
        
        for (int i = 0; i < data.length; i++) {
            data[i] /= total;
        }        
        
        Kernel kernel = null;
        if (horizontal) {
            kernel = new Kernel(size, 1, data);
        } else {
            kernel = new Kernel(1, size, data);
        }
        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    }
	 public static int[] GetRandomSequence2(int total)//不重复的随机数
     {

         int[] sequence = new int[total];
         int[] output = new int[total];

         for (int i = 0; i < total; i++)
         {
             sequence[i] = i;
         }

         Random random = new Random();

         int end = total - 1;

         for (int i = 0; i < total; i++)
         {
             int num = random.nextInt(end + 1);
             output[i] = sequence[num];
             sequence[num] = sequence[end];
             end--;
         }

         return output;
     }
}
