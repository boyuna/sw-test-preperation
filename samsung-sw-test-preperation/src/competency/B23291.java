package competency;

import java.io.*;
import java.util.*;

public class B23291 {
	
	static int N,K;
	static int[][] map;
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			map[N][i] = Integer.parseInt(st.nextToken());
		}
		//입력
		
		int res = 0;
		while(!isFinish()) {
			res++;
			pushFish();
			make2D();
			adjustFish();
			make1D();
			fold();
			adjustFish();
			make1D();
		}
		System.out.print(res);

	}
	private static void pushFish() {// 물고기 추가 
		List<Integer> list = new ArrayList<>();
		int min = 987654321;
		for(int i=1; i<=N; i++) {
			if(map[N][i] < min) {
				min=map[N][i];
				list.clear();
				list.add(i);
			} else if(map[N][i]==min) {
				list.add(i);
			}
		}
		for (int index: list) {
			map[N][index]++;
		}
	}
	
	private static void make2D() {	// 어항 쌓기 
		int pivotX = 1;
		int w=1; 
		int h=1;
		int index=0;
		while (pivotX - 1 + w + h <= N){
			index++;
			for(int x=pivotX; x<pivotX+w; x++) {
				for(int y=N; y>N-h; y--) {
					int ny = N - w + x - pivotX;
					int nx = pivotX + w + N - y;
					map[ny][nx] = map[y][x];
					map[y][x]=0;
				}
			}
			pivotX += w;
			if(index%2==0) {
				w++;
			} else {
				h++;
			}
			
		}
	}
	private static void adjustFish() {//물고기수 조절 
		int[][] newMap = new int[N+1][N+1];
		
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=N; x++) {
				if(map[y][x]==0) continue;
				for (int d=0; d<4; d++) {
					int ny = y+dy[d];
					int nx = x+dx[d];
					if ((ny<1||nx<1||ny>N||nx>N)||map[ny][nx]==0) continue;
					int diff = map[y][x] - map[ny][nx];
					diff /= 5;
					if(diff > 0) {
						newMap[y][x] -= diff;
						newMap[ny][nx] += diff;
					}
				}
			}
		}
		for (int y=1; y<=N; y++) {
			for(int x=1; x<=N; x++) {
				map[y][x] += newMap[y][x];
			}
		}
	}
	
	private static void make1D() { // 일렬로 어항 배치 
		List<Integer> list = new ArrayList<>();
		for(int x=1; x<=N; x++) {
			for(int y=N; y>=1; y--) {
				if(map[y][x]==0) break;
				list.add(map[y][x]);
				map[y][x]=0;
			}
		}
		for(int i=0; i<N; i++) {
			map[N][i+1]=list.get(i);
		}
	}
	
	private static void fold() {// 어항 접기 
		List<Integer> list = new ArrayList<>();
		int pivotX = 1;
		int yCnt = 1;
		for(int cnt = 1; cnt<=2; cnt++) {
			int nny = N-yCnt * 2 +1;
			for(int y=N; y>N-yCnt; y--) {
				list.clear();
				for(int x=pivotX; x< pivotX + (N-pivotX+1)/2; x++) {
						list.add(map[y][x]);
						map[y][x]=0;
				}
				for(int index=0; index<list.size(); index++) {
					map[nny][N-index]=list.get(index);
				}
				nny++;
			}
			yCnt *=2;
			pivotX +=N/2;
		}
	}
	
	private static boolean isFinish() { // 종료 여부 확인 
		int max=0; 
		int min = 987654321;
		for(int y=1; y<=N; y++) {
			for(int x=1; x<=N; x++) {
				if(map[y][x]==0) continue;
				if(max<map[y][x]) max = map[y][x];
				if(min>map[y][x]) min = map[y][x];
			}		
		}
		if(max-min<=K) {
			return true;
		} else {
			return false;
		}
	}
	

}
