package com.company.P2580;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Sudoku_2580_me {

    public static int[][] arr = new int[9][9];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        int tmp_result;
        Queue <String> q = new LinkedList<>();
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(arr[i][j] != 0) {
                    continue;
                }

                tmp_result = sudoku(i,j);
                if(tmp_result == -1){
                    String tmp = Integer.toString(i) + ',' + j;
                    q.add(tmp);
                } else {
                    arr[i][j] = tmp_result;
                }
            }
        }

        while(q.size() != 0){
            String out = q.poll();
            String[] out_splited = out.split(",");

            tmp_result = sudoku(Integer.parseInt(out_splited[0]),Integer.parseInt(out_splited[1]));
            if(tmp_result == -1){
                q.add(out);
            } else {
                arr[Integer.parseInt(out_splited[0])][Integer.parseInt(out_splited[1])] = tmp_result;
            }
        }
        EDA();

    }



    public static int sudoku(int x, int y){
        int[] ch_x_arr = {0,0,0,0,0,0,0,0,0};
        int[] ch_y_arr = {0,0,0,0,0,0,0,0,0};
        int[] ch_z_arr = {0,0,0,0,0,0,0,0,0};

        int result_x = 1;
        int result_y = 1;
        int result_z = 1;

        int flg_x = 0;
        int flg_y = 0;
        int flg_z = 0;
        int ans_x = -1;
        int ans_y = -1;
        int ans_z = -1;

        for(int i=0; i<9; i++){
            if(arr[x][i] != 0){
                ch_x_arr[arr[x][i]-1] = 1;
            }
            if(arr[i][y] != 0){
                ch_y_arr[arr[i][y]-1] = 1;
            }
        }

        int s_start_x = (x / 3) * 3;
        int s_start_y = (y / 3) * 3;

        for(int i=s_start_x; i<s_start_x+3; i++){
            for(int j=s_start_y; j<s_start_y+3; j++){
                if(arr[i][j] != 0){
                    ch_z_arr[arr[i][j]-1] = 1;
                }
            }
        }


        for(int i=0; i<9; i++){

            if(ch_x_arr[i] == 0){
                if (flg_x == 0){
                    flg_x = 1;
                    ans_x = i + 1;
                } else {
                    result_x = 0;
                }
            }

            if(ch_y_arr[i] == 0){
                if (flg_y == 0){
                    flg_y = 1;
                    ans_y = i + 1;
                } else {
                    result_y = 0;
                }
            }

            if(ch_z_arr[i] == 0){
                if (flg_z == 0){
                    flg_z = 1;
                    ans_z = i + 1;
                } else {
                    result_z = 0;
                }
            }
        }

        if(result_x == 1){
            return ans_x;
        } else if (result_y == 1){
            return ans_y;
        } else if(result_z == 1){
            return ans_z;
        } else{
            return -1;
        }
    }


    public static void EDA(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(arr[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
//        System.out.println();
    }
//
//    public static void test(int x, int y){
//
//        int s_start_x = (x / 3) * 3;
//        int s_start_y = (y / 3) * 3;
//        System.out.println(s_start_x);
//        System.out.println(s_start_y);
//    }
}
