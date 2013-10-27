package List1.sortingAlgorithms;

import List1.tools.Element;

import java.util.Collections;
import java.util.List;

/**
 * Created by bresiu on 23.10.13.
 */
public class QuickSort {

    public static void quick(List<Element> list, int start, int end) {

        int i = start;
        int j = end;
        int temp;
        // select pivot
        int pivot = list.get((start + end) / 2).getValue();
        do {
            while (list.get(i).getValue() < pivot) {
                temp = list.get(i).getNumberOfComparison();
                list.get(i).setNumberOfComparison(++temp);
                temp = list.get((start + end) / 2).getNumberOfComparison();
                list.get((start + end) / 2).setNumberOfComparison(++temp);

                i++;
            }
            while (pivot < list.get(j).getValue()) {

                temp = list.get(i).getNumberOfComparison();
                list.get(i).setNumberOfComparison(++temp);
                temp = list.get((start + end) / 2).getNumberOfComparison();
                list.get((start + end) / 2).setNumberOfComparison(++temp);

                j--;
            }
            if (i <= j) {

                Collections.swap(list, i, j);
                /*
                temp = list.get(i).getNumberOfInversion();
                list.get(i).setNumberOfInversion(++temp);
                temp = list.get(j).getNumberOfInversion();
                list.get(j).setNumberOfInversion(++temp);
                */
                i++;
                j--;
            }
        } while (i <= j);
        if (start < j) {
            quick(list, start, j);
        }
        if (i < end) {
            quick(list, i, end);
        }
    }
}
