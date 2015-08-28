package menu;

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
import util.ImageListFactory;
import util.ImageListBox;

public class TextureMenu extends Pull{
	private MainFace frame;
	private ImageButton button;
	private JList list;
	MenuContainer mc;
	@SuppressWarnings("unchecked")
	public TextureMenu(MainFace frame, ImageButton button,MenuContainer mc) {
		super(300,350,button,mc);
		this.mc = mc;
		this.frame = frame;
		this.button = button;
		//setSize(300, 350);
		list = new ImageListBox();	
		list.setModel(ImageListFactory.TextureModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int index = list.locationToIndex(e.getPoint());
				BufferedImage temp = ImageListFactory.Textureimg.get(index);
				Rectangle rec = new Rectangle(temp.getWidth(),temp.getHeight());
				TexturePaint tp = new TexturePaint(temp, rec);
	            AbstractTool.texture = tp;
	            AbstractTool.color = null;
	            AbstractTool.gradient = null;
	            AbstractTool.isColorRandom = false;
			}
			 
		});
		JScrollPane scr = new JScrollPane(list);
		scr.setSize(55*5-28, 55*5);
		scr.setLocation(15, 37);
		scr.setOpaque(false);
		add(scr);
	}
}
