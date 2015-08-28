package menu;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mainface.MainFace;
import tool.AbstractTool;
import util.ImageButton;

public class EraserMenu extends Pull{
	private JSlider size,opactity;
	public EraserMenu(MainFace frame,ImageButton button,MenuContainer mc){
		super(300,210,button,mc);
		size = new SimpleSlider(100, 1, 20, 5);
		size.setValue(20);
		size.setLocation(15, 50);
		AbstractTool.Stroke = 20;
		opactity = new SimpleSlider(100,0,20,5);
		opactity.setValue(100);
		opactity.setLocation(15, 135);
		AbstractTool.brushOpactity = 1.0f;
		add(size);
		add(opactity);
		size.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				AbstractTool.Stroke = size.getValue();
				repaint();
			}
		});
		opactity.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				AbstractTool.brushOpactity = opactity.getValue()/100.0f;
				repaint();
			}
		});
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.drawString("大小", size.getLocation().x,size.getLocation().y-15);
		g.drawString(""+AbstractTool.Stroke, size.getWidth()-30,size.getLocation().y-15);
		g.drawString("透明度", opactity.getLocation().x,opactity.getLocation().y-15);
		g.drawString(""+AbstractTool.brushOpactity, opactity.getWidth()-30, opactity.getLocation().y-15);
	}
	
}
