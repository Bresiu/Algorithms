package List1.sortingAlgorithms;

import List1.tools.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bresiu on 23.10.13.
 */
public class MergeSort {
    public static List<Element> mergeSort(List<Element> list) {
        if (list.size() <= 1) {
            return list;
        }

        List<Element> left = new ArrayList<Element>();
        List<Element> right = new ArrayList<Element>();
        int middle = list.size() / 2;

        for (int i = 0; i < list.size(); i++) {
            if (i < middle) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    private static List<Element> merge(List<Element> left, List<Element> right) {
        List<Element> merged = new ArrayList<Element>();
        while (left.size() > 0 || right.size() > 0) {
            if (left.size() > 0 && right.size() > 0) {
                if (left.get(0).getValue() < right.get(0).getValue()) {
                    merged.add(left.get(0));
                    left.remove(0);
                } else {
                    merged.add(right.get(0));
                    right.remove(0);
                }
            } else if (left.size() > 0) {
                merged.add(left.get(0));
                left.remove(0);
            } else if (right.size() > 0) {
                merged.add(right.get(0));
                right.remove(0);
            }
        }
        return merged;
    }
}
