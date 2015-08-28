package util;
/*
 * 能显示图片的JLabel
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import tool.AbstractTool;


/**
 * @author Bleach
 *
 */
@SuppressWarnings("rawtypes")
public class ImageListBox extends JList{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//private String[] img;//ImageListFactory里面的图片名数组
	//private BufferedImage bi;//用于显示在list上的图片
	public ImageListBox(){
		setOpaque(false);
		setSelectionBackground(new Color(101,119,115));
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		 setLayoutOrientation(JList.HORIZONTAL_WRAP);
		 setVisibleRowCount(0);
	}
}
