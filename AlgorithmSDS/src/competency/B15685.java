package competency;

import java.io.*;
import java.util.*;

public class B15685 {
	
	static int N;
	static boolean[][] map = new boolean[101][101];
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	static int res = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			
			dragonCurve(x, y, d, g);
		}
		
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j]&&map[i][j+1]&&map[i+1][j]&&map[i+1][j+1]) res++;
			}
		}
		System.out.print(res);	

	}
	
	
	private static void dragonCurve(int x, int y, int d, int g) {
		List<Integer> dList = new ArrayList<>();
		dList.add(d);
		
		for(int i=1; i<=g; i++) {
			for(int j=dList.size()-1; j>=0; j--) {
				dList.add((dList.get(j)+1)%4);
			}
		}
		map[y][x] = true;
		for(Integer dir : dList) {
			x += dx[dir];
			y += dy[dir];
			map[y][x] = true;
		}
	}

}
