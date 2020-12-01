package other;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import data.Question;
import sql.util.JDBCUtils;

public class Convert {
	
	public static final String Singel_5 = new String("A1");
	public static final String Singel_4 = new String("A2");
//	public static final String Singel_3 = new String("A3");
//	public static final String Singel_2 = new String("A4");
	public static final String Multiple_5 = new String("B1");
	public static final String Multiple_4 = new String("B2");
//	public static final String Multiple_3 = new String("B3");
//	public static final String Multiple_2 = new String("B4");
	public static final String Judge = new String("C1");
	
	public static void convert() throws Exception {
		
		Scanner console = new Scanner(Paths.get("import_test.txt"), StandardCharsets.UTF_8.name());
		String raw = console.useDelimiter("\\A").next();
		String raw_without_space = replaceBlank(raw);

		String[] chapTitles = {"绪论马克思主义是关于无产阶级和人类解放的科学","第一章世界的物质性及其发展规律","第二章认识世界和改造世界","第三章人类社会及其发展规律","第四章资本主义的形成及其本质"};
		
		System.out.println("chap_string:");
		
		String[] chapStrings = getChap(raw_without_space,chapTitles);
		
		System.out.println("processed:");
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_simulator", "root", "149109");
		
		String sql = "INSERT INTO `basic_principle_of_marxism` (`ID`, `章`, `节`, `知识点`, `题目描述`, `难度`, `题干`, `选择A`, `选择B`, `选择C`, `选择D`, `选择E`, `答案`, `题型`)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
		PreparedStatement statement = connection.prepareStatement(sql);
		
		for (int i = 0; i < chapStrings.length; i++) {
			
			ArrayList<Question> chap_processed = process(chapStrings[i],chapTitles[i]);
			
			for (int j = 0; j < chap_processed.size(); j++) {
				statement.setInt(1, chap_processed.get(j).id);
				statement.setString(2, chap_processed.get(j).chap);
				statement.setString(3, chap_processed.get(j).section);
				statement.setString(4, chap_processed.get(j).point);
				statement.setString(5, chap_processed.get(j).description);
				statement.setString(6, chap_processed.get(j).difficulty);
				statement.setString(7, chap_processed.get(j).stem);
				String type = chap_processed.get(j).type;
				if (type.equals(Singel_5)  || type.equals(Multiple_5)) {
					statement.setString(8, chap_processed.get(j).option_A);
					statement.setString(9, chap_processed.get(j).option_B);
					statement.setString(10, chap_processed.get(j).option_C);
					statement.setString(11, chap_processed.get(j).option_D);
					statement.setString(12, chap_processed.get(j).option_E);
				} else if (type.equals(Singel_4) || type.equals(Multiple_4)) {
					statement.setString(8, chap_processed.get(j).option_A);
					statement.setString(9, chap_processed.get(j).option_B);
					statement.setString(10, chap_processed.get(j).option_C);
					statement.setString(11, chap_processed.get(j).option_D);
					statement.setString(12, "NULL");
		
				} else if (type.equals(Judge)) {
					statement.setString(8, "NULL");
					statement.setString(9, "NULL");
					statement.setString(10, "NULL");
					statement.setString(11, "NULL");
					statement.setString(12, "NULL");
				} 
				
				statement.setString(13, chap_processed.get(j).answer);
				statement.setString(14, chap_processed.get(j).type);
				statement.executeUpdate();
			}
			
		}
		
		JDBCUtils.close(null, statement, connection);
		System.out.println("导入数据库成功");
	}
	
	private static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
	
	@SuppressWarnings("resource")
	private static String[] getChap(String raw_without_space, String[] chapTitles) {
		String[] chapStrings = new String[chapTitles.length];
		Scanner scanner = new Scanner(raw_without_space);
		String pattern_string = "(";
		
		pattern_string += chapTitles[0];
		for (int i = 1; i < chapTitles.length; i++) {
			pattern_string += ("|" + chapTitles[i]);
		}
		pattern_string += ")";
		Pattern pattern = Pattern.compile(pattern_string);
		scanner.useDelimiter(pattern);
		
		scanner.next();
		
		for (int i = 0; i < chapStrings.length; i++) {
			chapStrings[i] = scanner.next();
			System.out.println(chapStrings[i]);
		}
		
		return chapStrings;
	}
	

	@SuppressWarnings("resource")
	private static ArrayList<Question> process(String chapString, String chapTitle){
		
		ArrayList<Question> processed = new ArrayList<Question>();
		
		Scanner number_scanner = new Scanner(chapString);
		Scanner bracket_scanner = null;
		Scanner option_scanner = null;
		Matcher answer_matcher = null;
		
		Pattern number_pattern = Pattern.compile("[0-9]+[、.．]");
		Pattern bracket_pattern = Pattern.compile("[\\(（][[A-E]|[a-e]]{0,5}[\\)）]");
		Pattern option_pattern = Pattern.compile("[A-E]+[、.．]");
		Pattern answer_pattern = Pattern.compile("[\\(（]([[A-E]|[a-e]]{1,5})[\\)）]");
		
		number_scanner.useDelimiter(number_pattern);
		
		for (int i = 0; number_scanner.hasNext(); i++) {
			
			String raw_question = number_scanner.next();
			String 	stem = null,
					answer = null,
					type = null;
			
			String[] note_group = {chapTitle,"","","","容易"};
			
			ArrayList<String> option_group = new ArrayList<String>();
			
			answer_matcher = answer_pattern.matcher(raw_question);
			if (answer_matcher.find()) {
				answer = answer_matcher.group(1).toUpperCase();
			}
			
			bracket_scanner = new Scanner(raw_question);
			bracket_scanner.useDelimiter(bracket_pattern);
			
			raw_question = bracket_scanner.next();
			while (bracket_scanner.hasNext()) {
				raw_question += "（    ）" + bracket_scanner.next();
			}
			
			option_scanner = new Scanner(raw_question);
			option_scanner.useDelimiter(option_pattern);
			
			stem = option_scanner.next();
			
			while (option_scanner.hasNext()) {
				option_group.add(option_scanner.next());
			}
			
			if (answer.length() == 1 && option_group.size() == 5) {
				type = Singel_5;
			} else if (answer.length() == 1 && option_group.size() == 4) {
				type = Singel_4;
			} else if (answer.length() > 1 && option_group.size() == 5) {
				type = Multiple_5;
			} else if (answer.length() > 1 && option_group.size() == 4) {
				type = Multiple_4;
			}
			
			System.out.println("第" + (i + 1) + "条记录： " + type);
			System.out.println("题干：" + stem);
			
			if (type.equals(Singel_5)  || type.equals(Multiple_5)) {
				System.out.println("A." + option_group.get(0));
				System.out.println("B." + option_group.get(1));
				System.out.println("C." + option_group.get(2));
				System.out.println("D." + option_group.get(3));
				System.out.println("E." + option_group.get(4));
			} else if (type.equals(Singel_4) || type.equals(Multiple_4)) {
				System.out.println("A." + option_group.get(0));
				System.out.println("B." + option_group.get(1));
				System.out.println("C." + option_group.get(2));
				System.out.println("D." + option_group.get(3));
	
			} else if (type.equals(Judge)) {
				System.out.println("A.正确");
				System.out.println("B.错误");
			}
			
			System.out.println("答案：" + answer);
			System.out.println();
			
			processed.add(new Question(0, stem, option_group, note_group, answer, type));
		}
		
		return processed;
	}
	
//	public static void main(String[] args) throws Exception {
//		Convert.convert();
//	}
	
}
