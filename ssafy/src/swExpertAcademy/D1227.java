package swExpertAcademy;

import java.io.*;

public class D1227 { // 미로2
	
	static int[][] map;
	static boolean[][] visited;
	static int res;
	static int startX, startY;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int testCase=1; testCase<=10; testCase++) {
			int N = Integer.parseInt(br.readLine());
			
			visited = new boolean[100][100];
			map = new int[100][100];
			for(int i=0; i<100; i++) {
				String line = br.readLine();
				
				for(int j=0; j<100; j++) {
					map[i][j] = line.charAt(j)-48;
					
					if(map[i][j]==2) {
						startX = i;
						startY = j;
					}
				}
			}
			res = 0;
			sb.append("#"+N+" ");
			dfs(startX, startY);
			sb.append(res+"\n");
		}
		System.out.println(sb.toString());

	}

	private static void dfs(int x, int y) {
		if(res==1) return;
		visited[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if(map[nx][ny]==3) {
				res=1;
				return;
			}
			if(check(nx, ny) && !visited[nx][ny] && (map[nx][ny]==0)) {
				dfs(nx, ny);
			}
		}
		
	}

	private static boolean check(int nx, int ny) {
		return nx>=0 && ny>=0 && nx<100 && ny<100;
	}
	

}
