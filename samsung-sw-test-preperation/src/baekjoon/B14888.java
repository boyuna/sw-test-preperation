package baekjoon;

import java.io.*;
import java.util.*;

public class B14888 {
	
	static int N;
	static int[] A;
	static int[] op = new int[4]; // +, -, *, /
	static int MAX = Integer.MIN_VALUE;
	static int MIN = Integer.MAX_VALUE;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		// 입력완료 
		makeCal(A[0], 1);
		System.out.println(MAX);
		System.out.println(MIN);

	}
	
	private static void makeCal(int n, int idx) {
		if(idx == N) {
			MAX = Math.max(MAX, n);
			MIN = Math.min(MIN, n);
		}
		
		for(int i=0; i<4; i++) {
			if(op[i] > 0) {
				op[i]--;
				
				switch(i) {
				case 0: makeCal(n + A[idx], idx+1); break;
				case 1: makeCal(n - A[idx], idx+1); break;
				case 2: makeCal(n * A[idx], idx+1); break;
				case 3: makeCal(n / A[idx], idx+1); break;
				}
				op[i]++; // 재귀호출 종료이므로 다시 연산자 개수 복구 
			}
			
		}
		
	}

}
