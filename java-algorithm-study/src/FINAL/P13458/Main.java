package FINAL.P13458;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        - long, double type issue
     */

    static int N;
    static long B, C;
    static long [] arr;

    public static void main(String[] args)throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P13458/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        long answer = 0;
        for(int i=0; i<N; i++){
            if(arr[i] > 0) {
                arr[i] -= B;
                answer++;
            }

            if(arr[i] > 0){
                answer += Math.ceil((double)arr[i] / C);
            }
        }

        System.out.println(answer);
    }
}
