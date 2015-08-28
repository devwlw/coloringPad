package menu;

import java.io.IOException;

import mainface.MainFace;

public class ForwardButton extends FunctionButton{
	private MainFace frame;
	public ForwardButton(String path,MainFace frame)throws IOException{
		super(path);
		this.frame = frame;
		setToolTipText("Ç°½ø");
	}
}
