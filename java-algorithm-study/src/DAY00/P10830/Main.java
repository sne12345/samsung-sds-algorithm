package DAY00.P10830;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static long B;
    public static long [][] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY00/P10830/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        arr = new long[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Long.parseLong(st.nextToken());
            }
        }

        long [][] result = pow(arr, B);

        StringBuilder sb = new StringBuilder();
        for(int k=0; k<N; k++){
            for(int j=0; j<N; j++){
                sb.append(result[k][j] % 1000);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static long [][] pow(long [][] A, long exp){
        if(exp == 1L){
            return A;
        }

        long [][] res = pow(A, exp / 2);

        res = muliply(res, res);

        if(exp % 2 == 1L){
            res = muliply(res, arr);
        }

        return res;
    }

    public static long [][] muliply(long [][] arrA, long [][] arrB){

        long [][] result = new long[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                long sumValue = 0;
                for(int k=0; k<N; k++){
                    sumValue += arrA[i][k] * arrB[k][j];
                }
                result[i][j] = sumValue % 1000;
            }
        }
        return result;

    }

}
