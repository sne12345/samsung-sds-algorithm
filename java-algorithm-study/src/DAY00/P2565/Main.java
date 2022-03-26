package DAY00.P2565;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    /*
        - LIS(dp)
        - dp[i] < dp[j] + 1
     */

    public static int N, maxValue = 0;
    public static ArrayList<Line> lineList;
    public static int [] dp;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/DAY00/P2565/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lineList = new ArrayList<>();
        dp = new int[101];

        lineList.add(new Line(-1, -1));

        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lineList.add(new Line(a, b));
        }

        Collections.sort(lineList);

        for(int i=1; i<=N; i++){
            dp[i] = 1;
            for(int j=1; j<i; j++){
                if(lineList.get(j).bIdx < lineList.get(i).bIdx && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                }
            }
            maxValue = Math.max(maxValue, dp[i]);
        }

        System.out.println(N - maxValue);
    }
}

class Line implements Comparable<Line> {
    int aIdx;
    int bIdx;

    public Line(int aIdx, int bIdx){
        this.aIdx = aIdx;
        this.bIdx = bIdx;
    }

    @Override
    public int compareTo(Line o) {
        return Integer.compare(this.aIdx, o.aIdx);
    }
}
