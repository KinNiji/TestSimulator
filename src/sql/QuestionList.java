package sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import data.Question;
import data.util.ArrayUtils;
import sql.util.JDBCUtils;
import ui.SqlInit;

public class QuestionList {
	 
	private ArrayList<Question> question_list;
	
	private Connection connection = SqlInit.getConnection();
	private String sql;
	
	public QuestionList(String table) {
		question_list = new ArrayList<Question>();
		sql = "SELECT * FROM `"+ table +"`";
	}
	
	public ArrayList<Question> getQuestionList() {
		return question_list;
	}
	
	public void ordinal() {
		read();
	}
	
	public void random() {
		read();
		Collections.shuffle(question_list);
	}
	
	public void shuffle() {
		Statement statement = null;
		ResultSet set = null;
		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);
			
			while (set.next()) {
				int id = set.getInt(1);
				
				String chap = set.getString(2);
				String section = set.getString(3);
				String point = set.getString(4);
				String description = set.getString(5);
				String difficulty = set.getString(6);
				String[] note_group = {chap,section,point,description,difficulty};
				
				String stem = set.getString(7);
				
				String option_A = set.getString(8);
				String option_B = set.getString(9);
				String option_C = set.getString(10);
				String option_D = set.getString(11);
				String option_E = set.getString(12);
				
				String answer = set.getString(13);
				
				String type = new String(set.getString(14));
				
				String true_answer = "";
				switch (answer) {
					case "A":{
						true_answer = option_A;
						break;
					}
					case "B":{
						true_answer = option_B;
						break;					
					}
					case "C":{
						true_answer = option_C;
						break;
					}
					case "D":{
						true_answer = option_D;
						break;
					}
					case "E":{
						true_answer = option_E;
						break;
					}
				}
				
				if (type.equals(Question.Singel_5) || type.equals(Question.Multiple_5)) {
					
					String[] option_group_shuffled = {option_A,option_B,option_C,option_D,option_E};
					ArrayUtils.shuffle(option_group_shuffled);
					int i;
					for (i = 0; i < option_group_shuffled.length; i++) {
						if (option_group_shuffled[i].equals(true_answer)) {
							break;
						}
					}
					switch (i) {
						case 0:{
							answer = "A";
							break;
						}
						case 1:{
							answer = "B";
							break;					
						}
						case 2:{
							answer = "C";
							break;
						}
						case 3:{
							answer = "D";
							break;
						}
						case 4:{
							answer = "E";
							break;
						}
					} 
					
					question_list.add(new Question(id,stem,option_group_shuffled,note_group,answer,type));
					
					
				} else if (type.equals(Question.Singel_4) || type.equals(Question.Multiple_4)) {
					
					String[] option_group_shuffled = {option_A,option_B,option_C,option_D};
					ArrayUtils.shuffle(option_group_shuffled);
					int i;
					for (i = 0; i < option_group_shuffled.length; i++) {
						if (option_group_shuffled[i].equals(true_answer)) {
							break;
						}
					}
					switch (i) {
						case 0:{
							answer = "A";
							break;
						}
						case 1:{
							answer = "B";
							break;					
						}
						case 2:{
							answer = "C";
							break;
						}
						case 3:{
							answer = "D";
							break;
						}
					} 
					
					question_list.add(new Question(id,stem,option_group_shuffled,note_group,answer,type));
					
				} else if (type.equals(Question.Judge)) {
					
					String[] option_group = null;
					question_list.add(new Question(id,stem,option_group,note_group,answer,type));
					
				}
			}
			
			Collections.shuffle(question_list);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(set, statement, null);
		}
		

	}
	
	public void getSpecific(ArrayList<Integer> number_list) {
		
		

	}
	
	private void read() {
		Statement statement = null;
		ResultSet set = null;
		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);
			
			while (set.next()) {
				int id = set.getInt(1);
				
				String chap = set.getString(2);
				String section = set.getString(3);
				String point = set.getString(4);
				String description = set.getString(5);
				String difficulty = set.getString(6);
				String[] note_group = {chap,section,point,description,difficulty};
				
				String stem = set.getString(7);
				
				String option_A = set.getString(8);
				String option_B = set.getString(9);
				String option_C = set.getString(10);
				String option_D = set.getString(11);
				String option_E = set.getString(12);
				
				String answer = set.getString(13);
				
				String type = new String(set.getString(14));
				
				if (type.equals(Question.Singel_5) || type.equals(Question.Multiple_5)) {
					String[] option_group = {option_A,option_B,option_C,option_D,option_E};
					question_list.add(new Question(id,stem,option_group,note_group,answer,type));
					
				} else if (type.equals(Question.Singel_4) || type.equals(Question.Multiple_4)) {
					String[] option_group = {option_A,option_B,option_C,option_D};
					question_list.add(new Question(id,stem,option_group,note_group,answer,type));
					
				} else if (type.equals(Question.Judge)) {
					String[] option_group = null;
					question_list.add(new Question(id,stem,option_group,note_group,answer,type));
					
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(set, statement, null);
		}
	}
}
