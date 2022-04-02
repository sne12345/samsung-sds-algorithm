package FINAL.P14499;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, cx, cy, K;
    static int [][] map;
    static int bottom = 0, top = 0, left = 0, right = 0, back = 0, front = 0;
    static int [] dx = {0, 0, -1, 1};
    static int [] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P14499/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cx = Integer.parseInt(st.nextToken());
        cy = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            int move = Integer.parseInt(st.nextToken());
            int nx = cx + dx[move-1];
            int ny = cy + dy[move-1];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M){
                continue;
            }
            int currentBottom = bottom;
            int currentTop = top;
            int currentLeft = left;
            int currentRight = right;
            int currentBack = back;
            int currentFront = front;

            switch (move) {
                case 1:
                    bottom = currentRight;
                    right = currentTop;
                    top = currentLeft;
                    left = currentBottom;
                    break;
                case 2:
                    bottom = currentLeft;
                    left = currentTop;
                    top = currentRight;
                    right = currentBottom;
                    break;
                case 3:
                    bottom = currentFront;
                    front = currentTop;
                    top = currentBack;
                    back = currentBottom;
                    break;
                case 4:
                    bottom = currentBack;
                    back = currentTop;
                    top = currentFront;
                    front = currentBottom;
                    break;
            }
            if(map[nx][ny] == 0){
                map[nx][ny] = bottom;
            } else {
                bottom = map[nx][ny];
                map[nx][ny] = 0;
            }

            System.out.println(top);

            cx = nx;
            cy = ny;
        }

    }
}
