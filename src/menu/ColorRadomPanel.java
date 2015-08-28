package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class ColorRadomPanel extends JPanel{
	//private int i,j;
	private int xOff = 0,yOff = 0;
	private Color tempColor;
	//private Color selectColor;
	
	private Color[][] colors = new Color[50][50];
	private ColorMenu cm1;
	public ColorRadomPanel(int width,int height,ColorMenu cm){
		setLayout(null);
		setSize(width,height);
		setOpaque(false);
		this.cm1 = cm;
		addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
						//如果鼠标在范围内
				try{
						int x = e.getX()/19;//宽
						int y = e.getY()/19;//高
						cm1.setSelectedColor(colors[x][y]);
						cm1.getR().setValue(colors[x][y].getRed());
						cm1.getG().setValue(colors[x][y].getGreen());
						cm1.getB().setValue(colors[x][y].getBlue());
				}
				catch(NullPointerException nu){
					
				}
			}
			
		});
		resetColor();
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		for(int i = 0;i< getWidth()/19;i++){	
			for(int j =0;j<getHeight()/19;j++){
				//colors[i][j] = tempColor = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
				g2.setColor(colors[i][j]);
				g2.fillRect(xOff, yOff, 17, 17);
				yOff+=19;
			}
			xOff += 19;
			yOff = 0;
		}
		xOff = 0;
		yOff = 0;
	}
	public Color[][] getColors() {
		return colors;
	}
	public void resetColor(){
		for(int i = 0;i< getWidth()/19;i++){	
			for(int j =0;j<getHeight()/19;j++){
				colors[i][j] = tempColor = new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255));
			}
		}
		repaint();
	}
}
