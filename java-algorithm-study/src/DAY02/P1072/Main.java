package DAY02.P1072;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    /*
        - 파라메트릭 서치
        - 이분탐색

        < 배운 것 > - 부동소수점 오차
        - Z = Y / X * 100   ---->   Z = Y * 100 / X
        - 변수에 실수형을 저장하면 오차가 발생하게 된다.
        - 실수형은 소수가 2진수로 저장되기 때문에 이를 나타낼 수 없을 경우 가장 근사한 값을 저장하기 때문이다.
        - 예를 들어 double 변수에 0.58을 저장하고 *100을 한다면 58이 나온다고 생각할 수 있지만 컴퓨터는 이를 저장하지 못하고 0.5799999로 저장하기 때문에 실제로는 57이 반환된다.
        - 따라서 실수를 계산할 때에는 최대한 정수범위 내에서 처리를 하거나 BigDecimal 을 사용해야 한다.
     */
    public static long X, Y;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY02/P1072/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Long.parseLong(st.nextToken());
        Y = Long.parseLong(st.nextToken());

        double Z_f = ((double) Y * 100 / (double) X);
        int Z = (int)(Z_f);

        PriorityQueue<Long> pq = new PriorityQueue<>();

        long start = 1;
        long end = X;
        while(start <= end){
            long mid = (start + end) / 2;

            double result_f_Y = ((double)Y + mid) * 100;
            double result_f_X = (double)X + mid;

            int result = (int)(result_f_Y / result_f_X);
            if(result > Z){
                end = mid - 1;
                pq.add(mid);
            }else {
                start = mid + 1;
            }
        }

        if(pq.isEmpty()){
            System.out.println(-1);
        } else {
            System.out.println(pq.poll());
        }

    }
}
