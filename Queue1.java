package STACKQUEUES;
import java.util.ArrayList;

public class Queue1 {
	public ArrayList<String> list;

	public Queue1(){
		list = new ArrayList<>();
	}
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public void push(String n){
		list.add(n);
	}
	
	public String pop(){
		String temp2 = list.get(0);
		list.remove(0);
		return temp2;
	}
	
	public String get(int i) {
		String x=list.get(i);
		return x;
	}
	
	public int size() {
		return list.size();
	}
	
	public void display() {
		for(int i=0; i < list.size(); i++)
			System.out.print(list.get(i) + " ");
	}
	
	public static boolean isNum(String s) {
		try {
			double num=Double.parseDouble(s);
		}
		catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
}
