package baekjoon;

import java.io.*;
import java.util.*;

public class B20055 {
	
	static int N, K;
	static int[] A; //벨트의 내구도 저장	
	static boolean robot[]; //로봇이 존재여부 저장 
	static int l, r; // 벨트의 좌우측 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[2*N];
		robot = new boolean[N];
		st = new StringTokenizer(br.readLine());		
		for(int i=0; i<A.length; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 완료 
		l = 0;
		r = N;
		
		int cnt = 0;
		while(K>0) {
			cnt++;
			moveBelt();
			moveRobot();
			newRobot();
		}
		System.out.print(cnt);

	}
	private static void newRobot() { // 시작부분에 로봇 올리기 
		if(!robot[0] && A[l]>0) {
			robot[0] = true;
			A[l]--;
			
			if (A[l]==0) {
				K--;
			}
		}
		
	}
	private static void moveBelt() { // 벨트 이동 
		l--;
		r--;
		if (l==-1) {
			l = 2*N - 1;
		}
		if (r==-1) {
			r = 2*N - 1;
		}
		
		for(int i = N-2; i>=0; i--) {
			if(robot[i]) {
				robot[i] = false;
				if(i+1<N-1) {
					robot[i+1] = true;
				}
			}
		}
	}
	
	private static void moveRobot() { // 로봇 이동 
		for(int i = N-2; i>=0; i--) {
			if(robot[i]) {
				int next = l + i + 1;
				if(next>=2*N) next -= 2*N;
				if(!robot[i+1] && A[next]>=1) {
					robot[i] = false;
					if(i+1<N-1) {
						robot[i+1] = true;
					}
					A[next]--;
					if(A[next]==0) {
						K--;
					}
				}
			}
		}
	}

}
