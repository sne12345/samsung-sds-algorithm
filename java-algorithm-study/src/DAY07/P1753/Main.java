package DAY07.P1753;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    /*
        - 다익스트라 알고리즘
        1. 출발점으로부터 연결된 노드들을 answer 에 업데이트 시켜준다. visited 체크
        2. 가중치 최소값을 가지는 노드를 방문한다. visited 체크
        3. 해당 노드로부터 또 다른 노드를 방문하면서, 기존의 가중치 > ( 2번 노드 가중치 + 3번 노드 가중치 ) 이면 업데이트한다.
        4. 모든 노드를 방문했다면 끝난다.
     */

    public static int V, E, K;
    public static int INF = Integer.MAX_VALUE;
    public static int [] answer;
    public static ArrayList<Info> [] nodeList;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY07/P1753/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        nodeList = new ArrayList[V+1];
        answer = new int[V+1];

        for(int i=1; i<=V; i++){
            nodeList[i] = new ArrayList<>();
            answer[i] = INF;
        }

        int u, v, w;
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            nodeList[u].add(new Info(v, w));
        }

        // 1. 출발점으로부터 연결된 노드들을 answer 에 업데이트 시켜준다. visited 체크

        // 2. 가중치 최소값을 가지는 노드를 방문한다. visited 체크
        PriorityQueue<Info> pq = new PriorityQueue<>();
        answer[K] = 0;
        pq.add(new Info(K, 0));

        while(!pq.isEmpty()) {

            Info current = pq.poll();

            if(current.distance > answer[current.node]){
                continue;
            }

            // 3. 해당 노드로부터 연결된 또 다른 노드를 방문하면서, 기존의 가중치 > ( 2번 노드 가중치 + 3번 노드 가중치 ) 이면 업데이트한다.
            for (Info adj : nodeList[current.node]) {
                int tmpA = answer[current.node] + adj.distance;
                int tmpB = answer[adj.node];
                if(answer[current.node] + adj.distance < answer[adj.node]){
                    answer[adj.node] = answer[current.node] + adj.distance;
                    pq.add(new Info(adj.node, answer[adj.node]));
                }
            }
        }

        for(int i = 1 ; i<= V ; i++) {
            if(answer[i] != INF) {
                bw.write(answer[i] + "\n");
            } else {
                bw.write("INF" + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}

class Info implements Comparable<Info> {
    int node;
    int distance;

    public Info(int node, int distance){
        this.node = node;
        this.distance = distance;
    }

    @Override
    public int compareTo(Info o) {
        return Integer.compare(distance, o.distance);
    }
}

