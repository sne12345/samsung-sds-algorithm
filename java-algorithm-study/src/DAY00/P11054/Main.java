package DAY00.P11054;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        - LIS(Longest Increasing Subsequence)
     */

    public static int N, maxInt = 0;
    public static int [] seq, dp, dpDown;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY00/P11054/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        seq = new int[N];
        dp = new int[N];
        dpDown = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
            dp[i] = 1;

            for(int j=0; j<i; j++){
                if(seq[j] < seq[i] && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                }
            }
        }

        for(int i=N-1; i>=0; i--){
            dpDown[i] = 1;

            for(int j=N-1; j>i; j--){
                if(seq[j] < seq[i] && dpDown[i] < dpDown[j] + 1){
                    dpDown[i] = dpDown[j] + 1;
                }
            }
        }

        for(int i=0; i<N; i++){
            maxInt = Math.max(dp[i] + dpDown[i] - 1, maxInt);
        }
        System.out.println(maxInt);
    }
}
