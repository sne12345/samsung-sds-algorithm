package FINAL.P23288;
import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K, diceD;
    static int [][] map;
    static int [] dx = {-1, 0, 1, 0};
    static int [] dy = {0, 1, 0, -1};

    static int [] curDice = {1, 5, 3, 2, 4, 6};
    static int [][] diceMove = {{3,0,2,5,4,1},
            {2,1,5,3,0,4},
            {1,5,2,0,4,3},
            {4,1,0,3,5,2}};

    static long bfs(int sx, int sy) {
        int sValue = map[sx][sy];

        Queue<Node> q = new LinkedList<>();
        int [][] visited = new int [N][M];
        q.add(new Node(sx, sy));
        visited[sx][sy] = 1;

        long cnt = 1;
        while(!q.isEmpty()) {
            Node current = q.poll();

            for(int i=0; i<4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && visited[nx][ny] == 0 && map[nx][ny] == sValue) {

                    visited[nx][ny] = 1;
                    q.add(new Node(nx, ny));
                    cnt++;
                }
            }
        }

        return cnt;
    }

    static void moveDice(int nd) {
        int [] diceChange = diceMove[nd];

        int [] tDice = new int [6];
        for(int i=0; i<6; i++) {
            tDice[diceChange[i]] = curDice[i];
        }

        for(int i=0; i<6; i++) {
            curDice[i] = tDice[i];
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P23288/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        diceD = 1;

        int cX = 0, cY = 0;
        long answer = 0;
        for(int k=0; k<K; k++) {
//			System.out.println("#" + k + "--------");
            // step 1
            int nx = cX + dx[diceD];
            int ny = cY + dy[diceD];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M) {
                diceD = (diceD + 2) % 4;
                nx = cX + dx[diceD];
                ny = cY + dy[diceD];
            }

            // step 2
            moveDice(diceD);
            long jumsoo = (bfs(nx, ny) * map[nx][ny]);
            answer += jumsoo;

            // step 3
            int A = curDice[5];
            int B = map[nx][ny];
            if(A > B) {
                diceD = (diceD + 1) % 4;
            } else if(A < B) {
                diceD = (diceD - 1 + 4) % 4;
            }

            cX = nx;
            cY = ny;
//			System.out.println("downValue : " + A + ", diceD : " + diceD);
        }

        System.out.println(answer);
    }


}

class Node {
    int x;
    int y;

    public Node (int x, int y){
        this.x = x;
        this.y = y;
    }
}

