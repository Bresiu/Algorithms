package List2.HashFunctions;

import List2.tools.HashEntry;

/**
 * Created by bresiu on 29.10.13.
 */
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
                System.out.println("[" + i + "] - null");
            } else {
                System.out.println("[" + i + "] - " + table[i].getKey() + " | " + table[i].getI());
            }

        }
    }

    public int put(int key) {
        int pos, i = 1;
        while (i < m) {
            pos = toHash(key, i);
            if (table[pos] == null) {
                table[pos] = new HashEntry(key, i);
                return i;
            }
            i++;
        }
        return -1;
    }
}
