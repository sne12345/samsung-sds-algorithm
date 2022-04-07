package FINAL.P15683;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer = 100;
    static int [][] map;
    static ArrayList<Cctv> cctvList = new ArrayList<>();
    static int [][][] directionX = {{{0}, {1}, {0}, {-1}}, {{0, 0}, {1, -1}}, {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}, {{0, -1, 0}, {-1, 0, 1}, {0, 1, 0}, {-1, 0, 1}}, {{0, -1, 0, 1}}};
    static int [][][] directionY = {{{1}, {0}, {-1}, {0}}, {{1,-1}, {0, 0}}, {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}, {{-1, 0, 1}, {0, 1, 0}, {-1, 0, 1}, {0, -1, 0}}, {{-1, 0, 1, 0}}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P15683/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6){
                    cctvList.add(new Cctv(i, j, map[i][j], -1));
                }
            }
        }

        dfs(0);
        System.out.println(answer);
    }

    static void dfs(int cctvIdx){
        if(cctvIdx >= cctvList.size()){
            int [][] newMap = new int[N][M];

            for(int k=0; k<N; k++){
                for(int m=0; m<M; m++){
                    newMap[k][m] = map[k][m];
                }
            }

            for(int i=0; i<cctvList.size(); i++){
                Cctv current = cctvList.get(i);

                int [] dirX = directionX[current.cNum - 1][current.selectedNum];
                int [] dirY = directionY[current.cNum - 1][current.selectedNum];
                for(int j=0; j<dirX.length; j++){
                    int dx = dirX[j];
                    int dy = dirY[j];

                    int nx = current.x;
                    int ny = current.y;
                    while(true){
                        nx += dx;
                        ny += dy;
                        if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 6){
                            break;
                        }

                        if(map[nx][ny] == 0){
                            newMap[nx][ny] = -1;
                        }
                    }
                }
            }

            int blindSpot = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(newMap[i][j] == 0){
                        blindSpot++;
                    }
                }
            }
            answer = Math.min(answer, blindSpot);

            return;
        }

        Cctv currentCCTV = cctvList.get(cctvIdx);
        int [][] currentDirX = directionX[currentCCTV.cNum-1];
        int [][] currentDirY = directionY[currentCCTV.cNum-1];

        for(int i=0; i<currentDirX.length; i++){
            cctvList.get(cctvIdx).selectedNum = i;
            dfs(cctvIdx+1);
        }
    }
}

class Cctv {
    int x;
    int y;
    int cNum;
    int selectedNum;

    public Cctv(int x, int y, int cNum, int selectedNum){
        this.x = x;
        this.y = y;
        this.cNum = cNum;
        this.selectedNum = selectedNum;
    }
}
