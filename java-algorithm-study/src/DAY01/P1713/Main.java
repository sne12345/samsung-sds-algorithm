package DAY01.P1713;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/DAY01/P1713/input.txt"));

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int total = sc.nextInt();
        List<Candidate> cand_list = new ArrayList<>();

        for(int i=0; i<total; i++){
            int student_num = sc.nextInt();

            int flg = 0;
            for (int j = 0; j < cand_list.size(); j++) {
                if(cand_list.get(j).num == student_num){
                    cand_list.get(j).votes += 1;
                    flg = 1;
                }
            }

            if(flg == 0) {
                if(cand_list.size() >= N){
                    Collections.sort(cand_list);
                    cand_list.remove(N-1);
                }
                cand_list.add(new Candidate(student_num, i, 1));
            }
        }
        Collections.sort(cand_list, Comparator.comparingInt(o -> o.num));

        for(int i=0; i<cand_list.size(); i++){
            System.out.print(cand_list.get(i).num);
            System.out.print(" ");
        }
        System.out.println();
    }
}

class Candidate implements Comparable<Candidate> {
    int num;
    int term;
    int votes;

    public Candidate(int num, int term, int votes){
        this.num = num;
        this.term = term;
        this.votes = votes;
    }

    @Override
    public int compareTo(Candidate o) {
        int comp = Integer.compare(o.votes, votes);
        if(comp == 0){
            return Integer.compare(o.term, term);
        } else {
            return comp;
        }
    }

    @Override
    public String toString(){
        return "num=" + num + ", term=" + term + ", votes=" + votes;
    }
}
