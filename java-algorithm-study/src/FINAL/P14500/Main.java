package FINAL.P14500;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long sumAnswer = 0;
    static int [][] map;
    static int [][] tx = {{0, 0, 0, 0}, {0, 1, 2, 3}, {0, 0, 1, 1}, {0, 1, 2, 2}, {0, 0, 0, 1}, {0, 0, 1, 2}, {0, 0, 0, -1}, {0, 1, 1, 2}, {0, 0, -1, -1}, {0, 0, 0, 1}, {0, 1, 1, 2}, {0, 0, 0, -1}, {0, 1, 1, 2}, {0, 0, -1, -2}, {0, 1, 1, 1}, {0, 0, 1, 2}, {0, 0, 0, 1}, {0, 0, -1, 1}, {0, 0, 1, 1}};
    static int [][] ty = {{0, 1, 2, 3}, {0, 0, 0, 0}, {0, 1, 0, 1}, {0, 0, 0, 1}, {0, 1, 2, 0}, {0, 1, 1, 1}, {0, 1, 2, 2}, {0, 0, 1, 1}, {0, 1, 1, 2}, {0, 1, 2, 1}, {0, 0, -1, 0}, {0, 1, 2, 1}, {0, 0, 1, 0}, {0, 1, 1, 1}, {0, 0, 1, 2}, {0, 1, 0, 0}, {0, 1, 2, 2}, {0, 1, 1, 0}, {0, 1, 1, 2}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P14500/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                int currentX = i;
                int currentY = j;
                for(int k=0; k<tx.length; k++){
                    long sumCurrent = 0;
                    for(int m=0; m<4; m++){
                        int nx = currentX + tx[k][m];
                        int ny = currentY + ty[k][m];
                        if(nx >= 0 && nx < N && ny >= 0 && ny <M){
                            sumCurrent += map[nx][ny];
                        }
                    }
                    sumAnswer = Math.max(sumCurrent, sumAnswer);
                }
            }
        }
        System.out.println(sumAnswer);

    }
}
