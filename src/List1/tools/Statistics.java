package List1.tools;

import java.util.List;

/**
 * Created by bresiu on 23.10.13.
 */
public class Statistics {

    public static int numberOfInversions(List<Element> a) {
        int counter = 0;

        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (a.get(i).getValue() > a.get(j).getValue()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static int numberOfInversionsB(List<Element> a) {
        int counter = 0;

        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i).getValue() > a.get(i + 1).getValue()) {
                counter++;
            }
        }

        return counter;
    }

    public static int countNumberOfComparisons(List<Element> a) {
        int counter = 0;

        for (int i = 0; i < a.size(); i++) {
            counter += a.get(i).getNumberOfComparison();
        }

        return counter / 2;
    }

    /*
    public static String countNumberOfSwitches(List<Element> a) {
        int counter = 0;

        for (int i = 0; i < a.size(); i++) {
            counter += a.get(i).getNumberOfInversion();
        }

        return "Number of Switches: " + counter / 2;
    }
    */
}
