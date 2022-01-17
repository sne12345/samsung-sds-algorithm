package DAY01.P3055;
import java.io.FileInputStream;
import java.util.*;

    /*
        1. 큐에서 꺼내옴 -> S, ., D, *
        2. 목적지인가? -> D
        3. 연결된 곳을 순회 -> 좌우상하
        4. 갈수있는가? (고슴도치) -> 맵을 벗어나지 않고, . or D, 방문하지 않은 곳
        5. 체크인 -> dp에 현재 + 1을 기록
        6. 큐에 넣음

        4. 갈수있는가? (물) -> 맵을 벗어나지 않고, .
        5. 체크인 -> 지도에 * 표기
        6. 큐에 넣음
     */


public class Main {

    public static int r, c;
    public static int [] dx = {0,0,-1,1};
    public static int [] dy = {-1,1,0,0};


    public static void main(String [] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY01/P3055/input.txt"));

        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();

        char [][] total_list = new char [r][c];
        Queue<Point> queue = new LinkedList<>();

        int [][] map = new int [r][c];
        Point st = null;

        for(int i=0; i<r; i++){
            String tmp = sc.next();
            for(int j=0; j<c; j++){
                total_list[i][j] = tmp.charAt(j);
                if(total_list[i][j] == '*'){
                    queue.add(new Point(i, j, '*'));
                } else if (total_list[i][j] == 'S'){
                    st = new Point(i, j, '.');
                }
            }
        }

        queue.add(st);


        while (!queue.isEmpty()){


            // 1. 큐에서 꺼내옴 -> S, ., *, D
            Point selected = queue.poll();

            int a = selected.x;
            int b = selected.y;

            // 2. 목적지인가? -> D
            if(total_list[a][b] == 'D'){
                System.out.println(map[a][b]);
                return;
            } else {

                // 3. 연결된 곳을 순회 -> 상하좌우
                for(int i=0; i<4; i++){
                    int x = a + dx[i];
                    int y = b + dy[i];

                    // 4. 갈 수 있는가? -> 맵을 벗어나지 않
                    if(x >= 0 && x < r && y >=0 && y <c) {

                        if (selected.type == '*'){
                            // 4. 갈 수 있는가? (물) -> 가는 곳이 .
                            if (total_list[x][y] == '.') {

                                // 5. 체크인(물) -> total_list에 물 표시
                                total_list[x][y] = '*';

                                // 6. 큐에 넣음
                                queue.add(new Point(x,y,'*'));
                            }
                        } else  {
                            // 4. 갈 수 있는가? (고슴도치) -> 가는 곳이 D, . & 방문한 적 없는 곳
                            if (total_list[x][y] == 'D' || (total_list[x][y] == '.' && map[x][y] == 0)) {

                                // 5. 체크인(고슴도치) -> map에 현재 +1 기록
                                map[x][y] = map[a][b] + 1;

                                // 6. 큐에 넣음
                                queue.add(new Point(x,y,'.'));
                            }
                        }
                    }
                }
            }
        }
        System.out.println("KAKTUS");
    }

}

class Point{
    int x;
    int y;
    char type;

    public Point(int x, int y, char type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
