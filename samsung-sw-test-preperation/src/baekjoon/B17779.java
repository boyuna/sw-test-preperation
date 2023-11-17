package baekjoon;
import java.io.*;
import java.util.*;

public class B17779 {

	static int N; 
	static int[][] map;
	static int total = 0;
	static int MIN = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				total += map[r][c];
			}
		}
		// 입력완료
		
		for(int x=0; x<N; x++) {
			for(int y=0; y<N; y++) {
				for(int d1=1; d1<N; d1++) {
					for(int d2=1; d2<N; d2++) {
						if(x+d1+d2>=N) continue;
						if(y-d1<1 || y+d2>=N) continue;
						
						sol(x, y, d1, d2);
					}
				}
			}
		}
		System.out.print(MIN);

	}
	
	private static void sol(int x, int y, int d1, int d2) {
		boolean[][] border = new boolean[N][N];
		
		// 경계선 
		for(int i=0; i<=d1; i++) {
			border[x+i][y-i] = true;
			border[x+d2+i][y+d2-i] = true;
		}
		
		for(int i=0; i<=d2; i++) {
			border[x+i][y+i] = true;
			border[x+d1+i][y-d1+i] = true;
		}
		
		int[] peopleSum = new int[5];
		
		//1구역 
		for (int r=0; r<x+d1; r++) {
			for(int c=0; c<=y; c++) {
				if(border[r][c]) break;
				peopleSum[0] += map[r][c];
			}
		}
		
		// 2구역
		for (int r=0; r<=x+d2; r++) {
			for(int c=N-1; c>y; c--) {
				if(border[r][c]) break;
				peopleSum[1] += map[r][c];
			}
		}
		
		// 3구역
		for (int r=x+d1; r<N; r++) {
			for(int c=0; c<y-d1+d2; c++) {
				if(border[r][c]) break;
				peopleSum[2] += map[r][c];
			}
		}
		
		// 4구역
		for (int r=x+d2+1; r<N; r++) {
			for(int c=N-1; c>=y-d1+d2; c--) {
				if(border[r][c]) break;
				peopleSum[3] += map[r][c];
			}
		}
		
		// 5구역 인구수 
		peopleSum[4] = total - (peopleSum[0] + peopleSum[1] + peopleSum[2] + peopleSum[3]);
		
		Arrays.sort(peopleSum);
		MIN = Math.min(MIN, peopleSum[4]-peopleSum[0]);
	}

}
