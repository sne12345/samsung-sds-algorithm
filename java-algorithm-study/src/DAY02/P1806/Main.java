package DAY02.P1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int [] arr;
    public static int max_cnt;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        max_cnt = 100001;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int low = 0, high = 0, sum = arr[0], length = 0;
        while(high < N){

            if(sum < S){
                sum += arr[++high];
            } else {
                length = high - low + 1;
                if(length < max_cnt){
                    max_cnt = length;
                }
                sum -= arr[low++];
            }
        }

        if(max_cnt == 100001){
            System.out.println(0);
        } else {
            System.out.println(max_cnt);
        }
    }
}
