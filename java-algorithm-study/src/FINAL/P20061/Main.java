package FINAL.P20061;
import java.io.*;
import java.util.*;

public class Main {
    static int N, rSize = 4, gSize = 6, bSize = 6;
    static int [][] map;
    static long answer = 0, cnt = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P20061/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[rSize+gSize][rSize+bSize];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(t == 1) {
                int ngx = x + 1;
                int ngy = y;
                while(ngx < (rSize + gSize) && map[ngx][ngy] != 1) {
                    ngx++;
                }
                ngx--;
                map[ngx][ngy] = 1;


                int nbx = x;
                int nby = y + 1;
                while(nby < (rSize + bSize) && map[nbx][nby] != 1) {
                    nby++;
                }
                nby--;
                map[nbx][nby] = 1;
            } else if(t == 2) {
                int ngx = x + 1;
                int ngy1 = y;
                int ngy2 = y + 1;
                while(ngx < (rSize + gSize) && map[ngx][ngy1] != 1 && map[ngx][ngy2] != 1) {
                    ngx++;
                }
                ngx--;
                map[ngx][ngy1] = 1;
                map[ngx][ngy2] = 1;


                int nbx = x;
                int nby1 = y;
                int nby2 = y + 1;
                while(nby2 < (rSize + bSize) && map[nbx][nby2] != 1) {
                    nby1++;
                    nby2++;
                }
                nby1--;
                nby2--;
                map[nbx][nby1] = 1;
                map[nbx][nby2] = 1;
            } else {
                int ngx1 = x;
                int ngx2 = x + 1;
                int ngy = y;
                while(ngx2 < (rSize + gSize) && map[ngx2][ngy] != 1) {
                    ngx1++;
                    ngx2++;
                }
                ngx1--;
                ngx2--;
                map[ngx1][ngy] = 1;
                map[ngx2][ngy] = 1;


                int nbx1 = x;
                int nbx2 = x + 1;
                int nby = y + 1;
                while(nby < (rSize + bSize) && map[nbx1][nby] != 1 && map[nbx2][nby] != 1) {
                    nby++;
                }
                nby--;
                map[nbx1][nby] = 1;
                map[nbx2][nby] = 1;
            }

            // green 가득찬부분
            for(int gr=0; gr<gSize; gr++) {
                int grFlg = 1;
                for(int gc=0; gc<rSize; gc++) {
                    if(map[rSize+gr][gc] == 0) {
                        grFlg = 0;
                        break;
                    }
                }
                if(grFlg == 1) {
                    answer++;
                    for(int tr=gr-1; tr>=0; tr--) {
                        for(int tc=0; tc<rSize; tc++) {
                            map[rSize+tr+1][tc] = map[rSize+tr][tc];
                        }
                    }
                    for(int tc=0; tc<rSize; tc++) {
                        map[rSize][tc] = 0;
                    }
                }
            }

            // green 연한부분
            int gFilled = 0;
            for(int gr=0; gr<2; gr++) {
                for(int gc=0; gc<rSize; gc++) {
                    if(map[rSize+gr][gc] == 1) {
                        gFilled++;
                        break;
                    }
                }
            }
            if(gFilled > 0) {
                for(int gr=(gSize-gFilled-1); gr>=0; gr--) {
                    for(int gc=0; gc<rSize; gc++) {
                        map[rSize+gr+gFilled][gc] = map[rSize+gr][gc];
                    }
                }
                for(int gr=1; gr>=(2-gFilled); gr--) {
                    for(int gc=0; gc<rSize; gc++) {
                        map[rSize+gr][gc] = 0;
                    }
                }
            }

            // blue 가득찬부분
            for(int bC=0; bC<bSize; bC++) {
                int bcFlg = 1;
                for(int bR=0; bR<rSize; bR++) {
                    if(map[bR][rSize + bC] == 0) {
                        bcFlg = 0;
                        break;
                    }
                }
                if(bcFlg == 1) {
                    answer++;
                    for(int tc=bC-1; tc>=0; tc--) {
                        for(int tr=0; tr<rSize; tr++) {
                            map[tr][rSize+tc+1] = map[tr][rSize+tc];
                        }
                    }

                    for(int tr=0; tr<rSize; tr++) {
                        map[tr][rSize] = 0;
                    }
                }
            }

            // blue 연한부분
            int bFilled = 0;
            for(int bC=0; bC<2; bC++) {
                for(int bR=0; bR<rSize; bR++) {
                    if(map[bR][rSize+bC] == 1) {
                        bFilled++;
                        break;
                    }
                }
            }
            if(bFilled > 0) {
                for(int bC=(bSize-bFilled-1); bC>=0; bC--) {
                    for(int bR=0; bR<rSize; bR++) {
                        map[bR][rSize+bC+bFilled] = map[bR][rSize+bC];
                    }
                }
                for(int bc=1; bc>=(2-bFilled); bc--) {
                    for(int bR=0; bR<rSize; bR++) {
                        map[bR][rSize+bc] = 0;
                    }
                }
            }

        }

        for(int i=0; i<(rSize+gSize); i++) {
            for(int j=0; j<(rSize+bSize); j++) {
                if(i < rSize && j < rSize) {
                    continue;
                }
                if(map[i][j] == 1) {
                    cnt++;
                }
            }
        }

        System.out.println(answer);
        System.out.println(cnt);
    }
}
