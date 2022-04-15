package FINAL.P16234;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, L, R;
    static int [][] map;
    static Queue<Node> q = new LinkedList<>();
    static ArrayList<Node> nodeList = new ArrayList<>();
    static int [][] visited;
    static int [] dx = {0, 0, 1, -1};
    static int [] dy = {1, -1, 0, 0};
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P16234/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            int flg = 1;
            visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == 1) {
                        continue;
                    }

                    q = new LinkedList<>();
                    nodeList = new ArrayList<>();

                    q.add(new Node(i, j));
                    nodeList.add(new Node(i, j));
                    visited[i][j] = 1;
                    long pSum = map[i][j];

                    while (!q.isEmpty()) {
                        Node current = q.poll();

                        for (int idx = 0; idx < 4; idx++) {
                            int nx = current.x + dx[idx];
                            int ny = current.y + dy[idx];

                            if (nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == 0) {
                                long diff = Math.abs(map[current.x][current.y] - map[nx][ny]);

                                if (diff >= L && diff <= R) {
                                    visited[nx][ny] = 1;
                                    pSum += map[nx][ny];
                                    q.add(new Node(nx, ny));
                                    nodeList.add(new Node(nx, ny));
                                }
                            }
                        }
                    }

                    int nodeSize = nodeList.size();
                    int result = (int) Math.floor((double) pSum / nodeSize);
                    for (int k = 0; k < nodeSize; k++) {
                        if(map[nodeList.get(k).x][nodeList.get(k).y] != result){
                            flg = 0;
                        }
                        map[nodeList.get(k).x][nodeList.get(k).y] = result;
                    }
                }
            }
            if(flg == 1){
                break;
            }
            answer++;
        }
        System.out.println(answer);

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
