package test;

import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.plaf.basic.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JWindow;

public class doubleFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new fra();

	}

}
class fra extends JFrame{
	JDialog a = new JDialog(this,true);
	JButton jb = new JButton("OK");
	JButton jc = new JButton("CLOSE");
	public fra(){
		this.setSize(600, 500);
		this.setVisible(true);
		this.setLayout(new FlowLayout());
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				a.setSize(400, 300);
				a.setVisible(true);
				a.setAlwaysOnTop(false);
				add(a);
				
			}
		});
		jc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				a.setVisible(false);
				
			}
		});
		add(jb);
		add(jc);
	}
}