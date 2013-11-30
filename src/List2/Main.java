package List2;

import List2.hashFunctions.DoubleHashing;
import List2.hashFunctions.LinearProbing;
import List2.hashFunctions.QuadraticProbing;
import List2.tools.MakeList;
import List2.tools.Stats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bresiu on 29.10.13.
 */
public class Main {
    final static int SIZE = 10000;
    public static List<Stats> statsLinear;
    public static List<Stats> statsQuadratic;
    public static List<Stats> statsDouble;

    public static void main(String[] args) {

        //////////////////////////////////////////////////////////////////////////
        // LINEAR PROBING ////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////

        System.out.println("START OF LINEAR PROBING");

        statsLinear = new ArrayList<Stats>();

        for (int i = 100; i <= 10000; i += 100) {
            for (int j = 0; j < 50; j++) {
                List<Integer> list = MakeList.makePermutation(i);
                // System.out.println(list + "\n");

                LinearProbing linearProbing = new LinearProbing(i);

                for (int n = 0; n < list.size(); n++) {
                    linearProbing.put(list.get(n), n);
                }
                // linearProbing.printTable();
                // System.out.println();
            }
        }
        System.out.println("END OF LINEAR PROBING");

        //////////////////////////////////////////////////////////////////////////
        // QUADRATIC PROBING /////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////

        System.out.println("START OF QUADRATIC PROBING");

        statsQuadratic = new ArrayList<Stats>();

        for (int i = 100; i <= 10000; i += 100) {
            for (int j = 0; j < 50; j++) {
                List<Integer> list = MakeList.makePermutation(i);
                // System.out.println(list + "\n");

                QuadraticProbing quadraticProbing = new QuadraticProbing(i);

                for (int n = 0; n < list.size(); n++) {
                    quadraticProbing.put(list.get(n), n);
                }
                // quadraticProbing.printTable();
                // System.out.println();
            }
        }
        System.out.println("END OF LINEAR PROBING");

        //////////////////////////////////////////////////////////////////////////
        // DOUBLE HASHING ////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////

        System.out.println("START OF DOUBLE HASHING");

        statsDouble = new ArrayList<Stats>();

        for (int i = 100; i <= 10000; i += 100) {
            for (int j = 0; j < 50; j++) {
                // TODO primes
                List<Integer> list = MakeList.makePermutation(i);
                // System.out.println(list + "\n");

                DoubleHashing doubleHashing = new DoubleHashing(i);

                for (int n = 0; n < list.size(); n++) {
                    doubleHashing.put(list.get(n), n);
                }
                // doubleHashing.printTable();
                // System.out.println();
            }
        }
    }
}

