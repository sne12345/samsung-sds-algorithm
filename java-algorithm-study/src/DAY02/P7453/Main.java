package DAY02.P7453;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /*
        - 투포인터
        - 시간복잡도 : 16,000,000(4000 * 4000)(두배열의합) + 32,000,000(4,000 * 4,000 * 2)(투포인터) = 48,000,000(N^2)
        - 공간복잡도 : 4,000 * 4 * 4 + 16,000,000 * 2 * 4 = 128,064,000 -> 약 128Mb

        < 최적화 >
        - ArrayList<Integer> -> int [] : Collections.sort()(MergeSort) -> Arrays.sort()(Dual Pivot QuickSort)
        - cntAB, cntCD : int -> long
     */

    public static int n;
    public static long answer;
    public static int [] A, B, C, D;
    public static int [] sumAB, sumCD;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY02/P7453/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        A = new int [n];
        B = new int [n];
        C = new int [n];
        D = new int [n];
        sumAB = new int [n * n];
        sumCD = new int [n * n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int sumABIdx = 0, sumCDIdx = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                sumAB[sumABIdx++] = A[i] + B[j];
                sumCD[sumCDIdx++] = C[i] + D[j];
            }
        }

        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        int abIdx = 0;
        int cdIdx = n * n - 1;

        while(abIdx <= (n * n - 1) && cdIdx >= 0){
            int currentAB = sumAB[abIdx];
            int currentCD = sumCD[cdIdx];
            int sumABCD = currentAB + currentCD;
            if(sumABCD == 0){

                long cntAB = 0, cntCD = 0;
                while(sumAB[abIdx] == currentAB){
                    cntAB++;
                    abIdx++;

                    if(abIdx > (n * n - 1)){
                        break;
                    }
                }

                while(sumCD[cdIdx] == currentCD){
                    cntCD++;
                    cdIdx--;

                    if(cdIdx < 0){
                        break;
                    }
                }

                answer += cntAB * cntCD;
            } else if(sumABCD < 0){
                abIdx++;
            } else {
                cdIdx--;
            }
        }

        System.out.println(answer);

    }

}
