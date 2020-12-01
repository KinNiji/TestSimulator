package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import data.Answer;
import data.Question;
import data.util.TestUtils;
import sql.QuestionList;
import ui.util.ScrollBarUI;
import ui.util.TextBorderUtils;
import ui.util.UIUtils;

public class Exercise {
	
	private final Rectangle r = TestUtils.getResolution();
	
	private int 
				window_height = (int) r.getHeight(),
				window_width = (int) r.getWidth(),
				
				//30
				topdown_margin = window_height / 36,
				//40
				line_height = window_height / 27,
				stem_height = window_height / 6,
				jp_height = window_height / 8,
				button_height = window_height / 30,
				//24
				height_gap_medium = window_height / 45,
				//5
				height_gap_small = window_height / 216,
				line1h = topdown_margin,
				line2h = line1h + stem_height + height_gap_medium,
				line3h = line2h + line_height + height_gap_small,
				line4h = line3h + line_height + height_gap_small,
				line5h = line4h + line_height + height_gap_small,
				line6h = line5h + line_height + height_gap_small,
				line7h = line6h + line_height + height_gap_medium,
				line8h = line7h + line_height + height_gap_medium,
				line9h = line8h + line_height + height_gap_medium + window_height / 20,
				line10h = line9h + line_height + height_gap_small,
				
				down_line1h = window_height - topdown_margin - jp_height,
				down_line2h = down_line1h - jp_height - height_gap_medium,
				
				leftright_margin = window_width / 40,
				//72
				info_lab_width = window_width / 10,
				button_width = window_width / 12,
				jp_width = window_width - 2 * leftright_margin,
				width_gap = window_width / 198,
				question_column1w = leftright_margin,
				question_width = window_width - 2 * leftright_margin,
				button_column1w = leftright_margin,
				button_column2w = button_column1w + button_width + width_gap,
				button_column3w = button_column2w + button_width + width_gap,
				question_ringht_column1w = window_width - leftright_margin - info_lab_width;
			
//			width_left = leftright_margin,
//			width_right = window_width - leftright_margin,
//			width_middle = window_width / 2;
			
//			trisection_width = (window_width - 2 * leftright_margin - 2 * width_gap) / 3,
//			trisection_column1w = leftright_margin,
//			trisection_column2w = trisection_column1w + trisection_width + width_gap,
//			trisection_column3w = trisection_column2w + trisection_width + width_gap;
	
	private int 
				//135
				note_height = jp_height,
				//1824
				note_width = window_width - 2 * leftright_margin,
				
				note_topdown_margin = note_height / 8,
				note_line_gap = note_height / 27,
				note_line_height = note_height / 4,
				note_textarea_height = 3 * note_line_height + 2 * note_line_gap,
				note_button_height = note_line_height ,
				
				note_line1h = note_topdown_margin,
				note_line2h = note_line1h + note_line_gap + note_line_height,
				note_line3h = note_line2h + note_line_gap + note_line_height,
				note_button_h = note_line3h + note_line_height - note_button_height,
				
				note_width_gap = note_width / 190,
				note_leftright_margin = note_width / 30,
				note_info_lab_width = note_width / 50,
				note_text_width = (int)(note_width / 3.5),
				note_textarea_width = note_width / 2,
				note_button_width = note_width / 18,
				note_box_width = 2 * note_button_width - note_info_lab_width,
				
				
				column1w = note_leftright_margin,
				column2w = column1w + note_width_gap + note_info_lab_width,
				column3w = column2w + note_width_gap + note_text_width,
				column4w = column3w + note_width_gap + note_info_lab_width,
				column5w = column4w + note_width_gap + note_textarea_width,
				column6w = column5w + note_width_gap + note_info_lab_width,
				column6w_2 = column5w + note_width_gap + note_button_width
				;
	
	private Icon unchosed_icon = UIUtils.change(Index.unchosed_icon,line_height,line_height);
	private Icon chosed_icon = UIUtils.change(Index.chosed_icon,line_height,line_height);
	private Icon multichosed_icon = UIUtils.change(Index.multichosed_icon,line_height,line_height);
	
	private JFrame jf = new JFrame();
	
	private JPanel jp1 = new JPanel();
	
