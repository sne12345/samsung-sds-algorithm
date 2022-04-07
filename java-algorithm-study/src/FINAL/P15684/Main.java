package FINAL.P15684;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M, H, answer = 4;
    static int [][] map;
    static ArrayList<Node> blankList = new ArrayList<>();
    static Node [] pickBucket = new Node[3];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P15684/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H+1][N+1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
        }

        for(int i=1; i<=H; i++){
            for(int j=1; j<N; j++){
                if(map[i][j] != 1 && map[i][j-1] != 1 && map[i][j+1] != 1){
                    blankList.add(new Node(i, j));
                }
            }
        }

        for(int i=0; i<=3; i++){
            dfs(0, i, 0);
        }

        if(answer == 4){
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

    }

    static void dfs(int blankIdx, int allPick, int pickIdx){
        if(pickIdx >= allPick){
            int [][] newMap = new int[H+1][N+1];
            for(int i=1; i<=H; i++){
                for(int j=1; j<N; j++){
                    newMap[i][j] = map[i][j];
                }
            }

            for(int i=0; i<allPick; i++){
                Node picked = pickBucket[i];
                newMap[picked.x][picked.y] = 1;
            }

            int answerFlg = 1;
            for(int i=1; i<=N; i++){
                int currentIdx = i;
                int floor = 1;
                while(floor <= H){
                    if(newMap[floor][currentIdx-1] == 1){
                        currentIdx -= 1;
                    } else if(newMap[floor][currentIdx] == 1){
                        currentIdx += 1;
                    }

                    floor++;
                }
                if(currentIdx != i){
                    answerFlg = 0;
                    break;
                }
            }
            if(answerFlg == 1){
                answer = Math.min(answer, allPick);
            }
            return;
        }

        if(blankIdx >= blankList.size()){
            return;
        }


        for(int i=blankIdx; i<blankList.size(); i++) {
            pickBucket[pickIdx] = blankList.get(i);
            dfs(i+1, allPick, pickIdx + 1);
        }
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
