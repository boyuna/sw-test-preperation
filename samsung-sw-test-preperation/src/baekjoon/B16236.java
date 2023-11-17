package baekjoon;

import java.io.*;
import java.util.*;

public class B16236 {
	
	static int N;
	static int[][] map;
	static int[][] dist; // 물고기와의 거리를 체크할 배열 (방문 처리) 
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int size = 2; // 상어 사이즈 
	static int moveCnt = 0; // 상어의 이동 횟수 = 시간 
	static int eatCnt = 0; // 상어가 먹은 물고기 수 카운트 
	static Queue<State> q;
	static int minDist; // 상어에서 가장 가까이 있는 물고기 거리
	static int minX, minY; 
	static int sharkX, sharkY;
	
	static class State{
		int x, y;
		State(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 9) {
					sharkX = i;
					sharkY = j;
					map[i][j] = 0;
				}
			}
		}
		// 입력 완료 

		while(true) {
			dist = new int[N][N];
			minDist = Integer.MAX_VALUE; // 상어에서 가장 가까이 있는 물고기 거리
			minX = Integer.MAX_VALUE; 
			minY = Integer.MAX_VALUE; 
			
			solve(sharkX, sharkY);
			
			if(minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
				eatCnt++;
				map[minX][minY] = 0;
				sharkX = minX;
				sharkY = minY;
				moveCnt += dist[minX][minY];

				if(eatCnt == size) {
					size++;
					eatCnt = 0;
				}
			} else {
				break;
			}
		}
		System.out.println(moveCnt);
	}
	
	private static void solve(int x, int y) {
		Queue<State> q = new LinkedList<>(); // 메소드 호출 시 마다 새로운 큐를 생성해야 함 
		q.add(new State(x, y));
		
		while(!q.isEmpty()) {
			State s = q.poll();
			
			int cx = s.x;
			int cy = s.y;
			
			for(int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy = dy[i];
				
				// 범위 안이면서, 방문한적 없고, 이동가능한 위치여야 
				if(isIn(nx, ny) && isMove(nx, ny) && dist[nx][ny] == 0) {
					dist[nx][ny] = dist[cx][cy] + 1;
					
					// 해당 위치에 물고기가있다면, 먹을 수 있는지 없는지 판별
					
					if(isEat(nx, ny)) { // 먹을 수 있는 물고기라면 거리에 따라 어떤 물고기를먹을 것인지
						// 가장 가까운 물고기 >> 가장 위의 물고기 >> 가장 왼쪽 물고기 
						if(minDist > dist[nx][ny]) {
							minDist = dist[nx][ny];
							minX = nx;
							minY = ny;
						} else if(minDist == dist[nx][ny]) {
							if(minX == nx) {
								if(minY > ny) {
									minX = nx; 
									minY = ny;
								} 
							} else if(minX > nx) {
								minX = nx;
								minY = ny;
							}
						}
					}
					q.add(new State(nx, ny));
				}

			}
		}
	}
	
	private static boolean isIn(int x, int y) { // 범위 안인지 판별
		if(x>=0&&x<N&&y>=0&&y<N) return true;
		return false;
	}
	
	private static boolean isMove(int x, int y) { // 상어가 이동 가능한 공간인지 판별 
		if(map[x][y]<=size) return true;
		return false;
	}
	
	private static boolean isEat(int x, int y) { // 먹을 수 있는 물고기인지 판별  
		if(map[x][y] != 0 && map[x][y]<size) return true;
		return false;
	}

}
