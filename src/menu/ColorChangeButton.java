package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class ColorChangeButton extends JButton{
	private Font f ;
	private ColorRadomPanel crp1;
	private Color backColor;
	public ColorChangeButton(ColorRadomPanel crp){
		this.crp1 = crp;
		setSize(200, 40);
		setOpaque(false);
		setLayout(null);
		setContentAreaFilled(false);  
		setBorder(null);
		f = new Font("宋体",Font.PLAIN,23);
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				//setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				//setVisible(false);
			}
			
		});
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				crp1.resetColor();
			}
		});
	}
	public void setBackColor(Color backColor){
		this.backColor = backColor;
		repaint();
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿提示符
		setFont(f);
		g2.setColor(new Color(255-backColor.getRed(),255-backColor.getGreen(),255-backColor.getBlue()));
		//g2.fillOval(495+50,rcp.getLocation().y+60,125,125);
		g2.fill3DRect(0, 0, getWidth(), getWidth(),true);
		g2.setColor(backColor);
		g2.drawString("换 一 组",40,23);
	}
}
