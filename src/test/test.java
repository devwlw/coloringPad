package test;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
public class test extends JPanel{
	private boolean isDraing = false;//ÕýÔÚ×÷»­
	private int upCount = 0;
	private int x=0,y=0,x1=0,y1=0;
	private ArrayList<Point> line = new ArrayList<Point>();
	//enderingHints c;
	public test(){
		setSize(60, 60);
		setLocation(0,0);
		setLayout(null);
		
		setOpaque(false);
		initLinster();
		
	}

	@Override
	protected void paintComponent(Graphics arg0) {
		Graphics2D g2 = (Graphics2D) arg0;
		//g2.setXORMode(arg0);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING ,RenderingHints.VALUE_ANTIALIAS_ON );
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(0.3f));
		Iterator<Point> p =  line.iterator();
		while(p.hasNext()&&p!=null){
			
			Point one = p.next();
			Point two = p.next();
			g2.drawLine(one.x, one.y, two.x,two.y);
		}
		if(isDraing)
			g2.drawLine(x, y, x1, y1);
		
	}
	private void initLinster(){
		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				x1 = arg0.getX();
				y1 = arg0.getY();
				isDraing = true;
				repaint();
			}
		});
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent arg0) {
				x = arg0.getX();
				y = arg0.getY();
				line.add(new Point(arg0.getX(),arg0.getY()));
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				line.add(new Point(arg0.getX(),arg0.getY()));
				isDraing = false;
				repaint();
			}
			
		});
	}
	@Override
	public void updateUI() {
		repaint();
	}
}
