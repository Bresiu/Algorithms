package List3;

import List3.tools.Export;
import List3.tools.Make;
import List3.tools.Stats;
import List3.trees.BST;
import List3.trees.RBBST;

import java.util.ArrayList;

/**
 * Created by bresiu on 02.01.14.
 */
public class Main {


    public static ArrayList<Stats> statsInsRBBST = new ArrayList<Stats>();
    public static ArrayList<Stats> statsDelRBBST = new ArrayList<Stats>();

    public static ArrayList<Stats> statsInsBST = new ArrayList<Stats>();
    public static ArrayList<Stats> statsDelBST = new ArrayList<Stats>();
    // //////////////////////////////////////////////////////////////////////////////////////
    // RB-BST TEST //////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////

    public static void rbbstTest() {
        for (int i = 0; i < 100; i++) {
            RBBST tree = new RBBST();

            Make make = new Make();
            ArrayList<Integer> list = make.list();

            for (int n = 0; n < list.size(); n++) {
                tree.insert(list.get(n));
                if (n % 10 == 0) {
                    statsInsRBBST.add(new Stats(n, tree.getComparisonsIns()));
                }
                tree.setComparisonsIns(0);
                //System.out.println(tree.toString());
            }
            //System.out.println(list.toString());
            list = make.shuffleList(list);
            //System.out.println(list.toString());
            for (int n = 0; n < list.size(); n++) {
                tree.delete(list.get(n));
                if (n % 10 == 0) {
                    statsDelRBBST.add(new Stats(n, tree.getComparisonsDel()));
                }
                tree.setComparisonsDel(0);
            }
            //System.out.println(tree.toString());
            //if (tree.empty()) {
            //    System.out.println("Completed");
            //}
        }
        System.out.println("Completed RB-BST");
    }

    // //////////////////////////////////////////////////////////////////////////////////////
    // BST TEST /////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////

    public static void bstTest() {
        for (int i = 0; i < 100; i++) {
            BST tree = new BST();

            Make make = new Make();
            ArrayList<Integer> list = make.list();

            for (int n = 0; n < list.size(); n++) {
                tree.insert(list.get(n));
                if (n % 10 == 0) {
                    statsInsBST.add(new Stats(n, tree.getComparisonsIns()));
                }
                tree.setComparisonsIns(0);
            }

            //System.out.println(list.toString());
            list = make.shuffleList(list);
            //System.out.println(list.toString());
            for (int n = 0; n < list.size(); n++) {
                tree.remove(list.get(n));
                if (n % 10 == 0) {
                    statsDelBST.add(new Stats(n, tree.getComparisonsDel()));
                }
                tree.setComparisonsDel(0);
            }
            //if (tree.isEmpty()) {
            //    System.out.println(i + ": is empty");
            //}
        }
        System.out.println("Completed BST");
    }

    // //////////////////////////////////////////////////////////////////////////////////////
    // MAIN /////////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        rbbstTest();
        Export export = new Export();
        export.toFile(export.toString(statsInsRBBST), "statsInsRBBST");
        export.toFile(export.toString(statsDelRBBST), "statsDelRBBST");
        bstTest();
        export.toFile(export.toString(statsInsBST), "statsInsBST");
        export.toFile(export.toString(statsDelBST), "statsDelBST");
    }
}
