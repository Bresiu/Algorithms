package List2.hashFunctions;

import List2.Main;
import List2.tools.HashEntry;
import List2.tools.Stats;

/**
 * Created by bresiu on 30.10.13.
 */
public class QuadraticProbing {
    HashEntry[] table;
    // size
    int m;

    public QuadraticProbing(int m) {
        this.m = m;
        table = new HashEntry[m];
        for (int i = 0; i < m; i++)
            table[i] = null;
    }

    // h(k, i) = (h′(k) + c_1*i + c_2*i^2) mod m
    public int toHash(int k, int i) {
        int c_1 = 1;
        int c_2 = m / 2; // c_2 = 2d, d = 1/4 m', d = 1/2 m
        return ((k % m) + c_1 * i + c_2 * i ^ 2) % m;
    }

    public void printTable() {
        for (int i = 0; i < m; i++) {
            if (table[i] == null) {
                System.out.println("m = " + m + " i = [" + i + "] - null");
            } else {
                System.out.println("m = " + m + " i = [" + i + "] - " + table[i].getKey() + " | " + table[i].getI());
            }
        }
    }

    public int put(int key, int n) {
        int pos, t = 1;
        while (t < m) {
            pos = toHash(key, t);
            if (table[pos] == null) {
                table[pos] = new HashEntry(key, t);
                // ???????????????????????????????????????????????????????????????????????????????????? //
                // ???????????????????????????????????????????????????????????????????????????????????? //
                if (n % (Math.ceil(m / 50)) == 0) {
                    Stats stats = new Stats(m, n, t);
                    Main.statsLinear.add(stats);
                }
                // ???????????????????????????????????????????????????????????????????????????????????? //
                // ???????????????????????????????????????????????????????????????????????????????????? //
                return t;
            }
            t++;
        }
        return -1;
    }
}