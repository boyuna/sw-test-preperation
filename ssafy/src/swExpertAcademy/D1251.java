package swExpertAcademy;

import java.io.*;
import java.util.*;

public class D1251 { // 하나로 (프림 알고리즘 사용) 

	static int N;
	static double E;
	static long[] X, Y;
	static boolean[] visit;
	
	static class Node implements Comparable<Node>{
		int to;
		long cost;
		
		Node(int to, long cost){
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			X = new long[N];
			Y = new long[N];
			visit = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				Y[i] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			
			LinkedList<Node>[] list = new LinkedList[N]; // 가능한 모든 간선 저장 
			
			for (int i = 0; i < N; i++) {
				list[i] = new LinkedList<>();
				for (int j = 0; j < N; j++) {
					if(i == j)	continue;
					long L = (X[i]-X[j])*(X[i]-X[j]) + (Y[i]-Y[j])*(Y[i]-Y[j]);
					list[i].add(new Node(j, L));
				}
			}
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(0, 0));
			long ans = 0;
			int cnt = 0;
			while(!pq.isEmpty()) {
				Node n = pq.poll();
				if(visit[n.to])	continue;
				visit[n.to] = true;
				ans += n.cost;
				if(++cnt == N)	break;
				
				for (Node node : list[n.to]) {
					if(!visit[node.to])	pq.add(node);
				}
			}
			System.out.println("#"+t+" "+Math.round(ans*E));
		}
	}
	
}
