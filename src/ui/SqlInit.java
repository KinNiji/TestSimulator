package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import data.util.TestUtils;
import sql.util.JDBCUtils;
import ui.util.UIUtils;

public class SqlInit {
	
	private Properties info  = new Properties();
	private String properties_path = "profiles/data/sql.properties";
	
	private int 
				line_height = 20,
				jb_width = 70,
				jl_width = 100,
				jt_width = 180,
				height_gap = 10,
				width_gap = 5,
				line1h = 10,
				line2h = line1h + line_height + height_gap,
				line3h = line2h + line_height + height_gap,
				line4h = line3h + line_height + height_gap,
				column1w = 7,
				column2w = column1w + jb_width + width_gap,
				column3w = column2w + jb_width + width_gap,
				column4w = column3w + jb_width + width_gap,
				column5w = column4w + jb_width + width_gap,
				column3w_special = column2w + jt_width + width_gap,
				
				panel1h = line2h + line_height + height_gap,
				panel2h = line4h + line_height + height_gap + 25,
				panel3h = line1h + line_height + height_gap,
				panel1_width = column5w + jb_width + width_gap,
				panel2_width = column5w + jb_width + width_gap - 12,
				panel3_width = column5w + jb_width + width_gap
				;
	
	private JFrame fake = null;
	
	private JDialog jd =  new JDialog(fake,"连接到SQL主机",true);
	
	private JPanel jp1 = new JPanel();
	
	private JButton jb1 = new JButton("新建");
	private JButton jb2 = new JButton("克隆");
	private JButton jb3 = new JButton("重命名");
	private JButton jb4 = new JButton("保存");
	private JButton jb5 = new JButton("删除");
	
	private JLabel jl0 = new JLabel("保存的连接",JLabel.CENTER);
	private JComboBox<String> jcb = new JComboBox<String>();
	
	private JTabbedPane jtb = new JTabbedPane();
	
	private JPanel MySQL = new JPanel();

	private JLabel jl1 = new JLabel("SQL主机地址");
	private JLabel jl2 = new JLabel("用户名");
	private JLabel jl3 = new JLabel("密码");
	private JLabel jl4 = new JLabel("端口");
	
	private JTextField hostaddress = new JTextField("localhost");
	private JTextField username = new JTextField("root");
	private JPasswordField password = new JPasswordField();
	private JTextField port = new JTextField("3306");
	
	
	private JButton initialize = new JButton("导入数据");
	private JRadioButton remember_password = new JRadioButton("记住密码");
	private JButton connect_test = new JButton("测试连接");
	
	private JPanel jp2 = new JPanel();
	
	private JButton connect = new JButton("连接");
	private JButton cancel = new JButton("取消");
	
	private JPanel HTTP = new JPanel();
	private JPanel SSH = new JPanel();
	private JPanel SSL = new JPanel();
	private JPanel proConfig = new JPanel();
	
	private JLabel unfinished1 = new JLabel("UNFINISHED");
	private JLabel unfinished2 = new JLabel("UNFINISHED");
	private JLabel unfinished3 = new JLabel("UNFINISHED");
	private JLabel unfinished4 = new JLabel("UNFINISHED");
	private boolean test_connect_flag = false;
	private static boolean connect_flag = false;
	private boolean properties_read_flag = false;
	
	private static Connection connection;
	
