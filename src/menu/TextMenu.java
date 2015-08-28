package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.SizeLimitExceededException;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mainface.MainFace;
import tool.AbstractTool;
import tool.TextArea;
import util.FontFactory;
import util.ImageButton;
import util.ImageCombox;
import util.ImageListFactory;

public class TextMenu extends Pull{
	private MainFace frame1;
	private ImageButton button;
	private JComboBox in,out;//内部字体和外部字体
	private String inSelect,outSelect;
	private String[] systemFontNames,outFontName;
	private Font font;
	private JSlider size,opactity;
	public TextMenu(MainFace frame,ImageButton button,MenuContainer mc){
		super(300,400,button,mc);
		this.frame1 = frame;
		this.button = button;
		in = getSystemFont();
		out = getOutFont();
		in.setLocation(13,43);
		out.setLocation(13,115);
		add(in);
		add(out);
		size = new SimpleSlider(100,10,20,5);
		size.setValue((int)TextArea.Size);
		size.setLocation(13, 205);
		opactity = new SimpleSlider(100,0,20,5);
		opactity.setValue((int)(100*TextArea.opactity));
		opactity.setLocation(13, 315);
		add(size);
		add(opactity);
		size.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				TextArea.Size = size.getValue();
				repaint();
			}
		});
		opactity.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				TextArea.opactity = opactity.getValue();
				repaint();
			}
		});
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON );
		g2.setFont(FontFactory.getSongFont(17));
		g2.setColor(Color.WHITE);
		g2.drawString("系统字体",13, 40);
		g2.drawLine(13,80,180, 80);
		g2.drawString("外部字体", 13,110);
		g2.drawLine(13, 160, 180, 160);
		g2.drawString("大小",13, 185);
		g2.drawString(""+TextArea.Size,230, 185);
		g2.drawString("透明度", 13, 285);
		g2.drawString(""+TextArea.opactity, 230, 285);
		if(font != null){
			g2.setFont(font.deriveFont(30.0f));
			g2.drawString("字 体", 200, 70);
			g2.drawString("AaBb", 200, 140);
		}
	}
	private JComboBox getSystemFont(){
		JComboBox a = new ImageCombox();
		systemFontNames = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for(String t : systemFontNames){
			a.addItem(t);
		}
		a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox temp = (JComboBox)e.getSource();
				inSelect = (String) temp.getSelectedItem();
				font = new Font(inSelect,Font.PLAIN,(int)TextArea.Size);
				TextArea.textFont = font;
				
				repaint();
			}
		});
		return a;
	}
	private JComboBox getOutFont(){
		JComboBox a = new ImageCombox();
		for(String t: FontFactory.fontName){
			a.addItem(t.substring(0, t.length()-4));
		}
		a.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox temp = (JComboBox)e.getSource();
				inSelect = (String) temp.getSelectedItem();
				int a = temp.getSelectedIndex();
				font = FontFactory.fontMap.get(FontFactory.fontName[a]).deriveFont(TextArea.Size);
				TextArea.textFont = font;
				repaint();
			}
		});
		return a;
	}
}
