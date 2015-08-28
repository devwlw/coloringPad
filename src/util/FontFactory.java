package util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import tool.DrawTool;

public class FontFactory {
	private static File file;
	private static FileInputStream a;
	private static Font fa,f;
	public static Map<String, Font> fontMap = null;//字体map
	public static String[] fontName = null;//字体名称
	/**
	 * @param size 字体大小
	 * @return  (garden)字体
	 * @throws IOException 
	 * @throws FontFormatException 
	 */
	public static Font getGardenFont(float size) throws FontFormatException, IOException{
		file = new File("Font/garden.ttf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getDekarFont(float size) throws FontFormatException, IOException{
		file = new File("Font/dekar.otf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getLobsterFont(float size) throws FontFormatException, IOException{
		file = new File("Font/lobster.otf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getPincoyablackFont(float size) throws FontFormatException, IOException{
		file = new File("Font/pincoyablack.otf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getQubFont(float size) throws FontFormatException, IOException{
		file = new File("Font/qub.ttf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getTeardropFont(float size) throws FontFormatException, IOException{
		file = new File("Font/teardrop.ttf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getHelloKittyFont(float size) throws FontFormatException, IOException{
		file = new File("Font/hellokitty.ttf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getOstrichFont(Float size) throws FontFormatException, IOException{
		file = new File("Font/鸵鸟君中文.ttf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getPassionFont(Float size) throws FontFormatException, IOException{
		file = new File("Font/passion.ttf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getLedFont(float size) throws FontFormatException, IOException{
		file = new File("Font/led.ttf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getAngelsFont(float size) throws FontFormatException, IOException{
		file = new File("Font/angels.ttf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getFallenFont(float size) throws FontFormatException, IOException{
		file = new File("Font/fallen.ttf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getHouseFont(float size) throws FontFormatException, IOException{
		file = new File("Font/house.otf");
		a = new FileInputStream(file);
		fa = Font.createFont(Font.TRUETYPE_FONT, a);
		f = fa.deriveFont(size);
		return f;
	}
	public static Font getSongFont(int size){
		f = new Font("宋体",Font.PLAIN,size);
		return f;
	}
}
