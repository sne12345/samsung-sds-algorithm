package com.company.P18870;

import java.util.*;
import java.util.Map.Entry;

public class CoordinateCompression_18870_me {

    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        Integer N = sc.nextInt();
        Integer [] arr = new Integer[N];
        Map<Integer, Integer> orderedMap = new HashMap<>();


        for (int i=0; i<N; i++){
            arr[i] = sc.nextInt();
        }

        Integer [] sortedArr = arr.clone();
        Arrays.sort(sortedArr);


        Integer cnt = 0;
        for(int num : sortedArr){
            if (!orderedMap.containsKey(num)){
                orderedMap.put(num, cnt++);
            }
        };


        StringBuilder sb = new StringBuilder();     // 하나씩 출력하다가 StringBuiler 객체에 넣어서 한번에 출력하니까 채점 되기 시작함
        for(int num : arr){
            sb.append(orderedMap.get(num)).append(' ');
        }

        System.out.println(sb.toString());

    }
}
