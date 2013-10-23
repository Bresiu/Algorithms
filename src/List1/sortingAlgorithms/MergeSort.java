package List1.sortingAlgorithms;

import List1.tools.Element;
import List1.tools.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bresiu on 23.10.13.
 */
public class MergeSort {
    public static List<Element> numbers;
    public static List<Element> helper;
    static int number;

    public static void sort(List<Element> values) {
        numbers = new ArrayList<Element>(values);
        number = values.size();
        helper = new ArrayList<Element>(number);
        mergeSort(0, number - 1);
        System.out.println("trololo");
        Util.toStringList(values);
    }

    private static void mergeSort(int low, int high) {
        // check if low is smaller then high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergeSort(low, middle);
            // Sort the right side of the array
            mergeSort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private static void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper.add(i, numbers.get(i));
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper.get(i).getValue() <= helper.get(j).getValue()) {
                numbers.add(k, helper.get(i));
                i++;
            } else {
                numbers.add(k, helper.get(j));
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers.add(k, helper.get(i));
            k++;
            i++;
        }

    }
    /*
    public static void mergeSort(List<Element> a, int p, int r) {
        System.out.println();
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(a, p, q);
            mergeSort(a, q + 1, r);
            merge(a, p, q, r);
        }
    }


    private static void merge(List<Element> a, int p, int q, int r) {
        List<Element> n = new ArrayList<Element>(a.subList(0, q));
        Util.toStringList(n);
        System.out.println("n.size = " + n.size());
        List<Element> m = new ArrayList<Element>(a.subList(q, a.size()));
        System.out.println("m.size = " + m.size());
        Util.toStringList(m);

        int i = 0;
        int j = 0;

        for (int k = p; k < r; k++) {
            if (n.get(i).getValue() <= m.get(j).getValue()) {
                //a.set(k, n.get(i));
                i++;
            } else {
                //a.set(k, m.get(j));
                j++;
            }
        }
    }
    */
}
