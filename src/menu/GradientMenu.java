package menu;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JList;
import javax.swing.JScrollPane;

import mainface.MainFace;
import tool.AbstractTool;
import util.ImageButton;
import util.ImageListBox;
import util.ImageListFactory;

public class GradientMenu extends Pull{
	private MainFace frame;
	private ImageButton button;
	private JList list;
	MenuContainer mc;
	float[] po = new float[]{0.0f,1.0f/6,2.0f/6f,3.0f/6,4.0f/6,5.0f/6,1.0f};
	public GradientMenu(MainFace frame, ImageButton button,MenuContainer mc) {
		super(300,350 , button, mc);
		this.button = button;
		this.frame = frame;
		list = new ImageListBox();	
		list.setModel(ImageListFactory.gradientMode);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int index = list.locationToIndex(e.getPoint());
				Color[] temp = ImageListFactory.gradientColor.get(index);
				AbstractTool.gradientColor = temp;
				LinearGradientPaint gradient1 = new LinearGradientPaint(0,0,AbstractTool.drawWidth,AbstractTool.drawHeight,po,temp);
				AbstractTool.gradient = gradient1;
				AbstractTool.texture = null;
	            AbstractTool.color = null; 
	            AbstractTool.isColorRandom = false;
			}
			 
		});
		JScrollPane scr = new JScrollPane(list);
		scr.setSize(234, 55*5);
		scr.setLocation(15, 37);
		scr.setOpaque(false);
		add(scr);

	}
	
	
}
