package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sql.util.JDBCUtils;
import ui.SqlInit;

public class LoginCheck {
	private String username;
	private String password;
	
	public LoginCheck(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public boolean ispass() {
		String sql = "SELECT COUNT(*) FROM admin WHERE username=?  AND password=?";
		Connection connection = SqlInit.getConnection();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet set = statement.executeQuery();
			
			if (set.next()) {
				int count = set.getInt(1);
				if (count>0) {
					return true;
				}
			}
			
			JDBCUtils.close(set, statement, null);
			
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
