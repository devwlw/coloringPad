package menu;
/*
 * 面板上的颜色按钮
 */
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mainface.MainFace;

import org.w3c.dom.css.Rect;


import tool.AbstractTool;
import tool.PencilTool;
import tool.ToolFactory;

public class ColorButton extends FunctionButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6807288904327573431L;
	private MainFace frame1;
	private Color t;
	private MenuContainer mc;
	private ColorMenu cm;
	public ColorButton(String path,final MainFace frame) throws IOException {
		super(path);
		this.frame1 = frame;
		setToolTipText("颜色");
		mc = new MenuContainer(frame1, ColorButton.this);
		cm = new ColorMenu(frame1,ColorButton.this,mc);
		mc.add(cm);
		mc.setSize(cm.getSize());
		addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				if( getCount()==0)	{
					mc.setLocation(getLocation().x,getLocation().y+80);
					mc.setVisible(true);
					setCount(1);
				}
			}
		});
		class moveLinstener extends MouseAdapter{
			int pressX,pressY;
			int locationX,locationY;
			int presentX,presentY;
			@Override
			public void mouseDragged(MouseEvent arg0) {
				presentX = arg0.getLocationOnScreen().x;
				presentY = arg0.getLocationOnScreen().y;
				mc.setLocation(locationX+presentX-pressX, locationY+presentY-pressY);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				pressX = arg0.getXOnScreen();
				pressY = arg0.getYOnScreen();
				locationX = mc.getLocation().x;
				locationY = mc.getLocation().y;
			}
			
		}
		moveLinstener ml = new moveLinstener();
		mc.removeMM();
		mc.addMouseListener(ml);
		mc.addMouseMotionListener(ml);
	}

}
