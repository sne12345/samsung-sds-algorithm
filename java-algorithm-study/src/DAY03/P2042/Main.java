package DAY03.P2042;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /*
        - Indexed Tree
        0. 배열 정의 -> 인덱스 0값 처리 필요할 수도?
        1. Init : Bottom Up
            1) 인덱스 S(2의 n승 중에 N개를 포함할 수 있는 값 중 최솟값)부터 S + N - 1까지 값 넣기
            2) 인덱스 S-1부터 하나씩 내려가면서 왼쪽자식 + 오른쪽자식 -> 인덱스 1까지
        2. Query : Top Down(DFS)
            0) DFS(left, right, index, leftAns, rightAns)
            1) 범위에서 벗어나면 -> + 0, return
            2) 범위에 포함되면 -> + 해당 노드값, return
            3) 범위에 걸치면 -> 왼쪽 자식 + 오른쪽 자식
        3. Update : Top Down(DFS)
            0) diff = c - arr[S + b - 1] -> DFS(left, right, index, target, diff)
            1) 범위에 바꿔야할 인덱스가 포함되면 -> + diff
            2) 포함되지 않으면 -> return

     */
    public static int N, M, K, S;
    public static long [] indexTree;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY03/P2042/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = 1;
        while(S < N){
            S *= 2;
        }

        indexTree = new long[2 * S];
        for(int i=0; i<N; i++){
            indexTree[S + i] = Long.parseLong(br.readLine());
        }
        for(int i=S-1; i>=1; i--){
            indexTree[i] = indexTree[i * 2] + indexTree[i * 2 + 1];
        }


        long a, b, c;
        long diff;
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());
            c = Long.parseLong(st.nextToken());

            if(a == 1){
                diff = c - indexTree[(int)(S + b - 1)];
                updateTD(1, S, 1, (int)b, diff);
            } else if(a == 2){
//                System.out.println(queryBU(b, c));
                System.out.println(queryTD(1, S, 1, (int)b, (int)c));
            }
        }
    }

    public static long queryBU(long leftAns, long rightAns){
        long left = S + leftAns - 1;
        long right = S + rightAns - 1;
        long sum = 0;
        while(left <= right){
            if(left % 2 == 1){
                sum += indexTree[(int)left++];
            }
            if(right % 2 == 0){
                sum += indexTree[(int)right--];
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    public static long queryTD(long left, long right, int index, int leftAns, int rightAns){
        if(right < leftAns && left > rightAns){
            return 0;
        } else if(index >= 2 * S){
            return 0;
        } if(left >= leftAns && right <= rightAns){
            return indexTree[index];
        }
        long mid = (left + right) / 2;
        return queryTD(left, mid, index * 2, leftAns, rightAns) +
                queryTD(mid + 1, right, index * 2 + 1, leftAns, rightAns);
    }

    public static void updateTD(long left, long right, int index, int target, long diff){
        if(target >= left && target <= right){
            indexTree[index] += diff;
            if(left != right) {
                updateTD(left, (left + right) / 2, index * 2, target, diff);
                updateTD((left + right) / 2 + 1, right, index * 2 + 1, target, diff);
            }
        } else {
            return;
        }
    }
}
