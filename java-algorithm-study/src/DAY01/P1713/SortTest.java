package DAY01.P1713;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTest {
    public static void main(String[] args) {

        Item item1 = new Item(4,8,3);
        Item item2 = new Item(4,3,3);
        Item item3 = new Item(3,1,2);

        List<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);

        System.out.println(list);

        Collections.sort(list);

        System.out.println(list);

        Collections.sort(list, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return Integer.compare(o1.c, o2.c);
            }
        });

    }
}

class Item implements Comparable<Item> {

    int a;
    int b;
    int c;

    public Item(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString(){
        return "{" + "a=" + a + ",b=" + b +",c=" + c;
    }

    // 음수 : -
    // 0 : -
    // 양수 : Swap
    @Override
    public int compareTo(Item o) {
        int comp1 = Integer.compare(o.a, a);
        if(comp1 == 0){
            return Integer.compare(b, o.b);
        } else {
            return comp1;
        }

    }
}