	private JRadioButton option_A = new JRadioButton("  A、Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
	private JRadioButton option_B = new JRadioButton("  B、Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
	private JRadioButton option_C = new JRadioButton("  C、Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
	private JRadioButton option_D = new JRadioButton("  D、Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
	private JRadioButton option_E = new JRadioButton("  E、Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
	private JRadioButton[] option_group = {option_A,option_B,option_C,option_D,option_E};
	private JLabel answer = new JLabel("答案：ABCDE");
	private JLabel answer_status = new JLabel("对错");
	private JLabel NO = new JLabel("题数：1/1");
	
	private JTextArea stem = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
			+ "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
	private JPanel stem_panel = new JPanel(new BorderLayout());
	private JScrollPane jsp1 = new JScrollPane(stem,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	private JButton prev = new JButton("上一题");
	private JButton next = new JButton("下一题");
	private JButton favourite = new JButton("收藏");
	private JButton settings = new JButton("设置");
	private JButton quit = new JButton("退出");
	private JButton goto_start = new JButton("回到第一题");
	private JButton goto_target = new JButton("跳到指定题");
	private JButton goto_end = new JButton("快进到最后");
	private JButton show_answer = new JButton("查看答案");
	private JButton edit = new JButton("修改");
	private JButton save = new JButton("保存");
	private JButton[] button_group = {prev,next,favourite,settings,quit,goto_start,goto_target,goto_end,show_answer,edit,save};
	
	private TitledBorder tb2 = BorderFactory.createTitledBorder("笔记");
	private JPanel jp2 = new JPanel();
	
	private JLabel jl1 = new JLabel("章");
	private JLabel jl2 = new JLabel("节");
	private JLabel jl3 = new JLabel("要点");
	private JLabel jl4 = new JLabel("解析");
	private JLabel jl5 = new JLabel("难度");
	
	private JTextField chap = new JTextField("Lorem ipsum dolor sit amet");
	private JTextField section = new JTextField("Lorem ipsum dolor sit amet");
	private JTextField point = new JTextField("Lorem ipsum dolor sit amet");
	private JTextField[] texts = {chap,section,point};
	private JTextArea description = new JTextArea("Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

	private JScrollPane jsp2 = new JScrollPane(stem,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private String[] difficulty_options = {"  ——未指定——","容易","中等","困难"};
	private JComboBox<String> difficulty = new JComboBox<String>();
	
	private TitledBorder tb3 = BorderFactory.createTitledBorder("题目选择");
	private JPanel jp3 = new JPanel();
	private ButtonGroup question_number_group = new ButtonGroup();
	private ArrayList<JRadioButton> question_number_list = new ArrayList<JRadioButton>();
	
	QuestionList ql;
	private ArrayList<Question> question_list;
	
	private Answer user_choice = new Answer();
	private String true_answer;
	
	private String type;
	
	private int current_NO = 1;
	private int start_NO = 1;
	private int end_NO = 1;
	
	public Exercise(String mode, String table) {
		
		ql = new QuestionList(table);
		
		//设置窗口显示尺寸
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
        //通过调用GraphicsEnvironment的getDefaultScreenDevice方法获得当前的屏幕设备了 
        GraphicsDevice gd = ge.getDefaultScreenDevice(); 
        // 全屏设置 
        gd.setFullScreenWindow(jf);
		
//      //设置窗口显示尺寸
//		jf.setSize(1600,900); 
//		//将窗口置于屏幕的中央
//		jf.setLocationRelativeTo(null);
//		//使窗口大小无法调节
//		jf.setResizable(false);
		
		//将布局设为绝对布局，方便直接通过坐标来放label和button
		jp1.setLayout(null);
		jp2.setLayout(null);
		jp3.setLayout(new GridLayout(4, 33, 5, 5));
		//设置背景颜色
		jp1.setBackground(Index.BACKGROUND_EYESHIELD);
		jp2.setBackground(Index.BACKGROUND_EYESHIELD);
		jp3.setBackground(Index.BACKGROUND_EYESHIELD);
		//设置panel标题
		//TitledBorder tb1 = BorderFactory.createTitledBorder("题号");
		
		//tb1.setTitleFont(Qfont_medium);
		tb2.setTitleFont(Index.t3);
		tb3.setTitleFont(Index.t3);
		//jp1.setBorder(tb1);
		jp2.setBorder(tb2);
		jp3.setBorder(tb3);
		
		//设置文本域自动换行
		stem.setLineWrap(true); 
		//设置自动换行不会断单词
		stem.setWrapStyleWord(true);
		//设置tab size
		stem.setTabSize(2);
		//设置文本域不能被编辑
		stem.setEditable(false);
		//设置无边框
		stem.setBorder(null);
		stem_panel.setBorder(null);
		jsp1.setBorder(null);
		//设置背景透明
		stem.setOpaque(false);
		stem_panel.setOpaque(false);
		jsp1.setOpaque(false);
		jsp1.getViewport().setOpaque(false);
		//设置文本默认左下
        stem_panel.add(stem, BorderLayout.PAGE_END);
		
		//设置文本域自动换行
		description.setLineWrap(true); 
		//设置自动换行不会断单词
		description.setWrapStyleWord(true);
		//设置tab size
		description.setTabSize(2);
		//设置文本域不能被编辑
		description.setEditable(false);
		//设置边框
		TextBorderUtils border = new TextBorderUtils(Index.BACKGROUND_EYESHIELD, 10, true);
		description.setBorder(border);
		
		//设置背景透明
		description.setOpaque(false);
		jsp2.setOpaque(false);
		jsp2.getViewport().setOpaque(false);
		
		//设置滚动区域滑动条UI
		jsp1.getVerticalScrollBar().setUI(new ScrollBarUI());
		jsp2.getVerticalScrollBar().setUI(new ScrollBarUI());
		
		//将文本域添加进滚动区域
		jsp1.setViewportView(stem_panel);
		jsp2.setViewportView(description);
		
		//设置选项背景相对透明
		option_A.setBackground(Index.BACKGROUND_EYESHIELD);
		option_B.setBackground(Index.BACKGROUND_EYESHIELD);
		option_C.setBackground(Index.BACKGROUND_EYESHIELD);
		option_D.setBackground(Index.BACKGROUND_EYESHIELD);
		option_E.setBackground(Index.BACKGROUND_EYESHIELD);
		
		//设置文本域透明并不可编辑
		chap.setOpaque(false);
		section.setOpaque(false);
		point.setOpaque(false);
		chap.setEditable(false);
		section.setEditable(false);
		point.setEditable(false);
		
		//设置字体
		option_A.setFont(Index.Qfont_medium);
		option_B.setFont(Index.Qfont_medium);
		option_C.setFont(Index.Qfont_medium);
		option_D.setFont(Index.Qfont_medium);
		option_E.setFont(Index.Qfont_medium);
		answer.setFont(Index.Qfont_large);
		answer_status.setFont(Index.Qfont_large);
		NO.setFont(Index.Qfont_large);
		stem.setFont(Index.Qfont_large);
		prev.setFont(Index.Qfont_medium);
		next.setFont(Index.Qfont_medium);
		favourite.setFont(Index.Qfont_medium);
		goto_start.setFont(Index.Qfont_medium);
		goto_target.setFont(Index.Qfont_medium);
		goto_end.setFont(Index.Qfont_medium);
		show_answer.setFont(Index.Qfont_medium);

		jl1.setFont(Index.t3);
		jl2.setFont(Index.t3);
		jl3.setFont(Index.t3);
		jl4.setFont(Index.t3);
		jl5.setFont(Index.t3);
		chap.setFont(Index.t3);
		section.setFont(Index.t3);
		point.setFont(Index.t3);
		description.setFont(Index.t3);
		difficulty.setFont(Index.t3);
		edit.setFont(Index.t3);
		save.setFont(Index.t3);
		
		settings.setFont(Index.Qfont_medium);
		quit.setFont(Index.Qfont_medium);
		quit.setForeground(Color.red); 
		
		//设置组件位置
		option_A.setBounds(question_column1w,line2h,question_width,line_height);
		option_B.setBounds(question_column1w,line3h,question_width,line_height);
		option_C.setBounds(question_column1w,line4h,question_width,line_height);
		option_D.setBounds(question_column1w,line5h,question_width,line_height);
		option_E.setBounds(question_column1w,line6h,question_width,line_height);
		jsp1.setBounds(question_column1w,line1h,question_width,stem_height);

		answer.setBounds(question_ringht_column1w - window_width / 120,line7h,info_lab_width,button_height);
		answer_status.setBounds(button_column3w + button_width + width_gap + window_width / 5,line7h,2 * info_lab_width,button_height);
		NO.setBounds(button_column3w + button_width + width_gap + (int)(window_width / 2.8),line7h,2 * info_lab_width,button_height);
		show_answer.setBounds(button_column3w + button_width + width_gap + window_width / 60,line7h,button_width,button_height);
		
		prev.setBounds(button_column1w,line7h,button_width,button_height);
		favourite.setBounds(button_column2w,line7h,button_width,button_height);
		next.setBounds(button_column3w,line7h,button_width,button_height);
		
		goto_start.setBounds(button_column1w,line8h,button_width,button_height);
		goto_target.setBounds(button_column2w,line8h,button_width,button_height);
		goto_end.setBounds(button_column3w,line8h,button_width,button_height);
		
		settings.setBounds(question_ringht_column1w,line9h,button_width,button_height);
		quit.setBounds(question_ringht_column1w,line10h,button_width,button_height);
		
		
		jp2.setBounds(question_column1w,down_line2h,jp_width, jp_height);
		
		jp3.setBounds(question_column1w,down_line1h,jp_width, jp_height);
		
		//设置按钮图案
		for (int i = 0; i < button_group.length; i++) {
			//button_group[i].setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0  
	        //button_group[i].setBorderPainted(false);//不打印边框
	        //button_group[i].setBorder(null);//除去边框  
	        button_group[i].setFocusPainted(false);//除去焦点的框  
	        //button_group[i].setContentAreaFilled(false);//除去默认的背景填充 
	        button_group[i].setBackground(Index.BACKGROUND_EYESHIELD);
		}
        
		prev.setIcon(UIUtils.change(Index.prev_icon, (int)(button_height * 0.8), (int)(button_height * 0.8)));
		next.setIcon(UIUtils.change(Index.next_icon, (int)(button_height * 0.8), (int)(button_height * 0.8)));
		favourite.setIcon(UIUtils.change(Index.favourite_unchosed_icon, (int)(button_height * 0.8), (int)(button_height * 0.8)));
		settings.setIcon(UIUtils.change(Index.settings_icon, (int)(button_height * 0.8), (int)(button_height * 0.8)));
		quit.setIcon(UIUtils.change(Index.quit_icon, (int)(button_height * 0.8), (int)(button_height * 0.8)));
		edit.setIcon(UIUtils.change(Index.edit_icon, (int)(note_button_height * 0.8), (int)(note_button_height * 0.8)));
		save.setIcon(UIUtils.change(Index.save_icon, (int)(note_button_height * 0.8), (int)(note_button_height * 0.8)));
		
		
		jl1.setBounds(column1w,note_line1h,note_info_lab_width,note_line_height);
		jl2.setBounds(column1w,note_line2h,note_info_lab_width,note_line_height);
		jl3.setBounds(column1w,note_line3h,note_info_lab_width,note_line_height);
		jl4.setBounds(column3w,note_line1h,note_info_lab_width,note_line_height);
		jl5.setBounds(column5w,note_line1h,note_info_lab_width,note_line_height);
		chap.setBounds(column2w,note_line1h,note_text_width,note_line_height);
		section.setBounds(column2w,note_line2h,note_text_width,note_line_height);
		point.setBounds(column2w,note_line3h,note_text_width,note_line_height);
		jsp2.setBounds(column4w,note_line1h,note_textarea_width,note_textarea_height);
		difficulty.setBounds(column6w,note_line1h,note_box_width,note_line_height);
		edit.setBounds(column5w,note_button_h,note_button_width,note_button_height);
		save.setBounds(column6w_2,note_button_h,note_button_width,note_button_height);
		
		for (int i = 0; i < difficulty_options.length; i++) {
			difficulty.addItem(difficulty_options[i]);
		}
		
		//禁用组件
		save.setEnabled(false);
		difficulty.setEnabled(false);
		
		//添加选项到选项组
		for (int i = 0; i < option_group.length; i++) {
			option_group[i].setFocusPainted(false);//除去焦点的框
			option_group[i].setIcon(unchosed_icon);
		}
		
		
		//添加组件
		jp1.add(answer);
		jp1.add(answer_status);
		jp1.add(NO);
		jp1.add(jsp1);
		for (int i = 0; i < option_group.length; i++) {
			jp1.add(option_group[i]);
		}
		jp1.add(prev);
		jp1.add(next);
		jp1.add(favourite);
		jp1.add(goto_start);
		jp1.add(goto_end);
		jp1.add(goto_target);
		jp1.add(show_answer);
		jp1.add(settings);
		jp1.add(quit);
		
		jp2.add(jl1);
		jp2.add(jl2);
		jp2.add(jl3);
		jp2.add(jl4);
		jp2.add(jl5);
		jp2.add(chap);
		jp2.add(section);
		jp2.add(point);
		jp2.add(jsp2);
		jp2.add(difficulty);
		jp2.add(edit);
		jp2.add(save);
		
		jp1.add(jp2);
		jp1.add(jp3);
		
		jf.add(jp1);
		
		Init(mode);
		
		Event();
		
		//设置窗口可见
		jf.setVisible(true);
		//设置窗口可以关闭
		jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
	}
	
	public void Event() {
		
//		for (int i = 0; i < option_group.length; i++) {
//			option_group[i].addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					user_choice = option_group[i].getText();
//
//				}
//			});
//		}
		
		option_A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeOption("A",option_A);
				
			}
		});
		
		option_B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeOption("B",option_B);
				
			}
		});
		
		option_C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeOption("C",option_C);
				
			}
		});
		
