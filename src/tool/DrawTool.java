package tool;

import java.awt.event.MouseEvent;

import javax.swing.event.CaretEvent;

public interface DrawTool {
	public static final String ARROW_TOOL = "Arrow_Tool";//箭头工具
	public static final String STREAMER_TOOL = "Streamer_TooL";//纸带工具
	public static final String CALLIGRAPHY_TOOL = "Calligraphy_Tool";//笔迹工具
	public static final String CRAYON_TOOL = "Crayon_Tool";//蜡笔工具
	public static final String PAINTBRUSH_TOOL = "PaintBrush_Tool";//喷涂工具(一直按颜色不变)
	public static final String PENCIL_TOOL = "Pencil_Tool";//铅笔工具
	public static final String SPRAYPAINT_TOOL = "SprayPaint_Tool";//喷漆工具(随着按下的时间颜色逐渐加深)
	public static final String CHROME_TOOL = "Chrome_Tool";//铬金工具(在弯曲处做线条)
	public static final String FURTOOL = "Fur_Tool";//茸毛工具(若下次的鼠标轨迹和这次的不同，将作出很多直线)
	public static final String WEB_TOOL = "Web_Tool";//网状工具(和chrome类似，不过只会在距当前最近的线条处做线)
	public static final String SPIROGRAPH_TOOL = "Spirograph_TooL";//螺旋工具
	public static final String CIRCLE_TOOl = "Circle_Tool";
	public static final String JUMP_TOOL = "Jump_Tool";
	
	public static final String STAMP_TOOL = "Stamp_Tool";//印章工具(独立)
	public static final String ERASER_TOOL = "Eraser_Tool";//擦子
	public static final String TEXT_TOOL = "Text_Tool";//文字工具
	public void mouseDragged(MouseEvent e);//鼠标拖动
	public void mouseMoved(MouseEvent e);//鼠标移动
	public void mouseReleased(MouseEvent e);//鼠标释放
	public void mousePressed(MouseEvent e);//鼠标按下
	public void mouseClicked(MouseEvent e);//鼠标按下放回
}
