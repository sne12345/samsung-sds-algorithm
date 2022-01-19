package DAY03.P1202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    /*
        - priority queue 사용
        1. 가방을 오름차순으로 정렬 -> 모든 가방 for loop
        2. 보석을 무게를 기준으로 오름차순으로 정렬 -> 앞부터 돌면서 무게 조건에 안맞을 때 break
        3. pq를 무게로 내림차순으로 정렬
            2. 가능한 보석을 pq에 삽입
                4. top remove 해서 sum 에 추가
     */
    public static int N, K;
    public static Jewel [] jewelList;
    public static int [] bagList;
    public static PriorityQueue<Jewel> pq;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY03/P1202/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewelList = new Jewel[N];
        bagList = new int[K];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            jewelList[i] = new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            bagList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(bagList);
        Arrays.sort(jewelList, Comparator.comparingInt(Jewel::getM));
        pq = new PriorityQueue<>(Comparator.comparingInt(Jewel::getV).reversed());

        int JIndex = 0;
        long result = 0;
        for(int i=0; i<K; i++){
            int bag_M = bagList[i];

            while(JIndex < N && jewelList[JIndex].M <= bag_M){
                pq.add(jewelList[JIndex++]);
            }

            if(!pq.isEmpty()){
                result += pq.poll().V;
            }
        }
        System.out.println(result);
    }
}

class Jewel {
    int M;
    int V;

    public int getM(){
        return M;
    }

    public int getV(){
        return V;
    }

    public Jewel(int M, int V){
        this.M = M;
        this.V = V;
    }

    @Override
    public String toString(){
        return M + ", " + V;
    }

}
