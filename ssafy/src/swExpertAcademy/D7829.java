package swExpertAcademy;

import java.io.*;
import java.util.*;

public class D7829 {
	
	static int T, res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder("");
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			int P = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int MAX = Integer.MIN_VALUE;
			int MIN = Integer.MAX_VALUE;
			int n;
			if(st.countTokens()==1) {
				n = Integer.parseInt(st.nextToken());
				res = n*n; 
			} else {
				for(int i=0; i<P; i++) {
					n = Integer.parseInt(st.nextToken());
					MAX = Math.max(MAX, n);
					MIN = Math.min(MIN, n);
				}
				res = MAX*MIN;
			}
			sb.append("#"+t+" "+res+"\n");
		}
		System.out.println(sb);
	}
}
