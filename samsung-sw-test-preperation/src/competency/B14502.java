package competency;
import java.io.*;
import java.util.*;

public class B14502 {
	
	static int N, M;
	static int[][] map;
	static int[] dx = {-1,1,0,0}; // 상하좌우
	static int[] dy = {0,0,-1,1}; 
	static boolean[][] visited = new boolean[N][M];
	static int[][] wallMap;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		// 입력 완료
		
		
		makeWall(0);
		System.out.print(max);
		

	}
	
	private static void makeWall(int wall) { // 3개의 벽 세우기 (dfs) 
		if(wall == 3) {
			spreadVirus();
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if (map[i][j]==0) { 
					map[i][j]=1;
					makeWall(wall+1);
					map[i][j]=0;
				}
			}
			
		}
	
	}
	
	private static void spreadVirus() { // 바이러스 퍼뜨리기 (bfs) 
		Queue<int[]> q = new LinkedList<>();
		
		wallMap = new int[N][M];	// 벽이 세워진 map, 벽이 세워지면 bfs 실행이므로 초기화
		for(int i=0; i<N; i++) { 
			for(int j=0; j<M; j++) {
				wallMap[i][j] = map[i][j];
				if(wallMap[i][j]==2) {
					q.add(new int[] {i,j});
				}
			}
		}
		
		while(!q.isEmpty()) {
			int[] tmp = q.poll();
			int x = tmp[0];
			int y = tmp[1];
			
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if(nx>=0 && nx<N && ny>=0 && ny<M && wallMap[nx][ny]==0) {
					wallMap[nx][ny]=2;
					q.add(new int[] {nx, ny});
				}
			}
		}
		
		int cnt = 0;
		for(int i=0; i<N; i++) { 
			for(int j=0; j<M; j++) {
				if(wallMap[i][j]==0) {
					cnt++;
				}
			}
		}
		max = Math.max(max, cnt);
		
	}

}
