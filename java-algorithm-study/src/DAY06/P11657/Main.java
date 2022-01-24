package DAY06.P11657;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        - 벨만포드 알고리즘
        - 가중치에 음수값이 있을 때 사용할 수 있음
     */

    public static int N, M;
    public static int A, B, C;
    public static long INF = Long.MAX_VALUE;

    public static long [] answerList;
    public static Edge [] edgeList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY06/P11657/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        answerList = new long[N+1];
        edgeList = new Edge[M];

        for(int i=1; i<=N; i++){
            answerList[i] = INF;
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(A, B, C);
        }

        findShortestPath(1);
        if(findNegativePath()){
            System.out.println(-1);
        } else {
            for(int i=2; i<=N; i++){
                if(answerList[i] == INF){
                    System.out.println(-1);
                } else {
                    System.out.println(answerList[i]);
                }
            }
        }


    }

    public static void findShortestPath(int start){
        answerList[start] = 0;

        for(int i=0; i<N-1; i++){
            for(int j=0; j<M; j++){
                Edge currentEdge = edgeList[j];
                if(answerList[currentEdge.start] != INF){
                    answerList[currentEdge.end] = Math.min(answerList[currentEdge.end], answerList[currentEdge.start] + currentEdge.distance);
                }
            }
        }
    }

    public static boolean findNegativePath(){
        for(int i=0; i<M; i++){
            Edge currentEdge = edgeList[i];
            if(answerList[currentEdge.start] != INF){

                if(answerList[currentEdge.end] > answerList[currentEdge.start] + currentEdge.distance){
                    return true;
                }
            }
        }
        return false;
    }
}

class Edge {
    int start;
    int end;
    int distance;

    public Edge(int start, int end, int distance){
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
}
