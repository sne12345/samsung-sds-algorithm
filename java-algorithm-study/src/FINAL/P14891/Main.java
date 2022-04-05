package FINAL.P14891;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String [] gearList = new String[5];
    static int [] gearRotation = new int[5];
    static int K;
    static int [] score = {1,2,4,8};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P14891/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=1; i<=4; i++){
            gearList[i] = br.readLine();
        }

        K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<K; i++){
            gearRotation = new int[5];

            st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            gearRotation[gearNum] = direction;

            char leftStatus = gearList[gearNum].charAt(6);
            char rightStatus = gearList[gearNum].charAt(2);

            int leftGear = gearNum - 1;
            while(leftGear > 0){
                if(gearList[leftGear].charAt(2) != leftStatus){
                    direction *= (-1);
                    gearRotation[leftGear] = direction;
                } else {
                    break;
                }
                leftStatus = gearList[leftGear].charAt(6);
                leftGear = leftGear - 1;
            }

            direction = gearRotation[gearNum];
            int rightGear = gearNum + 1;
            while(rightGear <= 4){
                if(gearList[rightGear].charAt(6) != rightStatus){
                    direction *= (-1);
                    gearRotation[rightGear] = direction;
                } else {
                    break;
                }
                rightStatus = gearList[rightGear].charAt(2);
                rightGear = rightGear + 1;
            }

            for(int j=1; j<=4; j++){
                int start;
                int end;
                StringBuilder sb = new StringBuilder();

                if(gearRotation[j] == -1){
                    start = 1;
                    end = 9;
                } else if(gearRotation[j] == 1){
                    start = -1;
                    end = 7;
                } else {
                    continue;
                }
                for(int k=start; k<end; k++){
                    int idx = (k + 8) % 8;
                    sb.append(gearList[j].charAt(idx));
                }
                gearList[j] = sb.toString();
            }

        }

        long total = 0;
        for(int i=1; i<=4; i++){
            if(gearList[i].charAt(0) == '1'){
                total += score[i-1];
            }
        }
        System.out.println(total);

    }
}
