package DAY00.P2480;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer a, b, c, answer;
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        if(a == b && b == c){
            answer = 10000 + a * 1000;
        } else if(a == b){
            answer = 1000 + a * 100;
        } else if(b == c){
            answer = 1000 + c * 100;
        } else if(a == c){
            answer = 1000 + a * 100;
        } else {
            answer = Math.max(Math.max(a, b), c) * 100;
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();

    }
}
