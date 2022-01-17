package DAY01.DFS;

public class Fibonacci {

    public static void main(String [] args){
        System.out.println(fib(7));
    }

    public static int fib(int n){
        if (n==1 || n==2){
            return 1;
        } else {
            return fib(n-1) + fib(n-2);
        }
    }
}
