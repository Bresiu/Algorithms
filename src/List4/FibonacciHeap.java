package List4;

import List4.tools.Make;

import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////////////
// HEAP ////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
class FibonacciHeap {

    Entry minimum;
    int size;

    FibonacciHeap() {
        minimum = null;
        size = 0;
    }

    int getSize() {
        return size;
    }

    Entry getMinimum() {
        return minimum;
    }

    boolean isEmpty() {
        return getMinimum() == null;
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
            // ++ ++ ++
            minimum.right.left = node;
            // ++ ++
            node.right = minimum.right;
            // ++ ++
            minimum.right = node;
            // ++
            node.left = minimum;

            // nowe minimum
            if (node.getValue() < minimum.getValue()) {
                minimum = node;
            }
        }
        size++;

        return node;
    }

    Entry extractMinimum() {

        Entry t;
        Entry w;
        Entry z = minimum;

        if (z.child != null) {
            // Usuwamy referencje do rodzicow wszystkich synow "z"
            w = z.child;
            t = w;

            do {
                t.parent = null;
                t = t.right;
            }
            while (t != w);

            // Dodajemy synow do roota
            minimum.left.right = w.right;
            w.right.left = minimum.left;
            minimum.left = w;
            w.right = minimum;
        }

        // Usuwamy "z" z roota
        z.left.right = z.right;
        z.right.left = z.left;

        if (z == z.right) {
            // kopiec jest pusty
            minimum = null;
        } else {
            // Wywolanie metody "consolidate()"
            minimum = z.right;
            consolidate();
        }
        size--;

        // Zwraca stare minimum
        return z;
    }

    void consolidate() {
        // Tworzenie odpowiedniej wielkosci tablicy
        int dn = (int) Math.floor(Math.log(size) / Math.log(2)) + 2;
        Entry[] a = new Entry[dn];

        // Wezel wspomagajacy iteracje
        Entry iter = minimum;

        // Aktualny wezel
        Entry w = iter;

        Entry x;
        Entry y;
        Entry temp;

        int d;

        do {
            x = w;
            d = x.degree;

            if (a[d] != x) {
                while (a[d] != null) {
                    // y ma taki sam stopien jak x
                    y = a[d];

                    if (x.getValue() > y.getValue()) {
                        // Swap x i y.
                        temp = x;
                        x = y;
                        y = temp;
                    }
                    // Zrob z "y" syna "x-a"
                    link(y, x);
                    iter = x;
                    w = x;
                    a[d] = null;
                    d += 1;
                }

                a[d] = x;
            }

            // Nastepny wezel
            w = w.right;
        }
        while (w != iter);

        // Reset. Iterowanie od poczatku listy
        minimum = iter;
        w = iter;

        // Znajdz nowe minimum na liscie roota
        do {
            if (w.getValue() < minimum.getValue()) {
                // Znaleziono nowe minimum
                minimum = w;
            }

            // Nastepny
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

        //int count;

        for (int i = 100; i <= 10000; i += 100) {
            for (int j = 0; j < 100; j++) {
                //count = 0;
                ArrayList<Integer> list = make.list(i);
                int size = list.size();
                FibonacciHeap heap = new FibonacciHeap();
                for (int k = 0; k < size; k++) {
                    //System.out.println("key: " + k + " value: " + list.get(k));
                    //count++;
                    heap.insert(k, list.get(k));
                    //System.out.println("Minimum: " + heap.getMinimum().getValue());
                }
                while (!heap.isEmpty()) {
                    //count++;
                    //System.out.println("Minimum: " + heap.getMinimum().getValue());
                    heap.extractMinimum();
                }
                //System.out.println("COUNT = " + count);
            }
        }
        /**
         * Sprawdzenie poprawnosci z http://www.cse.yorku.ca/~aaw/Jason/FibonacciHeapAnimation.html
         **/
        /*
        FibonacciHeap heap = new FibonacciHeap();
        heap.insert(0, 0);
        heap.insert(55, 55);
        heap.insert(32, 32);
        heap.insert(33, 33);
        heap.insert(2, 2);
        System.out.println("Minimum: " + heap.getMinimum().getValue());
        heap.extractMinimum();
        System.out.println("Minimum: " + heap.getMinimum().getValue());
        System.out.println("next: " + heap.getMinimum().child.left.child.getValue());
        heap.extractMinimum();
        System.out.println("Minimum: " + heap.getMinimum().getValue());
        System.out.println("next " + heap.getMinimum().right.getValue());
        heap.extractMinimum();
        System.out.println("Minimum: " + heap.getMinimum().getValue());
        System.out.println("next " + heap.getMinimum().child.getValue());
        */
        System.out.println("END");
    }
}