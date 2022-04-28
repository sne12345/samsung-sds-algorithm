package SWEA.S2382;
import java.io.*;
import java.util.*;

public class Solution {

    static int T, N, M, K;
    static ArrayList<Node> [][] map;
    static int [] dx = {-1, 1, 0, 0};
    static int [] dy = {0, 0, -1, 1};

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/SWEA/S2382/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new ArrayList [N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = new ArrayList<>();
                }
            }
            for(int k=0; k<K; k++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int wNum = Integer.parseInt(st.nextToken());
                int wDirection = Integer.parseInt(st.nextToken());

                map[x][y].add(new Node(k, wNum, wDirection-1));
            }

            for(int m=0; m<M; m++) {
                ArrayList<Node> [][] tMap = new ArrayList [N][N];
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        tMap[i][j] = new ArrayList<>();
                    }
                }

                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        if(map[i][j].size() == 1) {
                            Node selected = map[i][j].get(0);
                            int nx = i + dx[selected.direction];
                            int ny = j + dy[selected.direction];

                            if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
                                map[i][j].remove(0);
                                if(nx == 0 || nx == (N-1) || ny == 0 || ny == (N-1)) {
                                    selected.wNum = (int)Math.floor((double)selected.wNum / 2);

                                    if(selected.direction == 0 || selected.direction == 2) {
                                        selected.direction++;
                                    } else {
                                        selected.direction--;
                                    }
                                }
                                if(selected.wNum != 0) {
                                    tMap[nx][ny].add(selected);
                                }
                            }
                        } else if(map[i][j].size() > 1) {
                            System.out.println("error");
                        }
                    }
                }

                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        if(tMap[i][j].size() > 1) {
                            long maxNum = tMap[i][j].get(0).wNum;
                            Node maxNode = tMap[i][j].get(0);
                            long sumValue = tMap[i][j].get(0).wNum;
                            for(int w=1; w<tMap[i][j].size(); w++) {
                                sumValue += tMap[i][j].get(w).wNum;
                                if(tMap[i][j].get(w).wNum > maxNum) {
                                    maxNum = tMap[i][j].get(w).wNum;
                                    maxNode = tMap[i][j].get(w);
                                }
                            }
                            tMap[i][j].clear();

                            maxNode.wNum = sumValue;
                            tMap[i][j].add(maxNode);
                        }
                    }
                }

                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        map[i][j].clear();
                        for(int w=0; w<tMap[i][j].size(); w++) {
                            map[i][j].add(tMap[i][j].get(w));
                        }
                    }
                }
            }


            long answer = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {

                    if(map[i][j].size() > 0) {
                        answer += map[i][j].get(0).wNum;
                    }
                }
            }
            System.out.println("#" + (t+1) + " " + answer);
        }
    }

}

class Node {
    int wIdx;
    long wNum;
    int direction;

    public Node(int wIdx, long wNum, int direction) {
        this.wIdx = wIdx;
        this.wNum = wNum;
        this.direction = direction;
    }
}
