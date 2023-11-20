package swExpertAcademy;

import java.io.*;
import java.util.*;

public class D3316 { // 동아리실 관리하기 
	
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String key = br.readLine();
			dp = new int[key.length()][16];
			
			firstDay(key, dp);
			for(int i=1; i<key.length(); i++) {
				otherDay(key, dp, i);
			}
			int res = sol(dp);
			System.out.println("#"+t+ " " +res);
		}
		
	}

	private static int sol(int[][] dp) {
		int sum = 0;
		for(int i=1; i<16; i++) {
			sum += dp[dp.length-1][i];
			sum %= 1000000007;
		}
		return sum;
	}

	private static void otherDay(String key, int[][] dp, int day) {
		int keyNum = 1 << (key.charAt(day) - 'A');
		for(int i=1; i<16; i++) {
			if(dp[day - 1][i] != 0) {
				for(int j=1; j<16; j++){
					if((j&i)!=0 && (j&keyNum)!=0) {
						dp[day][j] += dp[day-1][i];
						dp[day][j] %= 1000000007;
					}
				}
			}
		}
		
	}

	private static void firstDay(String key, int[][] dp) {
		int keyNum = 1 << (key.charAt(0) - 'A');
		for(int i=1; i<16; i++) {
			if((i&keyNum)!=0 && (i&1)!=0) dp[0][i]=1;
		}
	}

}
