    package DAY03.P7578;

    import java.io.BufferedReader;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.StringTokenizer;


    public class Main {

        public static int N, S;
        public static long result;

        public static long [] indexTree;
        public static long [] aList;

        public static void main(String[] args) throws IOException {

            System.setIn(new FileInputStream("src/DAY03/P7578/input.txt"));

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            aList = new long[N+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++){
                aList[i] = Long.parseLong(st.nextToken());
            }

            Map<Long, Integer> B = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++){
                B.put(Long.parseLong(st.nextToken()), i);
            }

            S = 1;
            while(S < N){
                S *= 2;
            }
            indexTree = new long[S * 2];

            result = 0;
            for(int i=1; i<=N; i++){
                int bIndex = B.get(aList[i]);
                result += queryTD(1, S, 1, bIndex+1, N);
                updateBU(S + bIndex - 1);
            }

            System.out.println(result);

        }



        public static long queryTD(int startIndex, int endIndex, int current, int leftAns, int rightAns){
            if(endIndex < leftAns || startIndex > rightAns){
                return 0;
            } else if(current >= 2 * S){
                return 0;
            } else if(leftAns <= startIndex && endIndex <= rightAns) {
                return indexTree[current];
            }

            int midIndex = (startIndex + endIndex) / 2;
            return queryTD(startIndex, midIndex, current * 2, leftAns, rightAns) +
                    queryTD(midIndex+1, endIndex, current * 2 + 1, leftAns, rightAns);
        }

        public static void updateBU(int updateIndex){
            while(updateIndex >= 1){
                indexTree[updateIndex] += 1;
                updateIndex /= 2;
            }
        }


        public static void updateTD(long left, long right, int index, int target, long diff){
            if(target >= left && target <= right){
                indexTree[index] += diff;
                if(left != right) {
                    updateTD(left, (left + right) / 2, index * 2, target, diff);
                    updateTD((left + right) / 2 + 1, right, index * 2 + 1, target, diff);
                }
            } else {
                return;
            }
        }
    }
