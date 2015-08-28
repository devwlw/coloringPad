package svg;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageTranscoder;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import javax.swing.JPanel;
public class svgCanvas extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3801636333407125780L;
	private int x,y,x1,y1,x2,y2;
	public svgCanvas(String uri){
		setOpaque(false);
		setLayout(null);
		setSize(300, 300);
		setLocation(1, 1);
		//setURI("file:"+uri);
		addWindowMoveListener();
		//PNGTranscoder png = new PNGTranscoder();
		//png.
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;  
		Composite com = g2.getComposite(); 
	    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP , 0.3f));  
	    g2.setComposite(com);
	    g2.setColor(Color.BLUE);
	    g2.drawString("123", 50,50);
	    
	    
	}

	private void addWindowMoveListener(){
		class MM extends MouseAdapter{//窗口移动事件
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getXOnScreen();
				y = e.getYOnScreen();
				x2 = getLocation().x;
				y2 = getLocation().y;
			}
			public void mouseDragged(MouseEvent arg0) {
				x1 = arg0.getXOnScreen();
				y1 = arg0.getYOnScreen();
				//setSize(250,grid*num);
				setLocation((x1-x+x2),(y1-y+y2));
				repaint();
			}
		}
		MM mm = new MM();
		addMouseListener(mm);//窗口移动事件
		addMouseMotionListener(mm);//添加监听
	}
}
