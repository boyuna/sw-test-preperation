package competency;

import java.io.*;
import java.util.*;

public class B14889 {
	
	static int N;
	static int[][] map;
	static boolean[] visit;
	static int MIN = Integer.MAX_VALUE;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력완료 
		
		track(0,0);
		System.out.print(MIN);

	}
	
	private static void track(int idx, int depth) { // 백트래킹   
		if(depth == N/2) { //팀 조합이 완성 
			diff();
			return;
		}
		
		for(int i=idx; i<N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				track(i+1, depth+1);
				visit[i] = false;
			}
		}
	}
	
	private static void diff() {
		int start = 0;
		int link = 0;
		for(int i=0; i<N-1; i++) {
			for(int j = i; j<N; j++) {
				if(visit[i] && visit[j]) {
					start+=map[i][j];
					start+=map[j][i];
				} else if (!visit[i] && !visit[j]) {
					link+=map[i][j];
					link+=map[j][i];
				}
			}
		}
		int val = Math.abs(start - link);
		MIN = Math.min(MIN, val);
	}

}
