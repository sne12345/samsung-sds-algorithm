package FINAL.P17142;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int [][] map;
    static int [] selectedList;
    static ArrayList<Node> nodeList;
    static long minTime = Long.MAX_VALUE;
    static int [] dx = {0, 0, -1, 1};
    static int [] dy = {1, -1, 0, 0};
    static int [][] visited;

    static int bfs() {

        int [][] tMap = new int [N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                tMap[i][j] = map[i][j];
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>();

        visited = new int [N][N];
        for(int i=0; i<M; i++) {
            Node selected = nodeList.get(selectedList[i]);
            tMap[selected.x][selected.y] = 3;
            q.add(new Node(selected.x, selected.y, 0));
            visited[selected.x][selected.y] = 1;
        }


        int flg = 1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(tMap[i][j] == 0) {
                    flg = 0;
                    break;
                }
            }
            if(flg == 0) {
                break;
            }
        }

        if(flg == 1) {
//			System.out.println("all full");
            return 0;
        }

        while(!q.isEmpty()) {
            Node current = q.poll();

            for(int k=0; k<4; k++) {
                int nx = current.x + dx[k];
                int ny = current.y + dy[k];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == 0 && tMap[nx][ny] != 1) {
                    visited[nx][ny] = 1;
                    if(tMap[nx][ny] == 2) {
                        tMap[nx][ny] = 3;
                    } else {
                        tMap[nx][ny] = -1 * (current.time + 1);
                    }
                    q.add(new Node(nx, ny, current.time+1));


                    flg = 1;
                    for(int i=0; i<N; i++) {
                        for(int j=0; j<N; j++) {
                            if(tMap[i][j] == 0) {
                                flg = 0;
                                break;
                            }
                        }
                        if(flg == 0) {
                            break;
                        }
                    }

                    if(flg == 1) {
//						System.out.println("x=" + nx + ", y=" + ny);
                        return current.time + 1;
                    }

//					for(int i=0; i<N; i++) {
//						for(int j=0; j<N; j++) {
//							System.out.print(tMap[i][j] + " ");
//						}
//						System.out.println();
//					}
//					System.out.println();
                }
            }

        }

        return -1;

    }

    static void comb(int num, int total, int idx, int totalSelect) {

        if(idx == totalSelect) {
//			for(int i=0; i<M; i++) {
//				System.out.print(selectedList[i] + " ");
//			}
//			System.out.println();

            int result = bfs();
//			System.out.println(result);
            if(result != -1) {
                minTime = Math.min(minTime, result);
            }

            return;
        }

        for(int i=num; i<total; i++) {
            selectedList[idx] = i;
            comb(i+1, total, idx+1, totalSelect);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P17142/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        nodeList = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    nodeList.add(new Node(i, j, 0));
                }
            }
        }


        selectedList = new int [M];
        comb(0, nodeList.size(), 0, M);

        if(minTime == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minTime);
        }


    }

}

class Node implements Comparable<Node>{
    int x;
    int y;
    int time;

    public Node(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        // TODO Auto-generated method stub
        return Integer.compare(this.time, o.time);
    }
}
