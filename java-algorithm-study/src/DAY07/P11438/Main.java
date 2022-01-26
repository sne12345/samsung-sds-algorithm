package DAY07.P11438;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int N, M;
    public static int LogN;  // 2의 몇 제곱까지 계산할지를 저장하는 변수 (N이 10만이니 17로 선언해도 무방)
    public static int [] Depth;
    public static int [][] Parents;
    public static ArrayList<Integer> [] Map;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY07/P11438/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Map = new ArrayList[N + 1];
        Depth = new int [N + 1];

        getLogN();
        Parents = new int[LogN + 1][N + 1];

        for(int i=0; i<=N; i++){
            Map[i] = new ArrayList<>();
        }

        StringTokenizer st;
        int a, b;
        for(int i=1; i<N; i++){

            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            Map[a].add(b);
            Map[b].add(a);
        }

        doBFS(1);
        makeSparseTable();

        M = Integer.parseInt(br.readLine());
        for(int i=1; i<=M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            System.out.println(getLCA(a, b));
        }


    }

    public static int getLCA(int a, int b){
        if(Depth[a] < Depth[b]){
            return getLCA(b, a);
        }

        // 높이 맞추기
        for(int i=0; i<=LogN; i++){
            if(((Depth[a] - Depth[b]) & (1 << i)) >= 1){
                a = Parents[i][a];
            }
        }

        if(a == b){
            return a;
        }

        for(int i=LogN; i >= 0; i--){
            if(Parents[i][a] != Parents[i][b]){
                a = Parents[i][a];
                b = Parents[i][b];
            }
        }

        return Parents[0][a];
    }

    public static void makeSparseTable(){
        for(int i=1; i<=LogN; i++){
            for(int j=1; j<=N; j++){
                Parents[i][j] = Parents[i-1][Parents[i-1][j]];
            }
        }
    }

    public static void getLogN(){
        LogN = 0;
        for(int i=1; i<N; i *=2){
            LogN++;
        }
    }

    public static void doBFS(int start){
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        Depth[start] = 1;
        dq.add(start);

        while(!dq.isEmpty()){
            int now = dq.poll();
            for(int next : Map[now]){
                if(Depth[next] == 0){  // 탐색하지 않았다면,
                    Depth[next] = Depth[now] + 1;
                    Parents[0][next] = now;
                    dq.add(next);
                }
            }
        }
    }
}
