package swExpertAcademy;

import java.io.*;

public class D7393 { // 대규의 팬덤 활동 (bitmask, dp) 
	static int N;
	static long ans;
	static long[][][] dp;
	static int MOD = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			dp = new long[101][10][1<<10];
			bitMask();
			ans = sol(N);
			System.out.println("#"+t+" "+ ans);
		}
	}

	private static long sol(int n) {
		long sum = 0;
		for(int i=0; i<10; i++) {
			sum = (sum + dp[n][i][1023]) % MOD;
		}
		return sum;
	}

	private static void bitMask() {
		dp = new long[101][10][1<<10];
		for(int i=1; i<10; i++) {
			dp[1][i][1<<i]=1;
		}
		for(int i=2; i<N+1; i++) {
			for(int j=0; j<10; j++) {
				for(int k=0; k<1024; k++) {
					if(j==0) {
						dp[i][j][(1<<j)|k] = (dp[i][j][(1 << j) | k] + dp[i-1][j+1][k]) % MOD;
					}
					else if(j==9) {
						dp[i][j][(1<<j)|k] = (dp[i][j][(1 << j) | k] + dp[i-1][j-1][k]) % MOD;
					}
					else {
						dp[i][j][(1<<j)|k] = (dp[i][j][(1<<j)|k] + dp[i-1][j+1][k] + dp[i-1][j-1][k])% MOD;
					}
				}
			}
		}
	}
	
	

}
