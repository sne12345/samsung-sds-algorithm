package DAY07.P11266;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    /*
        - 단절점
     */

    public static int V, E, A, B, order;
    public static Node [] nodeList;
    public static boolean [] isCutVertex;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY07/P11266/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        isCutVertex = new boolean[V+1];
        nodeList = new Node[V+1];
        for(int i=1; i<=V; i++){
            nodeList[i] = new Node(i, 0, new ArrayList<>());
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            nodeList[A].adjList.add(B);
            nodeList[B].adjList.add(A);
        }

        order = 0;
        for(int i=1; i<=V; i++){
            if(nodeList[i].order == 0){
                dfs(i, true);
            }
        }

        ArrayList<Integer> answerList = new ArrayList<>();
        for(int i=1; i<=V; i++){
            if(isCutVertex[i]){
                answerList.add(i);
            }
        }

        Collections.sort(answerList);
        StringBuilder sb = new StringBuilder();

        System.out.println(answerList.size());
        for(Integer value : answerList){
            sb.append(value + " ");
        }
        System.out.println(sb.toString());
    }

    public static int dfs(int idx, boolean isRoot){
        order++;
        nodeList[idx].order = order;
        int rtn = order;        // 지금 정점 이후의 탐색값 중 가장 작은 값 -> low
        int child = 0;

        for(int adj : nodeList[idx].adjList){
            if(nodeList[adj].order == 0){
                child++;

                int low = dfs(adj, false);

                if(isRoot == false && low >= nodeList[idx].order){
                    isCutVertex[idx] = true;
                }
                rtn = Math.min(low, rtn);
            } else {
                rtn = Math.min(rtn, nodeList[adj].order);
            }
        }

        if(isRoot == true && child >= 2){
            isCutVertex[idx] = true;
        }

        return rtn;
    }
}

class Node {
    int num;
    int order;
    List<Integer> adjList;

    public Node(int num, int order, List<Integer> adjList){
        this.num = num;
        this.order = order;
        this.adjList = adjList;
    }
}
