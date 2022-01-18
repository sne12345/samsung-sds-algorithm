package DAY02.P2003;
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int [] seq;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY02/P2003/input.txt"));

        // Scanner 보다 속도 빠름
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        // 투포인터 쓸 때는  + 1 해주기
        seq = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int low = 0, high = 0, sum = seq[0], cnt = 0;
        while(high < N){

            if(sum == M){
                cnt++;
                sum -= seq[low++];
            } else if(sum < M){
                sum += seq[++high];
            } else {
                sum -= seq[low++];
            }
        }
        System.out.println(cnt);
    }
}
