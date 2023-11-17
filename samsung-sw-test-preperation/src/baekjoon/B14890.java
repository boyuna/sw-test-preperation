package baekjoon;
import java.io.*;
import java.util.*;

public class B14890 {
	
	static int N, L;
	static int[][] map;
	static int cnt=0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 완료 
		
		for (int i=0; i<N; i++) {
			if(passable(i,0,0)) cnt++;
			if(passable(0,i,1)) cnt++;
		}
		System.out.print(cnt);
	}
	
	private static boolean passable(int x, int y, int d) { // d -> 0: 행, 1: 열 
		int[] road = new int[N];
		boolean[] visited = new boolean[N];
		
		for(int i=0; i<N; i++) {
			road[i] = (d==0)? map[x][y+i] : map[x+i][y];
		}
		
		for(int i=0; i<N-1; i++) {
			if(road[i]==road[i+1]) continue; // 높이가 같다면 pass 
			if(Math.abs(road[i]-road[i+1])>1) return false; // 높이차이가 2 이상이면 false 처리
			
			if(road[i] - 1 == road[i+1]) { // 내려가는 경사 
				for(int j=i+1; j<=i+L; j++) {
					if(j>=N || road[i+1] != road[j] || visited[j]) return false;
					visited[j] = true;
				} 
			}
			
			else if(road[i] + 1 == road[i+1]) { // 올라가는 경사 
				for(int j=i; j>i-L; j--) {
					if(j<0 || road[i] != road[j] || visited[j]) return false;
					visited[j] = true;
				} 
			}
			
		}
		return true;
	}

}
