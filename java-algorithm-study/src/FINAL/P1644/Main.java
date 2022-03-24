package FINAL.P1644;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    public static int N, answer = 0;
    public static int [] notPrimeArr, arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P1644/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        notPrimeArr = new int [N+1];
        arr = new int [N+1];

        notPrimeArr[1] = 1;
        Era();

        int arrIdx = 0;
        for(int i=2; i<=N; i++){
            if(notPrimeArr[i] == 0){
                arr[arrIdx++] = i;
            }
        }

        int first = 0, second = 0, sum = arr[0];
        while(first <= second && second < arrIdx){

            if(sum == N){
                answer++;
                sum += arr[++second];
            } else if (sum < N){
                sum += arr[++second];
            } else {
                sum -= arr[first++];
            }
        }
        System.out.println(answer);
    }

    public static void Era(){
        for(int i=2; i<=N; i++){
            int operand = 2;
            int result = i * operand;
            while(result <= N){
                notPrimeArr[result] = 1;
                result = i * operand++;
            }
        }
    }
}
