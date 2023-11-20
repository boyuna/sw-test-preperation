package swExpertAcademy;

import java.io.*;
import java.util.*;

public class D1233 { // 사칙연산 유효성 검사 
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = 10;
		for(int t=1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int ans = 1;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				
				char node = st.nextToken().charAt(0);
				
				if(st.hasMoreTokens()) {
					// 숫자라면 불가능 
					if(node>='0' && node<='9') ans = 0;
					
				}
				else { // 단말노드일때 
					// 연산자라면 불가능
					if(node<'0' || node>'9') ans = 0;
				}	
			}
			sb.append("#" + t + " " + ans + "\n");
		}
		System.out.println(sb.toString());
	}

}
