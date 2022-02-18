package DAY07.P5719;

import java.io.*;
import java.util.*;

public class Main {

    /*
        - 다익스트라
     */

    public static int N, M, S, D, U, V, P;
    public static int INF = Integer.MAX_VALUE;

    public static int [][] rootList;
    public static int [] shortestPath;
    public static ArrayList<Integer> [] parent;
    public static boolean [][] possiblePath;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY07/P5719/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        String nextStr = br.readLine();
        while(!nextStr.equals("0 0")){

            st = new StringTokenizer(nextStr);
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            rootList = new int[N][N];
            shortestPath = new int[N];
            parent = new ArrayList[N];
            possiblePath = new boolean[N][N];

            for(int i=0; i<N; i++){
                shortestPath[i] = INF;
                parent[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                U = Integer.parseInt(st.nextToken());
                V = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());

                rootList[U][V] = P;
                possiblePath[U][V] = true;
            }

            dijkstra();
            backTracking(S, D);

            for(int i=0; i<N; i++){
                shortestPath[i] = INF;
            }

            bw.write(dijkstra() + "\n");

            nextStr = br.readLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void backTracking(int start, int current){
        if(start == current){
            return;
        } else {
            for(Integer previous : parent[current]){
                if(possiblePath[previous][current]) {
                    possiblePath[previous][current] = false;
                    backTracking(start, previous);
                }
            }
        }
    }


    public static int dijkstra(){

        Queue<Integer> pq = new LinkedList<>(); // 노드의 인덱스 번호
        pq.add(S);
        shortestPath[S] = 0;
        while(!pq.isEmpty()){
            int current = pq.poll();

            for(int i=0; i<N; i++){
                int nextLength = rootList[current][i];

                if(possiblePath[current][i] && shortestPath[i] > shortestPath[current] + nextLength) {
                    shortestPath[i] = shortestPath[current] + nextLength;
                    parent[i].clear();
                    parent[i].add(current);
                    pq.add(i);
                } else if(possiblePath[current][i] && shortestPath[i] == shortestPath[current] + nextLength){
                    parent[i].add(current);
                }
            }
        }
        return shortestPath[D] == INF ? -1 : shortestPath[D];
    }
}


