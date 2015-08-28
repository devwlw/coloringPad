package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;

import tool.ToolFactory;

import mainface.MainFace;

public class SetButton extends FunctionButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFace frame1;
	private SetMenu sm;
	private MenuContainer mc;
	public SetButton(String path,MainFace frame) throws IOException {
		super(path);
		this.frame1 = frame;
		setToolTipText("…Ë÷√");
		mc = new MenuContainer(frame1,SetButton.this);
		sm = new SetMenu(frame1, SetButton.this,mc);
		mc.add(sm);
		mc.setSize(sm.getSize());
		mc.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent arg0) {
				mc.setVisible(false);
				setCount(0);
			}
	
			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				mc.setVisible(true);
				
			}
		});
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if( getCount()==0)	{
					mc.setLocation(getLocationOnScreen().x,getLocationOnScreen().y+30);
					mc.setVisible(true);
					setCount(1);
				}
			}
		});
	}
	
}
