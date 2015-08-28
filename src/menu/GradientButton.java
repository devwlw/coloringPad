package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import mainface.MainFace;

public class GradientButton extends FunctionButton{
	private MainFace frame1;
	private GradientMenu gm;
	MenuContainer mc;
	public GradientButton(String path, MainFace frame) throws IOException {
		super(path);
		this.frame1 = frame;
		setToolTipText("½¥±ä");
		mc = new MenuContainer(frame1, GradientButton.this);
		gm = new GradientMenu(frame1,GradientButton.this,mc);
		mc.add(gm);
		mc.setSize(gm.getSize());
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
