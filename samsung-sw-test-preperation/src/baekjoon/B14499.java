package baekjoon;
import java.io.*;
import java.util.*;
public class B14499 {
	
	static int N, M, x, y, K;
	static int[][] map;
	static int dir;
	static int[] dx = {0, 0, -1, 1}; // 동서북남  
	static int[] dy = {1, -1, 0, 0};
	static int[] dice = new int[6];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			dir = Integer.parseInt(st.nextToken());
			rollDice(dir);
		}
		// 입력완료 
	}
	
	private static void rollDice(int d) {// 주사위를 굴린다. 
		int nx = x + dx[d-1];
		int ny = y + dy[d-1];
			
		if(nx<0 || ny<0 || nx>=N || ny>=M) return;
		getNum(nx, ny, d);
		x = nx; 
		y = ny;
	}
	
	private static void getNum(int x, int y, int d) { // 주사위를 굴리며 얻는 숫자 정보 
		int tmp = dice[2];
		switch(d) {
		case 1: // 동 
			dice[2] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[3];
			dice[3] = tmp;
			break;
		case 2: // 서 
			dice[2] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[1];
			dice[1] = tmp;
			break;
		case 3: // 북
			dice[2] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[0];
			dice[0] = tmp;
			break;
		case 4: // 남
			dice[2] = dice[0];
			dice[0] = dice[5];
			dice[5] = dice[4];
			dice[4] = tmp;
			break;
		}
		if(map[x][y]==0) {
			map[x][y] = dice[5];
		} else {
			dice[5] = map[x][y];
			map[x][y] = 0;
		}
		System.out.println(dice[2]);
	}
}
