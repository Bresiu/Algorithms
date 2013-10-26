package List1.sortingAlgorithms;

import List1.tools.Element;

import java.util.Collections;
import java.util.List;

/**
 * Created by bresiu on 23.10.13.
 */
public class HeapSort {

    private static int n;
    private static int temp;
    private static int left;
    private static int right;
    private static int largest;

    public static void sort(List<Element> a) {
        buildheap(a);
        for (int i = n; i > 0; i--) {
            Collections.swap(a, 0, i);

            // Update number of sitches
            /*
            temp = a.get(0).getNumberOfInversion();
            a.get(0).setNumberOfInversion(++temp);
            temp = a.get(i).getNumberOfInversion();
            a.get(i).setNumberOfInversion(++temp);
            */

            n--;
            maxheap(a, 0);
        }
    }

    public static void buildheap(List<Element> a) {
        n = a.size() - 1;
        for (int i = n / 2; i >= 0; i--) {
            maxheap(a, i);
        }
    }

    private static void maxheap(List<Element> a, int i) {
        left = 2 * i;
        right = 2 * i + 1;

        if (left <= n && a.get(left).getValue() > a.get(i).getValue()) {
            temp = a.get(left).getNumberOfComparison();
            a.get(left).setNumberOfComparison(++temp);
            temp = a.get(i).getNumberOfComparison();
            a.get(i).setNumberOfComparison(++temp);

            largest = left;
        } else {
            temp = a.get(largest).getNumberOfComparison();
            a.get(largest).setNumberOfComparison(++temp);
            temp = a.get(i).getNumberOfComparison();
            a.get(i).setNumberOfComparison(++temp);

            largest = i;
        }

        if (right <= n && a.get(right).getValue() > a.get(largest).getValue()) {

            temp = a.get(right).getNumberOfComparison();
            a.get(right).setNumberOfComparison(++temp);
            temp = a.get(largest).getNumberOfComparison();
            a.get(largest).setNumberOfComparison(++temp);

            largest = right;
        }
        if (largest != i) {
            Collections.swap(a, i, largest);

            // Update number of sitches
            /*
            temp = a.get(i).getNumberOfInversion();
            a.get(i).setNumberOfInversion(++temp);
            temp = a.get(largest).getNumberOfInversion();
            a.get(largest).setNumberOfInversion(++temp);
            */
            maxheap(a, largest);
        }
    }
}
