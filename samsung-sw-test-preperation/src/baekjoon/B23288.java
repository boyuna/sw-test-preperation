package baekjoon;
import java.io.*;
import java.util.*;

public class B23288 { // 주사위 굴리기 2 
	
	static int N, M, K;
	static int[][] map;
	static int[][] dir = {{0,1}, {1,0}, {0,-1},{-1, 0}}; // 동남서북   
	static int d = 0;	// 동쪽 시작 
	static int x=0, y=0;
	static int[][] dice = {{0,2,0},{4,1,3},{0,5,0},{0,6,0}};
	static int score = 0;
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}// 입력 완료 
		
		while(K-- >0) {
			rollDice();
			getScore();
			changeDir();
		}
		
		System.out.print(score);

	}
	
	private static void rollDice() {	// 주사위 굴리기 
		int nx = x + dir[d][0];
		int ny = y + dir[d][1];
		
		if(!isIn(nx, ny)) {
			d=(d+2)%4;
		}
		
		switch(d) {
			case 0: //동
				int floor = dice[1][2]; // 3
				dice[1][2] = dice[1][1];
				dice[1][1] = dice[1][0];
				dice[1][0] = dice[3][1];
				dice[3][1] = floor;
				break;
			case 1: //남
				floor = dice[2][1]; //5
				dice[2][1] = dice[1][1];
				dice[1][1] = dice[0][1];
				dice[0][1] = dice[3][1];
				dice[3][1] = floor;
				break;
				
			case 2: //서
				floor = dice[1][0];  // 4
				dice[1][0] = dice[1][1];
				dice[1][1] = dice[1][2];
				dice[1][2] = dice[3][1];
				dice[3][1] = floor;
				break;

			case 3: // 북 
				floor = dice[0][1]; // 2
				dice[0][1]= dice[1][1];
				dice[1][1] = dice[2][1];
				dice[2][1] = dice[3][1];
				dice[3][1] = floor;
				break;
		}
		x += dir[d][0];
		y+=dir[d][1];
	}
	
	private static void getScore() { //얻을 수 있는 점수 확인 (bfs) 
		Queue<int []> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int n = map[x][y];
		int cnt=1;
		
		q.add(new int[] {x, y});
		visited[x][y]=true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			
			for(int i=0; i<4; i++) {
				int nx = now[0] + dir[i][0];
				int ny = now[1] + dir[i][1];
				
				if(!isIn(nx,ny)||visited[nx][ny]||map[nx][ny]!=n) continue;
				// 범위 밖 또는 이미 방 또는 같은 점수가 아니면 계속
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny});
				cnt++;
			}
		}
		score += cnt*n;		
	}
	
	private static boolean isIn(int x, int y) {	//범위를 지나지 않았는지 확인 
		return x>=0 && x<N && y>=0 && y<M;	
	}
	
	private static void changeDir() { // 방향 변경  
		int floor = dice[3][1];
		int n = map[x][y];
		if(floor>n) {
			d = (d+1)%4;
		} else if(floor < n) {
			d--;
			if(d<0) d=3;
		}
	}

}
