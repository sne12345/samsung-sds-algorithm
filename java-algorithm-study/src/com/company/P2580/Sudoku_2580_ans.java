package com.company.P2580;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Sudoku_2580_ans {

    static ArrayList<int[]> empty_spots = new ArrayList<>();
    static int[][] matrix = new int[9][9];

    public static void main(String [] args){
        BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++) {
                matrix[i][j] = sc.nextInt();
                if (matrix[i][j] == 0) {
                    empty_spots.add(new int[]{i, j});
                }
            }
        }

        sudoku(0);

        EDA();
    }

    static boolean sudoku(int count){
        if(count == empty_spots.size()){
            return true;
        } else {
            int [] position = empty_spots.get(count);
            int n = position[0];
            int m = position[1];
            for(int i=1; i<10; i++){
                if(isPromising(matrix, n, m, i)){
                    matrix[n][m] = i;
                    if(sudoku(count+1)) return true; // 끝에 도달했으면 return true
                    else matrix[n][m] = 0;
                }
            }
        }

        return false;
    }

    static boolean isPromising(int[][] matrix, int n, int m, int num){
        int len = matrix.length;
        int n_block = (n / 3) * 3;
        int m_block = (m / 3) * 3;

        for(int i=0; i<len; i++){
            if(matrix[n][i] == num) return false;
            if(matrix[i][m] == num) return false;
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(matrix[n_block + i][m_block + j] == num) return false;
            }
        }

        return true;
    }

    public static void EDA(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(matrix[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}
