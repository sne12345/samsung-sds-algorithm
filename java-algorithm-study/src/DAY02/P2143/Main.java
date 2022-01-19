package DAY02.P2143;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static long [] A;
    public static long [] B;
    public static ArrayList<Long> subA;
    public static ArrayList<Long> subB;


    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/DAY02/P2143/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long T = Long.parseLong(br.readLine());

        int n = Integer.parseInt(br.readLine());
        A = new long[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            A[i] = Long.parseLong(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        B = new long[m + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            B[i] = Long.parseLong(st.nextToken());
        }

        subA = new ArrayList<>();
        subB = new ArrayList<>();

        long sumA = 0, sumB = 0;
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                sumA += A[j];
                subA.add(sumA);
            }
            sumA = 0;
        }
        for(int i=0; i<m; i++){
            for(int j=i; j<m; j++){
                sumB += B[j];
                subB.add(sumB);
            }
            sumB = 0;
        }

        // 정렬
        Collections.sort(subA);
        Collections.sort(subB);


        /*
        1 1 2 3 3 4 4 5 6 7
        1 2 3 4 5 6
         */
        // 투포인터 : 앞뒤값 비교x
        int a_point = 0, b_point = subB.size() - 1;
        long sub_sum = 0, total_cnt = 0;
        while(a_point < subA.size() && b_point > -1){
            long currentA = subA.get(a_point);
            long currentB = subB.get(b_point);
            sub_sum = currentA + currentB;

            if(sub_sum == T){
                long countA = 0, countB = 0;

                while(a_point < subA.size() && subA.get(a_point) == currentA){
                    a_point++;
                    countA++;
                }

                while(b_point > -1 && subB.get(b_point) == currentB){
                    b_point--;
                    countB++;
                }
                total_cnt += countA * countB;

            } else if(sub_sum > T){
                b_point--;
            } else {
                a_point++;
            }
        }
        System.out.println(total_cnt);
    }
}
