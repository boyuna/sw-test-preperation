package competency;

import java.io.*;
import java.util.*;

public class B14503 {
	
	static int N, M;
	static int r, c, d;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0}; // 북동남서 
	static int[] dy = {0, 1, -0, -1};
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		cnt = 1;
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력완료 
		
		clean(r, c, d);
		System.out.print(cnt);
		

	}
	
	private static void clean(int r, int c, int d) { 
		map[r][c] = -1; // 현재 위치 청소  
		
		for(int i=0; i<4; i++) {
			d = (d+3)%4; // 북 -> 서 -> 남 -> 동 
			int nx = r + dx[d];
			int ny = c + dy[d];
			
			if(nx>=0 && nx<N && ny>=0 && ny<M && map[nx][ny]==0) { // 범위 안에 청소 안된 곳이 있으면 			
				cnt++;
				clean(nx, ny, d);
				return;
			
			}
		
		}
		int back = (d+2)%4;
		int bx = r + dx[back];
		int by = c + dy[back];
		
		if(bx>=0 && bx<N && by>=0 && by<M && map[bx][by] != 1) {
			clean(bx, by, d);
		}

	}

}
