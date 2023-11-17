package baekjoon;

import java.io.*;
import java.util.*;

public class B14500 { // 테트로미노 
	
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int max = Integer.MIN_VALUE;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완료
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visit[i][j] = true;
				solve(i, j, map[i][j], 1);
				visit[i][j] = false;
			}
		}
		
		System.out.print(max);

	}
	
	private static void solve(int x, int y, int sum, int cnt) {
		if(cnt == 4) {
			max = Math.max(max, sum);
			return;
		}
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0||nx>=N||ny<0||ny>=M) continue;
			
			if(!visit[nx][ny]) {
				if(cnt==2) {
					visit[nx][ny] = true;
					solve(x, y, sum+map[nx][ny], cnt+1);
					visit[nx][ny] = false;
				}
				visit[nx][ny] = true;
				solve(nx, ny, sum+map[nx][ny], cnt+1);
				visit[nx][ny] = false;
			}
		}
	}
}
