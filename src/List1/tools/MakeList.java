package List1.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by bresiu on 23.10.13.
 */
public class MakeList {
    // make list of specific size with sorted elements, than shuffle it
    public static List<Element> makePermutation(int size) {
        List<Element> list = new ArrayList<Element>();
        for (int i = 0; i < size; i++) {
            list.add(makeElement(i));
        }
        return shuffleList(list);
    }

    // create element
    public static Element makeElement(int i) {
        Element element = new Element();
        element.setValue(i);
        element.setInitialPosition(i);
        element.setNumberOfComparison(0);
        //element.setNumberOfInversion(0);
        return element;
    }

    // shuffle elements in list using Collections.swap
    public static List<Element> shuffleList(List<Element> a) {
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
