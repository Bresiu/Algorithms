package List1;

import List1.sortingAlgorithms.HeapSort;
import List1.tools.Chart;
import List1.tools.Element;
import List1.tools.MakeList;
import List1.tools.Statistics;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        int[] arr = new int[]{10, 50, 100, 500, 1000};

        //System.out.println("START");
        //long startTime = System.nanoTime();
        /*
        // INSERTION SORT
        for (int i = 0; i < arr.length; i++) {
            XYSeries series = new XYSeries("Array length: " + arr[i]);

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
            makeChart(series);
        }
        */
        /*
        // MERGE SORT
        for (int i = 0; i < arr.length; i++) {
            XYSeries series = new XYSeries("Array length: " + arr[i]);

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
            makeChart(series);
        }
        */

        /*
        // QUICK SORT
        for (int i = 0; i < arr.length; i++) {
            XYSeries series = new XYSeries("Array length: " + arr[i]);
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
            makeChart(series);
        }
        */

        ///*
        // HEAP SORT
        for (int i = 0; i < arr.length; i++) {
            XYSeries series = new XYSeries("Array length: " + arr[i]);
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
                //Util.toStringList(list);
                //System.out.println(Statistics.countNumberOfComparisons(list));
                //System.out.println(Statistics.countNumberOfSwitches(list));
            }
            makeChart(series);
        }
        //*/

        /*
        System.out.println("END");
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(estimatedTime);
        */

        /*
        Chart chart = new Chart("Chart Comparisons/Inversions");
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
        */
    }

    private static void makeChart(XYSeries series) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        Chart chart = new Chart("Insertion Sort", dataset);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

}