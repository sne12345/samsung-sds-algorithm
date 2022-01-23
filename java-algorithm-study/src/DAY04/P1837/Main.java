package DAY04.P1837;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static int MAX = 1000000;
    public static char [] P;
    public static int K;
    public static boolean [] isNotPrime;
    public static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY04/P1837/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        P = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[MAX+1];


        for(int i=2; i<=MAX; i++){
            if(isNotPrime[i] == false) {
                primes.add(i);
                for (int j = i * 2; j <= MAX; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        for(int i=0; i<primes.size(); i++){
            int selected = primes.get(i);
            if(selected >= K){
                break;
            }
            if(checkIsBad(selected)){
                System.out.print("BAD ");
                System.out.println(selected);
                return;
            }
        }
        System.out.println("GOOD");
        return;
    }

    public static boolean checkIsBad(int x){
        int r = 0;
        for(int i=0; i<P.length; i++){
            r = (r * 10 + (P[i] - '0')) % x;
        }
        if(r == 0){
            return true;
        } else {
            return false;
        }
    }
}
