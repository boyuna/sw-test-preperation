package baekjoon;

import java.io.*;
import java.util.*;

public class B13460 { // 구슬 탈출 2 

	static int N, M;
	static char[][] map;
	static boolean[][][][] visit;
	static int holeX, holeY;
	static Marble red, blue;	
	static Queue<Marble> q;
	static int[] dx = {-1,0,1,0}; // 상우좌하 (시계)
	static int[] dy = {0,1,0,-1};
	
	static class Marble{
		int rx, ry, bx, by, cnt;
		public Marble(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt; // 이동 횟수 
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visit = new boolean[N][M][N][M];
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'O') {
					holeX = i; 
					holeY = j;
				} else if(map[i][j] == 'R') {
					red = new Marble(i, j, 0, 0, 0); 
				} else if(map[i][j] == 'B') {
					blue = new Marble(0, 0, i, j, 0);
				}
			}
		}
		// 입력 완료 
		
		System.out.print(escape());
		br.close();
		
	}
	
	private static int escape() { // bfs 
		q = new LinkedList<>();
		q.add(new Marble(red.rx, red.ry, blue.bx, blue.by, 1));
		visit[red.rx][red.ry][blue.bx][blue.by] = true;
		
		while(!q.isEmpty()) {
			Marble marble = q.poll();
			
			int cRx = marble.rx;
			int cRy = marble.ry;
			int cBx = marble.bx;
			int cBy = marble.by;
			int cCnt = marble.cnt;
			
			if(cCnt>10) return -1;
			
			for(int i=0; i<4; i++) {
				int nRx = cRx;
				int nRy = cRy;
				int nBx = cBx;
				int nBy = cBy;
				
				boolean isRed = false;
				boolean isBlue = false;
				
				// 빨간 구슬이 벽을 만날 때까지 이동 
				while(map[nRx+dx[i]][nRy+dy[i]] != '#') {
					nRx += dx[i];
					nRy += dy[i];
					
					if(nRx == holeX && nRy == holeY) {
						isRed = true;
						break;
					}
				}
				
				// 파란 구슬이 벽을 만날 때까지 이동  
				while(map[nBx+dx[i]][nBy+dy[i]] != '#') {
					nBx += dx[i];
					nBy += dy[i];
					
					if(nBx == holeX && nBy == holeY) {
						isBlue = true;
						break;
					}
				}
				
				if(isBlue) continue; // 파란 구슬 구멍에 빠지면 실패 
				if(isRed && !isBlue) return cCnt; // 빨간 구슬만 빠지면 반
				
				// 두 구슬 모두 구멍에 빠지지 않았고 이동 위치가 같은 경우 
				if(nRx == nBx && nRy == nBy) {
					switch(i) {
					case 0: // 위 
						// 더 큰 x값을 가지는 구슬이 뒤로 
						if(cRx>cBx) nRx -= dx[i];
						else nBx -= dx[i];
						break;
					case 1:// 오른쪽
						// 더 작은 y값을 가지는 구슬이 뒤로 
						if(cRy<cBy) nRy -= dy[i];
						else nBy -= dy[i];
						break;
					case 2: // 아래
						// 더 작은 x값을 가지는 구슬이 뒤로
						if(cRx<cBx) nRx -= dx[i];
						else nBx -= dx[i];
						break;
					case 3: // 왼쪽 
						// 더 큰 y값을 가지는 구슬이 뒤로 
						if(cRy>cBy) nRy -= dy[i];
						else nBy -= dy[i];
						break;
					}
				}
				if(!visit[nRx][nRy][nBx][nBy]) {
					visit[nRx][nRy][nBx][nBy] = true;
					q.add(new Marble(nRx, nRy, nBx, nBy, cCnt+1));
				}
			}
		}
		return -1;
		
	}
}
