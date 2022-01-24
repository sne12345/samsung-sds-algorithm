package DAY06.P2252;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, M, a, b;
    public static Node [] nodeList;
    public static Queue<Node> queue;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/DAY06/P2252/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new Node[N+1];
        queue = new LinkedList<>();

        for(int i=1; i<N+1; i++){
            nodeList[i] = new Node(new ArrayList<>(), i, 0);
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            nodeList[a].adjList.add(b);
            nodeList[b].inNum++;
        }

        // 1. 최초 진입차수가 0인 정점들을 큐에 넣는다.
        for(int i=1; i<=N; i++){
            if(nodeList[i].inNum == 0){
                queue.add(nodeList[i]);
            }
        }

        // 2. 큐가 빌때까지 반복한다.
        while(!queue.isEmpty()){
            // 1) 큐에서 꺼내서
            Node current = queue.poll();
            System.out.print(current.stuNum);
            System.out.print(" ");

            List<Integer> curAdjList = current.adjList;

            // 2) 인접한 정점들의 진입차수를 -1한다.
            for(int i=0; i<curAdjList.size(); i++){
                Node selectedNode = nodeList[curAdjList.get(i)];
                selectedNode.inNum--;
                // 3) 이때, 대상 정점의 진입차수가 0이되면 해당 정점을 큐에 넣는다.
                if(selectedNode.inNum == 0){
                    queue.add(selectedNode);
                }
            }
        }
    }
}

class Node {
    List<Integer> adjList;
    int stuNum;
    int inNum;

    public Node(List<Integer> adjList, int stuNum, int inNum){
        this.adjList = adjList;
        this.stuNum = stuNum;
        this.inNum = inNum;
    }
}