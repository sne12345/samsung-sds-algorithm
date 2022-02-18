package DAY06.P1922;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /*
        - 최소 신장 트리 : 크루스칼
        - Union & Find
     */

    public static int N, M;
    public static int a, b, c;
    public static long sum, cnt;

    public static Edge [] edgeList;
    public static int [] nodeList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY06/P1922/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edgeList = new Edge[M];
        nodeList = new int[N+1];
        for(int i=1; i<N+1; i++){
            nodeList[i] = i;
        }

        StringTokenizer st;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(a, b, c);
        }

        Arrays.sort(edgeList);

        for(int i=0; i<M; i++){
            if(edgeList[i].start == edgeList[i].end){
                continue;
            }

            int rootX = find(edgeList[i].start);
            int rootY = find(edgeList[i].end);
            if(rootX != rootY){
                union(edgeList[i].start, edgeList[i].end, rootX, rootY);
                sum += edgeList[i].cost;
                cnt += 1;
            }
            if(cnt >= N-1){
                break;
            }
        }
        System.out.println(sum);
    }

    public static void union(int x, int y, int rootX, int rootY){
        nodeList[rootX] = rootY;
    }

    public static int find(int x){
        if(nodeList[x] == x){
            return x;
        } else {
            return nodeList[x] = find(nodeList[x]);
        }
    }
}


class Edge implements Comparable<Edge> {
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
        return Integer.compare(cost, o.cost);
    }

}
