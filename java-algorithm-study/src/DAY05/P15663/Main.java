package DAY05.P15663;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    /*
        - 백트래킹
        - 조합
     */

    public static int N, M;
    public static ArrayList<Integer> arr;
    public static boolean [] possible;
    public static ArrayList<String> answerList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY05/P15663/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new ArrayList<>();
        possible = new boolean[N];
        answerList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);
        backTracking(0, M, new int[M]);
        Set<String> answerSet = new LinkedHashSet<>(answerList);
        answerList = new ArrayList<>(answerSet);

        for(int i=0; i<answerList.size(); i++){
            System.out.println(answerList.get(i));
        }


    }

    public static void backTracking(int curIdx, int totalCnt, int [] sequence){
        if(curIdx == totalCnt){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<totalCnt; i++){
                sb.append(sequence[i] + " ");
            }
            answerList.add(sb.toString());
        } else {
            for(int i=0; i<N; i++){
                if(!possible[i]) {
                    sequence[curIdx] = arr.get(i);
                    possible[i] = true;
                    backTracking(curIdx + 1, totalCnt, sequence);
                    possible[i] = false;
                }
            }
        }
    }
}
