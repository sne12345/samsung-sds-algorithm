package DAY00.P1912;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY00/P1912/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        StringTokenizer st = new StringTokenizer(br.readLine());
        int [] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int [] dp = new int[N];
        dp[0] = arr[0];
        int maxValue = dp[0];
        for(int i=1; i<N; i++){
            dp[i] = arr[i];
            if(dp[i-1] + dp[i] > dp[i]){
                dp[i] += dp[i-1];
            }
            maxValue = Math.max(dp[i], maxValue);
        }

        System.out.println(maxValue);
    }
}
