package List2.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bresiu on 23.10.13.
 */
public class MakeList {
    // make list of random values
    public static List<Integer> makePermutation(int size) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            list.add(random.nextInt(size));
        }
        return list;
    }
}
