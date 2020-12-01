package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import sql.BankList;

public class ModeChoose {

	//设定绝对位置
	private int line_height = 30,
				width = 100,
				gap = 5,
				line1h = 10,
				line2h = line1h + line_height + gap,
				line3h = line2h + line_height + gap,
				line4h = line3h + line_height + gap,
				line5h = line4h + line_height + gap,
				line6h = line5h + line_height + gap,
				column1w = 15,
				column2w = column1w + width + gap,
				column3w = column2w + width + gap;
	
	private JFrame jf =  new JFrame("模拟题库ByNiji");
	
	private JTabbedPane jtb = new JTabbedPane();
    
    private JPanel mode = new JPanel();
    private JLabel jl1 = new JLabel("sql操作");
    private JLabel jl2 = new JLabel("题库选择");
    private JLabel jl3 = new JLabel("练习模式");
    private JLabel jl4 = new JLabel("考试模式");
    private JLabel jl5 = new JLabel("其他模式");
    private JLabel jl6 = new JLabel("做题记录");
	private JComboBox<String> jcb = new JComboBox<String>();
	private JButton import_table = new JButton("导入题库");
    private JButton export_table = new JButton("导出题库");
    private JButton ordinal = new JButton("顺序抽取");
    private JButton random = new JButton("随机抽取");
    private JButton test = new JButton("仿真模拟");
    private JButton favourite = new JButton("收藏");
    private JButton mistakes = new JButton("做错题");
    private JButton history = new JButton("历史");
    private JButton analysis = new JButton("错题分析");
    
    //private static Connection connection = SqlInit.getConnection();
	
	public ModeChoose() {
		
		//设置窗口显示尺寸
		jf.setSize(370, 290); 
		//将窗口置于屏幕的中央
		jf.setLocationRelativeTo(null);
		//使窗口大小无法调节
		jf.setResizable(false);
		
		//将布局设为绝对布局，方便直接通过坐标来放label和button
		mode.setLayout(null);
		//设置背景颜色
		mode.setBackground(Index.BACKGROUND);	
		
		//设置字体
		jl1.setFont(Index.t1); 
		jl2.setFont(Index.t1); 
		jl3.setFont(Index.t1);
		jl4.setFont(Index.t1);
		jl5.setFont(Index.t1);
		jl6.setFont(Index.t1);
		jcb.setFont(Index.t3);
		import_table.setFont(Index.t3);
		export_table.setFont(Index.t3);
		ordinal.setFont(Index.t3);
		random.setFont(Index.t3);
		test.setFont(Index.t3);
		favourite.setFont(Index.t3);
		mistakes.setFont(Index.t3);
		history.setFont(Index.t3);
		analysis.setFont(Index.t3);
		
		//初始化题库列表
		ArrayList<String> table_list = BankList.getTableList();
		jcb.addItem("请选择题库");
		for (String table : table_list) {
			if (!table.equals("admin"))
				jcb.addItem(table);
		}
			
		//通过绝对定位确定位置
		jl1.setBounds(column1w,line1h,width,line_height);
		jl2.setBounds(column1w,line2h,width,line_height);
		jl3.setBounds(column1w,line3h,width,line_height);
		jl4.setBounds(column1w,line4h,width,line_height);
		jl5.setBounds(column1w,line5h,width,line_height);
		jl6.setBounds(column1w,line6h,width,line_height);
		
		import_table.setBounds(column2w,line1h,width,line_height);
		export_table.setBounds(column3w,line1h,width,line_height);
		
		jcb.setBounds(column2w,line2h,2 * width + gap,line_height);
		
		ordinal.setBounds(column2w,line3h,width,line_height);
		random.setBounds(column3w,line3h,width,line_height);
		
		test.setBounds(column2w,line4h,2 * width + gap,line_height);
		
		favourite.setBounds(column2w,line5h,width,line_height);
		mistakes.setBounds(column3w,line5h,width,line_height);
		
		history.setBounds(column2w,line6h,width,line_height);
		analysis.setBounds(column3w,line6h,width,line_height);
		
		//添加各个组件
		mode.add(jl1);
		mode.add(jl2);
		mode.add(jl3);
		mode.add(jl4);
		mode.add(jl5);
		mode.add(jl6);
		mode.add(jcb);
		mode.add(import_table);
		mode.add(export_table);
		mode.add(ordinal);
		mode.add(random);
		mode.add(test);
		mode.add(favourite);
		mode.add(mistakes);
		mode.add(history);
		mode.add(analysis);
		
		//初始化禁用组件
		ordinal.setEnabled(false);
		random.setEnabled(false);
		test.setEnabled(false);
		favourite.setEnabled(false);
		mistakes.setEnabled(false);
		
		jtb.add(mode, "模式选择");
		
		jf.add(jtb);
		
		
		Event();
		
		//设置窗口可见
		jf.setVisible(true);
		//设置窗口可以关闭
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
	}
	
	public boolean chosed_flag = false;
	
	public void Event() {
		
		import_table.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Warning(jf,"版本提示","后续版本加入功能",true);
			}
		});
		
		export_table.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Warning(jf,"版本提示","后续版本加入功能",true);
			}
		});
		
		jcb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
		    	if (String.valueOf(jcb.getSelectedItem()).equals("请选择题库")) {
		    		ordinal.setEnabled(false);
		    		random.setEnabled(false);
		    		test.setEnabled(false);
		    		favourite.setEnabled(false);
		    		mistakes.setEnabled(false);
		    	} else {
		    		ordinal.setEnabled(true);
		    		random.setEnabled(true);
		    		test.setEnabled(true);
		    		favourite.setEnabled(true);
		    		mistakes.setEnabled(true);
		    	}
		    }
		});
		
		ordinal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Exercise("ordinal", String.valueOf(jcb.getSelectedItem()));
			}
		});
		
		random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Exercise("random", String.valueOf(jcb.getSelectedItem()));
			}
		});
		
		test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Exercise("test", String.valueOf(jcb.getSelectedItem()));
			}
		});
		
		favourite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Warning(jf,"版本提示","后续版本加入功能",true);
			}
		});
		
		mistakes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Warning(jf,"版本提示","后续版本加入功能",true);
			}
		});
		
		history.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Warning(jf,"版本提示","后续版本加入功能",true);
			}
		});
		
		analysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Warning(jf,"版本提示","后续版本加入功能",true);
			}
		});
				
//		.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
	}
	
	
}