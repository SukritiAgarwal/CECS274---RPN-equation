package STACKQUEUES;
/* Name: Sukriti Agarwal
 * Class: CECS 274 - 05
 * Project Name: Prog 5 - RPN Equation (Stacks and Queues)
 * Due Date: April 19, 2018
 */

import java.util.Scanner;

public class Parser {
	static Stack1 myStack = new Stack1();
	public static void main(String[] args) {
		Stack1 stack = new Stack1();
		Queue1 queue = new Queue1();
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your equation: ");
		String expr = in.nextLine();
		in.close();
		String exprCopy =  expr;
		
		exprCopy = exprCopy.replaceAll("\\+", " + ");
		exprCopy = exprCopy.replaceAll("\\-", " - ");
		exprCopy = exprCopy.replaceAll("\\*", " * ");
		exprCopy = exprCopy.replaceAll("\\/", " / ");
		exprCopy = exprCopy.replaceAll("\\(", " ( ");
		exprCopy = exprCopy.replaceAll("\\)", " ) ");
		exprCopy = exprCopy.replaceAll("\\^", " ^ ");
		
		String delims = "[ ]+" ;
		String[] token = exprCopy.split(delims);
		int i = 0;
		if(expr.charAt(0) == '(' || expr.charAt(0) == '+' || expr.charAt(0) == '-' || expr.charAt(0) == '*' || expr.charAt(0) == '/' || expr.charAt(0) == '^')
			i = 1;
		for( ; i < token.length ; i++) {
			if (token[i].compareTo("^") == 0 || token[i].compareTo("(") == 0)
				stack.push(token[i]);
			
			else if(token[i].compareTo("*") == 0 || token[i].compareTo("/") == 0) {	
				if(stack.isEmpty())
					stack.push(token[i]);
				else if(stack.top().compareTo("*") == 0 || stack.top().compareTo("/") == 0) {
					do
						queue.push(stack.pop());
					while(!stack.isEmpty() && (stack.top().compareTo("*") == 0 || stack.top().compareTo("/") == 0));
						stack.push(token[i]);
				}
				else {
					if(stack.top().compareTo("^") != 0)
						stack.push(token[i]);
					else {
						queue.push(stack.pop());
						stack.push(token[i]);
					}
				}
			}
			else if(token[i].compareTo("+") == 0 || token[i].compareTo("-") == 0) {
				if(stack.isEmpty())
					stack.push(token[i]);
				else {
					while(!stack.isEmpty() && stack.top().compareTo("(") != 0) {
						queue.push(stack.pop());
					}
					stack.push(token[i]);
				}
			}
			else if (token[i].compareTo(")") == 0) {
				while(stack.top().compareTo("(") != 0) 
					queue.push(stack.pop());
				stack.pop();
			}
			else
				queue.push(token[i]);
		}

		while(!stack.isEmpty()) 
			queue.push(stack.pop());
		
		System.out.print("RPN: ");
		queue.display();
		
		System.out.println();
		
		System.out.print("Answer: ");
		//if(checkEquation(expr))
			solve(queue);
		//System.out.println("Invalid.");
	}
	
	public static void solve(Queue1 myQueue) {
		double value = 0;
		String operators="+-*/^";
		for (int i=0; i<myQueue.size(); i++) {
			if (!operators.contains(myQueue.get(i))) { 
				myStack.push(myQueue.get(i));
			} 
			else {
				double a = Double.valueOf(myStack.pop());
				double b = Double.valueOf(myStack.pop());
				switch (myQueue.get(i)) {
					case "+":
						myStack.push(String.valueOf(b + a));
						break;
					case "-":
						myStack.push(String.valueOf(b - a));	
						break;
					case "*":
						myStack.push(String.valueOf(b * a));	
						break;
					case "/":
						myStack.push(String.valueOf(b / a));
						break;
					case "^":					
						myStack.push(String.valueOf(Math.pow(b, a)));
						break;
				}
			}
		}
		value=Double.valueOf(myStack.pop());
		System.out.printf("%.2f",value);	
	}
}