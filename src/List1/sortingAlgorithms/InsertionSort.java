package List1.sortingAlgorithms;

import List1.tools.Element;

import java.util.Collections;
import java.util.List;

/**
 * Created by bresiu on 23.10.13.
 */
public class InsertionSort {
    public static List<Element> insertionSort(List<Element> list) {
        int i, j, temp, size = list.size();

        for (i = 1; i < size; i++) {
            j = i;
            while (j > 0 && list.get(j).getValue() < list.get(j - 1).getValue()) {

                Collections.swap(list, j, j - 1);

                // Update number of sitches
                /*
                temp = list.get(j).getNumberOfInversion();
                list.get(j).setNumberOfInversion(++temp);
                temp = list.get(j - 1).getNumberOfInversion();
                list.get(j - 1).setNumberOfInversion(++temp);
                */

                // Update Comparison when loop condition is true
                temp = list.get(j).getNumberOfComparison();
                list.get(j).setNumberOfComparison(++temp);
                temp = list.get(j - 1).getNumberOfComparison();
                list.get(j - 1).setNumberOfComparison(++temp);

                j--;
            }
            // Update Comparison when loop condition is false
            temp = list.get(i).getNumberOfComparison();
            list.get(i).setNumberOfComparison(++temp);
            temp = list.get(j).getNumberOfComparison();
            list.get(j).setNumberOfComparison(++temp);
        }
        return list;
    }
}