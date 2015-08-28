package test;
import javax.swing.*;

import mainface.Open;

import tool.TextPanel;
public class frame extends JFrame{
	public frame(){
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//TextPanel a = new TextPanel();
		//a.setLocation(200, 200);
		setLayout(null);
	//	add(a);
	}
public static void main(String[] args){
		new frame().setVisible(true);
	}
}
