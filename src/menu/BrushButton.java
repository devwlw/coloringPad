package menu;
/*
 * ª≠À¢¿‡
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import mainface.MainFace;

import util.Info;



public class BrushButton extends FunctionButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1713395682738721543L;
	private MainFace frame1;
	private BrushMenu bm;
	private MenuContainer mc;
	public BrushButton(String path,MainFace frame) throws IOException {
		super(path);
		this.frame1 = frame;
		setToolTipText("ª≠À¢");
		mc = new MenuContainer(frame1,BrushButton.this);
		//mc.setSize(bm.getSize());
		bm = new BrushMenu(frame1, BrushButton.this,mc);
		//PullDownPanel.setInfoType(PullDownPanel.BRUSHINFO);
		mc.add(bm);
		mc.setSize(bm.getSize());
		addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if( getCount()==0)	{
					mc.setLocation(getLocation().x,getLocation().y+80);
					mc.setVisible(true);
					setCount(1);
				}
			}
		});
	}

}
