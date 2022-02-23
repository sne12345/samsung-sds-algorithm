package DAY02.P13417;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static int T, N;
    public static String [] arr;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/DAY02/P13417/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            N = Integer.parseInt(br.readLine());

            arr = new String[N];
            Deque<String> dq = new ArrayDeque<>();

            st = new StringTokenizer(br.readLine());
            String first = st.nextToken();
            dq.offer(first);

            for(int j=1; j<N; j++){
                String newCard = st.nextToken();

                if(first.compareTo(newCard) < 0){
                    dq.offer(newCard);
                } else if(first.compareTo(newCard) >= 0){
                    dq.addFirst(newCard);
                    first = newCard;
                }
            }

            while(!dq.isEmpty()){
                bw.write(dq.pollFirst());
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}
