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
    public static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY05/P1256/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N+M+1][N+M+1];

        int M_num = M;
        boolean answer_flag = false;
        for(int i=N+M-1; i>=0; i--){
            int comb = combination(i, M_num);
            if(comb >= K){
                answer.append('a');
            } else {
                answer.append('z');
                M_num--;
                K -= comb;
            }

            if(i == M_num){
                for(int j=0; j<i; j++){
                    answer.append('z');
                }
                answer_flag = true;
                break;
            } else if(M_num < 0){
                break;
            }
        }

        if(answer_flag == false){
            System.out.println(-1);
        } else {
            System.out.println(answer.toString());
        }


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

