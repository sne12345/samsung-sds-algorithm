package DAY03.P1927;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    /*
        0. 공통
            - x가 자연수 : 배열에 넣기
            - x가 0 : 배열에서 가장 작은 값을 출력하고, 그 값을 배열에서 제거
            - 만약 배열이 비었다면 0을 출력
        1. 삽입
            - 리스트 마지막에 삽입
            - 부모와 비교하면서 계속 올라감 : root / 부모보다 클 때까지
        2. 삭제
            - root 값 출력
            - 리스트 마지막 값을 root 로 올림
            - min(왼쪽자식, 오른쪽자식) 이 자신보다 작으면 swap : 단말노드 / 자식보다 작을 때까지
     */

    public static int N;
    public static ArrayList<Integer> arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY03/P1927/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new ArrayList<>();

        // index 0은 사용x
        arr.add(0);

        int value;
        for(int i=0; i<N; i++){
            value = Integer.parseInt(br.readLine());
            if(value == 0){
                // 최소값 출력
                if(arr.size() > 1){
                    System.out.println(arr.get(1));
                } else {
                    System.out.println(0);
                    continue;
                }

                // 마지막 인덱스 값 root로 올림
                arr.set(1, arr.get(arr.size()-1));
                arr.remove(arr.size()-1);

                // min(왼쪽자식, 오른쪽자식) 이 자신보다 작으면 swap : 단말노드 / 자식보다 작을 때까지
                /*
                    1. 자식 x
                    2. 왼쪽자식만
                    3. 둘다 o
                */
                int idxFirst = 1;
                while(true){
                    int left_child = idxFirst * 2;
                    int right_child = idxFirst * 2 + 1;

                    if(arr.size()-1 < left_child){
                        break;
                    } else if (arr.size()-1 == left_child){
                        if(arr.get(left_child) < arr.get(idxFirst)) {
                            int tmp_insert = arr.get(idxFirst);
                            arr.set(idxFirst, arr.get(left_child));
                            arr.set(left_child, tmp_insert);

                            idxFirst = left_child;
                        } else {
                            break;
                        }
                    } else {
                        if(Math.min(arr.get(left_child), arr.get(right_child)) < arr.get(idxFirst)) {
                            if (arr.get(left_child) >= arr.get(right_child)) {
                                int tmp_insert = arr.get(idxFirst);
                                arr.set(idxFirst, arr.get(right_child));
                                arr.set(right_child, tmp_insert);

                                idxFirst = right_child;
                            } else {
                                int tmp_insert = arr.get(idxFirst);
                                arr.set(idxFirst, arr.get(left_child));
                                arr.set(left_child, tmp_insert);

                                idxFirst = left_child;
                            }
                        } else {
                            break;
                        }
                    }

                }
            } else {
                arr.add(value);

                int idxLast = arr.size()-1;
                while(idxLast != 1 && arr.get(idxLast) < arr.get(idxLast / 2)){
                    int tmp = arr.get(idxLast);
                    arr.set(idxLast, arr.get(idxLast / 2));
                    arr.set(idxLast / 2, tmp);

                    idxLast = idxLast / 2;
                }
            }
        }
    }
}
