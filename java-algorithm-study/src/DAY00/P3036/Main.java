package DAY00.P3036;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int [] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        for(int i=1; i<N; i++){
            int tmp = Integer.parseInt(st.nextToken());
            int gcf = gcp(arr[0], tmp);
            System.out.print(arr[0] / gcf + "/");
            System.out.print(tmp / gcf + "\n");
        }
    }

    public static int gcp(int a, int b){
        while(b != 0){
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }
}
