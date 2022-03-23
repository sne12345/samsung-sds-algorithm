package FINAL.P3190;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static int N, K, L, second = 0, isChangeEnd = 0;
    public static int [] dx = {0, 1, 0, -1};
    public static int [] dy = {1, 0, -1, 0};
    public static int [][] board;
    public static Queue<SnakeInfo> queue;
    public static Deque<Location> snakeQ;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P3190/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N][N];
        queue = new LinkedList<>();
        snakeQ = new LinkedList<>();

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int aT = Integer.parseInt(st.nextToken());
            int bT = Integer.parseInt(st.nextToken());
            board[aT - 1][bT - 1] = -1;
        }

        L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++){
            st = new StringTokenizer(br.readLine());

            int aT = Integer.parseInt(st.nextToken());
            String bT = st.nextToken();
            queue.add(new SnakeInfo(aT, bT));
        }

        board[0][0] = 1;
        snakeQ.add(new Location(0, 0));
        int headX = 0, headY = 0, direction = 0;
        SnakeInfo current = queue.poll();

        while(true){

            second++;

            headX = headX + dx[direction];
            headY = headY + dy[direction];

            if(headX < 0 || headX >= N || headY < 0 || headY >= N || board[headX][headY] == 1){
                break;
            }

            if(board[headX][headY] != -1){
                Location location = snakeQ.removeFirst();
                board[location.row][location.col] = 0;
            }
            board[headX][headY] = 1;
            snakeQ.add(new Location(headX, headY));

            if(isChangeEnd == 0 && second == current.X){
                if(current.C.equals("D")){
                    direction = (direction + 1) % 4;
                } else {
                    direction = (direction - 1 + 4) % 4;
                }

                if(!queue.isEmpty()){
                    current = queue.poll();
                } else {
                    isChangeEnd = 1;
                }
            }
        }

        System.out.println(second);
    }
}

class SnakeInfo {
    int X;
    String C;

    public SnakeInfo(int X, String C){
        this.X = X;
        this.C = C;
    }
}

class Location {
    int row;
    int col;

    public Location(int row, int col){
        this.row = row;
        this.col = col;
    }
}
