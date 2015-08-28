package util;

import java.awt.Color;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ImageCombox extends JComboBox{
	public ImageCombox(){
		setFont(FontFactory.getSongFont(15));
		setSize(180,30);//大小和位置
		setOpaque(false);
		setBackground(Color.WHITE);
		setUI(new BasicComboBoxUI() {//重写UI类
            public void installUI(JComponent comboBox) {
                super.installUI(comboBox);
                //listBox.setForeground(Color.WHITE);
                //listBox.setSize(170, getHeight());
                listBox.setOpaque(false);
                listBox.setSelectionBackground(new Color(0,0,0,0));
                listBox.setSelectionForeground(Color.BLACK);
            }
             
            @Override
			protected ComboBoxEditor createEditor() {
				// TODO Auto-generated method stub
				return super.createEditor();
			}

			/**
             * 该方法返回右边的按钮
             */
            protected JButton createArrowButton() {
                //return super.createArrowButton();
            	ImageButton a = new ImageButton("icon/down1.png");//设置向下的箭头的图片
            	//a.setLocation(a.getLocation().x, 10);
            	//a.setLayout(null);
            	return a;
            }
        });
	}
}
