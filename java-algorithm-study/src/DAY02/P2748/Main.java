package DAY02.P2748;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static long [] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N < 2){
            N = 2;
        }
        dp = new long[N + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for(int i=3; i<=N; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[N]);

    }
}
