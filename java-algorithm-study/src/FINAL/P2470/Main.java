package FINAL.P2470;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int N, smallest = Integer.MAX_VALUE;
    public static int [] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P2470/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int start = 0, end = N-1, answerS = 0, answerE = N-1;
        while(start < end){
            int sum = arr[start] + arr[end];
            if(Math.abs(sum) < Math.abs(smallest)) {
                smallest = sum;
                answerS = arr[start];
                answerE = arr[end];
            }

            if(sum >= 0){
                end--;
            } else {
                start++;
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(answerS);
        sb.append(" ");
        sb.append(answerE);
        System.out.println(sb);
    }
}
