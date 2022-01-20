package DAY03.P9202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    /*
        - 트라이 문제
        1. 트라이 트리 구현
            1) class Node:
                    Node [] child;
                    boolean isEnd;
                    boolean isHit;
        2. DFS 탐색
            1) 체크인 : ?
            2) 목적지인가? : 트라이에서 isEnd
            3) 연결된 곳 탐색 : 8방
                4) 갈 수 있는가? -> a) 맵 안에 b) 트라이 c) check 배열
                5) 간다. -> DFS
            6) 체크아웃 : ?
     */

    public static int w, b;
    public static Node root = new Node();
    public static StringBuilder sb = new StringBuilder();
    public static boolean [][] visited;
    public static char [][] map;
    public static int sum, count;
    public static String answer;

    public static int [] dx = {0, 0, 1, -1, -1, 1, -1, 1};
    public static int [] dy = {-1, 1, 0, 0, 1, -1, -1, 1};
    public static int [] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY03/P9202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        w = Integer.parseInt(br.readLine());

        String word;

        for(int i=0; i<w; i++){
            word = br.readLine();
            Node current = root;
            for(int j=0; j<word.length(); j++){

                if(!current.hasChild(word.charAt(j))){
                    current.child[word.charAt(j) - 'A'] = new Node();
                }
                current = current.getChild(word.charAt(j));
            }
            current.isEnd = true;
        }

        b = Integer.parseInt(br.readLine());
        StringBuilder resultSb = new StringBuilder();
        for(int n=0 ;n<b; n++){
            map = new char[4][4];
            visited = new boolean[4][4];
            sum = 0;
            count = 0;
            answer = "";
            sb = new StringBuilder();

            for(int i=0; i<4; i++){
                String in = br.readLine();
                for(int j=0; j<4; j++){
                    map[i][j] = in.charAt(j);
                }
            }

            for(int y=0; y<4; y++){
                for(int x=0; x<4; x++){
                    if(root.hasChild(map[y][x])){
                        search(y, x, 1, root.getChild(map[y][x]));
                    }
                }
            }
            root.clearHit();
        }

    }
    /*
        2. DFS 탐색
            1) 체크인 : ?
            2) 목적지인가? : 트라이에서 isEnd, isHit
            3) 연결된 곳 탐색 : 8방
                4) 갈 수 있는가? -> a) 맵 안에 b) 트라이 c) check 배열
                5) 간다. -> DFS
            6) 체크아웃 : ?
     */
    public static void search(int y, int x, int length, Node node){
        // 1) 체크인
        visited[y][x] = true;
        sb.append(map[y][x]);

        // 2) 목적지인가?
        if(node.isEnd && node.isHit == false){
            node.isHit = true;
            //  답 처리
            sum += score[length];
            count ++;
            String foundWord = sb.toString();
            if(compare(foundWord, answer) > 0){
                answer = foundWord;
            }
            return;
        }
        // 3) 연결된 곳 탐색
        for(int i=0; i<8; i++){
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny >= 0 && ny < 4 && nx >= 0 && nx < 4){
                if(visited[ny][nx] == false){
                    if(node.hasChild(map[y][x])){
                        search(ny, nx, length + 1, node.getChild(map[y][x]));
                    }
                }
            }
        }

        // 6) 체크아웃
        visited[y][x] = false;
        sb.deleteCharAt(length - 1);
    }

    public static int compare(String o1, String o2){
        int result = Integer.compare(o1.length(), o2.length());
        if(result == 0){
            return o1.compareTo(o2);
        } else {
            return result;
        }
    }
}

class Node {
    Node [] child = new Node[26];
    boolean isEnd;
    boolean isHit;

    boolean hasChild(char c){
        return child[c - 'A'] != null;
    }

    Node getChild(char c){
        return child[c - 'A'];
    }

    void clearHit(){
        isHit = false;
        for (int i = 0; i < child.length; i++) {
            Node node = child[i];
            if(node != null){
                node.clearHit();
            }
        }
    }

}
