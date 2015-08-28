package tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;


import canvas.Canvas;

public class TextTool extends AbstractTool{
	private static DrawTool tool;
	public static Font font;
	private TextTool(Canvas canvas){
		super(canvas);
	}
	public static DrawTool getInstance(Canvas canvas) {
		if (tool == null) {
			tool = new TextTool(canvas);
		}
		return tool;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		getCanvas().add(new TextPanel(e.getX(),e.getY(),getCanvas()));
		getCanvas().validate();
		
	}

}
