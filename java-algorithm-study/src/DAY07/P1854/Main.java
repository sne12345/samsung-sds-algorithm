package DAY07.P1854;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /*
        - 다익스트라
     */

    public static int n, m, k;
    public static int a, b;
    public static long c;

    public static Long INF = Long.MAX_VALUE;

    public static PriorityQueue<Long> [] pqList;
    public static List<Node> [] pathList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY07/P1854/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        pqList = new PriorityQueue[n+1];
        pathList = new List[n+1];

        for(int i=1; i<=n; i++){
            pathList[i] = new ArrayList<>();
            pqList[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Long.parseLong(st.nextToken());

            pathList[a].add(new Node(b, c));
        }
        pqList[1].add((long)0);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        while(!pq.isEmpty()){
            Node current = pq.poll();

            for(Node adj : pathList[current.destination]){

                if(pqList[adj.destination].size() < k){
                    pqList[adj.destination].add(current.time + adj.time);
                    pq.add(new Node(adj.destination, current.time + adj.time));
                } else if(pqList[adj.destination].peek() > current.time + adj.time){
                    pqList[adj.destination].poll();
                    pqList[adj.destination].add(current.time + adj.time);
                    pq.add(new Node(adj.destination, current.time + adj.time));
                }
            }
        }

        for(int i=1; i<=n; i++){
            if(pqList[i].size() == k){
                System.out.println(pqList[i].poll());
            } else {
                System.out.println(-1);
            }
        }
    }
}

class Node implements Comparable<Node>{
    int destination;
    long time;

    public Node(int destination, long time){
        this.destination = destination;
        this.time = time;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.time, o.time);
    }
}
