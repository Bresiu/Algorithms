package List4.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by bresiu on 23.01.14.
 */
public class Make {

    // make sorted array
    public ArrayList<Integer> list(int n) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            arrayList.add(i);
        }
        return shuffleList(arrayList);
    }

    // shuffle elements in list using Collections.swap
    public ArrayList<Integer> shuffleList(ArrayList<Integer> a) {
        int n = a.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            Collections.swap(a, i, change);
        }
        return a;
    }
}
