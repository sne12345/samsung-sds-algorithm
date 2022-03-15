package DAY00.P11053;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        - LIS(Longest Increasing Subsequence)
     */

    public static int N, maxCnt = 0;
    public static int [] seq, dp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY00/P11053/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        seq = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
            dp[i] = 1;

            for(int j=0; j<i; j++){
                if(seq[i] > seq[j] && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                }
            }
            maxCnt = Math.max(dp[i], maxCnt);
        }
        System.out.println(maxCnt);

    }
}
