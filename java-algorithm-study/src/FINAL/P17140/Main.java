package FINAL.P17140;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int r, c, k;
    static int rSize, cSize;
    static int [][] map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P17140/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int [100][100];

        rSize = 3;
        cSize = 3;

        for(int i=0; i<rSize; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<cSize; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while(true) {

            if(map[r-1][c-1] == k || answer >= 101) {
                break;
            }
            answer++;
            int [][] tMap = new int[100][100];
            if(rSize >= cSize) {
                int maxCol = 0;

                for(int i=0; i<rSize; i++) {
                    ArrayList<Integer> arr = new ArrayList<>();
                    PriorityQueue<Count> pq = new PriorityQueue<>();

                    for(int j=0; j<cSize; j++) {
                        if(map[i][j] != 0) {
                            arr.add(map[i][j]);
                        }
                    }
                    Collections.sort(arr);

                    if(arr.size() >= 1) {
                        int num = arr.get(0);
                        int cnt = 1;
                        for(int j=1; j<arr.size(); j++) {
                            if(num == arr.get(j)) {
                                cnt++;
                            } else {
                                pq.add(new Count(num, cnt));

                                num = arr.get(j);
                                cnt = 1;
                            }
                        }
                        pq.add(new Count(num, cnt));
                    }

                    int column = 0;
                    while(!pq.isEmpty() && column < 100) {
                        Count current = pq.poll();
                        tMap[i][column++] = current.num;
                        tMap[i][column++] = current.cnt;
                    }
                    maxCol = Math.max(maxCol, column);
                }
                cSize = maxCol;
            } else {
                int maxRow = 0;

                for(int i=0; i<cSize; i++) {
                    ArrayList<Integer> arr = new ArrayList<>();
                    PriorityQueue<Count> pq = new PriorityQueue<>();

                    for(int j=0; j<rSize; j++) {
                        if(map[j][i] != 0) {
                            arr.add(map[j][i]);
                        }
                    }
                    Collections.sort(arr);

                    if(arr.size() >= 1) {
                        int num = arr.get(0);
                        int cnt = 1;
                        for(int j=1; j<arr.size(); j++) {
                            if(num == arr.get(j)) {
                                cnt++;
                            } else {
                                pq.add(new Count(num, cnt));

                                num = arr.get(j);
                                cnt = 1;
                            }
                        }
                        pq.add(new Count(num, cnt));
                    }

                    int row = 0;
                    while(!pq.isEmpty() && row < 100) {
                        Count current = pq.poll();
                        tMap[row++][i] = current.num;
                        tMap[row++][i] = current.cnt;
                    }
                    maxRow = Math.max(maxRow, row);
                }
                rSize = maxRow;
            }

//			System.out.println("row="+rSize+",col="+cSize);
            map = new int[100][100];
            for(int i=0; i<rSize; i++) {
                for(int j=0; j<cSize; j++) {
                    map[i][j] = tMap[i][j];
//					System.out.print(map[i][j]);
                }
//				System.out.println();
            }
//			System.out.println();
        }

        if(answer >= 101) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }


    }

}

class Count implements Comparable<Count> {
    int num;
    int cnt;

    public Count(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Count o) {
        if(this.cnt == o.cnt) {
            return Integer.compare(this.num, o.num);
        } else {
            return Integer.compare(this.cnt, o.cnt);
        }
    }
}

