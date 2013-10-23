package List1.tools;

import java.util.List;

/**
 * Created by bresiu on 23.10.13.
 */
public class Util {
    // print list
    public static void toStringList(List<Element> a) {
        int size = a.size();
        for (int i = 0; i < size; i++) {
            System.out.print(a.get(i).getValue());
            if (i < size - 1) {
                System.out.print(", ");
            } else {
                System.out.println("");
            }
        }
    }
}
