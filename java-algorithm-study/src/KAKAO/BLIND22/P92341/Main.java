package KAKAO.BLIND22.P92341;

import java.util.*;

public class Main {

    /*
        < 배운 것 >
        - 해시맵을 정렬하려면 트리맵에 넣을 것
     */

    static HashMap<String, Integer> InOutHashMap = new HashMap<>();
    static HashMap<String, Integer> totalHashMap = new HashMap<>();

    public static void main(String[] args) {
        int [] fees = {1, 461, 1, 10};
        String [] records = {"00:00 1234 IN"};

        for(int i=0; i<records.length; i++){
            String [] splitedRecord = records[i].split(" ");
            String [] splitedTime = splitedRecord[0].split(":");
            int minute = Integer.parseInt(splitedTime[0]) * 60 + Integer.parseInt(splitedTime[1]);

            if(splitedRecord[2].equals("IN")){
                InOutHashMap.put(splitedRecord[1], minute);
            } else {
                int inMinute = InOutHashMap.get(splitedRecord[1]);
                int difference = minute - inMinute;
                InOutHashMap.put(splitedRecord[1], -1);

                if(totalHashMap.containsKey(splitedRecord[1])){
                    totalHashMap.put(splitedRecord[1], totalHashMap.get(splitedRecord[1]) + difference);
                } else {
                    totalHashMap.put(splitedRecord[1], difference);
                }
            }
        }

        Set<String> keySet = InOutHashMap.keySet();
        for(String key : keySet){
            if(InOutHashMap.get(key) != -1){
                int inMinute = InOutHashMap.get(key);
                int difference = 1439 - inMinute;

                if(totalHashMap.containsKey(key)){
                    totalHashMap.put(key, totalHashMap.get(key) + difference);
                } else {
                    totalHashMap.put(key, difference);
                }
            }
        }

        Set<String> totalKeySet = totalHashMap.keySet();
        for(String key : totalKeySet){
            int totalMinute = totalHashMap.get(key);
            int price;
            if(totalMinute > fees[0]){
                totalMinute -= fees[0];
                price = (int)(fees[1] + Math.ceil((double) totalMinute / fees[2]) * fees[3]);
            } else {
                price = fees[1];
            }
            totalHashMap.put(key, price);
        }

        Map<String, Integer> sortedMap = new TreeMap<>(totalHashMap);

        int [] answer = new int[totalHashMap.size()];
        int idx = 0;
        for(String key : sortedMap.keySet()){
            answer[idx++] = totalHashMap.get(key);
            System.out.println(answer[idx-1]);
        }

    }
}
