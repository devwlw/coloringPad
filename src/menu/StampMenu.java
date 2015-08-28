package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicComboBoxUI;

import mainface.MainFace;
import tool.AbstractTool;
import tool.ToolFactory;
import util.FontFactory;
import util.ImageButton;
import util.ImageCombox;
import util.ImageListBox;
import util.ImageListFactory;

public class StampMenu extends Pull{
	private MainFace frame1;
	private ImageButton button;
	private JList list;
	private JComboBox stamp;
	private String select;//combox的选择项
	private JSlider opactity = null;//设置透明度
	private JSlider gap = null;//设置间隔
	private JSlider angle = null;//每两张图片的角度
	private float value,value1,value2;
	public StampMenu(MainFace frame,ImageButton button,MenuContainer mc) {
		super(300,700,button,mc);
		this.frame1 = frame;
		this.button = button;
	//	this.mc = mc;
		//setSize(300, 650);
		list = new ImageListBox();
		list.setModel(ImageListFactory.StampModel.get(ImageListFactory.StampFolderName[0]));//设置list默认模型为Map中的第一个
		
		JScrollPane scr = new JScrollPane(list);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frame1.getCanvas().setTool(ToolFactory.getToolInstance(frame1.getCanvas(),"Stamp_Tool"));
				int index = list.locationToIndex(e.getPoint());//根据坐标获得list索引
				String imagePath = "media/draw/"+select+"/"+ImageListFactory.StampFileName.get("media/draw/"+select)[index];//根据索引获得图片名	
				BufferedImage stampImage = null;
				//System.out.println(imagePath);
				try {
					stampImage = ImageIO.read(new File(imagePath));//载入图片
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				AbstractTool.stamp = stampImage;//设置AbstractTool的印章图片为所选择的
			}
			 
		});
		scr.setSize(55*5-28, 55*5);//设置滚动条的大小
		scr.setLocation(15, 57);//位置
		scr.setOpaque(false);//背景透明
		stamp = getComboBox();
		//scr.setBackground(Color.WHITE);
		opactity = new SimpleSlider(100,0,20,5);
		opactity.setBounds(15,380,55*5-28,35);
		opactity.setValue((int)(AbstractTool.stampOpactity*100));
		gap = new SimpleSlider(55,5,10,5);
		gap.setBounds(15,465,55*5-28,35);
		gap.setToolTipText("像素");
		gap.setValue(AbstractTool.stampGap);
		angle = new JSlider(1,8);
		angle.setBounds(15,550,55*5-28,35);
		angle.setOpaque(false);
		angle.setForeground(Color.WHITE);
		angle.setValue(AbstractTool.stampAngle);
		//angle.setMaximum(8);
		//angle.setMinimum(1);
		angle.setPaintLabels(true);
		angle.setPaintTicks(true);
		angle.setPaintTrack(true);
		angle.setMajorTickSpacing(7);
		angle.setMinorTickSpacing(1);
		opactity.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int temp = opactity.getValue();
				value = AbstractTool.stampOpactity = temp/100.0f;
				repaint();
			}
		});
		gap.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int temp = gap.getValue();
				value1 = AbstractTool.stampGap = temp;
				repaint();
			}
		});
		angle.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int temp = angle.getValue();
				value2 = AbstractTool.stampAngle = temp;
				repaint();
			}
		});
		add(opactity);
		add(gap);
		add(angle);
		add(scr);
		add(stamp);
	}
	private JComboBox getComboBox(){//返回一个包含了内容的下拉框
		JComboBox stamp = new ImageCombox();
		stamp.setLocation(15,10);
		//stamp = new JComboBox();
		for(int i = 0;i<ImageListFactory.StampFolderCount;i++){
			stamp.addItem(ImageListFactory.StampFolderName[i].split("/")[2]);//为下拉框增加内容，只包含了存放图片的文件夹名称
																			//eg:  media/draw/动物    ---->  动物   
		}
		select = (String) stamp.getSelectedItem();//获得当前选择项(String  图片的根目录)
		stamp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox temp = (JComboBox)e.getSource();
				select = (String) temp.getSelectedItem();//获得当前选择项(String  图片的根目录)
				//System.out.println(select);
				DefaultListModel model = ImageListFactory.StampModel.get("media/draw/"+select);//根据下拉列表所选择的值来设置list的Mode
				list.setModel(model);
			}
		});
		return stamp;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.drawString("透明度", opactity.getLocation().x, opactity.getLocation().y-15);
		g.drawString(""+AbstractTool.stampOpactity,opactity.getWidth()-30, opactity.getLocation().y-15);
		g.drawString("间隔", gap.getLocation().x, gap.getLocation().y-15);
		g.drawString(""+AbstractTool.stampGap,gap.getWidth()-30, gap.getLocation().y-15);
		g.drawString("复位次数", angle.getLocation().x, angle.getLocation().y-15);
		g.drawString(""+AbstractTool.stampAngle, angle.getWidth()-30, angle.getLocation().y-15);
		g.drawString("由大到小",gap.getLocation().x,getHeight()-45);
		g.drawString("由小到大",gap.getWidth()-70,getHeight()-45);
		
		
	}
	
}
