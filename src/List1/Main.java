package List1;

import List1.sortingAlgorithms.HeapSort;
import List1.sortingAlgorithms.InsertionSort;
import List1.sortingAlgorithms.MergeSort;
import List1.sortingAlgorithms.QuickSort;
import List1.tools.Chart;
import List1.tools.Element;
import List1.tools.MakeList;
import List1.tools.Statistics;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int[] arr = new int[]{10, 50, 100, 500, 1000};

        XYSeries series;

        //System.out.println("START");
        //long startTime = System.nanoTime();

        Scanner sc = new Scanner(System.in);
        System.out.println("Select the algorithm:\n1. Insertion Sort\n2. Merge Sort\n3. Quick Sort\n4. Heap Sort");
        int algorithm = sc.nextInt();

        switch (algorithm) {
            case 1:
                // INSERTION SORT
                series = new XYSeries("Array length: 1000");
                for (int i = 0; i < arr.length; i++) {
                    // series = new XYSeries("Array length: " + arr[i]);

                    for (int j = 0; j < 300; j++) {
                        List<Element> list = MakeList.makePermutation(arr[i]);
                        //Util.toStringList(list);
                        //System.out.println();
                        //System.out.println(Statistics.numberOfInversionsB(list));
                        //System.out.println(Statistics.numberOfInversions(list));
                        //System.out.println();
                        //Util.toStringList(InsertionSort.insertionSort(list));
                        //System.out.println(Statistics.countNumberOfComparisons(list));
                        //System.out.println(Statistics.countNumberOfSwitches(list));

                        // A
                        int x = Statistics.numberOfInversions(list);

                        // B
                        // int x = Statistics.numberOfInversionsB(list);

                        InsertionSort.insertionSort(list);
                        int y = Statistics.countNumberOfComparisons(list);
                        // X - inversion
                        // Y - comparison
                        series.add(x, y);
                    }
                    //makeChart("Insertion Sort", series);
                }
                makeChart("Insertion Sort", series);
                break;
            case 2:
                // MERGE SORT
                series = new XYSeries("Array length: 1000");
                for (int i = 0; i < arr.length; i++) {
                    // series = new XYSeries("Array length: " + arr[i]);

                    for (int j = 0; j < 300; j++) {
                        List<Element> list = MakeList.makePermutation(arr[i]);
                        //Util.toStringList(list);
                        //System.out.println();
                        //System.out.println(Statistics.numberOfInversionsB(list));
                        //System.out.println(Statistics.numberOfInversions(list));
                        //System.out.println();
                        //Util.toStringList(MergeSort.mergeSort(list));

                        // A
                        int x = Statistics.numberOfInversions(list);

                        // B
                        // int x = Statistics.numberOfInversionsB(list);

                        MergeSort.mergeSort(list);
                        int y = Statistics.countNumberOfComparisons(list);
                        // X - inversion
                        // Y - comparison
                        series.add(x, y);
                    }
                    // makeChart("Merge Sort", series);
                }
                makeChart("Merge Sort", series);
                break;
            case 3:
                // QUICK SORT
                series = new XYSeries("Array length: 1000");

                for (int i = 0; i < arr.length; i++) {
                    // series = new XYSeries("Array length: " + arr[i]);
                    for (int j = 0; j < 300; j++) {
                        List<Element> list = MakeList.makePermutation(arr[i]);
                        //Util.toStringList(list);
                        //System.out.println();
                        //System.out.println(Statistics.numberOfInversionsB(list));
                        //System.out.println(Statistics.numberOfInversions(list));
                        //System.out.println();
                        // A
                        int x = Statistics.numberOfInversions(list);

                        // B
                        // int x = Statistics.numberOfInversionsB(list);
                        QuickSort.quick(list, 0, list.size() - 1);

                        //Util.toStringList(list);
                        //System.out.println(Statistics.countNumberOfComparisons(list));
                        //System.out.println(Statistics.countNumberOfSwitches(list));

                        int y = Statistics.countNumberOfComparisons(list);
                        // X - inversion
                        // Y - comparison
                        series.add(x, y);
                    }
                    //makeChart("Quick Sort", series);
                }
                makeChart("Quick Sort", series);
                break;
            case 4:
                // HEAP SORT
                series = new XYSeries("Array length: 1000");
                for (int i = 0; i < arr.length; i++) {
                    // series = new XYSeries("Array length: " + arr[i]);
                    for (int j = 0; j < 300; j++) {
                        List<Element> list = MakeList.makePermutation(10);
                        //Util.toStringList(list);
                        //System.out.println();
                        //System.out.println(Statistics.numberOfInversionsB(list));
                        //System.out.println(Statistics.numberOfInversions(list));
                        //System.out.println();
                        // A
                        int x = Statistics.numberOfInversions(list);

                        // B
                        // int x = Statistics.numberOfInversionsB(list);
                        HeapSort.sort(list);
                        int y = Statistics.countNumberOfComparisons(list);
                        series.add(x, y);
                        //System.out.println(Statistics.countNumberOfComparisons(list));
                        //System.out.println(Statistics.countNumberOfSwitches(list));
                    }
                    //Util.toStringList(list);
                    // makeChart("Heap Sort", series);
                }
                makeChart("Heap Sort", series);
                break;
        }

        /*
        System.out.println("END");
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(estimatedTime);
        */
    }

    private static void makeChart(String name, XYSeries series) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        Chart chart = new Chart(name, dataset);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

}