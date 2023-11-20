package swExpertAcademy;

import java.io.*;
import java.util.*;

public class D1486 { // 장훈이의 높은 선반 (부분집합) 
	
	static int T, N, B;
	static int[] H;
	static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			min = 0;
			
			H = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
				min += H[i];
			}
			min++;
			dfs(0,0);
			System.out.println("#"+tc+" "+(min-B));
		}
	}

	private static void dfs(int idx, int sum) {
		if(sum>=B) {
			min = Math.min(min, sum);
			return;
		}
		if(idx == N) {
			if(sum>=B) min = Math.min(min, sum);
			return;
		}
		dfs(idx+1, sum+H[idx]);
		dfs(idx+1, sum);
	}

}
