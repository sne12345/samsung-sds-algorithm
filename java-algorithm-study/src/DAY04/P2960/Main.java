package DAY04.P2960;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N, K;
    public static boolean [] isNotPrime;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY04/P2960/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[N+1];

        long cnt = 0;
        int P = 1;
        int value = 2;
        while(cnt < K){
            for(int i=P+1; i<=N; i++){
                if(isNotPrime[i] == false){
                    P = i;
                    break;
                }
            }

            int multiple = 1;
            value = P;
            while(value <= N){

                if(isNotPrime[value] == false) {
                    isNotPrime[value] = true;
                    cnt++;
                    if (cnt == K) {
                        break;
                    }
                }

                multiple++;
                value = P * multiple;
            }
        }
        System.out.println(value);
    }
}
