package List1.tools;

import java.util.List;

/**
 * Created by bresiu on 23.10.13.
 */
public class Statistics {
    //O = n^2
    public static String numberOfInversions(List<Element> a) {
        int counter = 0;

        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (a.get(i).getValue() > a.get(j).getValue()) {
                    counter++;
                }
            }
        }
        return "Number of Inversions in init list: " + counter;
    }

    public static String numberOfInversionsB(List<Element> a) {
        int counter = 0;

        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i).getValue() > a.get(i + 1).getValue()) {
                counter++;
            }
        }

        return "Number of Inversions between adjacent elements: " + counter;
    }

    public static String countNumberOfComparisons(List<Element> a) {
        int counter = 0;

        for (int i = 0; i < a.size(); i++) {
            counter += a.get(i).getNumberOfComparison();
        }

        return "Number of Comparisons: " + counter / 2;
    }

    public static String countNumberOfSwitches(List<Element> a) {
        int counter = 0;

        for (int i = 0; i < a.size(); i++) {
            counter += a.get(i).getNumberOfInversion();
        }

        return "Number of Switches: " + counter / 2;
    }
}
