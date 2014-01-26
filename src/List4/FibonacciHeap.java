package List4;

import List4.tools.Make;

import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////////////
// HEAP ////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
class FibonacciHeap {

    Entry minimum;
    int size;
    ////////////////
    int mod_count;

    FibonacciHeap() {
        // Null min.
        minimum = null;
        size = 0;
        mod_count = 0;
    }

    int getSize() {
        return size;
    }

    Entry getMinimum() {
        return minimum;
    }

    Entry insert(int key, int value) {
        Entry node = new Entry(key, value);

        node.degree = 0;
        node.marked = false;
        node.left = node.right = node;
        node.parent = null;
        node.child = null;

        // Podpinamy pod korzen
        if (minimum == null) {
            minimum = node;
        } else {

            // Wstawianie w liste korzenia
            minimum.right.left = node;
            node.right = minimum.right;
            minimum.right = node;
            node.left = minimum;

            // nowe minimum
            if (node.getValue() < minimum.getValue()) {
                minimum = node;
            }
        }
        size++;
        mod_count++;

        return node;
    }

    Entry extractMinimum() {

        Entry t;
        Entry w;
        Entry z = minimum;

        if (z.child != null) {
            // Remove parent references for all of z's children.
            w = z.child;
            t = w;

            do {
                t.parent = null;
                t = t.right;
            }
            while (t != w);

            // Add the children to the root list.
            minimum.left.right = w.right;
            w.right.left = minimum.left;
            minimum.left = w;
            w.right = minimum;
        }

        // Remove z from the root list.
        z.left.right = z.right;
        z.right.left = z.left;

        if (z == z.right) {
            // We hope the heap is now empty...
            minimum = null;
        } else {
            // We have some work to do.
            minimum = z.right;
            consolidate();
        }

        // Dec size, inc mod.
        size -= 1;
        mod_count += 1;

        // Return old minimum.
        return z;
    }

    void consolidate() {
        // Create the auxiliary array.
        int dn = (int) Math.floor(Math.log(size) / Math.log(2)) + 2;
        Entry[] a = new Entry[dn];

        // Iterating node - node at which to stop iterating...
        Entry iter = minimum;

        // The node we're on now; w from CLRS.
        Entry w = iter;

        // x and y from CLRS code.
        Entry x;
        Entry y;

        // temp ref.
        Entry temp;

        // d from CLRS code.
        int d;

        do {
            x = w;
            d = x.degree;

            if (a[d] != x) {
                while (a[d] != null) {
                    // y has same degree as x... This much we know.
                    y = a[d];

                    if (x.getValue() > y.getValue()) {
                        // Swap x and y.
                        temp = x;
                        x = y;
                        y = temp;
                    }

                    // Make y a child of x.
                    link(y, x);
                    iter = x;
                    w = x;
                    a[d] = null;
                    d += 1;
                }

                a[d] = x;
            }

            // Next node.
            w = w.right;
        }
        while (w != iter);

        // Reset... we need to iterate over the root list again.
        minimum = iter;
        w = iter;

        // Find the new minimum in the root list (if we don't already have it).
        do {
            if (w.getValue() < minimum.getValue()) {
                // Found a new minimum node.
                minimum = w;
            }

            // Next.
            w = w.right;
        }
        while (w != iter);

    }

    void link(Entry y, Entry x) {
        // Remove y from the root list.
        y.left.right = y.right;
        y.right.left = y.left;

        if (x.child == null) {
            // x is all alone in the world.
            y.right = y;
            y.left = y;
            x.child = y;
        } else {
            // Concat into child list of x.
            y.right = x.child.right;
            y.left = x.child;
            x.child.right.left = y;
            x.child.right = y;
        }

        // Some housekeeping for the nodes.
        y.parent = x;
        x.degree += 1;
        y.marked = false;
    }


////////////////////////////////////////////////////////////////////////////////////////
// ENTRY ///////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////

    class Entry {

        boolean marked;
        int degree;
        Entry parent;
        Entry child;
        Entry left;
        Entry right;
        int key;
        int value;

        int getValue() {
            return value;
        }

        // Konstruktor
        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

////////////////////////////////////////////////////////////////////////////////////////
// MAIN ////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        Make make = new Make();
        for (int i = 100; i <= 10000; i += 100) {
            for (int j = 0; j < 100; j++) {
                ArrayList<Integer> list = make.list(i);
                int size = list.size();
                FibonacciHeap heap = new FibonacciHeap();
                for (int k = 0; k < size; k++) {
                    heap.insert(list.get(k), k);
                }
                for (int k = 0; k < size; k++) {
                    heap.extractMinimum();
                }
            }
        }
    }
}