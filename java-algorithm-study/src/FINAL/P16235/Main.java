package FINAL.P16235;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int [][] A;
    static ArrayList<Tree> [][] map;
    static int [][] nutrients;
    static int [] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int [] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P16235/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        map = new ArrayList[N][N];
        nutrients = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new ArrayList<>();
                nutrients[i][j] = 5;
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            map[r-1][c-1].add(new Tree(0, age, 1));
        }

        int currentYear = 0;
        while(currentYear < K){

            // 봄
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(map[i][j].size() >= 2) {
                        Collections.sort(map[i][j]); // 원소 두개 이상이면 sort 하기
                    }
                    for(int k=0; k<map[i][j].size(); k++){
                        int curAge = map[i][j].get(k).age;
                        if(nutrients[i][j] >= curAge){
                            map[i][j].get(k).food += curAge;
                            map[i][j].get(k).age += 1;
                            nutrients[i][j] -= curAge;
                        } else {
                            map[i][j].get(k).live = 0;
                        }
                    }
                }
            }

            // 여름
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    for(int k=0; k<map[i][j].size(); k++){
                        if(map[i][j].get(k).live == 0){
                            nutrients[i][j] += Math.floor((double)map[i][j].get(k).age / 2);
                            map[i][j].remove(k);
                            k--;
                        }
                    }
                }
            }

            // 가을
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    for(int k=0; k<map[i][j].size(); k++){
                        if(map[i][j].get(k).age % 5 == 0){
                            for(int m=0; m<8; m++){
                                int nx = i + dx[m];
                                int ny = j + dy[m];

                                if(nx >= 0 && nx < N && ny >= 0 && ny < N){
                                    map[nx][ny].add(new Tree(0, 1, 1));
                                }
                            }
                        }
                    }
                }
            }


            // 겨울
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    nutrients[i][j] += A[i][j];
                }
            }

            currentYear++;
        }

        long answer = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                answer += map[i][j].size();
            }
        }

        System.out.println(answer);
    }
}

class Tree implements Comparable<Tree> {
    int food;
    int age;
    int live;

    public Tree(int food, int age, int live){
        this.food = food;
        this.age = age;
        this.live = live;
    }

    @Override
    public int compareTo(Tree o) {
        return Integer.compare(this.age, o.age);
    }
}
