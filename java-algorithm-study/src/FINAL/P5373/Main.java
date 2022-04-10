package FINAL.P5373;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, K;
    static char [][] cubeInit = {{'w', 'w', 'w',
                                'w', 'w', 'w',
                                'w', 'w', 'w'},
                                {'r', 'r', 'r',
                                'r', 'r', 'r',
                                'r', 'r', 'r'},
                                {'g', 'g', 'g',
                                'g', 'g', 'g',
                                'g', 'g', 'g'},
                                {'o', 'o', 'o',
                                'o', 'o', 'o',
                                'o', 'o', 'o'},
                                {'b', 'b', 'b',
                                'b', 'b', 'b',
                                'b', 'b', 'b'},
                                {'y', 'y', 'y',
                                'y', 'y', 'y',
                                'y', 'y', 'y'}};
    static char [][] cube = new char[6][9];
    static int [][] turn = {{2, 4, 6,
                            -2, 0, 2,
                            -6, -4, -2},
                            {6, 2, -2,
                            4, 0, -4,
                            2, -2, -6}};
    static int [][][] sideTurn = {{{0,1,2}, {0,1,2}, {0,1,2}, {0,1,2}}, {{6,7,8}, {0,3,6}, {6,7,8}, {8,5,2}}, {{0,3,6}, {0,3,6}, {8,5,2}, {8,5,2}},
                                    {{0,1,2}, {6,3,0}, {0,1,2}, {2,5,8}}, {{2,5,8}, {6,3,0}, {6,3,0}, {2,5,8}}, {{6,7,8}, {6,7,8}, {6,7,8}, {6,7,8}}};
    static int [][] sideTurnIdx = {{1,2,3,4}, {0,4,5,2}, {0, 1, 5, 3},
                                    {0, 2, 5, 4}, {0, 3, 5, 1}, {3, 2, 1, 4}};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P5373/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int r=0; r<6; r++){
                for(int c=0; c<9; c++){
                    cube[r][c] = cubeInit[r][c];
                }
            }

            for(int j=0; j<K; j++) {
                String command = st.nextToken();
                char alpha = command.charAt(0);
                char sign = command.charAt(1);
                int commandIdx = alphaToIdx(alpha);

                char [][] tmpCube = new char[6][9];
                int turnIdx = (sign == '-') ? 1 : 0;

                // tmpCube 초기화
                for(int r=0; r<6; r++){
                    for(int c=0; c<9; c++){
                        tmpCube[r][c] = cube[r][c];
                     }
                }

                // 본 페이지 처리
                for(int k=0; k<9; k++){
                    tmpCube[commandIdx][k+turn[turnIdx][k]] = cube[commandIdx][k];
                }

                // 사이드 페이지 처리
                int currentPage = sideTurnIdx[commandIdx][0];
                int [] currentElementsIdx = sideTurn[commandIdx][0];
                char [] currentElements = new char[3];
                for(int t=0; t<3; t++){
                    currentElements[t] = cube[currentPage][currentElementsIdx[t]];
                }

                int plusMinus = (sign == '-') ? -1 : 1;
                int m = (sign == '-') ? 3 : 1;
                int goal = (sign == '-') ? -1 : 5;
                while(m != goal){
                    int mn = (m % 4);
                    int nextPage = sideTurnIdx[commandIdx][mn];
                    int [] nextElementsIdx = sideTurn[commandIdx][mn];

                    for(int t=0; t<3; t++){
                        tmpCube[nextPage][nextElementsIdx[t]] = currentElements[t];
                    }

                    currentPage = nextPage;
                    currentElementsIdx = sideTurn[commandIdx][mn];
                    currentElements = new char[3];
                    for(int t=0; t<3; t++){
                        currentElements[t] = cube[currentPage][currentElementsIdx[t]];
                    }

                    m += plusMinus;
                }


                // tmpCube -> cube
                for(int r=0; r<6; r++){
                    for(int c=0; c<9; c++){
                        cube[r][c] = tmpCube[r][c];
                    }
                }
            }
            for (int k = 0; k < 9; k++) {
                System.out.print(cube[0][k]);
                if ((k + 1) % 3 == 0) {
                    System.out.println();
                }
            }
        }
    }

    static int alphaToIdx(char alpha){
        if(alpha == 'U'){
            return 0;
        } else if(alpha == 'F'){
            return 1;
        } else if(alpha == 'L'){
            return 2;
        } else if(alpha == 'B'){
            return 3;
        } else if(alpha == 'R'){
            return 4;
        } else {
            return 5;
        }
    }
}
