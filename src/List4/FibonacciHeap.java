package List4;

import List4.tools.Make;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

////////////////////////////////////////////////////////////////////////////////////////
// HEAP ////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
class FibonacciHeap {

    Entry minimum;
    int size;
    int access;

    FibonacciHeap() {
        minimum = null;
        size = 0;
        access = 0;
    }

    int getAccess() {
        return access;
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
        // ++
        access += 1;
        if (minimum == null) {
            // ++ ++
            access += 2;
            minimum = node;
        } else {

            // Wstawianie w liste korzenia
            // ++ ++ ++ ++
            access += 4;
            minimum.right.left = node;
            // ++ ++ ++ ++
            access += 4;
            node.right = minimum.right;
            // ++ ++ ++
            access += 3;
            minimum.right = node;
            // ++ ++ ++
            access += 3;
            node.left = minimum;

            // nowe minimum
            // ++ ++
            access += 2;
            if (node.getValue() < minimum.getValue()) {
                // ++ ++
                access += 2;
                minimum = node;
            }
        }
        size++;

        return node;
    }

    Entry extractMinimum() {

        Entry t;
        Entry w;
        // ++
        access += 1;
        Entry z = minimum;

        // ++ ++
        access += 2;
        if (z.child != null) {
            // Usuwamy referencje do rodzicow wszystkich synow "z"
            // ++ ++ ++
            access += 3;
            w = z.child;
            // ++ ++
            access += 2;
            t = w;

            do {
                // ++ ++
                access += 2;
                t.parent = null;
                // ++ ++ ++
                access += 3;
                t = t.right;
                // ++ ++
                access += 2;
            }
            while (t != w);
            // Dodajemy synow do roota
            // ++ ++ ++ ++ ++
            access += 4;
            minimum.left.right = w.right;
            // ++ ++ ++ ++ ++
            access += 5;
            w.right.left = minimum.left;
            // ++ ++ ++
            access += 3;
            minimum.left = w;
            // ++ ++ ++
            access += 3;
            w.right = minimum;
        }

        // Usuwamy "z" z roota
        // ++ ++ ++ ++ ++
        access += 5;
        z.left.right = z.right;
        // ++ ++ ++ ++ ++
        access += 5;
        z.right.left = z.left;

        // ++ ++ ++
        access += 3;
        if (z == z.right) {
            // kopiec jest pusty
            // ++
            access += 1;
            minimum = null;
        } else {
            // Wywolanie metody "consolidate()"
            // ++ ++ ++
            access += 3;
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
        // ++
        access += 1;
        Entry iter = minimum;

        // Aktualny wezel
        // ++
        access += 1;
        Entry w = iter;

        Entry x;
        Entry y;
        Entry temp;

        int d;

        do {
            // ++
            access += 1;
            x = w;
            d = x.degree;

            // ++ ++
            access += 2;
            if (a[d] != x) {
                while (a[d] != null) {
                    // ++
                    access += 1;
                    // y ma taki sam stopien jak x
                    // ++ ++
                    access += 2;
                    y = a[d];
                    // ++ ++
                    access += 2;
                    if (x.getValue() > y.getValue()) {
                        // Swap x i y.
                        // ++
                        access += 1;
                        temp = x;
                        // ++ ++
                        access += 2;
                        x = y;
                        // ++ ++
                        access += 2;
                        y = temp;
                    }
                    // Zrob z "y" syna "x-a"
                    link(y, x);
                    // ++ ++
                    access += 2;
                    iter = x;
                    // ++ ++
                    access += 2;
                    w = x;
                    a[d] = null;
                    d += 1;
                }
                // ++ ++
                access += 2;
                a[d] = x;
            }

            // Nastepny wezel
            // ++ ++ ++
            access += 3;
            w = w.right;
        }
        while (w != iter);

        // Reset. Iterowanie od poczatku listy
        // ++ ++
        access += 2;
        minimum = iter;
        // ++ ++
        access += 2;
        w = iter;

        // Znajdz nowe minimum na liscie roota
        do {
            // ++ ++
            // ++ ++
            access += 2; // while
            access += 2; // if
            if (w.getValue() < minimum.getValue()) {
                // Znaleziono nowe minimum
                // ++ ++
                access += 2;
                minimum = w;
            }

            // Nastepny
            // ++ ++ ++
            access += 3;
            w = w.right;
        }
        while (w != iter);

    }

    void link(Entry y, Entry x) {
        // Usun "y" z listy roota
        // ++ ++ ++ ++ ++
        access += 5;
        y.left.right = y.right;
        // ++ ++ ++ ++ ++
        access += 5;
        y.right.left = y.left;

        // ++ ++
        access += 2;
        if (x.child == null) {
            // "x" jest jedynym wezlem
            // ++ ++ ++
            access += 3;
            y.right = y;
            // ++ ++ ++
            access += 3;
            y.left = y;
            // ++ ++ ++
            access += 3;
            x.child = y;
        } else {
            // Konkatenacja w liste synow "x-a"
            // ++ ++ ++ ++ ++
            access += 5;
            y.right = x.child.right;
            // ++ ++ ++ ++
            access += 4;
            y.left = x.child;
            // ++ ++ ++ ++ ++
            access += 5;
            x.child.right.left = y;
            // ++ ++ ++ ++
            access += 4;
            x.child.right = y;
        }
        // ++ ++ ++
        access += 3;
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

    public static void main(String[] args) throws IOException {
        Make make = new Make();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("statsFH.dat"), true));
        //int count;

        for (int i = 100; i <= 10000; i += 100) {
            for (int j = 0; j < 100; j++) {
                //count = 0;
                ArrayList<Integer> list = make.list(i);
                int size = list.size();
                FibonacciHeap heap = new FibonacciHeap();
                int operation = 0;
                for (int k = 0; k < size; k++) {
                    //System.out.println("key: " + k + " value: " + list.get(k));
                    //count++;
                    heap.insert(k, list.get(k));
                    operation++;
                    bw.write(operation + " " + heap.getAccess() + "\n");
                    //System.out.println("Minimum: " + heap.getMinimum().getValue());
                }
                while (!heap.isEmpty()) {
                    //count++;
                    //System.out.println("Minimum: " + heap.getMinimum().getValue());
                    heap.extractMinimum();
                    operation++;
                    bw.write(operation + " " + heap.getAccess() + "\n");
                }
                // System.out.println(statsString);
                // System.out.println("COUNT = " + count);
            }
            System.out.println(i);
        }
        bw.close();
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