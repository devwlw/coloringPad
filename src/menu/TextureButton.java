package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import mainface.MainFace;


public class TextureButton extends FunctionButton{
	private MainFace frame1;
	private TextureMenu tm;
	MenuContainer mc;
	public TextureButton(String path,MainFace frame) throws IOException {
		super(path);
		this.frame1 = frame;
		setToolTipText("Œ∆¿Ì");
		mc = new MenuContainer(frame1, TextureButton.this);
		tm = new TextureMenu(frame1,TextureButton.this,mc);
		mc.add(tm);
		mc.setSize(tm.getSize());
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
