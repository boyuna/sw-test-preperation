package swExpertAcademy;

import java.io.*;
import java.util.*;

public class D1219 { // 길찾기 
	
	static int[][] arr;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int testCase = 0; testCase < 10; testCase++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken()); // 길의 총 개수
			
			res = 0;
			arr = new int[100][2];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				int idx = Integer.parseInt(st.nextToken()); // 순서쌍 인덱스 
				int val = Integer.parseInt(st.nextToken()); // 순서쌍 값 
				
				if(arr[idx][0]!=0) arr[idx][1] = val;
				else arr[idx][0] = val;
			}
			
			dfs(arr[0][0]);
			dfs(arr[0][1]);
			System.out.println("#" + t + " " + res);
		}
		br.close();
	}

	private static void dfs(int idx) {
		
		if(idx==0) return;
		if(idx == 99) {
			res = 1;
			return;
		}
		dfs(arr[idx][0]);
		dfs(arr[idx][1]);
	}	
}
