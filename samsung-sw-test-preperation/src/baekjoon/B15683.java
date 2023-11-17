package baekjoon;
import java.io.*;
import java.util.*;

public class B15683 { // 감시 
	
	static int N, M;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static ArrayList<CCTV> cctv;
	static class CCTV {
		int x, y, type;
		CCTV(int x, int y, int type){
			this.x = x;
			this.y = y;
			this.type = type; // cctv의 타입 (어떤 번호 타입인지) 
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new ArrayList<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] != 0 && map[i][j] != 6) {
					cctv.add(new CCTV(i,j,map[i][j]));
				}
			}
		}
		// 입력 완료
		
		solve(0, map, cctv);
		System.out.print(min);

		
	}
	
	private static void solve(int idx, int[][] map, ArrayList<CCTV> cctv) {
		if(idx == cctv.size()) {
			min = Math.min(min, zeroCnt(map));
			return;
		}
		
		int cctvNum = cctv.get(idx).type;
		int x = cctv.get(idx).x;
		int y = cctv.get(idx).y;
		int[][] tmp;
		
		switch(cctvNum) {
		case 1: // 1번 타입 cctv일 때 
			tmp = copyMap(map);
			checkRight(tmp, x, y);
			solve(idx+1, tmp, cctv);
			
			tmp = copyMap(map);
			checkLeft(tmp, x, y);
			solve(idx+1, tmp, cctv);
			
			tmp = copyMap(map);
			checkUp(tmp, x, y);
			solve(idx+1, tmp, cctv);
			
			tmp = copyMap(map);
			checkDown(tmp, x, y);
			solve(idx+1, tmp, cctv);
			break;
			
		case 2: // 2번 타입 cctv 일 때 
			tmp = copyMap(map);
			checkRight(tmp, x, y);
			checkLeft(tmp, x, y);
			solve(idx+1, tmp, cctv);
			
			tmp = copyMap(map);
			checkUp(tmp, x, y);
			checkDown(tmp, x, y);
			solve(idx+1, tmp, cctv);
			break;
			
		case 3:
			tmp = copyMap(map);
			checkUp(tmp, x, y);
			checkRight(tmp, x, y);
			solve(idx+1, tmp, cctv);
			
			tmp = copyMap(map);
			checkUp(tmp, x, y);
			checkLeft(tmp, x, y);
			solve(idx+1, tmp, cctv);

			tmp = copyMap(map);
			checkDown(tmp, x, y);
			checkRight(tmp, x, y);
			solve(idx+1, tmp, cctv);
			
			tmp = copyMap(map);
			checkDown(tmp, x, y);
			checkLeft(tmp, x, y);
			solve(idx+1, tmp, cctv);
			break;
			
		case 4:
			tmp = copyMap(map);
			checkUp(tmp, x, y);
			checkRight(tmp, x, y);
			checkLeft(tmp, x, y);
			solve(idx+1, tmp, cctv);
			
			tmp = copyMap(map);
			checkUp(tmp, x, y);
			checkDown(tmp, x, y);
			checkLeft(tmp, x, y);
			solve(idx+1, tmp, cctv);

			tmp = copyMap(map);
			checkDown(tmp, x, y);
			checkRight(tmp, x, y);
			checkLeft(tmp, x, y);
			solve(idx+1, tmp, cctv);
			
			tmp = copyMap(map);
			checkUp(tmp, x, y);
			checkDown(tmp, x, y);
			checkRight(tmp, x, y);
			solve(idx+1, tmp, cctv);
			break;
			
		case 5:
			tmp = copyMap(map);
			checkUp(tmp, x, y);
			checkDown(tmp, x, y);
			checkRight(tmp, x, y);
			checkLeft(tmp, x, y);
			solve(idx+1, tmp, cctv);
			break;
		}
		
		
	}
	private static int[][] copyMap(int[][] map) {
		int[][] tmp = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		return tmp;
	}
	
	private static void checkRight(int[][] map, int x, int y) {
		for(int i=y+1; i<M; i++) {
			if(map[x][i] == 6) return;
			if(map[x][i] != 0) continue;
			map[x][i] = -1;
			
		}
	}
	private static void checkLeft(int[][] map, int x, int y) {
		for(int i=y-1; i>=0; i--) {
			if(map[x][i] == 6) return;
			if(map[x][i] != 0) continue;
			map[x][i] = -1;
			
		}
	}
	private static void checkUp(int[][] map, int x, int y) {
		for(int i=x-1; i>=0; i--) {
			if(map[i][y] == 6) return;
			if(map[i][y] != 0) continue;
			map[i][y] = -1;
			
		}
	}
	private static void checkDown(int[][] map, int x, int y) {
		for(int i=x+1; i<N; i++) {
			if(map[i][y] == 6) return;
			if(map[i][y] != 0) continue;
			map[i][y] = -1;
			
		}
	}
	
	private static int zeroCnt(int[][] map) {
		int zeroCnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) zeroCnt++;
			}
		}
		return zeroCnt;
	}
	

}
