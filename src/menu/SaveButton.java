package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import mainface.MainFace;

public class SaveButton extends FunctionButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainFace frame1;
	public SaveButton(String path,MainFace frame) throws IOException {
		super(path);
		this.frame1 = frame;
		setToolTipText("±£´æ");
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame1.getCanvas().getService().save(true, frame1.getCanvas());
			}
		});
	}

}