	public SqlInit(JFrame father) {
		//设置窗口显示尺寸
		jd.setSize(400, 305); 
		//将窗口置于屏幕的中央
		jd.setLocationRelativeTo(father);
		//使窗口大小无法调节
		jd.setResizable(false);
		//设置标题
		jd.setTitle("连接到SQL主机");
		
		//将布局设为绝对布局，方便直接通过坐标来放label和button
		jd.setLayout(null);
		jp1.setLayout(null);
		jp2.setLayout(null);
		MySQL.setLayout(null);
		//设置背景颜色
		jd.setBackground(Index.BACKGROUND);
		jp1.setBackground(Index.BACKGROUND);
		jp2.setBackground(Index.BACKGROUND);
		jtb.setBackground(Index.BACKGROUND);
		MySQL.setBackground(Index.BACKGROUND);
		HTTP.setBackground(Index.BACKGROUND);
		SSH.setBackground(Index.BACKGROUND);
		SSL.setBackground(Index.BACKGROUND);
		proConfig.setBackground(Index.BACKGROUND);
		
		//设置字体
		jb1.setFont(Index.t5);
		jb2.setFont(Index.t5);
		jb3.setFont(Index.t5);
		jb4.setFont(Index.t5);
		jb5.setFont(Index.t5);
		
		jl0.setFont(Index.t5);
		jcb.setFont(Index.t5);
		
		jl1.setFont(Index.t5);
		jl2.setFont(Index.t5);
		jl3.setFont(Index.t5);
		jl4.setFont(Index.t5);
		
//		hostaddress.setFont(t5);
//		username.setFont(t5);
//		password.setFont(t5);
//		port.setFont(t5);
		
		initialize.setFont(Index.t5);
		remember_password.setFont(Index.t5);
		connect_test.setFont(Index.t5);
		
		connect.setFont(Index.t5);
		cancel.setFont(Index.t5);
		
		unfinished1.setFont(Index.t5);
		unfinished2.setFont(Index.t5);
		unfinished3.setFont(Index.t5);
		unfinished4.setFont(Index.t5);
		
		initialize.setForeground(Index.WARNING);
		
		//设定绝对位置
		
		jp1.setBounds(0,0,panel1_width,panel1h);
		
		jb1.setBounds(column1w,line1h,jb_width,line_height);
		jb2.setBounds(column2w,line1h,jb_width,line_height);
		jb3.setBounds(column3w,line1h,jb_width,line_height);
		jb4.setBounds(column4w,line1h,jb_width,line_height);
		jb5.setBounds(column5w,line1h,jb_width,line_height);
		
		jl0.setBounds(column1w,line2h,jb_width,line_height);
		jcb.setBounds(column2w,line2h,4 * jb_width + 3 * width_gap,line_height);
		
		jtb.setBounds(column1w,panel1h,panel2_width,panel2h);
		
		jl1.setBounds(column1w,line1h,jl_width,line_height);
		jl2.setBounds(column1w,line2h,jl_width,line_height);
		jl3.setBounds(column1w,line3h,jl_width,line_height);
		jl4.setBounds(column1w,line4h,jl_width,line_height);
		
		hostaddress.setBounds(column2w,line1h,jt_width,line_height);
		username.setBounds(column2w,line2h,jt_width,line_height);
		password.setBounds(column2w,line3h,jt_width,line_height);
		port.setBounds(column2w,line4h,jt_width,line_height);
		
		initialize.setBounds(column3w_special,line1h,jl_width-10,line_height);
		remember_password.setBounds(column3w_special,line3h,jl_width-10,line_height);
		connect_test.setBounds(column3w_special,line4h,jl_width-10,line_height);
		
		jp2.setBounds(0,panel1h + panel2h,panel3_width,panel3h);
		
		connect.setBounds(column4w,line1h,jb_width,line_height);
		cancel.setBounds(column5w,line1h,jb_width,line_height);
		
		
		//禁用组件
		jb4.setEnabled(false);
		initialize.setEnabled(false);
		
		//properties文件读取
		try {
			info.load(new FileInputStream(properties_path));
			properties_read_flag = true;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//初始化文本域内容
		if (properties_read_flag) {
			hostaddress.setText(info.getProperty("hostaddress"));
			username.setText(info.getProperty("username"));
			port.setText(info.getProperty("port"));
			if (TestUtils.getRememberPasswordFlag()) {
				password.setText(info.getProperty("password"));
				remember_password.setIcon(UIUtils.change(Index.chosed_icon,line_height,line_height));
				remember_password.setSelected(true);
			} else {
				remember_password.setIcon(UIUtils.change(Index.unchosed_icon,line_height,line_height));
			}
		}

		
		//添加各个组件
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
		jp1.add(jb5);
		jp1.add(jl0);
		jp1.add(jcb);
		
		MySQL.add(jl1);
		MySQL.add(jl2);
		MySQL.add(jl3);
		MySQL.add(jl4);
		MySQL.add(hostaddress);
		MySQL.add(username);
		MySQL.add(password);
		MySQL.add(port);
		MySQL.add(initialize);
		MySQL.add(remember_password);
		MySQL.add(connect_test);
		
		//未完成内容
		HTTP.add(unfinished1);
		SSH.add(unfinished2);
		SSL.add(unfinished3);
		proConfig.add(unfinished4);
		
		jp2.add(connect);
		jp2.add(cancel);
		
		jtb.add(MySQL,"MySQL");
		jtb.add(HTTP,"HTTP");
		jtb.add(SSH,"SSH");
		jtb.add(SSL,"SSL");
		jtb.add(proConfig,"高级配置");
		
		jd.add(jp1);
		jd.add(jtb);
		jd.add(jp2);
		
		jd.setFocusable(true);
		
		Event();
		
		//未完成内容
		JButton[] unf = {jb1,jb2,jb3,jb4,jb5};
		for (JButton c : unf) {
			c.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Warning(null,"版本提示","后续版本加入功能",true);
				}
			});
		}
		
		//设置窗口可见
		jd.setVisible(true);
		//设置窗口可以关闭
		jd.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE); 
		
	}
	
	public void Event() {
		
		jd.addWindowListener(new WindowAdapter() {
		    public void windowActivated(WindowEvent e){
		    	if (test_connect_flag) {
		    		initialize.setEnabled(true);
		    	}
		    }
		});
		
		initialize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cmd = "mysql -hlocalhost -P3306 -uroot -p149109 test_simulator < ";
				
				File file = new File("profiles/data/backup.sql");   
				String filePath = file.getAbsolutePath();  
				
				cmd += filePath;
				initialize.setEnabled(false);
				test_connect_flag = false;
				
				new Warning(null,"复制该段命令至cmd",cmd,true);
				
//				cmdTask ct = new cmdTask(cmd);
//				ct.run();
			}
		});
		
		remember_password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (remember_password.isSelected()) {
					remember_password.setIcon(UIUtils.change(Index.chosed_icon,line_height,line_height));
				} else {
					remember_password.setIcon(UIUtils.change(Index.unchosed_icon,line_height,line_height));
				}
			}
		});
		
		connect_test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ha = hostaddress.getText();
				String un = username.getText();
				String pw = new String(password.getPassword());
				String p = port.getText();
				
				String url = "jdbc:mysql://" + ha + ":" + p + "/test_simulator";
				Connection connection = JDBCUtils.getConnection(url, un, pw);
				
				String sql = "select version()";
				Statement statement;
				try {
					statement = connection.createStatement();
					ResultSet set = statement.executeQuery(sql);
					
					set.next();

					String version = set.getString(1);
					
					JDBCUtils.close(set, statement, connection);
					
					test_connect_flag = true;
					
					new Warning(null,"连接信息","连接成功！MySQL version: " + version,true);
					
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ha = hostaddress.getText();
				String un = username.getText();
				String pw = new String(password.getPassword());
				String p = port.getText();
				
				String url = "jdbc:mysql://" + ha + ":" + p + "/test_simulator";
				connection = JDBCUtils.getConnection(url, un, pw);
				
				connect_flag = true;
				
				if (properties_read_flag) {
					info.setProperty("hostaddress", ha);
					info.setProperty("username", un);
					info.setProperty("password", pw);
					info.setProperty("port", p);
					info.setProperty("remember_password", String.valueOf(remember_password.isSelected()));
					
		            FileOutputStream fos;
					try {
						//true表示追加,false会将原文件清空后,重新添加
						fos = new FileOutputStream(properties_path,false);
						//引入Writer,可以明确该输出流的字符集,确保写入配置文件的中文编码正确
						OutputStreamWriter opw = new OutputStreamWriter(fos,"utf-8");
			            info.store(opw,"sql_info");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            
					
				}
				jd.dispose();
				
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("SQL连接窗口退出");
				jd.dispose();
			}
		});
		
//		jf.addFocusListener(new FocusListener() {
//			@Override
//			public void focusGained(FocusEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void focusLost(FocusEvent e) {
//				// TODO Auto-generated method stub
//				jf.toFront();
//			}
//		});
	}
	
	public static boolean getConnectFlag() {
		return connect_flag;
	}
	
	public static Connection getConnection() {
		return connection;
	}
}
