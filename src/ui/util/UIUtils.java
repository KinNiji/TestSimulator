package ui.util;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UIUtils {
	
	public static JPanel getBaseTextArea() {
        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(textArea.getBackground());
        panel.setBorder(textArea.getBorder());
        textArea.setBorder(null);
        panel.add(textArea, BorderLayout.PAGE_END);

        return panel;
    }
	
	public static ImageIcon change(ImageIcon image, double i){//  i 为放缩的倍数
		 
        int width=(int) (image.getIconWidth()*i);
        int height=(int) (image.getIconHeight()*i);
        Image img = image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);//第三个值可以去查api是图片转化的方式
        ImageIcon image2 = new ImageIcon(img);

        return image2;
	}
	
	public static ImageIcon change(ImageIcon image, int width, int height){
		
        Image img = image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);//第三个值可以去查api是图片转化的方式
        ImageIcon image2 = new ImageIcon(img);

        return image2;
	}
}
