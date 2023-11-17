package baekjoon;

import java.io.*;
import java.util.*;

public class B3190 { // 뱀 
	
	static int N, K, L, X;
	static int[][] map;
	static List<int[]> snake = new ArrayList<>();
	static String C;
	static HashMap<Integer, String> hashmap = new HashMap<>();
	static int[] dx = {0,1,0,-1}; // 동남서북 (시계) 
	static int[] dy = {1,0,-1,0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;
		}

		L = Integer.parseInt(br.readLine());
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			C = st.nextToken();
			hashmap.put(X, C);
		}
		// 입력완료 

		solve();
		 
		
	}
	
	private static void solve() {
		// 처음 시작 시
		int cx = 0, cy = 0; // 뱀의 좌표 
		int t = 0; // 시간 (초) 
		int d = 0; // 방향 (처음은 오른쪽) 
		snake.add(new int[] {0, 0});
		
		while(true) {
			t++;
			
			// 뱀의 이동
			int nx = cx + dx[d];
			int ny = cy + dy[d];
			
			if(isFinish(nx, ny)) break; // 종료조건에 해당하면 반복문 탈출 
			
			// 사과가 있다면 
			if(map[nx][ny]==1) {
				map[nx][ny]=0;
				snake.add(new int[] {nx, ny});
			} else { //사과가 없다면 
				snake.add(new int[] {nx, ny});
				snake.remove(0);
			}
			
			// 방향을 바꾸는 시간이라면 변경
			if(hashmap.containsKey(t)) {
				if(hashmap.get(t).equals("D")) {
					d = (d+1)%4;
				} else {
					d = (d+3)%4;
				}
			}
			
			// 값 갱신
			cx = nx; 
			cy = ny;
			
		}
		System.out.print(t);
		
	}
	
	private static boolean isFinish(int nx, int ny) { // 종료 조건 검증 
		// 범위를 벗어나면 종료
		if(nx<0||nx>=N||ny<0||ny>=N) return true;
					
		// 뱀 몸통을 만나면 종료 
		for(int i =0; i<snake.size(); i++) {
			int[] s = snake.get(i);
			if(nx==s[0] && ny==s[1]) return true;
		}
		return false;
	}

}
