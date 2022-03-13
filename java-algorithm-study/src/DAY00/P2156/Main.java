package DAY00.P2156;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static int arr [];
    public static long dp [];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY00/P2156/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n+1];
        dp = new long[n+1];

        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());

            if(i == 1){
                dp[i] = arr[i];
            } else if(i == 2){
                dp[i] = arr[i-1] + arr[i];
            } else {
                dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]));
            }
        }

        System.out.println(dp[n]);


    }
}
