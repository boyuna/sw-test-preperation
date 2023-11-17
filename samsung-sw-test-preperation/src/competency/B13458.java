package competency;

import java.io.*;
import java.util.*;

public class B13458 { // 시험감독 
	static int N, B, C;
	static int[] A;
	static int cnt; // 감독관 수 카운트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// 입력완료
	
		cnt += N;
		
		for(int i=0; i<N; i++) {
			A[i] -= B;
			if(A[i]<=0) continue;
			cnt += A[i]/C;
			if(A[i]%C!=0) cnt++;
			
		}
		System.out.print(cnt);
	}
	

}
