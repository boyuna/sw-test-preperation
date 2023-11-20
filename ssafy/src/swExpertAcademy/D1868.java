package swExpertAcademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D1868 {
	
	static char[][] map;
	static int[] dx = {-1,1,0,0, 1, -1, 1, -1};
	static int[] dy = {0,0,-1,1, 1, -1, -1, 1};
	static int N, cnt, res;
	static int[][] mapCnt;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			res = 0;
			map = new char[N][N];
			mapCnt = new int[N][N];
			
			for(int i=0; i<N; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			countMine();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(mapCnt[i][j] == 0 && map[i][j] != '*') {
						click(i, j);
						res++;
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(mapCnt[i][j] > 0 && map[i][j] != '*') {
						res++;
					}
				}
			}
			
			System.out.println("#" + t + " " + res);
		}

	}
	
	private static void click(int x, int y) {
		int now = mapCnt[x][y];
		mapCnt[x][y] = -1; // 방문 처리 
		
		if(now==0) {
			for(int d=0; d<8; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny] == '*' || mapCnt[nx][ny] == -1) continue;
				click(nx,ny);
				
			}
		}
		
	}
	
	private static void countMine() {
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				
				if(map[x][y] == '.') {
					int cnt = 0;
					for(int d=0; d<8; d++) {
						int nx = x + dx[d];
						int ny = y + dy[d];
						
						if(nx<0 || nx>=N || ny<0 || ny>=N || map[nx][ny] != '*') continue;
						cnt++;
					}
					mapCnt[x][y] = cnt;
				}
				
			}
		}
	
	}


}
