package tool;

import java.util.HashMap;
import java.util.Map;

import canvas.Canvas;


public class ToolFactory {

	private static Map<String, DrawTool> toolMap = null;

	/**
	 * 获取工具类实例
	 * 
	 * @param type
	 *            String 工具类型
	 * @return Tool 返回工具类实例
	 */
	public static DrawTool getToolInstance(Canvas canvas, String type) {
		if (toolMap == null) {
			toolMap = new HashMap<String, DrawTool>();
			toolMap.put(DrawTool.ARROW_TOOL, ArrowTool.getInstance(canvas));
			toolMap.put(DrawTool.CALLIGRAPHY_TOOL, CalligraphyTool.getInstance(canvas));
			toolMap.put(DrawTool.CRAYON_TOOL, CrayonTool.getInstance(canvas));
			toolMap.put(DrawTool.CHROME_TOOL, ChromeTool.getInstance(canvas));
			toolMap.put(DrawTool.FURTOOL, FurTool.getInstance(canvas));
			toolMap.put(DrawTool.PAINTBRUSH_TOOL, PaintBrushTool.getInstance(canvas));
			toolMap.put(DrawTool.PENCIL_TOOL, PencilTool.getInstance(canvas));
			toolMap.put(DrawTool.SPIROGRAPH_TOOL, SpirographTooL.getInstance(canvas));
			toolMap.put(DrawTool.SPRAYPAINT_TOOL, SprayPaintTool.getInstance(canvas));
			toolMap.put(DrawTool.STREAMER_TOOL, StreamerTool.getInstance(canvas));
			toolMap.put(DrawTool.WEB_TOOL, WebTool.getInstance(canvas));
			toolMap.put(DrawTool.STAMP_TOOL, StampTool.getInstance(canvas));
			toolMap.put(DrawTool.CIRCLE_TOOl, CircleTool.getInstance(canvas));
			toolMap.put(DrawTool.JUMP_TOOL, JumpTool.getInstance(canvas));
			toolMap.put(DrawTool.ERASER_TOOL,EraserTool.getInstance(canvas));
			toolMap.put(DrawTool.TEXT_TOOL, TextTool.getInstance(canvas));
		}
		return (DrawTool) toolMap.get(type);
	}
}
