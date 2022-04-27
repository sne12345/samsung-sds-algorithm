package SWEA.S2383;
import java.io.*;
import java.util.*;

public class Solution {
    static int T, N;
    static int [][] map;
    static ArrayList<Node> nodeList;
    static ArrayList<Node> [] pqList;
    static ArrayList<Node> stairList;
    static long minAns = Long.MAX_VALUE;

    static long getAnswer(int [] arr, int pCount) {
        ArrayList<Node> tNodeList = new ArrayList<>();

        for(int i=0; i<pCount; i++) {
            int sIdx = arr[i];
            long diff = Math.abs(stairList.get(sIdx).x - nodeList.get(i).x) + Math.abs(stairList.get(sIdx).y - nodeList.get(i).y);

            tNodeList.add(new Node(nodeList.get(i).x, nodeList.get(i).y, diff, sIdx, 0, i));
        }

        Collections.sort(tNodeList);
        MyComparator myC = new MyComparator();

        long curTime = 0;
        while(true) {

            curTime++;
            for(int i=0; i<tNodeList.size(); i++) {
                tNodeList.get(i).diff--;
            }
            for(int k=0; k<2; k++) {
                Collections.sort(pqList[k], myC);
                for(int i=0; i<pqList[k].size(); i++) {
                    if(i >= 3) {

                    } else if(pqList[k].get(i).isDown == 0) {
                        pqList[k].get(i).isDown = 1;
                    } else {
                        pqList[k].get(i).diff--;
                    }

                    if(pqList[k].get(i).diff == stairList.get(pqList[k].get(i).sIdx).diff) {
                        pqList[k].remove(i);
                        i--;
                    }
                }
            }
            while(!tNodeList.isEmpty() && tNodeList.get(0).diff == 0) {
                pqList[tNodeList.get(0).sIdx].add(tNodeList.get(0));
                tNodeList.remove(0);
            }

            if(tNodeList.isEmpty() && pqList[0].isEmpty() && pqList[1].isEmpty()) {
                break;
            }
        }
        return curTime;
    }

    static void comb(int idx, int pCount, int [] arr) {
        if(idx == pCount) {
            long answer = getAnswer(arr, pCount);
            minAns = Math.min(minAns, answer);
            return;
        }

        arr[idx] = 0;
        comb(idx+1, pCount, arr);

        arr[idx] = 1;
        comb(idx+1, pCount, arr);
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/SWEA/S2383/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            nodeList = new ArrayList<>();
            pqList = new ArrayList [2];
            stairList = new ArrayList<>();
            int hFlg = 0;
            int pCount = 0;

            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) {
                        pCount++;
                        nodeList.add(new Node(i, j, 0, -1, 0, nodeList.size()));
                    } else if(map[i][j] > 1 && hFlg == 0) {
                        hFlg = 1;
                        stairList.add(new Node(i, j, (-1) * map[i][j], 0, -1, -1));
                        pqList[0] = new ArrayList<>();
                    } else if(map[i][j] > 1 && hFlg == 1) {
                        stairList.add(new Node(i, j, (-1) * map[i][j], 1, -1, -1));
                        pqList[1] = new ArrayList<>();
                    }
                }
            }

            comb(0, pCount, new int[pCount]);
            System.out.println("#" + (t+1) + " " + minAns);
            minAns = Long.MAX_VALUE;
        }

    }

}

class MyComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o2.isDown, o1.isDown);
    }

}

class Node implements Comparable<Node> {
    int x;
    int y;
    long diff;
    int sIdx;
    int isDown;
    int hIdx;

    public Node(int x, int y, long diff, int sIdx, int isDown, int hIdx) {
        this.x = x;
        this.y = y;
        this.diff = diff;
        this.sIdx = sIdx;
        this.isDown = isDown;
        this.hIdx = hIdx;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.diff, o.diff);
    }
}