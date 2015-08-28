package canvas;

import java.awt.image.BufferedImage;

public class SaveImage extends BufferedImage{
	private boolean isSaved = true;//是否已经保存
	public SaveImage(int width, int height, int imageType) {
		super(width, height, imageType);
		this.getGraphics().fillRect(0, 0, width, height);
	}
	/*
	 * 设置是否已经保存
	 */
	public void setIsSaved(boolean b) {
		this.isSaved = b;
	}
	/*
	 * 返回是否已经保存
	 */
	public boolean isSaved() {
		return this.isSaved;
	}
}
