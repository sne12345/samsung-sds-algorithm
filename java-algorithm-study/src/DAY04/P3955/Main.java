package DAY04.P3955;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {


        // A(-x) + By = 1;
        System.setIn(new FileInputStream("src/DAY04/P3955/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());

            ExtendedGCD gcd0 = getSTR(A, B);
            long D = gcd0.r;

            // 1. 해 검증 : 배주 항등식(Ax + By = C일 때, C % D == 0이어야 해를 가질 수 있음, D = gcd(A,B)
            if(1 % D != 0) {
                System.out.println("IMPOSSIBLE");
                continue;
            }

            // 2. 초기 해 구하기
            // x0 = s * C / D;
            // y0 = t * C / D;
            long x0 = gcd0.s  * 1 / 1;
            long y0 = gcd0.t  * 1 / 1;


            // 3. 일반 해 구하기
            // x = x0 + B / 1 * k;
            // y = y0 + B / 1 * k;


            // 4. k의 범위
            // x < 0
            // x0 + B * k < 0
            // k < -x0 / B

            // 0 < y <= 1e9
            // 0 < y0 - A * k <= 1e9
            // y0 / A > * k >= (y0 + 1e9) / A

            // ( y0 - 1e9) / A <= k < y0 / A
            //                    k < - x0 / B

            // 4.2 k의 범위를 못 구할 경우, while 문으로 k 조절

            long kFromY = (long) Math.ceil((double) y0 / (double) A) - 1;
            long kFromX = (long) Math.ceil((double) -x0 / (double) B) - 1;
            long k = Math.min(kFromY, kFromX);

            long kLimitFromY = (long) Math.ceil((y0 - 1e9) / (double) A);
            if(kLimitFromY <= k){
                System.out.println(y0 - A * k);
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }

    public static ExtendedGCD getSTR(long a, long b){
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        while(r1 != 0){
            long q = r0 / r1;

            long tmp = r0 % r1;
            r0 = r1;
            r1 = tmp;

            long tmp_s = s0 - q * s1;
            s0 = s1;
            s1 = tmp_s;

            long tmp_t = t0 - q * t1;
            t0 = t1;
            t1 = tmp_t;
        }

        return new ExtendedGCD(s0, t0, r0);
    }

}

class ExtendedGCD{
    long s;
    long t;
    long r;

    public ExtendedGCD(long s, long t, long r){
        this.s = s;
        this.t = t;
        this.r = r;
    }

}
