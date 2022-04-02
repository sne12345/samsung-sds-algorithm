package KAKAO.BLIND22.P92334;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String [] id_list = {"con", "ryan"};
        String [] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k = 2;

        HashMap<String, Integer> answerHashMap = new HashMap<>();
        HashSet<String> hashReport = new HashSet<>(Arrays.asList(report));
        String [] arrayReport = hashReport.toArray(new String[0]);

        HashMap<String, Integer> hashMap = new HashMap<>();
        for(int i=0; i<arrayReport.length; i++){
            String current = arrayReport[i];
            String [] splitedCurrent = current.split(" ");
            if(hashMap.containsKey(splitedCurrent[1])) {
                hashMap.put(splitedCurrent[1], hashMap.get(splitedCurrent[1]) + 1);
            } else {
                hashMap.put(splitedCurrent[1], 1);
            }
        }

        for(int i=0; i<arrayReport.length; i++){
            String current = arrayReport[i];
            String [] splitedCurrent = current.split(" ");

            if(hashMap.containsKey(splitedCurrent[1])){
                if(hashMap.get(splitedCurrent[1]) >= k){
                    if(answerHashMap.containsKey(splitedCurrent[0])){
                        answerHashMap.put(splitedCurrent[0], answerHashMap.get(splitedCurrent[0]) + 1);
                    } else {
                        answerHashMap.put(splitedCurrent[0], 1);
                    }
                }
            }
        }

        int [] answer = new int[id_list.length];

        for(int i=0; i<id_list.length; i++){
            if(answerHashMap.containsKey(id_list[i])){
                answer[i] = answerHashMap.get(id_list[i]);
            } else {
                answer[i] = 0;
            }
        }


        for(int i=0; i<id_list.length; i++){
            System.out.println(answer[i]);
        }
    }
}
