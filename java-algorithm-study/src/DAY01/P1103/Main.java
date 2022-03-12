package DAY01.P1103;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    /*
        - dfs + dp
        - 백트래킹

        < 오늘 배운 것 >
        - 백트래킹이 하고 싶으면 dfs 를 쓰자
        - dp 를 적용할 수 있는지 생각해보자
     */

    public static int N, M, answer;
    public static int arr[][];
    public static int dx [] = {1, -1, 0, 0};
    public static int dy [] = {0, 0, 1, -1};
    public static long maxInt = -1;
    public static boolean check[][];
    public static long dp [][];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY01/P1103/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        check = new boolean[N][M];
        dp = new long[N][M];

        for(int i=0; i<N; i++){
            String str = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j] = str.charAt(j) - 48;
            }
        }
        dfs(0, 0, 0);

        if(answer == -1){
            System.out.println(answer);
        } else {
            System.out.println(maxInt);
        }
    }

    public static void dfs(int x, int y, long n){

        if(dp[x][y] == 0 || dp[x][y] < n){
            dp[x][y] = n;
        } else {
            return;
        }

        for(int i=0; i<4; i++){
            int nx = x + dx[i] * arr[x][y];
            int ny = y + dy[i] * arr[x][y];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M || arr[nx][ny] == 24){
                if(n+1 > maxInt){
                    maxInt = n + 1;
                }
            } else if(check[nx][ny]){
                answer = -1;
            } else {
                check[nx][ny] = true;
                dfs(nx, ny, n + 1);
                check[nx][ny] = false;
            }
        }
    }
}
