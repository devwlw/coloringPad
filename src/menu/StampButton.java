package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import tool.ToolFactory;

import mainface.MainFace;

public class StampButton extends FunctionButton{
	private MainFace frame1;
	private StampMenu sm;
	private MenuContainer mc;
	public StampButton(String path,MainFace frame) throws IOException {
		super(path);
		this.frame1 = frame;
		setToolTipText("ำกีย");
		mc = new MenuContainer(frame1,StampButton.this);
		sm = new StampMenu(frame1, StampButton.this,mc);
		mc.add(sm);
		mc.setSize(sm.getSize());
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame1.getCanvas().setTool(ToolFactory.getToolInstance(frame1.getCanvas(),"Stamp_Tool"));
				if( getCount()==0)	{
					mc.setLocation(getLocation().x,getLocation().y+80);
					mc.setVisible(true);
					setCount(1);
				}
			}
		});
	}
	
}
