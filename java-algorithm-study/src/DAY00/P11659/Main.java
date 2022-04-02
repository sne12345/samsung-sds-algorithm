package DAY00.P11659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int [] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        arr[0] = Integer.parseInt(st.nextToken());
        for(int i=1; i<N; i++){
            int tmp = Integer.parseInt(st.nextToken());
            arr[i] = arr[i-1] + tmp;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if(from == 1){
                System.out.println(arr[to-1]);
            } else {
                System.out.println(arr[to - 1] - arr[from - 2]);
            }
        }
    }
}
