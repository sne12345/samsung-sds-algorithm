package DAY02.P2805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        - 파라메트릭 서치(특정 범위나 주어진 조건을 만족하는 어떤 값을 찾는 것)
        - 이분탐색
        - 타입이슈 : 숫자가 크면 Long 타입을 쓰자
     */

    public static int N, M;
    public static Long [] trees;

    public static void main(String[] args) throws IOException {
        /*
        0. s = 0, e = 최대 idx, m = s와 e의 평균값
        1. sum > T : answer 넣어줌, s = m+1
        2. sum < T: e = m-1
        3. sum == T : answer 넣어줌, exit
         */
        System.setIn(new FileInputStream("src/DAY02/P2805/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new Long [N];

        st = new StringTokenizer(br.readLine());
        long max_tree = 0;
        for(int i=0; i<N; i++){
            trees[i] = Long.parseLong(st.nextToken());
            max_tree = Math.max(max_tree, trees[i]);
        }

        long s = 0, e = max_tree, m, answer = 0;
        while(s <= e){
            m = (s + e) / 2;

            long total_get = 0;
            for(int i=0; i<N; i++){
                total_get += Long.max(trees[i] - m, 0);
            }

            if(total_get == M){
                answer = m;
                break;
            } else if(total_get > M){
                s = m + 1;
                answer = m;
            } else {
                e = m - 1;
            }
        }
        System.out.println(answer);

    }
}
