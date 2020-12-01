package data;

import java.util.Iterator;
import java.util.TreeSet;

public class Answer {
	
	private TreeSet<String> answer_set; 
	
	public Answer() {
		answer_set = new TreeSet<String>();
	}
	
	public void add(String option) {
		answer_set.add(option);
	}
	
	public void remove(String option) {
//		Iterator<String> it = answer_set.iterator();
//		while(it.hasNext()){
//			String str = it.next();
//			if(option.equals(str)){
//				it.remove();
//			}
//		}
		answer_set.remove(option);
	}
	
	public void clear() {
		answer_set.clear();
	}
	
	public String toString() {
		String option_string = "";
		Iterator<String> it = answer_set.iterator();
		while (it.hasNext()) {
			option_string += it.next();
		}
		
		return option_string;
	}
}
