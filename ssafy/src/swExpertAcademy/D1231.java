package swExpertAcademy;

import java.io.*;
import java.util.*;

public class D1231 { // 중위순회 
	
	static String[] ans;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int t=1; t<=10; t++) {
			N = Integer.parseInt(br.readLine());
			ans = new String[N+1];
	
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				ans[Integer.parseInt(st.nextToken())] = st.nextToken();
						
			}
			System.out.printf("#%d ", t);
			inOrder(1);
			System.out.println();
		}

	}

	private static void inOrder(int i) {
		if(i*2 <= N) inOrder(i*2); // 왼쪽 자식 확인 
		System.out.print(ans[i]);
		if(i*2+1<=N) inOrder(i*2+1); // 오른쪽 자식 확인 
		
	}

}