		option_D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeOption("D",option_D);
				
			}
		});
		
		option_E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeOption("E",option_E);
				
			}
		});
		
		show_answer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(user_choice.toString() + " " + true_answer);
				if (type.equals(Question.Singel_5) || type.equals(Question.Singel_4) || type.equals(Question.Judge)) {
					if (user_choice.toString().equals(true_answer)) {
						answer_status.setText("正确");
						switch (user_choice.toString()) {
							case "A":{
								option_A.setBackground(Index.RIGHT);
								break;
							}
							case "B":{
								option_B.setBackground(Index.RIGHT);
								break;
							}
							case "C":{
								option_C.setBackground(Index.RIGHT);
								break;
							}
							case "D":{
								option_D.setBackground(Index.RIGHT);
								break;
							}
							case "E":{
								option_E.setBackground(Index.RIGHT);
								break;
							}
						}
					} else {
						answer_status.setText("错误");
						switch (user_choice.toString()) {
							case "A":{
								option_A.setBackground(Index.WRONG);
								break;
							}
							case "B":{
								option_B.setBackground(Index.WRONG);
								break;
							}
							case "C":{
								option_C.setBackground(Index.WRONG);
								break;
							}
							case "D":{
								option_D.setBackground(Index.WRONG);
								break;
							}
							case "E":{
								option_E.setBackground(Index.WRONG);
								break;
							}
						}
						switch (true_answer) {
							case "A":{
								option_A.setBackground(Index.RIGHT);
								break;
							}
							case "B":{
								option_B.setBackground(Index.RIGHT);
								break;
							}
							case "C":{
								option_C.setBackground(Index.RIGHT);
								break;
							}
							case "D":{
								option_D.setBackground(Index.RIGHT);
								break;
							}
							case "E":{
								option_E.setBackground(Index.RIGHT);
								break;
							}
						}
					}
				} else if (type.equals(Question.Multiple_5) || type.equals(Question.Multiple_4)) {
					if (user_choice.toString().equals(true_answer)) {
						answer_status.setText("正确");
					} else {
						answer_status.setText("错误");
					}
				}
				answer.setText("答案：" + true_answer);
			}
		});
		
		goto_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				current_NO = 1;
				changeQuestion();
				
			}
		});
		
		goto_target.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Warning(jf,"版本提示","后续版本加入功能",true);
				
			}
		});
		
		goto_end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				current_NO = end_NO;
				changeQuestion();
				
			}
		});
		
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				current_NO -= 1;
				changeQuestion();
				
			}
		});
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				current_NO += 1;
				changeQuestion();
				
			}
		});
		
		favourite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (favourite.isSelected()) {
					favourite.setIcon(UIUtils.change(Index.favourite_chosed_icon,(int)(button_height * 0.8), (int)(button_height * 0.8)));
				} else {
					favourite.setIcon(UIUtils.change(Index.favourite_unchosed_icon,(int)(button_height * 0.8), (int)(button_height * 0.8)));
				}
				new Warning(jf,"版本提示","后续版本加入功能",true);
				
			}
		});
		
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < texts.length; i++) {
					texts[i].setOpaque(true);
					texts[i].setEditable(true);
				}
				
				edit.setEnabled(false);
				save.setEnabled(true);
				difficulty.setEnabled(true);

				
				description.setEditable(true);
				
				description.setOpaque(true);
				jsp2.setOpaque(true);
				jsp2.getViewport().setOpaque(true);
			}
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < texts.length; i++) {
					texts[i].setOpaque(false);
					texts[i].setEditable(false);
				}
				
				edit.setEnabled(true);
				save.setEnabled(false);
				difficulty.setEnabled(false);
				

				description.setEditable(false);
				//设置背景透明
				description.setOpaque(false);
				jsp2.setOpaque(false);
				jsp2.getViewport().setOpaque(false);
				
				new Warning(jf,"版本提示","后续版本加入功能",true);
			}
		});
		
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Warning(jf,"版本提示","后续版本加入功能",true);
			}
		});
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}
		});
	}
	
	public void Init(String mode) {
		if (mode.equals("ordinal")) {
			start_NO = 1;
			current_NO = start_NO;
			
			ql.ordinal();
			question_list = ql.getQuestionList();
			
			int total = question_list.size();
			end_NO = total;
			
			jf.remove(jp3);
			jp3.setBorder(null);
			JRadioButton question_number;
			for (int i = 1; i <= total; i++) {
				question_number = new JRadioButton("" + i);
				question_number_group.add(question_number);
				question_number_list.add(question_number);
			}
			
			changeQuestion();

		} else if (mode.equals("random")) {
			
			jp1.remove(goto_target);
			
			start_NO = 1;
			int total = 100;
			end_NO = start_NO + total - 1;

			for (int i = start_NO; i <= end_NO; i++) {
				JRadioButton question_number = new JRadioButton("" + i);
				question_number.setFont(Index.Qfont_medium);
				question_number.setOpaque(false);
				question_number_group.add(question_number);
				question_number_list.add(question_number);
				jp3.add(question_number);
				question_number.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						current_NO = Integer.valueOf(question_number.getText());
						changeQuestion();
					}
				});
			}
			ql.random();
			question_list = ql.getQuestionList();
			current_NO = start_NO;
			changeQuestion();
			
		} else if (mode.equals("test")) {
			
			jp1.remove(goto_target);
			
			start_NO = 1;
			int total = 100;
			end_NO = start_NO + total - 1;

			for (int i = start_NO; i <= end_NO; i++) {
				JRadioButton question_number = new JRadioButton("" + i);
				question_number.setFont(Index.Qfont_medium);
				question_number.setOpaque(false);
				question_number_group.add(question_number);
				question_number_list.add(question_number);
				jp3.add(question_number);
				question_number.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						current_NO = Integer.valueOf(question_number.getText());
						changeQuestion();
					}
				});
			}
			ql.shuffle();
			question_list = ql.getQuestionList();
			current_NO = start_NO;
			changeQuestion();
			
		} else if (mode.equals("favourite")) {
			
		} else if (mode.equals("mistakes")) {
			
		}
	}
	
	private void changeQuestion() {
		NO.setText("题数：" + current_NO + " / " + end_NO);
		answer_status.setText("");
		int current_index = current_NO - 1;
		question_number_list.get(current_index).setSelected(true);
		type = question_list.get(current_index).type;
		
		if (type.equals(Question.Singel_5) || type.equals(Question.Singel_4)) {
			stem.setText("(ID:" + question_list.get(current_index).id + ")" + "(" + "单选" + ") "  + question_list.get(current_index).stem);
		} else if (type.equals(Question.Multiple_5) || type.equals(Question.Multiple_4)) {
			stem.setText("(ID:" + question_list.get(current_index).id + ")" + "(" + "多选" + ") "  + question_list.get(current_index).stem);
		} else if (type.equals(Question.Judge)) 
			stem.setText("(ID:" + question_list.get(current_index).id + ")" + "(" + "判断" + ") "  + question_list.get(current_index).stem);
		
		chap.setText(question_list.get(current_index).chap);
		section.setText(question_list.get(current_index).section);
		point.setText(question_list.get(current_index).point);
		description.setText(question_list.get(current_index).description);
		true_answer = question_list.get(current_index).answer;
		String diff = question_list.get(current_index).difficulty;
		switch (diff) {
			case "容易":{
				difficulty.setSelectedIndex(1);
				break;
			}
			case "中等":{
				difficulty.setSelectedIndex(2);
				break;
			}
			case "困难":{
				difficulty.setSelectedIndex(3);
				break;
			}
			default: {
				difficulty.setSelectedIndex(0);
			}
		}
		answer.setText("答案：");
		for (int i = 0; i < option_group.length; i++) {
			option_group[i].setSelected(false);
		}
		user_choice.clear();
		option_A.setIcon(unchosed_icon);
		option_B.setIcon(unchosed_icon);
		option_C.setIcon(unchosed_icon);
		option_D.setIcon(unchosed_icon);
		option_E.setIcon(unchosed_icon);
		option_A.setBackground(Index.BACKGROUND_EYESHIELD);
		option_B.setBackground(Index.BACKGROUND_EYESHIELD);
		option_C.setBackground(Index.BACKGROUND_EYESHIELD);
		option_D.setBackground(Index.BACKGROUND_EYESHIELD);
		option_E.setBackground(Index.BACKGROUND_EYESHIELD);
		show_answer.setEnabled(false);
		if (type.equals(Question.Singel_5)) {
			option_A.setText("  A、" + question_list.get(current_index).option_A);
			option_B.setText("  B、" + question_list.get(current_index).option_B);
			option_C.setText("  C、" + question_list.get(current_index).option_C);
			option_D.setText("  D、" + question_list.get(current_index).option_D);
			option_E.setText("  E、" + question_list.get(current_index).option_E);
			jp1.add(option_C);
			jp1.add(option_D);
			jp1.add(option_E);
			jp1.repaint();
		} else if (type.equals(Question.Singel_4)) {
			option_A.setText("  A、" + question_list.get(current_index).option_A);
			option_B.setText("  B、" + question_list.get(current_index).option_B);
			option_C.setText("  C、" + question_list.get(current_index).option_C);
			option_D.setText("  D、" + question_list.get(current_index).option_D);
			jp1.add(option_C);
			jp1.add(option_D);
			jp1.remove(option_E);
			jp1.repaint();
		} else if (type.equals(Question.Multiple_5)) {
			option_A.setText("  A、" + question_list.get(current_index).option_A);
			option_B.setText("  B、" + question_list.get(current_index).option_B);
			option_C.setText("  C、" + question_list.get(current_index).option_C);
			option_D.setText("  D、" + question_list.get(current_index).option_D);
			option_E.setText("  E、" + question_list.get(current_index).option_E);
			jp1.add(option_C);
			jp1.add(option_D);
			jp1.add(option_E);
			jp1.repaint();
		} else if (type.equals(Question.Multiple_4)) {
			option_A.setText("  A、" + question_list.get(current_index).option_A);
			option_B.setText("  B、" + question_list.get(current_index).option_B);
			option_C.setText("  C、" + question_list.get(current_index).option_C);
			option_D.setText("  D、" + question_list.get(current_index).option_D);
			jp1.add(option_C);
			jp1.add(option_D);
			jp1.remove(option_E);
			jp1.repaint();
		} else if (type.equals(Question.Judge)) {
			option_A.setText(question_list.get(current_index).option_A);
			option_B.setText(question_list.get(current_index).option_B);
			jp1.remove(option_C);
			jp1.remove(option_D);
			jp1.remove(option_E);
			jp1.repaint();
		}
		
		if (current_NO == 1) {
			prev.setEnabled(false);
		} else {
			prev.setEnabled(true);
		}
		
		if (current_NO == end_NO) {
			next.setEnabled(false);
		} else {
			next.setEnabled(true);
		}
	}
	
	private void changeOption(String option_string, JRadioButton option_button) {
		
		if (type.equals(Question.Singel_5) || type.equals(Question.Singel_4) || type.equals(Question.Judge)) {

			option_button.setIcon(chosed_icon);
			for (int i = 0; i < option_group.length; i++) {
				if (option_group[i] != option_button) {
					option_group[i].setIcon(unchosed_icon);
					option_group[i].setSelected(false);
				}
			}
			user_choice.clear();
			user_choice.add(option_string);

			show_answer.setEnabled(true);
			System.out.println(user_choice.toString());
			
		} else if (type.equals(Question.Multiple_5) || type.equals(Question.Multiple_4)) {
			
			if (option_button.isSelected()) {
				option_button.setIcon(multichosed_icon);
				user_choice.add(option_string);
			} else {
				option_button.setIcon(unchosed_icon);
				user_choice.remove(option_string);
			}
			show_answer.setEnabled(true);
			System.out.println(user_choice.toString());
		} 
	}

}

