package FINAL.P1450;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int n, c, cnt = 0, idx = 0;
    static int[] bags;
    static ArrayList<Integer> listA = new ArrayList<>();
    static ArrayList<Integer> listB = new ArrayList<>();

    static void input() throws IOException {
        System.setIn(new FileInputStream("src/FINAL/P1450/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        bags = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            bags[i] = Integer.parseInt(st.nextToken());
        }

    }

    static void findA(int k, int sum) {
        if (sum > c)
            return;
        if (k == n / 2) {
            listA.add(sum);
            return;
        }
        findA(k + 1, sum);
        findA(k + 1, sum + bags[k]);
    }

    static void findB(int k, int sum) {
        if (sum > c)
            return;
        if (k == n) {
            listB.add(sum);
            return;
        }
        findB(k + 1, sum);
        findB(k + 1, sum + bags[k]);
    }

    static void binarySearch(int l, int r, int value) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (listB.get(mid) + value <= c) {
                idx = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
    }

    static void find() {
        findA(0, 0);
        findB(n / 2, 0);

        listB.sort((i, j) -> (i - j));
        for (int i = 0; i < listA.size(); i++) {
            idx = -1;
            binarySearch(0, listB.size() - 1, listA.get(i));
            cnt += idx + 1;
        }
        System.out.println(cnt);

    }

    public static void main(String[] args) throws IOException {
        input();
        find();
    }
}
