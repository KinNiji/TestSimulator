package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import data.util.TestUtils;

public class Warning {
	
	private static JFrame fake = null;
	
	JDialog jd;
	
	private JPanel jp = new JPanel();
	
	private JTextArea text = new JTextArea();
	private JScrollPane jsp = new JScrollPane(text,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JButton confirm = new JButton(" 确认");
	private JButton copy = new JButton("复制信息");
	
	public Warning() {
		this(fake);
	}
	
	public Warning(JFrame jf) {
		this(jf, "标题", "测试文本", true);
	}
	
	public Warning(JFrame jf, String warning_title, String warning_text, boolean top_flag) {
		
		jd = new JDialog(jf, warning_title, top_flag);
		
		//设置窗口显示尺寸
		jd.setSize(245, 150); 
		//将窗口置于指定窗口的中央
		jd.setLocationRelativeTo(jf);
		//使窗口大小无法调节
		jd.setResizable(false);
		//设置窗口置顶
		jd.setAlwaysOnTop(true);
		
		//将布局设为BorderLayout
		jp.setLayout(null);
		//设置背景颜色
		jp.setBackground(Index.BACKGROUND);
		
		//设置文本域自动换行
		text.setLineWrap(true); 
		//设置自动换行不会断单词
		text.setWrapStyleWord(true);
		//设置文本域不能被编辑
		text.setEditable(false);
		//设置文本域背景为透明
		text.setOpaque(true);
		//设置tab size
		text.setTabSize(2);
		//设置无边框
		text.setBorder(null);
		jsp.setBorder(null);
		//设置背景透明
		text.setOpaque(false);
		jsp.setOpaque(false);
		jsp.getViewport().setOpaque(false);
		
		
		//将文本域添加进滚动区域
		jsp.setViewportView(text);
		
		//设置字体格式
		text.setFont(Index.t5);
		confirm.setFont(Index.t5);
		copy.setFont(Index.t5);
		
		//设置组件位置
		jsp.setBounds(10,10,211,70);
		confirm.setBounds(10,85,100,20);
		copy.setBounds(120,85,100,20);
		
		jp.add(jsp);
		jp.add(confirm);
		jp.add(copy);
		
		jd.add(jp);
		
		Event();
		
		//去除标题栏
		//jf_warning.setUndecorated(true);
		//设置标题栏文字
		jd.setTitle(warning_title);
		//设置警告文本
		text.setText(warning_text);
		
		//设置窗口可见
		jd.setVisible(true);
		//设置窗口可以关闭，并且只关闭当前窗口
		jd.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public void Event() {
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("警告窗口退出");
				jd.dispose();
			}
		});
		
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = text.getText();
				TestUtils.setClipboardString(string);
				text.append("\n已复制至剪贴板");
				copy.setEnabled(false);
			}
		});
		
//		marumaru.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (e.getSource() == marumaru){
//	
//				}
//			}
//		});
	}
}
