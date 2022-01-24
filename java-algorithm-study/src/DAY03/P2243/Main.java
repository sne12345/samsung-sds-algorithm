package DAY03.P2243;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    /*
        - 세그먼트 트리(Indexed Tree)
        1. 맨 아랫줄이 100만개이고 모든 원소가 0으로 구성되어 있는 트리를 만든다.
        2. a == 2
            1) c가 양수인경우 : index b 에 c를 더한다. -> update
            2) c가 음수인경우 : index b 에 c를 뺀다. -> update
        3. a == 1
            : 트리 루트부터 내려오면서 위치를 찾는다. -> query
            : 찾은 값을 -1 한다. -> update
     */

    /*
            1
          1   0
        0  1 0  0
      0 0 0 1 0 00 0
     */

    public static int n, A, B, MAX = 1000000, S;
    public static long C;
    public static long [] indexedTree;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY03/P2243/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        S = 1;
        while(S < MAX){
            S *= 2;
        }

        indexedTree = new long[2 * S];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            if(A == 1){
                int foundIndex = queryTD(B);
                updateBU(foundIndex, -1);

                System.out.println(foundIndex-S+1);
            } else {
                C = Long.parseLong(st.nextToken());

                updateBU(S+B-1, C);
            }

        }
    }


    public static int queryTD(long rank){
        int root = 1;
        while(root < S){
            int leftChild = root * 2;
            int rightChild = root * 2 + 1;

            if(indexedTree[leftChild] >= rank){
                root = leftChild;
            } else {
                rank -= indexedTree[leftChild];
                root = rightChild;
            }
        }
        return root;
    }

    public static void updateBU(int updateIndex, long plusMinus){

        while(updateIndex >= 1){
            indexedTree[updateIndex] += plusMinus;
            updateIndex = updateIndex / 2;
        }
    }
}
