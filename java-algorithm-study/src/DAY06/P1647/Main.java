package DAY06.P1647;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    /*
        - 최소 신장 트리 : 크루스칼
        - Union & Find
     */

    public static int [] rootList;
    public static Edge[] edgeList;
    public static int V, E, A, B, C;
    public static long total;
    public static PriorityQueue<Integer> biggestCost;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY06/P1647/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        rootList = new int[V+1];
        edgeList = new Edge[E];
        biggestCost = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(A, B, C);
        }

        for(int i=1; i<=V; i++){
            rootList[i] = i;
        }

        Arrays.sort(edgeList);

        for(int i=0; i<E; i++){
            int rootS = find(edgeList[i].start);
            int rootE = find(edgeList[i].end);
            if(rootS != rootE){
                union(rootS, rootE);
                total += edgeList[i].cost;
                biggestCost.add(edgeList[i].cost);
            }
        }

        System.out.println(total - biggestCost.poll());
    }

    public static void union(int rootA, int rootB){
        rootList[rootA] = rootB;
    }

    public static int find(int x){
        if(rootList[x] == x){
            return x;
        } else {
            return rootList[x] = find(rootList[x]);
        }
    }
}

class Edge implements Comparable<Edge>{
    int start;
    int end;
    int cost;

    public Edge(int start, int end, int cost){
        this.start = start;
        this.end = end;
        this.cost = cost;
    }


    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.cost, o.cost);
    }
}
