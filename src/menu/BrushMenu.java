package menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicListUI;

import mainface.MainFace;

import tool.AbstractTool;
import tool.CalligraphyTool;
import tool.ChromeTool;
import tool.CrayonTool;
import tool.DrawTool;
import tool.SpirographTooL;
import tool.ToolFactory;
import tool.WebTool;
import util.FontFactory;
import util.ImageButton;
import canvas.Canvas;

public class BrushMenu extends Pull{
	private JComboBox brushs;


	private MainFace frame;
	private ImageButton button;
	private JSlider Stroke = null,diameter = null,Opactity = null;//画笔大小,宽度,透明度
	
	
	private JSlider fixRadius = null,movingRadius = null,movingOffset = null;//螺旋的  固定半径，移动半径，移动分量
	private JSlider revoluteRadians = null;//旋转弧度
	private ImageButton CropStyle,RoundStyle,RectStyle;//笔画的三种样式
	private int value1,value2;//滑块的值
	private MenuContainer mc;
	private String actionInfo;

	/*private String[] brushContents = {"箭头","羽毛笔 ","烙金",
			  "蜡笔","绒毛","喷雾",
			  "铅笔","螺旋","喷漆",
			  "彩带","网","圆","间断"};*/
	private String[] brushContents = {"纸带 ","烙金",
			  "蜡笔","绒毛",
			  "铅笔","网","圆","间断"};
	public BrushMenu(MainFace frame,ImageButton button,MenuContainer mc) {
		super(300,350,button,mc);
		this.mc = mc;
		this.frame = frame;
		this.button = button;
		//setSize(300, 350);
		CropStyle = new ImageButton("icon/rd1.png");
		RectStyle = new ImageButton("icon/rd1.png");
		RoundStyle = new ImageButton("icon/rd.png");
		RoundStyle.setSelected(true);
		CropStyle.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CropStyle.setSelected(true);
				CropStyle.switchPic("icon/rd.png");
				RectStyle.setSelected(false);
				RectStyle.switchPic("icon/rd1.png");
				RoundStyle.setSelected(false);
				RoundStyle.switchPic("icon/rd1.png");
				AbstractTool.LineStyle = BasicStroke.CAP_BUTT;
			}
		});
		RectStyle.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				CropStyle.setSelected(false);
				CropStyle.switchPic("icon/rd1.png");
				RectStyle.setSelected(true);
				RectStyle.switchPic("icon/rd.png");
				RoundStyle.setSelected(false);
				RoundStyle.switchPic("icon/rd1.png");
				AbstractTool.LineStyle = BasicStroke.CAP_SQUARE;
				CrayonTool.change = true;
			}
		});
		RoundStyle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CropStyle.setSelected(false);
				CropStyle.switchPic("icon/rd1.png");
				RectStyle.setSelected(false);
				RectStyle.switchPic("icon/rd1.png");
				RoundStyle.setSelected(true);
				RoundStyle.switchPic("icon/rd.png");
				AbstractTool.LineStyle = BasicStroke.CAP_ROUND;
				CrayonTool.change = true;
			}
		});
		brushs = new JComboBox(brushContents);
		brushs.setBounds(15, 10,180,30);
		brushs.setOpaque(false);
		brushs.setBackground(Color.WHITE);
		brushs.setFont(FontFactory.getSongFont(15));
		brushs.setUI(new BasicComboBoxUI() {
            public void installUI(JComponent comboBox) {
                super.installUI(comboBox);
                //listBox.setForeground(Color.WHITE);
                //listBox.setSize(170, getHeight());
                listBox.setOpaque(false);
                listBox.setSelectionBackground(new Color(0,0,0,0));
                listBox.setSelectionForeground(Color.BLACK);
            }
             
            @Override
			protected ComboBoxEditor createEditor() {
				// TODO Auto-generated method stub
				return super.createEditor();
			}

			/**
             * 该方法返回右边的按钮
             */
            protected JButton createArrowButton() {
                //return super.createArrowButton();
            	ImageButton a = new ImageButton("icon/down1.png");
            	//a.setLocation(a.getLocation().x, 10);
            	//a.setLayout(null);
            	return a;
            }
        });

		buttonActionListener();
		add(brushs);
		RoundStyle.setLocation(getWidth()/7, getHeight()-35);
		RectStyle.setLocation(5*getWidth()/12, getHeight()-35);
		CropStyle.setLocation(9*getWidth()/12, getHeight()-35);
		add(RoundStyle);
		add(CropStyle);
		add(RectStyle);
		
	}
	private void buttonActionListener(){
			
			brushs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox temp = (JComboBox)e.getSource();
				switch(temp.getSelectedIndex()){
				/*case 0: {
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					validate();
					
					button.switchPic("icon/Arrow_Tool.png");
					Stroke = new SimpleSlider(100,0,20,5);
					Stroke.setBounds(15,75,180,30);
					Opactity = new SimpleSlider(100,0,20,5);
					Opactity.setBounds(15, 160, 180, 30);
					Stroke.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value1 = Stroke.getValue();
			//				me.setValue(new float[]{value1,value2});
							AbstractTool.Stroke = value1/100.0f;
							
						}
					});
					Opactity.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value2 = Opactity.getValue();
							AbstractTool.brushOpactity = value2/100.0f;
							
						}
					});
					add(Stroke);
					add(Opactity);
						frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),"Arrow_Tool"));
					break;
					}*/
				case 0:{
					actionInfo="calligraphy";
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					if(diameter != null) 
						remove(diameter);
					validate();
					repaint();
					button.switchPic("icon/Calligraphy_TooL.png");
					Stroke = new SimpleSlider(50,0,10,5);
					Stroke.setValue(20);
					AbstractTool.Stroke = 20;
					Stroke.setBounds(15,75,180,30);
					Opactity = new SimpleSlider(100,0,20,5);
					Opactity.setValue(100);
					AbstractTool.brushOpactity = 1.0f;
					Opactity.setBounds(15, 160, 180, 30);
					Stroke.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value1 = Stroke.getValue();
							AbstractTool.Stroke = value1;
							repaint();
						}
					});
					Opactity.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value2 = Opactity.getValue();
							AbstractTool.brushOpactity = value2/100.0f;
							repaint();
						}
					});
					add(Stroke);
					add(Opactity);
					if(frame.getCanvas().getTool() != null)					
						frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),DrawTool.CALLIGRAPHY_TOOL));
					break;
				}
				case 1:{
					actionInfo = "chrome";
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					if(diameter != null) 
						remove(diameter);
					validate();
					repaint();
					button.switchPic("icon/Chrome_TooL.png");
					Stroke = new SimpleSlider(6, 0, 5, 1);
					Stroke.setValue(0);
					AbstractTool.Stroke = 0;
					diameter = new SimpleSlider(50, 10, 5, 8);
					diameter.setValue(10);
					diameter.setBounds(15, 225, 180,30 );
					ChromeTool.spread = 10*100;
					Stroke.setBounds(15,75,180,30);
					Opactity = new SimpleSlider(100,0,20,5);
					Opactity.setValue(100);
					AbstractTool.brushOpactity = 1.0f;
					Opactity.setBounds(15, 150, 180, 30);
					Stroke.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value1 = Stroke.getValue();
							AbstractTool.Stroke = value1;
							repaint();
						}
					});
					Opactity.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value2 = Opactity.getValue();
							AbstractTool.brushOpactity = value2/100.0f;
							repaint();
						}
					});
					diameter.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							ChromeTool.spread = 100 * diameter.getValue();
							repaint();
						}
					});
					add(Stroke);
					add(Opactity);
					add(diameter);
					if(frame.getCanvas().getTool() != null)	{				
						frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),DrawTool.CHROME_TOOL));
					}
					break;
				}
				case 2:{
					actionInfo="crayon";
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					if(diameter != null) 
						remove(diameter);
					validate();
					repaint();
					Stroke = new SimpleSlider(50,0,10,2);
					Stroke.setBounds(15,75,180,30);
					AbstractTool.Stroke = 10;
					Stroke.setValue(10);
					Opactity = new SimpleSlider(100,0,20,5);
					Opactity.setBounds(15, 160, 180, 30);
					AbstractTool.brushOpactity = 0.8f;
					Opactity.setValue(80);
					Stroke.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value1 = Stroke.getValue();
							AbstractTool.Stroke = value1;
							CrayonTool.change = true;
							repaint();
						}
					});
					Opactity.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value2 = Opactity.getValue();
							AbstractTool.brushOpactity = value2/100.0f;
							repaint();
						}
					});
					add(Stroke);
					add(Opactity);
					button.switchPic("icon/Crayon_TooL.png");
					if(frame.getCanvas().getTool() != null)	{				
						frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),AbstractTool.CRAYON_TOOL));
					}
					break;
				}
				case 3:{
					actionInfo = "fur";
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					if(diameter != null) 
						remove(diameter);
					validate();
					repaint();
					Stroke = new SimpleSlider(6,0,5,1);
					Stroke.setBounds(15,75,180,30);
					AbstractTool.Stroke = 1;
					Stroke.setValue(1);
					Opactity = new SimpleSlider(100,0,20,5);
					Opactity.setBounds(15, 160, 180, 30);
					AbstractTool.brushOpactity = 0.8f;
					Opactity.setValue(80);
					Stroke.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value1 = Stroke.getValue();
							AbstractTool.Stroke = value1;
							repaint();
						}
					});
					Opactity.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value2 = Opactity.getValue();
							AbstractTool.brushOpactity = value2/100.0f;
							repaint();
						}
					});
					add(Stroke);
					add(Opactity);
					button.switchPic("icon/Fur_TooL.png");
					if(frame.getCanvas().getTool()!=null)
						frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),AbstractTool.FURTOOL));
					break;
				}
				/*case 5:{
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					validate();
					button.switchPic("icon/PaintBrush_TooL.png");
					break;
				}*/
				case 4:{
					actionInfo="pencil";
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					if(diameter != null) 
						remove(diameter);
					validate();
					repaint();
					button.switchPic("icon/Pencil_TooL.png");
					Stroke = new SimpleSlider(100,0,20,5);
					Stroke.setBounds(15,75,180,30);
					AbstractTool.Stroke = 10;
					Stroke.setValue(10);
					Opactity = new SimpleSlider(100,0,20,5);
					Opactity.setBounds(15, 160, 180, 30);
					AbstractTool.brushOpactity = 0.8f;
					Opactity.setValue(80);
					Stroke.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value1 = Stroke.getValue();
							AbstractTool.Stroke = value1;
							repaint();
						}
					});
					Opactity.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value2 = Opactity.getValue();
							AbstractTool.brushOpactity = value2/100.0f;
							repaint();
						}
					});
					add(Stroke);
					add(Opactity);
					if(frame.getCanvas().getTool() != null)	{				
						frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),"Pencil_Tool"));
					}
					break;
				}
				/*case 7:{
					actionInfo = "spirograph";
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					setSize(300, 650);
					mc.setSize(300, 650);
					Stroke = new SimpleSlider(100,0,20,5);
					Stroke.setBounds(15,75,180,30);
					//Stroke.setValue(AbstractTool.Stroke);
					Opactity = new SimpleSlider(100,0,20,5);
					Opactity.setBounds(15, 160, 180, 30);
					fixRadius = new SimpleSlider(100, 1, 20, 5);
					fixRadius.setBounds(15, 245, 180, 30);
					movingRadius = new SimpleSlider(100, 1, 20, 5);
					movingRadius.setBounds(15, 330, 180, 30);
					movingOffset = new SimpleSlider(100, 1, 20, 5);
					movingOffset.setBounds(15, 415, 180, 30);
					revoluteRadians = new SimpleSlider(399, 1, 80, 20);
					revoluteRadians.setBounds(15, 500, 180, 30);
					Stroke.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value1 = Stroke.getValue();
							AbstractTool.Stroke = value1;
							repaint();
						}
					});
					Opactity.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value2 = Opactity.getValue();
							AbstractTool.brushOpactity = value2/100.0f;
							repaint();
						}
					});
					fixRadius.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							SpirographTooL.fix = fixRadius.getValue();
							repaint();
						}
					});
					movingRadius.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent e) {
							SpirographTooL.radius = movingRadius.getValue();
							repaint();
						}
					});
					movingOffset.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent e) {
							SpirographTooL.offset = movingOffset.getValue();
							repaint();
						}
					});
					revoluteRadians.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							SpirographTooL.radians = revoluteRadians.getValue();
							repaint();
						}
					});
					add(Opactity);
					add(Stroke);
					add(fixRadius);
					add(movingRadius);
					add(movingOffset);
					add(revoluteRadians);
					RoundStyle.setLocation(getWidth()/7, getHeight()-35);
					RectStyle.setLocation(5*getWidth()/12, getHeight()-35);
					CropStyle.setLocation(9*getWidth()/12, getHeight()-35);
					validate();
					button.switchPic("icon/Spirograph_TooL.png");
					if(frame.getCanvas().getTool() != null)	{				
						frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),DrawTool.SPIROGRAPH_TOOL));
					}
					break;
				}
				case 8:{
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					validate();
					button.switchPic("icon/SprayPaint_TooL.png");
					break;
				}
				case 9:{
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					validate();
					button.switchPic("icon/Streamer_TooL.png");
					break;
				}*/
				case 5:{
					actionInfo = "web";
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					if(diameter != null) 
						remove(diameter);
					validate();
					repaint();
					button.switchPic("icon/Chrome_TooL.png");
					Stroke = new SimpleSlider(6, 0, 5, 1);
					Stroke.setValue(0);
					AbstractTool.Stroke = 0;
					diameter = new SimpleSlider(50, 10, 5, 8);
					diameter.setValue(30);
					diameter.setBounds(15, 225, 180,30 );
					WebTool.spread = 30*100;
					Stroke.setBounds(15,75,180,30);
					Opactity = new SimpleSlider(100,0,20,5);
					Opactity.setValue(100);
					AbstractTool.brushOpactity = 1.0f;
					Opactity.setBounds(15, 150, 180, 30);
					Stroke.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value1 = Stroke.getValue();
							AbstractTool.Stroke = value1;
							repaint();
						}
					});
					Opactity.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value2 = Opactity.getValue();
							AbstractTool.brushOpactity = value2/100.0f;
							repaint();
						}
					});
					diameter.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							WebTool.spread = 100 * diameter.getValue();
							repaint();
						}
					});
					add(Stroke);
					add(Opactity);
					add(diameter);
					button.switchPic("icon/Web_TooL.png");
					if(frame.getCanvas().getTool() != null)	{				
						frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),DrawTool.WEB_TOOL));
					}
					break;
				}
				case 6:{
					actionInfo = "circle";
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					if(diameter!= null)
						remove(diameter);
					Stroke = new SimpleSlider(20,0,5,1);
					Stroke.setBounds(15,75,180,30);
					Stroke.setValue(12);
					AbstractTool.Stroke = 1.2f;
					Opactity = new SimpleSlider(100,0,20,5);
					Opactity.setBounds(15, 160, 180, 30);
					Opactity.setValue(50);
					AbstractTool.brushOpactity = 0.5f;
					Stroke.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value1 = Stroke.getValue();
							AbstractTool.Stroke = value1/10.0f;
							repaint();
						}
					});
					Opactity.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value2 = Opactity.getValue();
							AbstractTool.brushOpactity = value2/100.0f;
							repaint();
						}
					});
					add(Stroke);
					add(Opactity);
					validate();
					repaint();
					button.switchPic("icon/Web_TooL.png");
					if(frame.getCanvas().getTool() != null)	{				
						frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),DrawTool.CIRCLE_TOOl));
					}
						break;
				}
				case 7:{
					actionInfo="jump";
					if(Stroke != null)
						remove(Stroke);
					if(Opactity != null)
						remove(Opactity);
					if(diameter != null) 
						remove(diameter);
					validate();
					repaint();
					button.switchPic("icon/Pencil_TooL.png");
					Stroke = new SimpleSlider(100,0,20,5);
					Stroke.setBounds(15,75,180,30);
					Stroke.setValue(30);
					AbstractTool.Stroke = 30;
					Opactity = new SimpleSlider(100,0,20,5);
					Opactity.setBounds(15, 160, 180, 30);
					Opactity.setValue(100);
					AbstractTool.brushOpactity = 1.0f;
					Stroke.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value1 = Stroke.getValue();
							AbstractTool.Stroke = value1;
							repaint();
						}
					});
					Opactity.addChangeListener(new ChangeListener() {
						
						@Override
						public void stateChanged(ChangeEvent arg0) {
							value2 = Opactity.getValue();
							AbstractTool.brushOpactity = value2/100.0f;
							repaint();
						}
					});
					add(Stroke);
					add(Opactity);
					if(frame.getCanvas().getTool() != null)	{				
						frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),"Jump_Tool"));
					}
					
				}
				default:break;
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		if(actionInfo=="circle" || actionInfo=="pencil" || actionInfo=="jump" ||
				actionInfo=="calligraphy"|| actionInfo=="crayon"||actionInfo=="fur"){
			g.drawString("画笔大小", Stroke.getLocation().x,Stroke.getLocation().y-15);
			g.drawString(""+AbstractTool.Stroke, Stroke.getWidth()-30,Stroke.getLocation().y-15);
			g.drawString("透明度", Opactity.getLocation().x,Opactity.getLocation().y-15);
			g.drawString(""+AbstractTool.brushOpactity, Opactity.getWidth()-30, Opactity.getLocation().y-15);
		}
		else if(actionInfo =="chrome" || actionInfo=="web"){
			g.drawString("画笔大小", Stroke.getLocation().x,Stroke.getLocation().y-15);
			g.drawString(""+AbstractTool.Stroke, Stroke.getWidth()-30,Stroke.getLocation().y-15);
			g.drawString("透明度", Opactity.getLocation().x,Opactity.getLocation().y-15);
			g.drawString(""+AbstractTool.brushOpactity, Opactity.getWidth()-30, Opactity.getLocation().y-15);
			g.drawString("宽度",diameter.getLocation().x, diameter.getLocation().y -15);
			if(actionInfo == "chrome")
				g.drawString(""+ChromeTool.spread/100, diameter.getWidth()-30, diameter.getLocation().y - 15);
			else
				g.drawString(""+WebTool.spread/100, diameter.getWidth()-30, diameter.getLocation().y - 15);
		}
		/*else if(actionInfo == "spirograph"){
			g.drawString("宽度", Stroke.getLocation().x,Stroke.getLocation().y-15);
			g.drawString(""+AbstractTool.Stroke, Stroke.getWidth()-30,Stroke.getLocation().y-15);
			g.drawString("透明度", Opactity.getLocation().x,Opactity.getLocation().y-15);
			g.drawString(""+AbstractTool.brushOpactity, Opactity.getWidth()-30, Opactity.getLocation().y-15);
			g.drawString("固定半径",fixRadius.getLocation().x, fixRadius.getLocation().y-15);
			g.drawString(""+SpirographTooL.fix, fixRadius.getWidth()-30, fixRadius.getLocation().y-15);
			g.drawString("移动半径", movingRadius.getLocation().x,movingRadius.getLocation().y-15);
			g.drawString(""+SpirographTooL.radius, movingRadius.getWidth()-30, movingRadius.getLocation().y -15);
			g.drawString("移动分量", movingOffset.getLocation().x, movingOffset.getLocation().y-15);
			g.drawString(""+SpirographTooL.offset, movingOffset.getWidth()-30, movingOffset.getLocation().y -15);
			g.drawString("旋转幅度", revoluteRadians.getLocation().x, revoluteRadians.getLocation().y-15);
			g.drawString(""+SpirographTooL.radians, revoluteRadians.getWidth()-30, revoluteRadians.getLocation().y - 15);
		}*/
		else{		
		}
		g.drawString("圆头",getWidth()/7,getHeight()-50);
		g.drawString("方头",5*getWidth()/12,getHeight()-50);
		g.drawString("平头",9*getWidth()/12,getHeight()-50);

	}
	public JComboBox getBrushs() {
		return brushs;
	}
	public void setBrushs(JComboBox brushs) {
		this.brushs = brushs;
	}
}
