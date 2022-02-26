package DAY00.SDS01;
import java.util.*;

public class CardGame_samsung1 {

    public static List<List<Integer>> combination_list = new ArrayList<>();
    public static int max_ans = 0;

    public static void comb(int [] tmp_arr, boolean [] visited, int index, int r){
        if(r == 0){
            List<Integer> tmp_list = new ArrayList<Integer>();
            for(int i=0; i<tmp_arr.length; i++){
                if(visited[i] == true){
                    tmp_list.add(tmp_arr[i]);
                }
            }
            combination_list.add(tmp_list);
            return;
        }

        if(index == tmp_arr.length){
            return;
        } else {
            visited[index] = true;
            comb(tmp_arr, visited, index+1, r-1);

            visited[index] = false;
            comb(tmp_arr, visited, index+1, r);
        }
    }

    public static void dfs(List<List<Integer>> new_combination_list, boolean [] dfs_visited , int r, int index, int sum){
        if(r == 0){
            if(sum > max_ans){
                max_ans = sum;
            }
            return;
        }
        if(index == new_combination_list.size()){
            return;
        } else {
            int indiv_max = Collections.max(new_combination_list.get(index));
            dfs_visited[index] = true;
            dfs(new_combination_list, dfs_visited, r-1, index+1, sum + indiv_max);

            dfs_visited[index] = false;
            dfs(new_combination_list, dfs_visited, r-1, index+1, sum);

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

//         ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
//         for (int i=0; i<T; i++){
//             int N = sc.nextInt();
//             ArrayList<Integer> tmp_arr = new ArrayList<Integer>();
//             for (int j=0; j<N; j++){
//                 tmp_arr.add(sc.nextInt());
//             }
//             arr.add(tmp_arr);
//         }
        int N = sc.nextInt();
        int [] tmp_arr = new int [N];
        boolean [] visited = new boolean[tmp_arr.length];

        for(int i=0; i<N; i++){
            tmp_arr[i] = sc.nextInt();
        }

        int r = 2;
        comb(tmp_arr, visited, 0, r);

        Set<List<Integer>> combination_set = new HashSet<>(combination_list);
        List<List<Integer>> new_combination_list = new ArrayList<>(combination_set);

        for(int i=0; i<new_combination_list.size(); i++){
            System.out.print(new_combination_list.get(i).get(0));
            System.out.print(" ");
            System.out.print(new_combination_list.get(i).get(1));
            System.out.println();
        }

        boolean [] dfs_visited = new boolean[new_combination_list.size()];
//        dfs(new_combination_list, dfs_visited, )




//        for (int i=0; i<T; i++){
//            System.out.print("#" + Integer.toString(i+1) + " ");
////            for (int j=0; j<arr.get(i).size(); j++){
////                System.out.print(arr.get(i).get(j));
////                System.out.print(" ");
////            }
//            System.out.println(1);
//        }

    }
}
