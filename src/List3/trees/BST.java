package List3.trees;

/**
 * Created by bresiu on 02.01.14.
 */

// //////////////////////////////////////////////////////////////////////////////////////
// BST //////////////////////////////////////////////////////////////////////////////////
// //////////////////////////////////////////////////////////////////////////////////////
public class BST {
    // void insert( x )         - wstaw x
    // void remove( x )         - usun x
    // Comparable find( x )     - zwraca x
    // Comparable findMin( )    - zwraca najmniejszy element
    // Comparable findMax( )    - zwraca najwiekszy element
    // boolean isEmpty( )       - zwraca true, jezeli puste
    // void makeEmpty( )        - usuwa wszystkie elementy
    // void printTree( )        - drukuje drzewo w posortowanej kolejności

    // Statystyki
    public int comparisonsIns;
    public int comparisonsDel;

    // Konstruktor
    public BST() {
        root = null;
        this.comparisonsIns = 0;
        this.comparisonsDel = 0;
    }

    public int getComparisonsIns() {
        return comparisonsIns;
    }

    public void setComparisonsIns(int value) {
        this.comparisonsIns = value;
    }

    public int getComparisonsDel() {
        return comparisonsDel;
    }

    public void setComparisonsDel(int value) {
        this.comparisonsDel = value;
    }

    // Wstaw element do drzewa
    public void insert(Comparable x) {
        root = insert(x, root);
    }

    // Usun element z drzewa, jezeli istnieje.
    public void remove(Comparable x) {
        root = remove(x, root);
    }

    // Znajdz najmniejszy element
    public Comparable findMin() {
        return elementAt(findMin(root));
    }

    // Znajdz najwiekszy element
    public Comparable findMax() {
        return elementAt(findMax(root));
    }

    // Znajdz element
    public Comparable find(Comparable x) {
        return elementAt(find(x, root));
    }

    // Usun elementy z drzewa
    public void makeEmpty() {
        root = null;
    }

    // Zwraca TRUE, jezeli drzewo jest puste
    public boolean isEmpty() {
        return root == null;
    }

    // Drukuje elementy drzewa w posortowanej kolejności
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }

    // Zwraca element z wezla
    private Comparable elementAt(BinaryNode t) {
        return t == null ? null : t.key;
    }

    // Wstawia element do poddrzewa
    private BinaryNode insert(Comparable x, BinaryNode t) {
        if (t == null) {
            t = new BinaryNode(x, null, null);
        } else if (x.compareTo(t.key) < 0) {
            comparisonsIns++;
            t.left = insert(x, t.left);
        } else if (x.compareTo(t.key) > 0) {
            comparisonsIns++;
            t.right = insert(x, t.right);
        } else {
            comparisonsIns++;
        }  // Duplikat
        return t;
    }

    // Usuwa element z poddrzewa
    private BinaryNode remove(Comparable x, BinaryNode t) {
        if (t == null)
            return t;   // Nie znaleziono elementu - nie rob nic
        if (x.compareTo(t.key) < 0) {
            comparisonsDel++;
            t.left = remove(x, t.left);
        } else if (x.compareTo(t.key) > 0) {
            comparisonsDel++;
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) // Dwoch synów
        {
            t.key = findMin(t.right).key;
            t.right = remove(t.key, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }

    // Najmniejszy element w poddrzewie
    private BinaryNode findMin(BinaryNode t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }

    // Najwiekszy element w poddrzewie
    private BinaryNode findMax(BinaryNode t) {
        if (t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    // Zwraca element szukany w poddrzewie
    private BinaryNode find(Comparable x, BinaryNode t) {
        if (t == null)
            return null;
        if (x.compareTo(t.key) < 0)
            return find(x, t.left);
        else if (x.compareTo(t.key) > 0)
            return find(x, t.right);
        else
            return t;    // Zgadza sie
    }

    // Drukowanie drzewa
    private void printTree(BinaryNode t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.key);
            printTree(t.right);
        }
    }

    // Korzen drzewa
    private BinaryNode root;

    // ///////////////////////////////////////////////////////////////////////////////////
    // BINARY NODE ///////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////////////

    public class BinaryNode {
        // Konstruktor
        BinaryNode(Comparable theKey) {
            this(theKey, null, null);
        }

        BinaryNode(Comparable theKey, BinaryNode lt, BinaryNode rt) {
            key = theKey;
            left = lt;
            right = rt;
        }

        // Klucz
        Comparable key;
        // Lewy syn
        BinaryNode left;
        // Prawy syn
        BinaryNode right;
    }
}