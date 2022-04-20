package FINAL.P16236;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int [][] map;
    static int [] dx = {-1, 0, 0, 1};
    static int [] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P16236/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int [N][N];

        PriorityQueue<Node> nodeQ = new PriorityQueue<>();
        int sharkCurX = 0, sharkCurY = 0;

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    sharkCurX = i;
                    sharkCurY = j;
                }
            }
        }

        int totalD = 0;
        int sharkSize = 2;
        int fishCnt = 0;

        int sX = sharkCurX;
        int sY = sharkCurY;

        int [][] visited = new int[N][N];
        int flg = 1;

        int qCnt = 0;
        while(flg == 1) {

            flg = 0;
            nodeQ.add(new Node(sX, sY, 0));
            visited[sX][sY] = 1;

            while(!nodeQ.isEmpty()) {

                Node curNode = nodeQ.poll();
                int curX = curNode.x;
                int curY = curNode.y;
                int curD = curNode.distance;

                if(map[curX][curY] == 9) {
                    map[curX][curY] = 0;
                } else if(map[curX][curY] > 0 && map[curX][curY] < sharkSize) {
                    map[curX][curY] = 0;

                    fishCnt++;
                    if(fishCnt == sharkSize) {
                        sharkSize++;
                        fishCnt = 0;
                    }

                    sX = curX;
                    sY = curY;

                    totalD += curD;
                    flg = 1;
//					System.out.println(curX + ", " + curY + ", " + totalD);
                    qCnt++;
                    break;
                } else if(map[curX][curY] > sharkSize){
                    continue;
                }

                for(int k=0; k<4; k++) 	{
                    int nx = curX + dx[k];
                    int ny = curY + dy[k];

                    if(nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == 0) {
                        visited[nx][ny] = 1;
                        nodeQ.add(new Node(nx, ny, curD+1));
                    }
                }
            }
            nodeQ.clear();
            visited = new int[N][N];
        }
        System.out.println(totalD);
//		System.out.println(qCnt);
    }

}

class Node implements Comparable<Node>{
    int x;
    int y;
    int distance;

    public Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        if(this.distance == o.distance) {
            if(this.x == o.x) {
                return Integer.compare(this.y, o.y);
            } else {
                return Integer.compare(this.x, o.x);
            }
        } else {
            return Integer.compare(this.distance, o.distance);
        }

    }
}
