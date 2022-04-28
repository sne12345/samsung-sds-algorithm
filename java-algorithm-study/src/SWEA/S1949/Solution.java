package SWEA.S1949;
import java.io.*;
import java.util.*;

public class Solution {

    static int T, N, K;
    static int [][] map;
    static int [] dx = {-1, 0, 1, 0};
    static int [] dy = {0, 1, 0, -1};
    static int answer = 0;

    static void dfs(int [][] visited, int cx, int cy, int cnt) {

        answer = Math.max(answer, cnt);

        for(int i=0; i<4; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if(nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == 0 && map[nx][ny] < map[cx][cy]) {
                visited[nx][ny] = 1;
                dfs(visited, nx, ny, cnt + 1);
                visited[nx][ny] = 0;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/SWEA/S1949/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            answer = 0;
            int maxValue = 0;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxValue = Math.max(maxValue, map[i][j]);
                }
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(map[i][j] == maxValue) {

                        int [][] visited = new int [N][N];
                        visited[i][j] = 1;

                        dfs(visited, i, j, 1);

                        for(int n=0; n<N; n++) {
                            for(int m=0; m<N; m++) {
                                if(i == n && j == m) {
                                    continue;
                                }

                                for(int k=1; k<=K; k++) {
                                    map[n][m] -= k;

                                    visited = new int [N][N];
                                    visited[i][j] = 1;

                                    dfs(visited, i, j, 1);
                                    map[n][m] += k;
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("#" + (t+1) + " " + answer);
        }
    }
}
