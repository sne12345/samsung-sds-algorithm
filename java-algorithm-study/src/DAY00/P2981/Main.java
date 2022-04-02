package DAY00.P2981;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        int N;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int [] arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int def1 = Math.abs(arr[0] - arr[1]);
        for(int i=0; i<N-1; i++){
            int def2 = Math.abs(arr[i] - arr[i+1]);
            def1 = gcd(def1, def2);
        }

        yaksu(def1);
    }

    public static void yaksu(int num){
        for(int i=2; i<=num; i++){
            if(num % i == 0){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static int gcd(int a, int b){
        while(b != 0){
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
