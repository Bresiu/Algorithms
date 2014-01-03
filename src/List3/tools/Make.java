package List3.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by bresiu on 02.01.14.
 */
public class Make {
    public final static int MAX = 100000;

    public ArrayList<Integer> list() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < 10000; i++) {
            arrayList.add(random());
        }
        //for (int i = 0; i < arrayList.size(); i++) {
        //    System.out.println(i + " - " + arrayList.get(i));
        //}
        return arrayList;
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

    public int random() {
        Random rand = new Random();
        int randomNum = rand.nextInt(MAX);
        return randomNum;
    }
}
