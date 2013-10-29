package List2;

import List2.HashFunctions.LinearProbing;
import List2.tools.MakeList;

import java.util.List;

/**
 * Created by bresiu on 29.10.13.
 */
public class Main {
    final static int SIZE = 100;

    public static void main(String[] args) {
        List<Integer> list = MakeList.makePermutation(SIZE);
        System.out.println(list);
        LinearProbing linearProbing = new LinearProbing(SIZE);

        for (int i = 0; i < list.size(); i++) {
            linearProbing.put(list.get(i));
        }
        linearProbing.printTable();
    }
}

