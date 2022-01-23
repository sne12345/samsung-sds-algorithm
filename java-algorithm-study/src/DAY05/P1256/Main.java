package DAY05.P1256;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
        - Indexed Tree + combination(dp)

     */

    public static int N, M, K;
    public static int [][] dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY05/P1256/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N+K+1][N+K+1];

        System.out.println(combination(4,2));

    }

    public static int combination(int n, int r){
        if (n == r || r == 0){
            return 1;
        } else if(dp[n][r] != 0){
            return dp[n][r];
        } else {
            return dp[n][r] = Math.min((int) 1e9, combination(n-1, r-1) + combination(n-1, r));
        }
    }
}

