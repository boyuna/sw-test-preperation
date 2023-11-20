package swExpertAcademy;

import java.io.*;
import java.util.*;

public class D1238 { // Contact 
	
	static int[][] map;
	static int[] visit;
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=10; t++) {
			st = new StringTokenizer(br.readLine());
			map = new int[101][101];
			visit = new int[101];
			
			int data = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<data/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				map[from][to] = 1;
			}
			sb.append("#").append(t).append(" ").append(bfs(start)).append("\n");
		}
		System.out.print(sb);
		br.close();
	}

	private static int bfs(int start) {
		q = new LinkedList<>();
		q.offer(start);
		int depth = 1;
		visit[start] = depth;

		
		while(!q.isEmpty()) {
			start = q.poll();
			
			for(int i=0; i<101; i++) {
				if(map[start][i]==1 && visit[i]==0) {
					q.offer(i);
					visit[i] = visit[start]+1;
			
					}
			}
			depth = Math.max(depth, visit[start]);
		}
		for(int i=100;i>=0;i--){
	         if(visit[i]==depth) return i;
	    }
		
		return -1;
	}

}
