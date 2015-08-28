package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import tool.AbstractTool;

public class ColorLabel extends JButton{
	private String textName = "随机颜色";
	//private ColorMenu cm;
	private Font f ;
	private Color backColor;
	public ColorLabel(int width,int height){
	//	this.cm = cm;
		setSize(width, height);
		setOpaque(false);
		setLayout(null);
		setContentAreaFilled(false);  
		setBorder(null);
		f = new Font("宋体",Font.PLAIN,23);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textName == "随机颜色"){
					AbstractTool.isColorRandom = true;
					AbstractTool.color = null;
					textName = "取消随机";
				}
				else{
					AbstractTool.isColorRandom = false;
					AbstractTool.color = backColor;
					textName = "随机颜色";
				}
				repaint();			
			}
		});
	}
	public void setTextName(String name){
		this.textName = name;
	}
	public String getTextName(){
		return textName;
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
		g2.fillOval(0, 0, getWidth(), getWidth());
		g2.setColor(backColor);
		g2.drawString(textName,12,65);
	}
	
}
