package DAY01.P15686;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int N, M;
    public static int [][] arr;
    public static Location [] arrChicken;
    public static Location [] arrHouse;
    public static int [][] arrDistance;
    public static int houseCnt = 0, chickendCnt = 0;
    public static int minValue = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/DAY01/P15686/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arrChicken = new Location[13];
        arrHouse = new Location[2 * N];
        arr = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                // TODO: 2 들을 먼저 찾아서 arrChicken[] 에 넣는다.
                if(arr[i][j] == 1){
                    arrHouse[houseCnt++] = new Location(i, j);

                // TODO: 1 들을 찾아서 arrHouse[] 에 넣는다.
                } else if(arr[i][j] == 2){
                    arrChicken[chickendCnt++] = new Location(i, j);
                }
            }
        }

        arrDistance = new int[houseCnt][chickendCnt];
        // TODO: 1 에서 모든 2 에 대응되는 치킨 거리를 arrDistance[][] 에 넣는다.
        for(int i=0; i<houseCnt; i++){
            for(int j=0; j<chickendCnt; j++){
                arrDistance[i][j] = Math.abs(arrHouse[i].r - arrChicken[j].r) + Math.abs(arrHouse[i].c - arrChicken[j].c);
            }
        }


        boolean [] visited = new boolean[chickendCnt];
        comb(visited, 0, chickendCnt, M);

        System.out.println(minValue);
    }

    public static void comb(boolean [] visited, int start, int n, int r){

        if(r == 0){
            int sumValue = 0;
            for(int i=0; i<houseCnt; i++){
                int minEach = Integer.MAX_VALUE;
                for(int j=0; j<chickendCnt; j++) {
                    if (visited[j] == true) {
                        minEach = Math.min(minEach, arrDistance[i][j]);
                    }
                }
                sumValue += minEach;
            }
            minValue = Math.min(minValue, sumValue);

            return;
        }

        for(int i=start; i<n; i++){
            visited[i] = true;
            comb(visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
}

class Location {
    int r;
    int c;

    public Location(int r, int c){
        this.r = r;
        this.c = c;
    }
}
