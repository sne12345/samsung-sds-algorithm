package DAY00.SDS01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CardGame2_samsung1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i=0; i<T; i++){
            int N = sc.nextInt();
            ArrayList<Integer> tmp_arr = new ArrayList<>();
            boolean [] visited = new boolean [N];

            for (int j=0; j<N; j++){
                tmp_arr.add(sc.nextInt());
            }

            Collections.sort(tmp_arr);

            int tmp_sum = 0;

            for (int j=N-1; j>=0; j--){
                if(visited[j] == true){
                    continue;
                }

                if(tmp_arr.get(j) % 2 == 0){
                    for (int k=0; k<j; k++){
                        if(tmp_arr.get(k) % 2 == 0 && visited[k] == false){
                            visited[k] = true;
                            visited[j] = true;
                            tmp_sum += tmp_arr.get(j);
                            break;
                        }
                    }
                } else {
                    for (int k=0; k<j; k++){
                        if(tmp_arr.get(k) % 2 == 1 && visited[k] == false){
                            visited[k] = true;
                            visited[j] = true;
                            tmp_sum += tmp_arr.get(j);
                            break;
                        }
                    }
                }

                if(visited[j] == false){
                    for(int z=j-1; z>=0; z--){
                        if(visited[z] == false){
                            visited[z] = true;
                            visited[j] = true;
                            tmp_sum += tmp_arr.get(z);
                            break;
                        }
                    }
                }
            }

            System.out.print("#" + Integer.toString(i+1) + " ");
            System.out.println(tmp_sum);
        }
    }
}
