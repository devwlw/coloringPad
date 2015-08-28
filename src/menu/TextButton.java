package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;

import tool.DrawTool;
import tool.ToolFactory;

import mainface.MainFace;

/*
 * 文本类
 */
public class TextButton extends FunctionButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFace frame1;
	private TextMenu tm;
	private MenuContainer mc;
	public TextButton(String path,MainFace frame) throws IOException {
		super(path);
		this.frame1 = frame;
		setToolTipText("文本");
		mc = new MenuContainer(frame1,TextButton.this);
		tm = new TextMenu(frame1, TextButton.this,mc);
		mc.add(tm);
		mc.setSize(tm.getSize());
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame1.getCanvas().setTool(ToolFactory.getToolInstance(frame1.getCanvas(),DrawTool.TEXT_TOOL));
				if( getCount()==0)	{
					mc.setLocation(getLocation().x,getLocation().y+80);
					mc.setVisible(true);
					setCount(1);
				}
			}
		});
	}

}
