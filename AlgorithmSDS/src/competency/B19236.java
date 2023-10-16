package competency;

import java.io.*;
import java.util.*;

public class B19236 {
	
	static int[][] map = new int[4][4]; // 물고기 번호 저장 	
	static Fish[] fish;	// 물고기 정보 저장 
	static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1}; // 1~8까지 방향 (반시계)
	static int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};
	static int res = 0;

	static class Fish{
		int n; // 물고기 번호
		int d; //방향
		int x, y; // 좌표
		int al; // 생사여부, 1: 생 0: 사
		
		Fish(int n,  int d, int x, int y, int al){
			this.n = n;
			this.d = d;
			this.x = x;
			this.y = y;
			this.al = al;
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		fish = new Fish[17];
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j=0; j<4; j++) {
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken())-1;
				fish[n]  = new Fish(n, d, i, j, 1);
				map[i][j] = n;
			}
		}
		// 입력 완료
		
		
		//상어가 처음 공간에 들어옴
		int sx=0, sy=0; // 상어 위치
		int sd = fish[map[0][0]].d; // 상어 방향
		int eat = map[0][0]; // 상어가 먹은 물고기 번호 저장 
		fish[map[0][0]].al=0; // 물고기 죽음
		map[0][0] = -1;	// 상어가 있는 위치 : -1 저장
		
		moveShark(sx, sy, sd, eat);
		
		System.out.print(res);
		
	}
	
	private static void moveFish() { // 물고기 이동 
		for(int i=1; i<=16; i++) {
			if(fish[i].al == 0) continue;
			
			int cnt=0;
			int dir = fish[i].d; 
			int nx = 0, ny = 0; // 물고기가 이동할 칸의 좌표
			
			while(cnt<8) {				
				dir %= 8;
				fish[i].d = dir;
				nx = fish[i].x + dx[dir];
				ny = fish[i].y + dy[dir];
				
				//물고기가 이동할 수 있는 경우 
				if(nx>=0 && nx<4 && ny>=0 && ny<4 && map[nx][ny]!=-1) {
					if(map[nx][ny]==0) { // 이동 위치가 빈칸인 경우 
						map[fish[i].x][fish[i].y] = 0; 					
						fish[i].x = nx;
						fish[i].y = ny;
						map[nx][ny] = i;
					} else { // 이동 위치에 다른 물고기가 있는 경우 
						int chFish = fish[map[nx][ny]].n;
						fish[chFish].x=fish[i].x;
						fish[chFish].y=fish[i].y;
						map[fish[chFish].x][fish[chFish].y] = chFish;

						fish[i].x = nx;
						fish[i].y = ny;
						map[nx][ny] = i;
					} 
					break;
				} else { // 물고기가 이동 불가한 경우 

					dir++;
					cnt++;
				}
			}
			
		}
	}
	
	private static void moveShark(int sx, int sy, int sd, int eat) { // 상어 이동 (dfs)
		res = Math.max(res, eat);

		// map 배열 복사 
		int[][] cpyMap = new int[map.length][map.length];
		for(int i=0; i<map.length; i++) {
			System.arraycopy(map[i], 0, cpyMap[i], 0, map.length);
		}
		
		// fish 배열 복사 
		Fish[] cpyFish = new Fish[fish.length];
		for(int i=1; i<=16; i++) {
			cpyFish[i] = new Fish(fish[i].n, fish[i].d, fish[i].x, fish[i].y, fish[i].al);
		}
		moveFish();
		// 배열 복사 후 물고기 이동 처리 완료
		
		
		for(int i=1; i<4; i++) {
			int nx = sx + dx[sd]*i;
			int ny = sy + dy[sd]*i;
			
			// 상어가 이동할 수 있는 경우 
			if(nx>=0 && nx<4 && ny>=0 && ny<4 && map[nx][ny]!=0) {
				int eatFish = map[nx][ny];
				int nd = fish[eatFish].d;
				map[sx][sy]=0;
				map[nx][ny] = -1;
				fish[eatFish].al=0;

				moveShark(nx, ny, nd, eat+eatFish);
				
				//물고기 상태, 상어의 위치 원래대로 돌리기
				fish[eatFish].al = 1;
				map[sx][sy]= -1;
				map[nx][ny] = eatFish;
			}
		}
		
		// map 상태, 물고기 정보 원래대로 돌리기
		for(int i=0; i<map.length; i++) {
			System.arraycopy(cpyMap[i], 0, map[i], 0, map.length);
		}
		for(int i=1; i<=16; i++) {
			fish[i] = new Fish(cpyFish[i].n, cpyFish[i].d, cpyFish[i].x, cpyFish[i].y, cpyFish[i].al);
		}
	}

}


