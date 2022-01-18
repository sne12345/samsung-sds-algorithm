package DAY01.P1065;
import java.util.Scanner;

public class Main {

    public static int diff;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int upper = sc.nextInt();

        int cnt = 0;
        for(int i=1; i<=upper; i++){
            int tmp = i;
            if(tmp < 100){
                cnt++;
                continue;
            }

            int lastDigit = tmp % 10;
            tmp /= 10;
            int secondLastDigit = tmp % 10;
            tmp /= 10;

            diff = secondLastDigit - lastDigit;
            if(dfs(tmp, secondLastDigit)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    public static boolean dfs(int num, int prevDigit){

        if(num <= 0){
            return true;
        }

        int digit = num % 10;
        int nowDiff = digit - prevDigit;

        if(nowDiff != diff){
            return false;
        } else {
            return dfs(num / 10, digit);
        }
    }


    /*
    1
    2
    3
    4
    ...
    11
    12
    13
    14
    ...
    111
    123
     */

}
