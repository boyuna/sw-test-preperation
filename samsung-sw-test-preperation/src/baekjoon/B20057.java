package baekjoon;
import java.io.*;
import java.util.*;

public class B20057 {
	
	static int[][] A;
	static int N; // N은 홀수 (3~499)
	static int r, c;
	static int dx[] = {-1, 1, 0, 0}; //상하
	static int dy[] = {0, 0, -1, 1}; //좌우
	static int ndir[] = {2, 3, 1, 0}; // 상->좌, 하->우, 좌->하, 우->상 
	static int res;
	
	static int rate[] = {1,1,2,2,5,7,7,10,10};
	static int dsx[][] = {{1,1,0,0,-2,0,0,-1,-1,-1},{-1,-1,0,0,2,0,0,1,1,1},{-1,1,-2,2,0,-1,1,-1,1,0},{-1,1,-2,2,0,-1,1,-1,1,0}}; // 모래가 흩어지는 x 방향
	static int dsy[][] = {{-1,1,-2,2,0,-1,1,-1,1,0}, {-1,1,-2,2,0,-1,1,-1,1,0}, {1,1,0,0,-2,0,0,-1,-1,-1}, {-1,-1,0,0,2,0,0,1,1,1}}; // 모래가 흩어지는 y 방향

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N][N];
		
		for(r = 0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(c = 0; c<N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		//입력완료 
		
		move();
		System.out.print(res);
		
	}
	
	private static void move() {// 토네이도의 이동 
		int startX = N/2, startY = N/2; // 시작점 
		int nx = 0, ny = 0; // 다음칸
		int num = 1; //이동해야하는 칸의 수 (1씩 갱신)
		int checkNum = 0; // 2번씩 이동하는지 확인  
		int cnt = 0; // 이동횟수
		int cdir = 2; // 시작은 좌로 이동 
		
		while (true) {
			if (startX == 0 && startY == 0) break;
			
			nx = startX + dx[cdir]; 
			ny = startY + dy[cdir];
			cnt++; 
			
			moveSand(startX, startY, nx, ny, cdir);
			
			if(num == cnt) { // 이동해야하는 칸의 수와 이동횟수가 일치하면 
				cnt = 0;
				cdir = ndir[cdir]; // 상하좌우 변경 
				checkNum++;
			}
			if(checkNum == 2) {
				checkNum =0;
				num++;
			}
			startX = nx;
			startY = ny;
		}	
		
	}
	
	private static void moveSand(int startX, int startY, int nx, int ny, int cdir) { //모래 이동 
		A[nx][ny] += A[startX][startY]; // y <- x
		A[startX][startY] = 0;
		
		int sand = A[nx][ny];
		int alpha = sand; // 알파칸에 들어가는 모래
		int sx = 0, sy = 0; // 흩어진 모래 좌표
		
		for (int i = 0; i<9; i++) {
			sx = nx + dsx[cdir][i];
			sy = ny + dsy[cdir][i];
			int amount = (int) (sand * (rate[i] * 0.01));
			
			check(sx, sy, amount); 
			alpha -= amount;
		}
		int ax = nx + dsx[cdir][9]; //알파자리에 남은 모래 넣기 
		int ay = ny + dsy[cdir][9];
		check(ax,ay,alpha);
		A[nx][ny] = 0; // y 자리 모래 제거 
		
	}
	
	private static void check(int sx, int sy, int amount) {	// 범위를 벗어나는지 확인 
		if(sx<0 || sx>=N || sy<0 || sy>=N) {
			res += amount;
		}
		else { 
			A[sx][sy] += amount;
		}
	}

}
