package DAY00.P2525;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Integer hour = Integer.parseInt(st.nextToken());
        Integer minute = Integer.parseInt(st.nextToken());
        Integer cookTime = Integer.parseInt(br.readLine());

        Integer currentMinutes = hour * 60 + minute;
        Integer addedMinutes = (currentMinutes + cookTime) % 1440;

        bw.write(addedMinutes / 60 + " " + addedMinutes % 60);

        bw.flush();
        bw.close();
    }
}
