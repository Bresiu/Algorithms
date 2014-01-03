package List3;

import List3.tools.Make;
import List3.trees.BST;
import List3.trees.RBBST;

import java.util.ArrayList;

/**
 * Created by bresiu on 02.01.14.
 */
public class Main {

    // //////////////////////////////////////////////////////////////////////////////////////
    // RB-BST TEST //////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////

    public static void rbbstTest() {
        for (int i = 0; i < 100; i++) {
            RBBST tree = new RBBST();

            Make make = new Make();
            ArrayList<Integer> list = make.list();

            for (int j : list) {
                tree.insert(j);
                //System.out.println(tree.toString());
            }
            //System.out.println(list.toString());
            list = make.shuffleList(list);
            //System.out.println(list.toString());
            for (int j = 0; j < list.size(); j++) {
                tree.delete(list.get(j));
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

            for (int j : list) {
                tree.insert(j);
            }

            //System.out.println(list.toString());
            list = make.shuffleList(list);
            //System.out.println(list.toString());
            for (int j = 0; j < list.size(); j++) {
                tree.remove(list.get(j));
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
        bstTest();
    }
}
