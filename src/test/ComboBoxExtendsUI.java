package test;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ComboBoxExtendsUI extends BasicComboBoxUI {  
  
    @Override  
    public void paint(Graphics g, JComponent c) {  
        hasFocus = comboBox.hasFocus();  
        if ( !comboBox.isEditable() ) {  
            Rectangle r = rectangleForCurrentValue();  
            paintCurrentValueBackground(g,r,hasFocus);  
            paintCurrentValue(g,r,hasFocus);  
        }  
   }  
  
    /** 
     * ÖØÐ´JComboBoxµÄ°´Å¥ 
     */  
    @Override  
    protected JButton createArrowButton() {  
          
        //String path = "C:/Documents and Settings/Administrator/workspace/IQBoard/src/com/returnstar/board/resource/img/toolbar/draw/h.png";  
        String path = "c:ar.png";
    	JButton button = new JButton(new ImageIcon(path));  
        button.setName("ComboBox.arrowButton");  
        return button;  
    }  
      
    @Override  
    protected Rectangle rectangleForCurrentValue() {  
        int width = comboBox.getWidth();  
        int height = comboBox.getHeight();  
        Insets insets = getInsets();  
        int editorWidth = width - (insets.left + insets.right);  
        int editorHeight = (height - (insets.top + insets.bottom - 1)) / 2;  
          
        return new Rectangle(
                insets.left, insets.top, editorWidth, editorHeight);  
    }  
      
    @Override  
    protected LayoutManager createLayoutManager() {  
        return new LayoutM();  
    }  
  
    class LayoutM implements LayoutManager{  
  
        public void addLayoutComponent(String name, Component comp) {}  
  
        public void layoutContainer(Container parent) {  
              
            JComboBox box = (JComboBox) parent;  
            int width = box.getWidth();  
            int height = box.getHeight();  
              
            Insets insets = getInsets();  
             
            int buttonWidth = width - (insets.left + insets.right);  
            int buttonHeight = (height - (insets.top + insets.bottom + 2)) / 2;  
              
            if (arrowButton != null){  
                arrowButton.setBounds(  
                        insets.left, height - insets.bottom - buttonHeight,  
                        buttonWidth, buttonHeight);  
            }  
  
            if (editor != null){  
                editor.setBounds(rectangleForCurrentValue());  
            }  
        }  
  
        public Dimension minimumLayoutSize(Container parent) {  
            return parent.getMinimumSize();  
        }  
  
        public Dimension preferredLayoutSize(Container parent) {  
            return parent.getPreferredSize();  
        }  
  
        public void removeLayoutComponent(Component comp) {}  
          
    }  
      
}  
