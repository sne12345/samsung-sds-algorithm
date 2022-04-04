package KAKAO.BLIND22.P92335;


public class Main {

    /*
        < 오늘 배운 것 >
        - 소수 구할 때 루트값까지만 구하면 됨
        - 진법 구하는 법
     */

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int n = 110011;
        int k = 10;
        int answer = 0;

        makeNotation(n, k);

        String [] primeList = String.valueOf(sb).split("0");
        for(int i=0; i<primeList.length; i++){
            if(primeList[i].equals("")){
                continue;
            }
            if(isPrime(Long.parseLong(primeList[i])) == 1){
                answer++;
            }
        }
        System.out.println(answer);

    }

    public static int isPrime(long n){
        if(n <= 1) return 0;
        else if(n == 2) return 1;
        for(int i = 2; i <= Math.sqrt(n); i++)
            if(n % i == 0)
                return 0;
        return 1;
    }


    static void makeNotation(int num, int k){
        if(num <= 0){
            return;
        }

        int quotient = num / k;
        int remainder = num % k;

        makeNotation(quotient, k);
        sb.append(remainder);
    }
}
