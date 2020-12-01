package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sql.util.JDBCUtils;
import ui.SqlInit;

public class BankList {
	
	public static ArrayList<String> getTableList() {
		
		ArrayList<String> table_list = new ArrayList<String>();
		
		String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema='test_simulator'";
		Connection connection = SqlInit.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);

			ResultSet set = statement.executeQuery();
			
			while (set.next()) {
				table_list.add(set.getString(1));
			}
			
			JDBCUtils.close(set, statement, null);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return table_list;
	}
}
