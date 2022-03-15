package DAY01.P1039;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /*
        - BFS

        < 고친 점 >
        - for 문에서 j 가 i+1부터 시작
        - visited 사용
     */

    public static int N, K, maxInt = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfsCustom();

        System.out.println(maxInt);
    }

    public static void bfsCustom(){
        Queue<Element> q = new LinkedList<>();
        boolean [][] visited = new boolean[1000001][K+1];

        q.add(new Element(N, 0));
        visited[N][0] = true;

        while(!q.isEmpty()){
            Element current = q.poll();
            if(current.K == K){
                maxInt = Math.max(current.number, maxInt);
                continue;
            }
            int len = String.valueOf(current.number).length();

            for (int i = 0; i < len - 1; i++) {
                for (int j = i+1; j < len; j++) {
                    int resultInt = swap(current.number, i, j);
                    if (resultInt != -1 && !visited[resultInt][current.K + 1]) {
                        q.add(new Element(resultInt, current.K + 1));
                        visited[resultInt][current.K + 1] = true;
                    }
                }
            }
        }
    }

    public static void bfs() {
        Queue<Element> q = new LinkedList<>();
        boolean[][] visited = new boolean[1000001][K + 1];

        q.add(new Element(N, 0));
        visited[N][0] = true;

        while (!q.isEmpty()) {
            Element t = q.poll();

            // 교환 횟수가 K일 경우 최댓값 갱신 후 다음 숫자로 넘어감
            if (t.K == K) {
                maxInt = Math.max(maxInt, t.number);
                continue;
            }

            int len = String.valueOf(t.number).length();

            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    int next = swap(t.number, i , j);

                    if (next != -1 && !visited[next][t.K + 1]) {
                        q.add(new Element(next, t.K + 1));
                        visited[next][t.K + 1] = true;
                    }
                }
            }
        }

    }


    public static int swap(int n, int i, int j) {
        char[] numArr = String.valueOf(n).toCharArray();

        if (i == 0 && numArr[j] == '0') {
            return -1;
        }

        char tmp;
        tmp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] = tmp;

        return Integer.parseInt(new String(numArr));
    }
}

class Element {
    int number;
    int K;

    public Element(int number, int K){
        this.number = number;
        this.K = K;
    }
}
