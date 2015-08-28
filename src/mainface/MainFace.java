package mainface;
import util.ImageButton;
import util.Info;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;

import canvas.Canvas;
import menu.FunctionPanel;

public class MainFace extends JFrame implements Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  int x,y,x1,y1,x2,y2;//记录鼠标坐标点，用于窗体的移动
	private ImageButton min,max,close;//最小化，最大化，关闭按钮;
	private boolean Switch = true;//是否可以最大化
	private Dimension defaultsize = new Dimension(DEFAULTWIDTH,DEFAULTHEIGHT);//窗口默认大小
	private FrameBorder left,right,top,bottom,left_top,left_bottom,right_top,right_bottom;//程序边框
	private TopPanel TopPanel;//最上方面板
	private FunctionPanel functionPanel;//安放按钮的菜单
	private Canvas canvas;
	public static boolean isMin = false;//用于记录当前窗口是否是最小化
	//JDesktopPane desktop;
	public static void main(String[] args){
		
		//WelcomePage wp = new WelcomePage();
		//wp.setVisible(true);
		Open.initSource();//载入资源
		//wp.setVisible(false);
		//open.run(new MainFace(), DEFAULTWIDTH, DEFAULTHEIGHT);
	}
	
	public MainFace() throws IOException{
		addTopRightButton();//添加右上角三个按钮
		addBorder();//添加边框
		addWindowMoveListener();//窗口移动事件
		addWindowChangeListener();//窗口大小改变
		addFunctionPanel();//添加按钮板
		canvas = new Canvas(this,DEFAULTWIDTH-2,DEFAULTHEIGHT-TopPanel.getHeight()-functionPanel.getHeight()-8);	
		canvas.setLocation(1, TopPanel.getHeight()+functionPanel.getHeight());
		add(getCanvas());//添加画板
		
		getContentPane().setBackground(Color.WHITE);
    
	}
	/*
	 * 添加一个空白面板，并加入右上角三个按钮
	 * 且为其添加事件
	 */
	private void addTopRightButton() throws IOException{
		TopPanel = new TopPanel(this);
		close = new ImageButton("button/close.png");//关闭按钮
		close.setLocation(defaultsize.width-close.getWidth()-9,2);//设置位置
		close.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);		
			}
		});
		class CL extends MouseAdapter{//鼠标进入退出事件

			@Override
			public void mouseEntered(MouseEvent arg0) {
					close.switchPic("button/close1.png");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
					close.switchPic("button/close.png");
			}
			
		}
		close.addMouseListener(new CL());
		TopPanel.add(close);
		
		max = new ImageButton("button/max.png");//最大化按钮
		max.setLocation(defaultsize.width-close.getWidth()-max.getWidth()-10, 3);
		class MX extends MouseAdapter{//最大化按钮鼠标事件
			@Override
			public void mouseEntered(MouseEvent e) {
				max.switchPic("button/max1.png");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				max.switchPic("button/max.png");
			}
			
		}
		max.addMouseListener(new MX());
		max.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Switch) {//如果能最大化
					setExtendedState(Frame.MAXIMIZED_BOTH);
					Switch = false;
					isMin = false;
				}
				else {
					setExtendedState(Frame.NORMAL);
					Switch = true;
					isMin = false;
				}	
			}
		});
		TopPanel.add(max);
		
		min = new ImageButton("button/min.png");
		min.setLocation(defaultsize.width-close.getWidth()-max.getWidth()-min.getWidth()-11,3);
		class MN extends MouseAdapter{
			@Override
			public void mouseEntered(MouseEvent e) {
				min.switchPic("button/min1.png");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				min.switchPic("button/min.png");
			}
			
		}
		min.addMouseListener(new MN());
		min.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setExtendedState(Frame.ICONIFIED);
				isMin = true;
			}
		});
		TopPanel.add(min);
		add(TopPanel);
	}
	
	private void addBorder() throws IOException{
		/*
		 * 程序边框
		 */
		left = new FrameBorder("texture/vertical.png");
		right = new FrameBorder("texture/vertical.png");
		top = new FrameBorder("texture/across.png");
		bottom = new FrameBorder("texture/across.png");
		left.setBounds(0,7,1,defaultsize.height-7);
		right.setBounds(defaultsize.width-1, 7, 1, defaultsize.height-14);
		top.setBounds(7, 0,defaultsize.width,1);
		bottom.setBounds(7, defaultsize.height-1,defaultsize.width-14,1);
		left_top = new FrameBorder("texture/left_top.png");
		left_top.setBounds(0,0, left_top.getBufferedImgSize().width, left_top.getBufferedImgSize().height);
		left_bottom = new FrameBorder("texture/left_bottom.png");
		left_bottom.setBounds(0, defaultsize.height-7, left_bottom.getBufferedImgSize().width, left_bottom.getBufferedImgSize().height);
		right_top = new FrameBorder("texture/right_top.png");
		right_top.setBounds(defaultsize.width-8, 1, right_top.getBufferedImgSize().width, right_top.getBufferedImgSize().height);
		right_bottom = new FrameBorder("texture/right_bottom.png");
		right_bottom.setBounds(defaultsize.width-7, defaultsize.height-9,right_bottom.getBufferedImgSize().width, right_bottom.getBufferedImgSize().height);
		add(top);
		add(bottom); 
		add(left);
		add(right);
		add(left_top);
		add(left_bottom);
		add(right_top);
		add(right_bottom);
		class EnterBorder extends MouseAdapter{//鼠标进入程序边框
			private Point lastPoint = null;
			/*
			 * 程序缩放默认以左边框为参考系
			 */
			@Override
			public void mouseDragged(MouseEvent e){
				Point point = e.getLocationOnScreen();
				if (point != null && lastPoint != null) {
		            int xOffset = point.x - lastPoint.x;//相对移动距离
		            int yOffset = point.y - lastPoint.y;
		            if (getExtendedState() != Frame.MAXIMIZED_BOTH) {
		                JComponent component = (JComponent) e.getSource();
		                //if move window.
		                if (component == top) {
		                    //if resize top
		                        int width = getWidth();
		                        int height = getHeight();
		                        int x = getLocation().x;
		                        int y = getLocation().y;
		                        y += yOffset;
		                        height -= yOffset;
		                        setSize(width, height);
		                        //当缩放达到最小尺寸时，防止拖动程序
		                        if(getHeight()!=MINWIDTH)
				                    setLocation(x, y);
		                        //为了防止超过最大缩放尺寸
		                        if(getHeight()>MAXHEIGHT) {
		                        	setSize(getWidth(), MAXHEIGHT);
		                        	}
		                }
		                //if resize left.
		                if (component == left) {
		                		int width = getWidth();
		                    	int height = getHeight();
		                    	int x = getLocation().x;
		                    	int y = getLocation().y;
		                    	x += xOffset;
		                    	width -= xOffset;
		                    	setSize(width, height);
		                    	if(getWidth()!=MINWIDTH)
		                    		setLocation(x, y);            	
		                    	if(getWidth()>MAXWIDTH) {
		                    		setSize(MAXWIDTH, getHeight());
		                    	}
		                }
		                //if resize right.
		                if (component == right) {
		                    int width = getWidth();
		                    int height = getHeight();
		                    int x = getLocation().x;
		                    int y = getLocation().y;
		                    width += xOffset;
		                    setBounds(x, y, width, height);
		                    if(getWidth()>MAXWIDTH) setSize(MAXWIDTH, getHeight());
		                }
		                //if resize bottom.
		                if (component == bottom) {
		                    int width = getWidth();
		                    int height = getHeight();
		                    int x = getLocation().x;
		                    int y = getLocation().y;
		                    height += yOffset;
		                    setBounds(x, y, width, height);
		                    if(getHeight()>MAXHEIGHT) setSize(getWidth(), MAXHEIGHT);
		                }
		                if(component == left_top){
		                	int width = getWidth();
		                    int height = getHeight();
		                    int x = getLocation().x;
		                    int y = getLocation().y;
		                    x += xOffset;
		                    width -= xOffset;
		                    y += yOffset;
		                    height -= yOffset;
		                    setSize(width, height);
		                    if(getWidth()!=MINWIDTH)
	                    		setLocation(x, y);
		                    if(getHeight()!=MINHEIGHT)
		                    	setLocation(x,y);
		                    if(getWidth()>MAXWIDTH) {
	                    		setSize(MAXWIDTH, getHeight());
	                    	if(getHeight()>MAXHEIGHT)
	                    		setSize(getWidth(), MAXHEIGHT);
	                    	}
		                }
		                if(component == right_top){
		                	 int width = getWidth();
		                     int height = getHeight();
		                     int x = getLocation().x;
		                     int y = getLocation().y;
		                     width += xOffset;
		                     height -= yOffset;
		                     y += yOffset;
		                     setSize(width, height);
			                    if(getWidth()!=MINWIDTH)
		                    		setLocation(x, y);
			                    if(getHeight()!=MINHEIGHT)
			                    	setLocation(x,y);
			                    if(getWidth()>MAXWIDTH) {
		                    		setSize(MAXWIDTH, getHeight());
		                    	if(getHeight()>MAXHEIGHT)
		                    		setSize(getWidth(), MAXHEIGHT);
		                    	}
		                }
		                if(component == left_bottom){
		                	int width = getWidth();
		                    int height = getHeight();
		                    int x = getLocation().x;
		                    int y = getLocation().y;
		                    x += xOffset;
		                    width -= xOffset;
		                    height += yOffset;
		                    setSize(width, height);
		                    if(getHeight()!=MINWIDTH || getWidth()!=MINWIDTH)
			                    setLocation(x, y);
		                    if(getHeight()>MAXHEIGHT)
	                        	setSize(getWidth(), MAXHEIGHT);
		                    if(getWidth()>MAXWIDTH)
		                    	setSize(MAXWIDTH, getHeight());
		                }
		                if(component == right_bottom){
		                	int width = getWidth();
		                    int height = getHeight();
		                    int x = getLocation().x;
		                    int y = getLocation().y;
		                    width += xOffset;
		                    height += yOffset;
		                    setSize(width, height);
		                    if(getHeight()!=MINWIDTH || getWidth()!=MINWIDTH)
			                    setLocation(x, y);
		                    if(getHeight()>MAXHEIGHT)
	                        	setSize(getWidth(), MAXHEIGHT);
		                    if(getWidth()>MAXWIDTH)
		                    	setSize(MAXWIDTH, getHeight());
		                }
		            }
		            lastPoint = point;
		        }
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(e.getSource().equals(right))
					right.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
				if(e.getSource().equals(bottom))
					bottom.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
				if(e.getSource().equals(left))
					left.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
				if(e.getSource().equals(top))
					top.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));
				if(e.getSource().equals(left_top))
					left_top.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
				if(e.getSource().equals(right_top))
					right_top.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
				if(e.getSource().equals(left_bottom))
					left_bottom.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
				if(e.getSource().equals(right_bottom))
					right_bottom.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
			
			}
			@Override
			public void mousePressed(MouseEvent e) {
				lastPoint = e.getLocationOnScreen();
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
		}
		EnterBorder EB = new EnterBorder();
		right.addMouseListener(EB);
		right.addMouseMotionListener(EB);
		left.addMouseListener(EB);
		left.addMouseMotionListener(EB);
		bottom.addMouseListener(EB);
		bottom.addMouseMotionListener(EB);
		top.addMouseListener(EB);
		top.addMouseMotionListener(EB);
		left_top.addMouseListener(EB);
		left_top.addMouseMotionListener(EB);
		right_top.addMouseListener(EB);
		right_top.addMouseMotionListener(EB);
		left_bottom.addMouseListener(EB);
		left_bottom.addMouseMotionListener(EB);
		right_bottom.addMouseListener(EB);
		right_bottom.addMouseMotionListener(EB);
	}
	/*
	 * 为topPanel添加窗口移动事件
	 */
	private void addWindowMoveListener(){
		class MM extends MouseAdapter{//窗口移动事件
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getXOnScreen();
				y = e.getYOnScreen();
				x2 = getLocationOnScreen().x;
				y2 = getLocationOnScreen().y;
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2){
					if(getExtendedState()==Frame.MAXIMIZED_BOTH){
						setExtendedState(Frame.NORMAL);
						return;
					}
					if(getExtendedState()==Frame.NORMAL){
						setExtendedState(Frame.MAXIMIZED_BOTH);
						return;
					}
				}
			}

			public void mouseDragged(MouseEvent arg0) {
				
				if(getExtendedState()==Frame.MAXIMIZED_BOTH){
					setExtendedState(Frame.NORMAL);
					if(Toolkit.getDefaultToolkit().getScreenSize().width-arg0.getXOnScreen()<getWidth()){
						setLocation(Toolkit.getDefaultToolkit().getScreenSize().width-getWidth(), 0);
						x2 = getLocationOnScreen().x;
						y2 = getLocationOnScreen().y;
					}
					else{
						setLocation(arg0.getXOnScreen()-getWidth()/2, 0);
						x2 = getLocationOnScreen().x;
						y2 = getLocationOnScreen().y;
					}
					Switch = true;
				}
				x1 = arg0.getXOnScreen();
				y1 = arg0.getYOnScreen();
				setLocation((x2+x1-x),(y2+y1-y));
			}
		}
		MM mm = new MM();
		TopPanel.addMouseListener(mm);//窗口移动事件
		TopPanel.addMouseMotionListener(mm);//添加监听
	}
	
	private void addWindowChangeListener(){
		class WC extends ComponentAdapter{//窗体大小改变

			@Override
			public void componentResized(ComponentEvent arg0) {
				close.setLocation(getWidth()-close.getWidth()-9,2);
				max.setLocation(getWidth()-close.getWidth()-max.getWidth()-10, 3);
				min.setLocation(getWidth()-close.getWidth()-max.getWidth()-min.getWidth()-11,3);
				right.setBounds(getWidth()-1, 7,1,getHeight()-14);
				left.setBounds(0, 7, 1, getHeight()-7);
				bottom.setBounds(7, getHeight()-1, getWidth()-14, 1);
				top.setBounds(7, 0, getWidth()-14, 1);
				left_top.setBounds(0,0, left_top.getBufferedImgSize().width, left_top.getBufferedImgSize().height);
				left_bottom.setBounds(0, getHeight()-7, left_bottom.getBufferedImgSize().width, left_bottom.getBufferedImgSize().height);
				right_top.setBounds(getWidth()-7, 0, right_top.getBufferedImgSize().width, right_top.getBufferedImgSize().height);
				right_bottom.setBounds(getWidth()-7,getHeight()-7,right_bottom.getBufferedImgSize().width, right_bottom.getBufferedImgSize().height);
				functionPanel.setSize(getWidth()-1, 39);
				TopPanel.setSize(getWidth(), 23);
				canvas.setSize(getWidth()-2, getHeight()-TopPanel.getHeight()-functionPanel.getHeight()-8);
				repaint();
			}
			
		}
		addComponentListener(new WC());
	}
	private void addFunctionPanel() throws IOException{
		functionPanel = new FunctionPanel("texture/funpanel.png",this);
		functionPanel.setLocation(1, 25);
		add(functionPanel);
	}
	
	
	public Canvas getCanvas(){
		
		//canvas.setSize(DEFAULTWIDTH-2, DEFAULTHEIGHT-TopPanel.getHeight()-functionPanel.getHeight()-1);
		return canvas;
	}
	public FunctionPanel getFunctionPanel() {
		return functionPanel;
	}

	public void setFunctionPanel(FunctionPanel functionPanel) {
		this.functionPanel = functionPanel;
	}
}
