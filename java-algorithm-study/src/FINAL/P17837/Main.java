package FINAL.P17837;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int [][] cMap;
    static int [] dx = {0, 0, -1, 1};
    static int [] dy = {1, -1, 0, 0};
    static Deque<Integer> [][] horseQList;
    static ArrayList<Location> locationList;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P17837/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cMap = new int [N][N];
        horseQList = new ArrayDeque [N][N];
        locationList = new ArrayList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                cMap[i][j] = Integer.parseInt(st.nextToken());
                horseQList[i][j] = new ArrayDeque<>();
            }
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            horseQList[r][c].add(i);
            locationList.add(new Location(r, c, d));
        }

        long answer = 0;
        int flg = 1;
        while(flg == 1 && answer < 1001) {
            answer++;

            for(int k=0; k<K; k++) {
                Location current = locationList.get(k);
                int nr = current.r + dx[current.d];
                int nc = current.c + dy[current.d];

                if(nr < 0 || nr >= N || nc < 0 || nc >= N || cMap[nr][nc] == 2) {
                    if(locationList.get(k).d == 0 || locationList.get(k).d == 2) {
                        locationList.get(k).d += 1;
                    } else {
                        locationList.get(k).d -= 1;
                    }

                    nr = current.r + dx[locationList.get(k).d];
                    nc = current.c + dy[locationList.get(k).d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N || cMap[nr][nc] == 2) {

                    } else {
                        k--;
                    }

                    continue;

                } else if(cMap[nr][nc] == 0) {
                    Deque<Integer> tQ = new ArrayDeque<>();
                    Integer tHorseId = horseQList[current.r][current.c].poll();
                    while(tHorseId != k) {
                        locationList.get(tHorseId).r = nr;
                        locationList.get(tHorseId).c = nc;
                        tQ.add(tHorseId);
                        tHorseId = horseQList[current.r][current.c].poll();
                    }
                    tQ.add(tHorseId);

                    while(!tQ.isEmpty()) {
                        horseQList[nr][nc].addFirst(tQ.pollLast());
                    }
                    locationList.get(k).r = nr;
                    locationList.get(k).c = nc;

                } else {
                    Deque<Integer> tQ = new ArrayDeque<>();
                    Integer tHorseId = horseQList[current.r][current.c].poll();
                    while(tHorseId != k) {
                        locationList.get(tHorseId).r = nr;
                        locationList.get(tHorseId).c = nc;
                        tQ.add(tHorseId);
                        tHorseId = horseQList[current.r][current.c].poll();
                    }
                    tQ.add(tHorseId);

                    while(!tQ.isEmpty()) {
                        horseQList[nr][nc].addFirst(tQ.poll());
                    }
                    locationList.get(k).r = nr;
                    locationList.get(k).c = nc;
                }

                if(horseQList[nr][nc].size() >= 4) {
                    flg = 0;
                    break;
                }
            }
        }

        if(answer > 1000) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }

    }

}

class Location {
    int r;
    int c;
    int d;

    public Location(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }

    public String toString() {
        return this.r + "," + this.c + "," + this.d + "\n";
    }
}