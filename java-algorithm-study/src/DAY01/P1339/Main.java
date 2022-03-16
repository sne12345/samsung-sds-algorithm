package DAY01.P1339;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    /*
        - 수학...아이디어
     */
    public static int N;
    public static int [] alphabetSum = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public static String [] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY01/P1339/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new String[N];

        for(int i=0; i<N; i++){
            arr[i] = br.readLine();
        }


        for(int i=0; i<N; i++){
            for(int j=0; j<arr[i].length(); j++){
                alphabetSum[arr[i].charAt(j) - 'A'] += Math.pow(10, arr[i].length()-j-1);
            }
        }

        Arrays.sort(alphabetSum);

        int ans = 0;
        for(int x=25; x>=17; x--){
            ans += alphabetSum[x] * (x - 16);
        }

        System.out.println(ans);
    }
}
