package DAY00.P11444;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static long n;
    public static long [][] original = {{1, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());

        long [][] result = pow(original, n-1);

        long [][] last = {{1}, {0}};

        long [][] realResult = multiply(result, last, 2, 1, 2);

        System.out.println(realResult[0][0] % 1000000007);
    }

    public static long [][] pow(long [][] A, long exp){
        if(exp == 1L || exp == 0){
            return A;
        }

        long [][] result = pow(A, exp / 2);

        result = multiply(result, result, 2, 2, 2);

        if(exp % 2 == 1){
            result = multiply(result, original, 2, 2, 2);
        }
        return result;
    }

    public static long [][] multiply(long [][] a, long [][] b, int sizeA, int sizeB, int tmp){
        long [][] result = new long[sizeA][sizeB];
        for(int i=0; i<sizeA; i++){
            for(int j=0; j<sizeB; j++){
                for(int k=0; k<tmp; k++){
                    result[i][j] += a[i][k] * b[k][j] % 1000000007;
                }
            }
        }
        return result;
    }
}
