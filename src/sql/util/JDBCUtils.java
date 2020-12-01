package sql.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ui.Warning;

public class JDBCUtils {	
	static{
		try {
			//1.注册驱动
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("Error loading MySQL Driver");
			new Warning(null,"Error","Error loading MySQL Driver",true);
			throw new RuntimeException(e);
		}
	}
	/**
	 * 功能：获取可用的连接对象
	 * @return 连接
	 * @throws Exception
	 */
	
	public static Connection getConnection(String url, String username, String password){
		
		try {
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println("Error getting MySQL connection");
			new Warning(null,"Error","Error getting MySQL connection",true);
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 功能：释放资源
	 * @param set
	 * @param statement
	 * @param connection
	 * @throws Exception
	 */
	public static void close(ResultSet set,Statement statement,Connection connection){
		try {
			if (set!=null) {
				set.close();
			}
			if (statement!=null) {
				statement.close();
			}
			if (connection!=null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Error closing MySQL connection");
			new Warning(null,"Error","Error getting MySQL connection",true);
			throw new RuntimeException(e);
		}
	}
	
	

}