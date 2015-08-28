package util;

import java.awt.Dimension;
import java.awt.Toolkit;

public interface Info {
	public static final int MAXWIDTH = new Dimension(Toolkit.getDefaultToolkit().getScreenSize()).width;//фад╩©М
	public static final int MAXHEIGHT = new Dimension(Toolkit.getDefaultToolkit().getScreenSize()).height;//фад╩╦ъ
	public static final int DEFAULTWIDTH = 2*MAXWIDTH/3;
	public static final int DEFAULTHEIGHT = 2*MAXHEIGHT/3;
	public static final int MINWIDTH = 345;
	public static final int MINHEIGHT = 345;
}
