package FINAL.P12100;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        1) 왼 -> 오
            - 3,4 합쳐질 수 있으면 합치고
            - 못 합치면
                - 2,3 합쳐질 수 있으면 합치고
            - 1,2 합쳐질 수 있으면 합치고
        2) 5 * 4 번 돌면서 각각의 횟수마다 가장 큰 블 저장하기
     */

    public static int N, maxBlock = 0;
    public static int board [][];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P12100/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(board, 0);
        System.out.println(maxBlock);
    }

    public static void recur(int s [][], int cnt){
        if(cnt >= 5){
            return;
        }
        recur(moveRight(s), cnt+1);
        recur(moveUp(s), cnt+1);
        recur(moveLeft(s), cnt+1);
        recur(moveDown(s), cnt+1);
    }

    public static void display(int s [][]){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(s[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static int[][] moveRight(int original [][]){
        int [][] s = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                s[i][j] = original[i][j];
            }
        }

        for(int i=0; i<N; i++){
            int currentIdx = N-1;
            int saveIdx = N-1;
            int realIdx = N-1;
            while(currentIdx >= 0){
                if(s[i][currentIdx] != 0){
                    s[i][saveIdx] = s[i][currentIdx];
                    if(currentIdx != saveIdx){
                        s[i][currentIdx] = 0;
                    }
                    saveIdx--;
                }
                currentIdx--;
            }

            int before = s[i][N-1];
            for(int j=N-2; j>saveIdx; j--){
                if(before == -1){
                    before = s[i][j];
                    continue;
                }
                if(before == s[i][j]){
                    maxBlock = Math.max(maxBlock, before * 2);
                    s[i][realIdx] = before * 2;
                    s[i][j] = 0;
                    if(realIdx != j+1){
                        s[i][j+1] = 0;
                    }
                    realIdx--;
                    before = -1;
                } else {
                    maxBlock = Math.max(maxBlock, before);
                    s[i][realIdx] = before;
                    if(realIdx != j+1){
                        s[i][j+1] = 0;
                    }
                    realIdx--;
                    before = s[i][j];
                }
            }
            if(before != -1 && saveIdx != N-1){
                maxBlock = Math.max(maxBlock, s[i][saveIdx+1]);

                s[i][realIdx] = s[i][saveIdx+1];
                if(realIdx != saveIdx+1) {
                    s[i][saveIdx+1] = 0;
                }
            }
        }
//        display(s);
        return s;
    }


    public static int[][] moveLeft(int original [][]){

        int [][] s = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                s[i][j] = original[i][j];
            }
        }
        for(int i=0; i<N; i++){
            int currentIdx = 0;
            int saveIdx = 0;
            int realIdx = 0;
            while(currentIdx < N){
                if(s[i][currentIdx] != 0){
                    s[i][saveIdx] = s[i][currentIdx];
                    if(currentIdx != saveIdx){
                        s[i][currentIdx] = 0;
                    }
                    saveIdx++;
                }
                currentIdx++;
            }

            int before = s[i][0];
            for(int j=1; j<saveIdx; j++){
                if(before == -1){
                    before = s[i][j];
                    continue;
                }
                if(before == s[i][j]){
                    maxBlock = Math.max(maxBlock, before * 2);
                    s[i][realIdx] = before * 2;
                    s[i][j] = 0;
                    if(realIdx != j-1){
                        s[i][j-1] = 0;
                    }
                    realIdx++;
                    before = -1;
                } else {
                    maxBlock = Math.max(maxBlock, before);
                    s[i][realIdx] = before;
                    if(realIdx != j-1){
                        s[i][j-1] = 0;
                    }
                    realIdx++;
                    before = s[i][j];
                }
            }
            if(before != -1 && saveIdx != 0){
                maxBlock = Math.max(maxBlock, s[i][saveIdx-1]);

                s[i][realIdx] = s[i][saveIdx-1];
                if(realIdx != saveIdx-1) {
                    s[i][saveIdx-1] = 0;
                }
            }
        }
//        display(s);
        return s;
    }


    public static int[][] moveDown(int original [][]){

        int [][] s = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                s[i][j] = original[i][j];
            }
        }
        for(int i=0; i<N; i++){
            int currentIdx = N-1;
            int saveIdx = N-1;
            int realIdx = N-1;
            while(currentIdx >= 0){
                if(s[currentIdx][i] != 0){
                    s[saveIdx][i] = s[currentIdx][i];
                    if(currentIdx != saveIdx){
                        s[currentIdx][i] = 0;
                    }
                    saveIdx--;
                }
                currentIdx--;
            }

            int before = s[N-1][i];
            for(int j=N-2; j>saveIdx; j--){
                if(before == -1){
                    before = s[j][i];
                    continue;
                }
                if(before == s[j][i]){
                    maxBlock = Math.max(maxBlock, before * 2);
                    s[realIdx][i] = before * 2;
                    s[j][i] = 0;
                    if(realIdx != j+1){
                        s[j+1][i] = 0;
                    }
                    realIdx--;
                    before = -1;
                } else {
                    maxBlock = Math.max(maxBlock, before);
                    s[realIdx][i] = before;
                    if(realIdx != j+1){
                        s[j+1][i] = 0;
                    }
                    realIdx--;
                    before = s[j][i];
                }
            }
            if(before != -1 && saveIdx != N-1){
                maxBlock = Math.max(maxBlock, s[saveIdx+1][i]);

                s[realIdx][i] = s[saveIdx+1][i];
                if(realIdx != saveIdx+1) {
                    s[saveIdx+1][i] = 0;
                }
            }
        }
//        display(s);
        return s;
    }


    public static int[][] moveUp(int original [][]){

        int [][] s = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                s[i][j] = original[i][j];
            }
        }
        for(int i=0; i<N; i++){
            int currentIdx = 0;
            int saveIdx = 0;
            int realIdx = 0;
            while(currentIdx < N){
                if(s[currentIdx][i] != 0){
                    s[saveIdx][i] = s[currentIdx][i];
                    if(currentIdx != saveIdx){
                        s[currentIdx][i] = 0;
                    }
                    saveIdx++;
                }
                currentIdx++;
            }

            int before = s[0][i];
            for(int j=1; j<saveIdx; j++){
                if(before == -1){
                    before = s[j][i];
                    continue;
                }
                if(before == s[j][i]){
                    maxBlock = Math.max(maxBlock, before * 2);
                    s[realIdx][i] = before * 2;
                    s[j][i] = 0;
                    if(realIdx != j-1){
                        s[j-1][i] = 0;
                    }
                    realIdx++;
                    before = -1;
                } else {
                    maxBlock = Math.max(maxBlock, before);
                    s[realIdx][i] = before;
                    if(realIdx != j-1){
                        s[j-1][i] = 0;
                    }
                    realIdx++;
                    before = s[j][i];
                }
            }
            if(before != -1 && saveIdx != 0){
                maxBlock = Math.max(maxBlock, s[saveIdx-1][i]);

                s[realIdx][i] = s[saveIdx-1][i];
                if(realIdx != saveIdx-1) {
                    s[saveIdx-1][i] = 0;
                }
            }
        }
//        display(s);
        return s;
    }
}
