package FINAL.P23290;
import java.io.*;
import java.util.*;

public class Main {

    static int M, S, sX, sY;
    static ArrayList<Node> [][] map, sMap;
    static int [] fdx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int [] fdy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int [] sdx = {-1, 0, 1, 0};
    static int [] sdy = {0, -1, 0, 1};
    static int [][] visited;
    static int maxKill;
    static int [] sAns;
    static int [][] smellMap;

    static void sComb(int idx, int cx, int cy, int [] tAns, int killF) {
        if(idx == 3) {

            if(maxKill < killF) {
                maxKill = killF;
                for(int i=0; i<3; i++) {
                    sAns[i] = tAns[i];
                }
            }
            return;
        }

        for(int i=0; i<4; i++) {
            int nx = cx + sdx[i];
            int ny = cy + sdy[i];

            if(nx >= 0 && nx < 4 && ny >= 0 && ny <4) {

                tAns[idx] = i;
                if(visited[nx][ny] == 0) {
                    visited[nx][ny] = 1;
                    sComb(idx + 1, nx, ny, tAns, killF + map[nx][ny].size());
                    visited[nx][ny] = 0;
                } else {
                    sComb(idx + 1, nx, ny, tAns, killF);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P23290/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = new ArrayList [4][4];
        sMap = new ArrayList [4][4];
        smellMap = new int [4][4];

        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                map[i][j] = new ArrayList<>();
                sMap[i][j] = new ArrayList<>();
            }
        }

        for(int m=0; m<M; m++) {
            st = new StringTokenizer(br.readLine());
            int fx = Integer.parseInt(st.nextToken()) - 1;
            int fy = Integer.parseInt(st.nextToken()) - 1;
            int fd = Integer.parseInt(st.nextToken()) - 1;
            map[fx][fy].add(new Node(fd));
        }

        st = new StringTokenizer(br.readLine());
        sX = Integer.parseInt(st.nextToken()) - 1;
        sY = Integer.parseInt(st.nextToken()) - 1;


        for(int s=0; s<S; s++) {
            // step 1
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    sMap[i][j].clear();
                    for(int f=0; f<map[i][j].size(); f++) {
                        sMap[i][j].add(new Node(map[i][j].get(f).fd));
                    }
                }
            }

            ArrayList<Node> [][] tMap = new ArrayList [4][4];
            for(int n=0; n<4; n++) {
                for(int m=0; m<4; m++) {
                    tMap[n][m] = new ArrayList<>();
                }
            }

            // step 2
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    for(int f=0; f<map[i][j].size(); f++) {
                        Node curF = map[i][j].get(f);
                        int gFlg = 0;
                        for(int plus=0; plus>-8; plus--) {
                            int nfd = (curF.fd + plus + 8) % 8;
                            int nx = i + fdx[nfd];
                            int ny = j + fdy[nfd];

                            if((nx < 0 || nx >= 4 || ny < 0 || ny >= 4) || (nx == sX && ny == sY)) {
                                continue;
                            }
                            if(smellMap[nx][ny] > 0) {
                                continue;
                            }

                            curF.fd = nfd;
                            tMap[nx][ny].add(curF);
                            gFlg = 1;
                            break;
                        }

                        if(gFlg == 0) {
                            tMap[i][j].add(curF);
                        }
                    }
                }
            }
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    map[i][j].clear();
                    for(int f=0; f<tMap[i][j].size(); f++) {
                        map[i][j].add(tMap[i][j].get(f));
                    }
                }
            }

            // step 3
            sAns = new int [3];
            maxKill = -1;
            visited = new int [4][4];
            sComb(0, sX, sY, new int [3], 0);

            for(int i=0; i<3; i++) {
                sX = sX + sdx[sAns[i]];
                sY = sY + sdy[sAns[i]];

                if(map[sX][sY].size() > 0) {
                    smellMap[sX][sY] = -1;
                }

                map[sX][sY].clear();
            }

            // step 4
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    if(smellMap[i][j] == -1) {
                        smellMap[i][j] = 1;
                    } else if(smellMap[i][j] >= 1) {
                        smellMap[i][j]++;
                    }
                    if(smellMap[i][j] == 3) {
                        smellMap[i][j] = 0;
                    }
                }
            }

            // step 5
            for(int i=0; i<4; i++) {
                for(int j=0; j<4; j++) {
                    for(int f=0; f<sMap[i][j].size(); f++) {
                        map[i][j].add(sMap[i][j].get(f));
                    }
                }
            }

        }

        int rAns = 0;
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                rAns += map[i][j].size();
            }
        }
        System.out.println(rAns);

    }

}

class Node {
    int fd;

    public Node(int fd) {
        this.fd = fd;
    }
}
