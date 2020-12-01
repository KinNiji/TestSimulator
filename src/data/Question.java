package data;

import java.util.ArrayList;

public class Question {
	
	public static final String Singel_5 = new String("A1");
	public static final String Singel_4 = new String("A2");
//	public static final String Singel_3 = new String("A3");
//	public static final String Singel_2 = new String("A4");
	public static final String Multiple_5 = new String("B1");
	public static final String Multiple_4 = new String("B2");
//	public static final String Multiple_3 = new String("B3");
//	public static final String Multiple_2 = new String("B4");
	public static final String Judge = new String("C1");
	
	public int id;
	
	public String stem;
	
	public String option_A;
	public String option_B;
	public String option_C;
	public String option_D;
	public String option_E;
	public String[] option_group = {option_A,option_B,option_C,option_D,option_E};
	
	public String chap;
	public String section;
	public String point;
	public String description;
	public String difficulty;
	public String[] note_group = {chap,section,point,description,difficulty};
	
	public String answer;
	
	public String type;
	
	public Question(int id, String stem, String[] option_group, String answer, String type) {
		this(id, stem,option_group,null,answer,type);
	}
	
	public Question(int id, String stem, String[] option_group, String[] note_group, String answer, String type) {
		this.id = id;
		
		this.stem = stem;
		this.answer = answer;
		this.type = type;
		
		if (this.type.equals(Judge)) {
			option_A = "正确";
			option_B = "错误";
		} else if (this.type.equals(Singel_4) || this.type.equals(Multiple_4)){
			option_A = option_group[0];
			option_B = option_group[1];
			option_C = option_group[2];
			option_D = option_group[3];
		} else if (this.type.equals(Singel_5) || this.type.equals(Multiple_5)){
			option_A = option_group[0];
			option_B = option_group[1];
			option_C = option_group[2];
			option_D = option_group[3];
			option_E = option_group[4];
		}
		
		if (note_group != null) {
			chap = note_group[0];
			section = note_group[1];
			point = note_group[2];
			description = note_group[3];
			difficulty = note_group[4];
		}
	}
	
	public Question(int id, String stem, ArrayList<String> option_group, String[] note_group, String answer, String type) {
		this.id = id;
		
		this.stem = stem;
		this.answer = answer;
		this.type = type;
		
		if (this.type.equals(Judge)) {
			option_A = "正确";
			option_B = "错误";
		} else if (this.type.equals(Singel_4) || this.type.equals(Multiple_4)){
			option_A = option_group.get(0);
			option_B = option_group.get(1);
			option_C = option_group.get(2);
			option_D = option_group.get(3);
		} else if (this.type.equals(Singel_5) || this.type.equals(Multiple_5)){
			option_A = option_group.get(0);
			option_B = option_group.get(1);
			option_C = option_group.get(2);
			option_D = option_group.get(3);
			option_E = option_group.get(4);
		}
		
		if (note_group != null) {
			chap = note_group[0];
			section = note_group[1];
			point = note_group[2];
			description = note_group[3];
			difficulty = note_group[4];
		}
		
	}
}
