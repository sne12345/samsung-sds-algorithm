package FINAL.P3273;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int n, x, answer = 0;
    public static int arr [];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P3273/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        x = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        int start = 0, end = n-1;
        while(start < end){
            long sum = arr[start] + arr[end];
            if(sum == x){
                answer++;
                start++;
            } else if(sum > x){
                end--;
            } else {
                start++;
            }
        }
        System.out.println(answer);
    }
}
