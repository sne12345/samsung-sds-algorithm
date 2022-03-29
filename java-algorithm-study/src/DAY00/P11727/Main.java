package DAY00.P11727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int n;
    public static long [] dpOrigin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dpOrigin = new long[n+1];

        dpOrigin[1] = 1;
        if(n > 1) {
            dpOrigin[2] = 3;
        }

        for(int i=3; i<=n ; i++){
            dpOrigin[i] = (dpOrigin[i-1] + dpOrigin[i-2] * 2) % 10007;
        }


        System.out.println(dpOrigin[n]);

    }
}
