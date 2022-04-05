package FINAL.P14503;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answerValue = 0;
    static int sR, sC, sD;
    static int [][] arr;
    static int [] dx = {-1, 0, 1, 0};
    static int [] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P14503/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sR = Integer.parseInt(st.nextToken());
        sC = Integer.parseInt(st.nextToken());
        sD = Integer.parseInt(st.nextToken());

        arr = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        while(true){
            if(arr[sR][sC] == 0){
                answerValue++;
            }
            arr[sR][sC] = 2;

            int flg = 0;
            for(int i=1; i<=4; i++) {
                int nD = (sD + 4 - i) % 4;
                int nR = sR + dx[nD];
                int nC = sC + dy[nD];
                if(arr[nR][nC] == 0){
                    sD = nD;
                    sR = nR;
                    sC = nC;
                    flg = 1;
                    break;
                }
            }
            if(flg == 0){
                int bD = (sD + 4 - 2) % 4;
                int bR = sR + dx[bD];
                int bC = sC + dy[bD];
                if(arr[bR][bC] == 1){
                    break;
                } else {
                    sR = bR;
                    sC = bC;
                }
            }
        }
        System.out.println(answerValue);



    }
}
