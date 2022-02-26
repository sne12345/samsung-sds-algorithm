package DAY00.P14889;
import java.util.Scanner;

public class StartAndLink_14889_me {


    static int min_diff = Integer.MAX_VALUE;;
    static int N;
    static boolean[] ans;
    static int[][] arr;


    public static void main(String [] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N][N];
        ans = new boolean[N];

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        startLink(0, 0);

        System.out.print(min_diff);
    }

    static void startLink(int idx, int count){

        if(count == N/2){
            int start_sum = 0;
            int link_sum = 0;
            for (int j=0; j<N-1; j++){
                for (int k=j+1; k<N; k++){
                    if (ans[j] == true && ans[k] == true){
                        start_sum += arr[j][k];
                        start_sum += arr[k][j];

                    } else if (ans[j] == false && ans[k] == false){
                        link_sum += arr[j][k];
                        link_sum += arr[k][j];

                    }
                }
            }

            int min_tmp = Math.abs(start_sum - link_sum);
            if(min_tmp == 0){
                System.out.print(0);
                System.exit(0);
            }

            min_diff = Math.min(min_tmp, min_diff);
            return;
        }

        for(int i = idx; i < N; i++) {  // 이 부분 때문에 계속 시간초과 났었음
            if(!ans[i]) {
                ans[i] = true;
                startLink(i + 1, count + 1);	// 이 부분 때문에 계속 시간초과 났었음
                ans[i] = false;
            }
        }
    }
}
