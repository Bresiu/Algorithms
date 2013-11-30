package List2.hashFunctions;

import List2.Main;
import List2.tools.HashEntry;
import List2.tools.Stats;

public class LinearProbing {
    HashEntry[] table;
    // size
    int m;

    public LinearProbing(int m) {
        this.m = m;
        table = new HashEntry[m];
        for (int i = 0; i < m; i++)
            table[i] = null;
    }

    // h(k, i) = (h′(k) + i) mod m = ((k mod m) + i) mod m
    // h′(k) = k mod m
    public int toHash(int k, int i) {
        return ((k % m) + i) % m;
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
