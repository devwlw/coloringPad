package menu;

import java.awt.Color;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JSlider;

import util.FontFactory;

public class SimpleSlider extends JSlider{
	private int max,min,major,minor;//滑块的最大值最小值，最大刻度和最小刻度
	public SimpleSlider(int max,int min,int major,int minor) {
		this.max = max;
		this.min = min;
		this.major = major;
		this.minor = minor;
		setSize(180, 50);
		setOpaque(false);
		try {
			setFont(FontFactory.getDekarFont(14.0f));
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setForeground(Color.WHITE);
		setMaximum(max);
		setMinimum(min);
		setPaintLabels(true);
		setPaintTicks(true);
		setPaintTrack(true);
		setMajorTickSpacing(major);
		setMinorTickSpacing(minor);
	}
	/*@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(34,34,34));
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}*/
	
	//@Override
	/*protected void paintChildren(Graphics g) {
		g.setColor(new Color(34,34,34));
		g.fillRect(0, 0, getWidth(), getHeight());
	}*/
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMajor() {
		return major;
	}
	public void setMajor(int major) {
		this.major = major;
	}
	public int getMinor() {
		return minor;
	}
	public void setMinor(int minor) {
		this.minor = minor;
	}
}
