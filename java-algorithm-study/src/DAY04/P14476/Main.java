package DAY04.P14476;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        1. 두 수의 최대공약수 구하는 함수 만들기 -> 유클리드 호제법
        2. N만큼 돌면서
            1) L to R, R to L -> 해당 인덱스 바로 전에서 멈추고 합치기
            2) 최대값 구하기
     */
    public static int biggest, N, answer;
    public static boolean sameGCD;

    public static int [] arr;
    public static int [] gcdLeftArr;
    public static int [] gcdRightArr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY04/P14476/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        gcdLeftArr = new int[N+1];
        gcdRightArr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            if(i == 0){
                gcdLeftArr[i] = arr[i];
            } else {
                gcdLeftArr[i] = getGCD(gcdLeftArr[i-1], arr[i]);
            }
        }

        for(int i=N-1; i>=0; i--){
            if(i == N-1){
                gcdRightArr[i] = arr[i];
            } else {
                gcdRightArr[i] = getGCD(gcdRightArr[i+1], arr[i]);
            }
        }

        biggest = 0;
        for(int i=0; i<N; i++){
            int totalGCD;
            if(i == 0){
                totalGCD = gcdRightArr[1];
            } else if(i == N-1){
                totalGCD = gcdLeftArr[N-2];
            } else {
                totalGCD = getGCD(gcdLeftArr[i-1], gcdRightArr[i+1]);
            }

            if(totalGCD >= biggest){
                if(totalGCD == biggest){
                    sameGCD = true;
                } else {
                    sameGCD = false;
                }
                biggest = totalGCD;
                answer = arr[i];
            }
        }
        if(sameGCD == true){
            System.out.println(-1);
        } else {
            System.out.print(biggest);
            System.out.print(" ");
            System.out.print(answer);
        }
    }

    public static int getGCD(int a, int b){

        while(b != 0){
            int r = a % b;
            a = b;
            b = r;
        }

        return a;
    }
}
