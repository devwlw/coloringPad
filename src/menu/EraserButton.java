package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * ╡авс
 */
import java.io.IOException;

import tool.ToolFactory;

import mainface.MainFace;


public class EraserButton extends FunctionButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFace frame1;
	private EraserMenu em;
	private MenuContainer mc;
	public EraserButton(String path,final MainFace frame) throws IOException {
		super(path);
		this.frame1 = frame;
		setToolTipText("╡авс");
		mc = new MenuContainer(frame1,EraserButton.this);
		em = new EraserMenu(frame1, EraserButton.this,mc);
		mc.add(em);
		mc.setSize(em.getSize());
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(getComponentCount()>0)
				if(frame.getCanvas().getTool() != null)	{				
					frame.getCanvas().setTool(ToolFactory.getToolInstance(frame.getCanvas(),"Eraser_Tool"));
					if( getCount()==0)	{
						mc.setLocation(getLocation().x,getLocation().y+80);
						mc.setVisible(true);
						setCount(1);
					}
				}
			}
		});
	}

}
