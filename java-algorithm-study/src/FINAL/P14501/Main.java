package FINAL.P14501;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, sumAnswer = 0;
    static int [][] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P14501/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N+1][3];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N+1; i++){
            for(int j=0; j<i; j++){
                if(arr[j][0] <= (i - j) && (arr[j][2] + arr[j][1]) > arr[i][2]){
                    arr[i][2] = arr[j][2] + arr[j][1];
                }
            }
            sumAnswer = Math.max(sumAnswer, arr[i][2]);
        }

        System.out.println(sumAnswer);

    }
}
