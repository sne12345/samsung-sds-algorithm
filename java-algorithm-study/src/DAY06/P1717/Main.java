package DAY06.P1717;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int n, m;
    public static int task, a, b;

    public static int [] parent;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY06/P1717/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i=1; i<=n; i++){
            parent[i] = i;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            task = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if(task == 0){
                union(a, b);
            } else {
                if(find(a) == find(b)){
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }

    }

    public static void union(int x, int y){
        int aRoot = find(x);
        int bRoot = find(y);
        parent[aRoot] = bRoot;
    }

    public static int find(int x){
        if(parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }
}
