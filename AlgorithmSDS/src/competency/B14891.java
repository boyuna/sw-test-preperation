package competency;
import java.io.*;


public class B14891 {
	
	static class Wheels{ // 톱니바퀴 
		String[] arr = new String[8]; //n,s극 정보 저장 
		int top, left, right; // 0, 2, 6번 인덱스
		
		Wheels(String[] arr){
			this.arr = arr;
			this.top = 0;
			this.left = 6;
			this.right = 2;
		}
		
		public void rotate(int d) { // 방향에 따른 톱니바퀴의 회전
			this.top = updateIdx(this.top, d);
			this.left = updateIdx(this.left, d);
			this.right = updateIdx(this.right, d);
			
		}
		
		public int updateIdx(int idx, int d) {	// 톱니바퀴 인덱스 갱신 
			//시계
			if(d== 1) {
				return (idx - 1 == -1)? 7: --idx;
			}
			
			//반시계 
			else {
				return (idx + 1 == 8)? 0: ++idx;
			}
				
		}
	}
	
	static int K;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Wheels wh1 = new Wheels(br.readLine().split(""));
		Wheels wh2 = new Wheels(br.readLine().split(""));
		Wheels wh3 = new Wheels(br.readLine().split(""));
		Wheels wh4 = new Wheels(br.readLine().split(""));
		
		K = Integer.parseInt(br.readLine());
		res = 0;
		
		for(int i=0; i<K; i++) {
			String[] whArr = br.readLine().split(" ");
			// 주어진 입력 완료
			
			int[] checkRt = new int[4]; //회전 여부 저장, -1: 반시계, 1: 시계, 0: 회전 안함
			
			// 1번 톱니바퀴가 회전하는 경우
			if(whArr[0].equals("1")) { 
				// 1번의 회전 방향 저장
				checkRt[0] = Integer.parseInt(whArr[1]); 
				
				// 1번과 2번과 서로 다른 극일 떄
				if(!wh1.arr[wh1.right].equals(wh2.arr[wh2.left])){
					// 2번의 회전 방향 저장
					checkRt[1] = 0 - checkRt[0];
					
					// 2번과 3번이 서로 다른 극일 때
					if(!wh2.arr[wh2.right].equals(wh3.arr[wh3.left])) {
						// 3번의 회전 방향 저장 
						checkRt[2] = 0 - checkRt[1];
						
						// 3번과 4번이 서로 다른 극일 때
						if(!wh3.arr[wh3.right].equals(wh4.arr[wh4.left])) {
							// 4번의 회전 방향 저장 
							checkRt[3] = 0 - checkRt[2];
						}
					}
				}
			}
			
			// 2번 톱니바퀴가 회전하는 경우
			else if(whArr[0].equals("2")) { 
				// 2번의 회전 방향 저장
				checkRt[1] = Integer.parseInt(whArr[1]); 
				
				// 2번과 1번과 서로 다른 극일 떄
				if(!wh2.arr[wh2.left].equals(wh1.arr[wh1.right])){
					// 1번의 회전 방향 저장
					checkRt[0] = 0 - checkRt[1];
					
				}
				// 2번과 3번이 서로 다른 극일 때
				if(!wh2.arr[wh2.right].equals(wh3.arr[wh3.left])) {
					// 3번의 회전 방향 저장 
					checkRt[2] = 0 - checkRt[1];
					
					// 3번과 4번이 서로 다른 극일 때
					if(!wh3.arr[wh3.right].equals(wh4.arr[wh4.left])) {
						// 4번의 회전 방향 저장 
						checkRt[3] = 0 - checkRt[2];
					}
				}
			}
	
			
			// 3번 톱니바퀴가 회전하는 경우
			else if(whArr[0].equals("3")) { 
				// 3번의 회전 방향 저장
				checkRt[2] = Integer.parseInt(whArr[1]); 
				
				// 4번과 3번이 서로 다른 극일 때
				if(!wh3.arr[wh3.right].equals(wh4.arr[wh4.left])) {
					// 4번의 회전 방향 저장 
					checkRt[3] = 0 - checkRt[2];
				}
				
				// 3번과 2번과 서로 다른 극일 떄
				if(!wh3.arr[wh3.left].equals(wh2.arr[wh2.right])){
					// 2번의 회전 방향 저장
					checkRt[1] = 0 - checkRt[2];

					// 2번과 1번이 서로 다른 극일 때
					if(!wh2.arr[wh2.left].equals(wh1.arr[wh1.right])) {
						// 1번의 회전 방향 저장 
						checkRt[0] = 0 - checkRt[1];
					}
				}	
				
			}
	
			
			// 4번 톱니바퀴가 회전하는 경우
			else { 
				// 1번의 회전 방향 저장
				checkRt[3] = Integer.parseInt(whArr[1]); 
				
				// 4번과 3번과 서로 다른 극일 떄
				if(!wh4.arr[wh4.left].equals(wh3.arr[wh3.right])){
					// 3번의 회전 방향 저장
					checkRt[2] = 0 - checkRt[3];
					
					// 3번과 2번이 서로 다른 극일 때
					if(!wh3.arr[wh3.left].equals(wh2.arr[wh2.right])) {
						// 2번의 회전 방향 저장 
						checkRt[1] = 0 - checkRt[2];
						
						// 2번과 1번이 서로 다른 극일 때
						if(!wh2.arr[wh2.left].equals(wh1.arr[wh1.right])) {
							// 1번의 회전 방향 저장 
							checkRt[0] = 0 - checkRt[1];
						}
					}
				}
			}
			
			// 회전하는 경우 저장 
			if(checkRt[0]!=0) {
				wh1.rotate(checkRt[0]);

			}
			if(checkRt[1]!=0) {
				wh2.rotate(checkRt[1]);

			}
			if(checkRt[2]!=0) {
				wh3.rotate(checkRt[2]);

			}
			if(checkRt[3]!=0) {
				wh4.rotate(checkRt[3]);
	
			}

	
		}
		
		// 점수 계산
		if(wh1.arr[wh1.top].equals("1")) res += 1;
		if(wh2.arr[wh2.top].equals("1")) res += 2;
		if(wh3.arr[wh3.top].equals("1")) res += 4;
		if(wh4.arr[wh4.top].equals("1")) res += 8;
		
		System.out.println(res);
			
	}

}
