package swExpertAcademy;

import java.io.*;
import java.util.*;

public class D1224 { // 계산기3
	
	static int T = 10;
	static char[] infixOp, postfixOp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int i=0; i<T; i++) {
			br.readLine();
			infixOp = br.readLine().toCharArray();
			postfixOp = makePostfix(infixOp);
			Stack<Integer> operands = new Stack<>();
			
			for(char op: postfixOp) {
				if(op>='0' && op <= '9') {
					operands.push(op - '0');
					continue;
				}
				int n2 = operands.pop();
				int n1 = operands.pop();
				operands.push(calculate(n1, n2, op));
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(Integer.toString(i + 1));
            sb.append(" ");
            sb.append(Integer.toString(operands.pop()));
            sb.append("\n");

            bw.write(sb.toString());	
		}
		bw.flush();
		bw.close();
	}

	private static int calculate(int n1, int n2, char op) {
		if(op == '*') return n1 * n2;
		
		return n1 + n2;
	}

	private static char[] makePostfix(char[] infixOp) {
		StringBuilder postfixOp = new StringBuilder();
		Stack<Character> operators = new Stack<>();
		
		for(char op : infixOp) {
			if (op >= '0' && op <='9') {
				postfixOp.append(op);
				continue;
			}
			if(operators.isEmpty() || op == '(') {
				operators.push(op);
				continue;
			}
			while(!operators.isEmpty()) {
				if(op != ')') {
					int priorityGap = getPriority(operators.peek()) - getPriority(op);
					if (priorityGap < 0) break;
				}
				
				char operator = operators.pop();
				if (operator == '(') break;
				postfixOp.append(operator);
			}
			if (op == ')') continue;
			operators.push(op);
		}
		while (!operators.isEmpty()) {
			postfixOp.append(operators.pop());
        }
			
		return postfixOp.toString().toCharArray();
	}
	


	private static int getPriority(char operator) {
		int priority = 0;

	    if (operator == '*' || operator == '/') return 1;
	        

	    if (operator == '(') return -1;
	        
	    return priority;
	 }

}