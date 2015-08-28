package tool;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import canvas.Canvas;



public class TextArea extends JTextArea{
	private int x,x1,y,y1;
	private boolean canEdit = false;
	private String str = "";
	private TextPanel tp1;
	private Canvas canvas1;
	public static Font textFont;
	public static float opactity = 1.0f;
	public static float Size = 20;
	String text = "";
	//private LinkedList<Character> text = new LinkedList<Character>();
	public TextArea(TextPanel tp,Canvas canvas){
		setOpaque(false);
		this.tp1 = tp;
		this.canvas1 = canvas;
		setWrapStyleWord(true);
		setLineWrap(true);
		addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent arg0) {
				setFont(textFont);
			}
		});
		addFocusListener(new FocusListener() {
	
			@Override
			public void focusLost(FocusEvent e) {
				if(getText().length()>=1) {
					try{
						drawTextOnCanvas(getText());
					}
					catch(java.awt.IllegalComponentStateException a){
					}
					tp1.setDrawBorder(false);
					if(tp1.isResize()) tp1.setDrawBorder(true);
				}
				else tp1.setDrawBorder(true);
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));//失去焦点时，设置光标类型为普通
				setVisible(false);	
				if(getText() != null){
					setVisible(true);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				tp1.setDrawBorder(true);
				setVisible(true);
			}
		});
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if(!isVisible())
					tp1.setCursor(new Cursor(Cursor.HAND_CURSOR));
				super.mousePressed(e);
			}
			
		});
	}
	private void drawTextOnCanvas(String str){
		Graphics2D g2 = (Graphics2D)canvas1.getImg().getGraphics();
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);//抗锯齿提示符
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,TextArea.opactity));//不透明度
		if(AbstractTool.color != null){
			g2.setColor(AbstractTool.color);
		}
		else if(AbstractTool.isColorRandom == true){
			g2.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
		}
		else if(AbstractTool.texture != null){
			g2.setPaint(AbstractTool.texture);
		}
		else
			g2.setPaint(AbstractTool.gradient);
		if(textFont != null){
			g2.setFont(textFont);
			setFont(textFont);
			FontMetrics fm = g2.getFontMetrics();
			g2.drawString(str, getLocationOnScreen().x, getLocationOnScreen().y -(fm.getAscent()+fm.getDescent())/2);
			canvas1.repaint();
		}
		setText("");
		tp1.setVisible(false);
		tp1.getCanvas().remove(tp1);
		
	}
}
