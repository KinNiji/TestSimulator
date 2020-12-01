package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import sql.LoginCheck;

public class Login {
	
	private JFrame jf = new JFrame("模拟题库ByNiji_demo");
	
	private JPanel jp1 = new JPanel();
	
	private JButton sql_connection = new JButton("连接到SQL主机");
	
	private JLabel jl0 = new JLabel("连接SQL");
	private JLabel jl1 = new JLabel("用户名");
	private JLabel jl2 = new JLabel("密 码");
	
	private JTextField username = new JTextField("root");
	private JPasswordField password = new JPasswordField("");
	
	private JButton jb1 = new JButton("登陆");
	private JButton jb2 = new JButton("测试");
	private JButton confirm = new JButton("确认");
	
	private JLabel infoLab = new JLabel("请先连接到SQL主机");
	
	private boolean login_flag = false;
	
	public Login() {
		//设置窗口显示尺寸
		jf.setSize(390, 250);
		//将窗口置于屏幕的中央
		jf.setLocationRelativeTo(null);
		//使窗口大小无法调节
		jf.setResizable(false);
		
		// 设置窗体背景颜色
		jp1.setBackground(Index.BACKGROUND);
		//将布局设为绝对布局
		jp1.setLayout(null);
		
		// 设置字体
		sql_connection.setFont(Index.t2);
		 
		jl0.setFont(Index.t2);
		jl1.setFont(Index.t2); 
		jl2.setFont(Index.t2); 
		
		jb1.setFont(Index.t2); 
		jb2.setFont(Index.t2);
		
		infoLab.setFont(Index.t2);
		
		confirm.setFont(Index.t2);
		
		// 通过坐标来确定标签和按钮的位置
		int line_height = 30,
			jb_width = 70,
			jl_width = 75,
			jt_width = 180,
			height_gap = 10,
			width_gap = 5,
			line1h = 15,
			line2h = line1h + line_height + height_gap,
			line3h = line2h + line_height + height_gap,
			line4h = line3h + line_height + height_gap,
			line5h = line4h + line_height + height_gap,
			column1w = 20,
			column2w = column1w + jl_width + width_gap,
			column3w = column2w + jt_width + width_gap,
			special_width = jt_width + jb_width + width_gap;
		
		sql_connection.setBounds(column2w,line1h,special_width,line_height);
		
		jl0.setBounds(column1w,line1h,jl_width,line_height);
		jl1.setBounds(column1w,line2h,jl_width,line_height);
		jl2.setBounds(column1w,line3h,jl_width,line_height);
		
		username.setBounds(column2w,line2h,jt_width,line_height);
		password.setBounds(column2w,line3h,jt_width,line_height);
		
		
		jb1.setBounds(column3w,line2h,jb_width,line_height);
		jb2.setBounds(column3w,line3h,jb_width,line_height);
		confirm.setBounds(column3w,line5h,jb_width,line_height);
		
		infoLab.setBounds(column1w,line5h,column2w + jt_width - column1w,line_height);
		
		// 添加组件
		jp1.add(jl0);
		jp1.add(sql_connection);
		
		jp1.add(jl1);
		jp1.add(username);
		jp1.add(jb1);

		jp1.add(jl2);
		jp1.add(password);
		jp1.add(jb2);
		
		jp1.add(infoLab);
		
		jf.add(jp1);
		
		//禁用登录，直到连接到sql主机后解禁
		jb1.setEnabled(login_flag);
		//jb2.setEnabled(login_flag);
		username.setEnabled(login_flag);
		password.setEnabled(login_flag);
		
		jf.add(jp1);
		
		// 事件处理
		InitEvent();
		
		//设置窗口可见
		jf.setVisible(true);
		//设置窗口可以关闭
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public void InitEvent() {
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Exercise("", "");
			}
		});
		
		sql_connection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new SqlInit(jf);
			}
		});
		
		jf.addWindowListener(new WindowAdapter() {
		    public void windowActivated(WindowEvent e){
		    	login_flag = SqlInit.getConnectFlag();
		    	if(login_flag) {
					jb1.setEnabled(true);
					jb2.setEnabled(true);
					username.setEnabled(true);
					password.setEnabled(true);
					
					sql_connection.setEnabled(false);
					sql_connection.setText("已连接");

					infoLab.setText("请输入用户名和密码");
					
				}
		    }
		});
			
		
		// 监听登录按钮事件
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == jb1){
					// 获取名字框的文本
					String tname = username.getText();
					// 获取密码框的文本
					String tpass = new String(password.getPassword());
					// 检查用户名和密码
					LoginCheck log = new LoginCheck(tname, tpass);
					if (log.ispass()){
						// 设置提示标签
						infoLab.setText("登陆成功，欢迎使用题库！");
						jb1.setEnabled(false);
						jb2.setEnabled(false);
						username.setEnabled(false);
						password.setEnabled(false);
						jp1.add(confirm);
						jp1.repaint();
						LodinConfirmEvent();
					} else {
						infoLab.setText("登陆失败，请检查用户名或密码！");
					}
				}
			}
		});
	}
	
	public void LodinConfirmEvent() {
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ModeChoose();
				jf.dispose();
			}
		});
	}
}
