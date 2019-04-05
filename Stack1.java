package STACKQUEUES;
import LINKEDLIST.LinkedList;
import LINKEDLIST.Node;

public class Stack1 {
	LinkedList list;
	Node head;
	String top;
	
	public Stack1() {
		list = new LinkedList();
	}
	
	public boolean isEmpty() {
		if (list.size() == 0)
			return true;
		return false;
	}
	
	public String top() {
		return list.getHead();
	}
	
	public void push(String n) {
		list.addToFront(n);
	}
	
	public String pop() {
		String temp = list.getHead();
		list.remove1();
		return temp;
	}
	
	public void display() {
		list.display();
	}
	
	public int size() {
		return list.size();
	}
}
