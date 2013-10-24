package List1;

import List1.sortingAlgorithms.MergeSort;
import List1.tools.Element;
import List1.tools.Statistics;
import List1.tools.Util;
import List1.tools.makeList;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        /*
        for (int i = 0; i < 300; i++) {
            List<Element> list = makeList.makePermutation(3);
            Util.toStringList(list);
            System.out.println();
            System.out.println(Statistics.numberOfInversionsB(list));
            System.out.println(Statistics.numberOfInversions(list));
            System.out.println();
            Util.toStringList(InsertionSort.insertionSort(list));
            System.out.println(Statistics.countNumberOfComparisons(list));
            System.out.println(Statistics.countNumberOfSwitches(list));
        }
        */

        /*
        List<Element> list = makeList.makePermutation(10);
        Util.toStringList(list);
        MergeSort.sort(list);
        */
        /*
        List<Element> list = makeList.makePermutation(10);
        Util.toStringList(list);
        System.out.println();
        System.out.println(Statistics.numberOfInversionsB(list));
        System.out.println(Statistics.numberOfInversions(list));
        System.out.println();
        QuickSort.quick(list, 0, list.size()-1);
        Util.toStringList(list);
        System.out.println(Statistics.countNumberOfComparisons(list));
        System.out.println(Statistics.countNumberOfSwitches(list));
        */
        /*
        List<Element> list = makeList.makePermutation(10);
        Util.toStringList(list);
        System.out.println();
        System.out.println(Statistics.numberOfInversionsB(list));
        System.out.println(Statistics.numberOfInversions(list));
        System.out.println();
        HeapSort.sort(list);
        Util.toStringList(list);
        System.out.println(Statistics.countNumberOfComparisons(list));
        System.out.println(Statistics.countNumberOfSwitches(list));
        */
        List<Element> list = makeList.makePermutation(1000);
        Util.toStringList(list);
        System.out.println();
        System.out.println(Statistics.numberOfInversionsB(list));
        System.out.println(Statistics.numberOfInversions(list));
        System.out.println();
        Util.toStringList(MergeSort.mergeSort(list));
    }
}