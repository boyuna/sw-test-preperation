package competency;

import java.io.*;
import java.util.*;

public class B17144 {

	static int R, C, T;
	static int[][] map;
	static int airCleaner = -1;
	static Queue<Dust> dusts;
	static class Dust{
		int x, y, amount;
		public Dust (int x, int y, int amount) {
			this.x=x;
			this.y=y;
			this.amount=amount;
		}
	}
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == -1 && airCleaner == -1) {
					airCleaner = i;
				}
			}
		}
		// 입력 완료 
		
		for(int t = 0; t<T; t++) {
			checkDust();
			spreadDust();
			cleanAir();
		}
		
		int res = 0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j] == -1) continue;
				res += map[i][j];	
			}
		}
		System.out.println(res);
	}
	
	private static void checkDust() { // 미세먼지 상태 확인 
		dusts = new LinkedList<>();
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if (map[i][j] == -1 || map[i][j] == 0) continue;
				dusts.add(new Dust(i, j, map[i][j]));
			}
		}
	}
	
	private static void spreadDust() { // 미세먼지 확산 
		while(!dusts.isEmpty()) { // 미세먼지가 있다면 
			Dust now = dusts.poll();
			if(now.amount<5) continue;
			
			int spreadAmount = now.amount/5;
			
			int cnt=0; // 먼지가 확산된 개수 count 
			for(int i=0; i<4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				// 범위를 벗어나거나 공기청정기가 있다면 확산되지 않음 
				if(nx<0||nx>=R||ny<0||ny>=C) continue;
				if(map[nx][ny]== -1) continue;
				
				map[nx][ny] += spreadAmount;
				cnt++;
			}
			
			map[now.x][now.y] -= spreadAmount*cnt;
		
		}
	}
	
	private static void cleanAir() { // 공기청정기 작동 
		int top = airCleaner;
		int bottom = airCleaner + 1;
		
		// 반시계 
		for(int i = top-1; i>0; i--) { //아래로 
			map[i][0] = map[i-1][0];
		}
		for(int i = 0; i<C-1; i++) { // 왼쪽으로 
	    	map[0][i] = map[0][i+1];
	    }
		for(int i = 0; i<top; i++) { // 위로 
	    	map[i][C-1] = map[i+1][C-1];
	    }
		for(int i = C-1; i>1; i--) { // 오른쪽으로 
			map[top][i] = map[top][i-1];
		}

	    map[top][1] = 0;
	    
	    // 시계
	    for(int i = bottom+1; i<R-1; i++) { //위로 
			map[i][0] = map[i+1][0];
		}
	    for(int i = 0; i<C-1; i++) { // 왼쪽으로 
	    	map[R-1][i] = map[R-1][i+1];
	    }
	    for(int i = R-1; i>bottom; i--) { // 아래로 
	    	map[i][C-1] = map[i-1][C-1];
	    }
		for(int i = C-1; i>1; i--) { // 오른쪽으로 
			map[bottom][i] = map[bottom][i-1];
		}
	    
	    map[bottom][1] = 0;
	    
	    
	}

}
