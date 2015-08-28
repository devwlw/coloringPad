package menu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import util.ImageButton;

public class FunctionButton extends ImageButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FunctionButton(String path) throws IOException {
		super(path);
		class ML extends MouseAdapter{
			@Override
			public void mouseEntered(MouseEvent e) {
				switchPic(getImageName()+"1.png");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setContentAreaFilled(false);
				switchPic(getImageName()+".png");
			}
			@Override
			public void mousePressed(MouseEvent e) {
				switchPic(getImageName()+"2.png");
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				switchPic(getImageName()+"1.png");
			}
		}
		addMouseListener(new ML());

	}
}
