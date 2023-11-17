package competency;

import java.io.*;
import java.util.*;

public class B14501 {
	
	static int N;
	static int[] t, p;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		t = new int[N];
		p = new int[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			t[i] = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 완료  
		
		res = 0;
		consult(0,0);
		System.out.print(res);
		
	}
	
	private static void consult(int day, int pay) { //day : 상담을 끝낸 날 
		if(day >= N) {
			res = Math.max(res, pay);
			return;
		}
		if(day + t[day] <= N) { 
			consult(day + t[day], pay + p[day]);
		} else {
			consult(day + t[day], pay);
		}
		
		consult(day+1, pay);
	}

}
