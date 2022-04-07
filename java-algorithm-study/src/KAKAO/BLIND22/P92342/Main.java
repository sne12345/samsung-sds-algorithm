package KAKAO.BLIND22.P92342;

public class Main {

    static int [] score = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static int biggestDifference = -1;
    static int [] answerList = new int[11];
    static int [] tmpList = new int[11];

    public static void main(String[] args) {
        int n = 10;
        int [] info = {0,0,0,0,0,0,0,0,3,4,3};

        dfs(0, n, 0, 0, info, 0);
        System.out.println(biggestDifference);

        int [] answer;
        if(biggestDifference == -1){
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = new int[11];
            for(int i=0; i<11; i++){
                System.out.print(answerList[i] + " ");
                answer[i] = answerList[i];
            }
        }
    }

    static void dfs(int idx, int n, int arrowSum, int scoreSum, int [] info, int apeachScore){
        if(arrowSum == n){

            for(int i=idx; i<11; i++){
                if(info[i] != 0) {
                    apeachScore += score[i];
                }
            }

            int tmpDifference = scoreSum - apeachScore;
            if(tmpDifference != 0 && biggestDifference <= tmpDifference){
                int winTmp = 1;

                if(biggestDifference == tmpDifference) {
                    for(int i=10; i>=idx; i--){
                        if(answerList[i] > 0){
                            winTmp = 0;
                            break;
                        }
                    }
                    if(winTmp == 1) {
                        for (int i = idx - 1; i >= 0; i--) {
                            if (tmpList[i] < answerList[i]) {
                                winTmp = 0;
                                break;
                            } else if (tmpList[i] > answerList[i]) {
                                break;
                            }
                        }
                    }
                }

                if(winTmp == 1) {
                    biggestDifference = tmpDifference;
                    for (int i = 0; i < idx; i++) {
                        answerList[i] = tmpList[i];
                    }
                    for (int i = idx; i < 11; i++) {
                        answerList[i] = 0;
                    }
                }
            }

            return;
        } else if(arrowSum > n){
            return;
        }

        if(idx > 10){
            return;
        }

        tmpList[idx] = info[idx] + 1;
        dfs(idx+1, n, arrowSum+info[idx]+1, scoreSum+score[idx], info, apeachScore);

        tmpList[idx] = 0;
        if(info[idx] == 0){
            dfs(idx+1, n, arrowSum, scoreSum, info, apeachScore);
        } else {
            dfs(idx + 1, n, arrowSum, scoreSum, info, apeachScore + score[idx]);
        }

        tmpList[idx] = n - arrowSum;
        dfs(idx+1, n, arrowSum + n - arrowSum, scoreSum, info, apeachScore+score[idx]);
    }
}
