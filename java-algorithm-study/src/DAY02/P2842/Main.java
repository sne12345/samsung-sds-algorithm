package DAY02.P2842;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /*
        - BFS + 투포인터

        < 배운 것 >
        - HashSet -> Array : 중복없는 배열 만들기
     */
    public static char [][] map;
    public static int [][] fatigueMap;
    public static Integer [] fatigueArr;
    public static int [] dx = { 0, 1, 1, 1, 0, -1, -1, -1};
    public static int [] dy = { -1, -1, 0, 1, 1, 1, 0, -1};
    public static int pX, pY, n, myFatigue;
    public static int count = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY02/P2842/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        fatigueMap = new int[n][n];
        myFatigue = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            String line = br.readLine();
            for(int j=0; j<n; j++){
                char ch = line.charAt(j);
                map[i][j] = ch;
                if(ch == 'K'){
                    count++;
                }
                if(ch == 'P'){
                    pX = j;
                    pY = i;
                }
            }
        }

        HashSet<Integer> fatigue = new HashSet<>();
        StringTokenizer st;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int thisFatigue = Integer.parseInt(st.nextToken());
                fatigueMap[i][j] = thisFatigue;
                fatigue.add(thisFatigue);
            }
        }

        fatigueArr = fatigue.toArray(new Integer[fatigue.size()]);

        Arrays.sort(fatigueArr);

        bfs(fatigue.size());
        System.out.println(myFatigue);

    }

    private static void bfs(int fatigueLen){
        Queue<SangDuk> queue = new LinkedList<>();

        SangDuk start = new SangDuk(pX, pY);
        int startPointer = 0;
        int endPointer = 0;

        // 투포인터
        while(startPointer <= endPointer && endPointer < fatigueLen){
            queue.clear();

            int kCount = 0;
            boolean [][] visited = new boolean[n][n];
            if(fatigueMap[pY][pX] >= fatigueArr[startPointer] && fatigueMap[pY][pX] <= fatigueArr[endPointer]){
                queue.offer(start);
                visited[pY][pX] = true;
            }

            while(!queue.isEmpty()){
                SangDuk sangDuk = queue.poll();
                for(int i=0; i<dx.length; i++){
                    int nextX = sangDuk.x + dx[i];
                    int nextY = sangDuk.y + dy[i];

                    if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n){
                        continue;
                    }

                    if(visited[nextY][nextX]){
                        continue;
                    }

                    if(fatigueArr[startPointer] > fatigueMap[nextY][nextX] || fatigueArr[endPointer] < fatigueMap[nextY][nextX]){
                        continue;
                    }

                    if(map[nextY][nextX] == 'K'){
                        kCount++;
                    }

                    visited[nextY][nextX] = true;
                    queue.offer(new SangDuk(nextX, nextY));
                }
            }

            if(count == kCount){
                myFatigue = Math.min(myFatigue, fatigueArr[endPointer] - fatigueArr[startPointer]);
                startPointer++;
            } else {
                endPointer++;
            }
        }
    }
}

class SangDuk {
    int x;
    int y;

    public SangDuk(int x, int y){
        this.x = x;
        this.y = y;
    }
}
