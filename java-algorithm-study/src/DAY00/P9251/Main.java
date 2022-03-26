package DAY00.P9251;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    /*
        - LCS(dp)
        - 생각 안나면 숫자들 써놓고 규칙 찾아보기
     */

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY00/P9251/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String aStr = br.readLine();
        String bStr = br.readLine();

        int [][] dp = new int[aStr.length()+1][bStr.length()+1];

        for(int i=1; i<=aStr.length(); i++){
            for(int j=1; j<=bStr.length() ;j++){
                if(aStr.charAt(i-1) == bStr.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[aStr.length()][bStr.length()]);
    }
}
