package DAY02.P11003;

import java.io.*;
import java.util.*;

public class Main {

    /*
        - dequeue
        - 최적화
        ① LinkedList 대신 ArrayDeque 사용.

        ② addLast(e) 대신 offer(e) 사용.

        ③ ArrayDeque 대신 Array 사용.

        ④ Deque에 (index, value) 객체 대신 index만 저장. value는 다른 Array에 저장.

        ⑤ for문 로직 내에서 bw.write() 하지 말고, 답을 array에 일단 저장하고 마지막 한번에 write 작업 수행.

        6. BufferedWriter 사용
     */

    public static int N, L;
    public static int [] answer;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY02/P11003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        answer = new int[N+1];

        st = new StringTokenizer(br.readLine());

        Deque<Node> pq = new ArrayDeque<>();
        for(int i=1; i<=N; i++){
            int tmp = Integer.parseInt(st.nextToken());

            while(!pq.isEmpty() && pq.getLast().num > tmp){
                pq.removeLast();
            }

            pq.offer(new Node(tmp, i));

            if(pq.getFirst().index <= i - L) {
                pq.removeFirst();
            }
            answer[i] = pq.getFirst().num;
        }

        for(int i=1; i<=N; i++){
            bw.write(answer[i] + " ");
        }
        bw.flush();
        bw.close();

    }
}

class Node {
    int num;
    int index;

    public Node(int num, int index){
        this.num = num;
        this.index = index;
    }

}
