package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;
/*
 * 这个类用于给pulldownpanel增加边框阴影
 */
public class ShadowBorder extends AbstractBorder{
	int xoff,yoff;
	Insets insets;
	public ShadowBorder(int x,int y){
		this.xoff = x;
		this.yoff = y;
		insets = new Insets(0, 0, xoff, yoff);
	}
	@Override
	public Insets getBorderInsets(Component c) {
		return insets;
	}
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		g.setColor(Color.BLACK);
		g.translate(x, y);
		g.fillRect(width, yoff, xoff, height-yoff);
		g.fillRect(xoff, height-yoff, width-xoff, yoff);
		g.translate(-x, -y);
	}
	
}
