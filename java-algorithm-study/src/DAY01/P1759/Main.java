package DAY01.P1759;
import java.io.FileInputStream;
import java.util.*;

/*
    [ 목적지인가 ]
    1. 모음 자음
    2. 길이짐

    [ 갈수있는가 ] -> 정렬하면 조건 사라짐
    1. 오름차순
    2. 이미 선택
 */

public class Main {

    public static int x, y;

    public static void main(String [] args) throws Exception {

        System.setIn(new FileInputStream("src/DAY01/P1759/input.txt"));

        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        y = sc.nextInt();

        ArrayList<String> arr = new ArrayList<>();
        for(int i=0; i<y; i++){
            String s = sc.next();
            arr.add(s);
        }

        Collections.sort(arr);

        ArrayList<String> ans_arr = new ArrayList<>();
        for(int i=0; i<y; i++){
            DFS(i, arr, ans_arr);
        }

    }


    public static void DFS(int s, ArrayList<String> total_list, ArrayList<String> tmp_list){
        // 1. 체크인
        tmp_list.add(total_list.get(s));

        // 2. 목적지 확인
        if(tmp_list.size() == x && vowel_check(tmp_list) == 1){
            for(int k=0; k<x; k++){
                System.out.print(tmp_list.get(k));
            }
            System.out.println();
        } else {

            // 3. 연결된 곳은 순회
            for (int j = s + 1; j < total_list.size(); j++) {

                // 4. 갈 수 있는가? <- 정렬함으로써 조건 필요x
                // 5. 간다.
                DFS(j, total_list, tmp_list);
            }
        }

        // 6. 체크아웃
        tmp_list.remove(tmp_list.size()-1);
    }


    public static int vowel_check(ArrayList<String> vowel_ch_arr){
        int m_cnt = 0;
        int n_cnt = 0;
        for(int i=0; i<vowel_ch_arr.size(); i++){

            if(vowel_ch_arr.get(i).equals("a") ||
                    vowel_ch_arr.get(i).equals("e") ||
                    vowel_ch_arr.get(i).equals("i") ||
                    vowel_ch_arr.get(i).equals("o") ||
                    vowel_ch_arr.get(i).equals("u")){
                m_cnt += 1;
            } else {
                n_cnt += 1;
            }
        }
        if(m_cnt >= 1 && n_cnt >= 2){
            return 1;
        } else {
            return 0;
        }
    }

}
