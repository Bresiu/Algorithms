package List3.trees;

public class RBBST {

    // Wartosc liscia
    static final int NilValue = -1;
    // Wartosc zwracana, gdy drzewo jest puste a szukamy min i max
    static final int EmptyMinMaxValue = -1;
    // Wskaznik na roota
    private RBNode root;
    // Wielkosc drzewa (bez pustych elementow)
    private int size;
    // Najmniejszy element w drzewie
    private int min;
    // Najwiekszy element w drzewie
    private int max;

    public RBBST() {
        this.root = new RBNode(RBBST.NilValue);
        this.size = 0;
        this.min = EmptyMinMaxValue;
        this.max = EmptyMinMaxValue;
    }

    // Zwraca roota
    private RBNode getRoot() {
        return this.root;
    }

    // Ustawia roota
    private void setRoot(RBNode root) {
        this.root = root;
    }

    // Zwraca true, jezeli drzewo jest puste
    public boolean empty() {
        return root.isNil();
    }

    // Zwraca true, jezeli drzewo zawiera "i"
    public boolean contains(int i) {
        if (!empty()) {
            return root.contains(i);
        } else {
            return false;
        }
    }

    // //////////////////////////////////////////////////////////////////////////////////////
    // RB-INSERT && RB-INSERT-FIXUP /////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////
    public void insert(int i) {
        RBNode newNode = new RBNode(i);

        // Pierwszy wezel w drzewie
        if (empty()) {
            setRoot(newNode);
            this.min = i;
            this.max = i;
        } else {
            redBlackInsert(newNode);

            // Ustawia wartosci min i max
            if (this.min > i) {
                this.min = i;
            }
            if (this.max < i) {
                this.max = i;
            }
        }

        // Zwieksza wielkosc drzewa
        this.size++;
    }

    private void redBlackInsert(RBNode newNode) {
        RBNode y;

        if (getRoot().insert(newNode)) {
            newNode.setRed();

            while (newNode != getRoot() && newNode.getParent().isRed()) {
                if (newNode.getParent() == newNode.getGrandParent()
                        .getLeftChild()) {
                    y = newNode.getGrandParent().getRightChild();

                    if (!y.isNil() && y.isRed()) {
                        newNode.getParent().setBlack();
                        y.setBlack();
                        newNode.getGrandParent().setRed();
                        newNode = newNode.getGrandParent();
                    } else {
                        if (newNode == newNode.getParent().getRightChild()) {
                            newNode = newNode.getParent();
                            leftRotate(newNode);
                        }

                        if (newNode.hasParent()) {
                            newNode.getParent().setBlack();
                            if (newNode.hasGrandParent()) {
                                newNode.getGrandParent().setRed();
                                rightRotate(newNode.getGrandParent());
                            }
                        }
                    }

                } else {
                    y = newNode.getGrandParent().getLeftChild();

                    if (!y.isNil() && y.isRed()) {
                        newNode.getParent().setBlack();
                        y.setBlack();
                        newNode.getGrandParent().setRed();
                        newNode = newNode.getGrandParent();
                    } else {
                        if (newNode == newNode.getParent().getLeftChild()) {
                            newNode = newNode.getParent();
                            rightRotate(newNode);
                        }

                        if (newNode.hasParent()) {
                            newNode.getParent().setBlack();
                            if (newNode.hasGrandParent()) {
                                newNode.getGrandParent().setRed();
                                leftRotate(newNode.getGrandParent());
                            }
                        }
                    }
                }

            }
            getRoot().setBlack();
        }
    }

    // Zwraca wezel z najmniejsza wartoscia, wieksza niz x.getKey()
    private RBNode successor(RBNode x) {
        if (x.hasRightChild()) {
            return x.getRightChild().minNode();
        } else {
            RBNode y = x.getParent();
            while (!y.isNil() && x == y.getRightChild()) {
                x = y;
                y = y.getParent();
            }
            return y;
        }
    }

    // Zwraca najmniejsza wartosc w drzewie
    // Gdy drzewo jest puste - zwraca -1
    public int min() {
        return this.min;
    }

    // Zwraca najwieksza wartosc w drzewie
    // Gdy drzewo jest puste - zwraca -1
    public int max() {
        return this.max;
    }

    // Zwraca rosnaca tabice wartosci w drzewie
    public int[] toIntArray() {
        int[] arr = new int[size()];
        getRoot().fillIntArray(arr, 0);
        return arr;
    }

    // Zwraca true, jezeli drzewo jest drzewem czerwono czarnym
    public boolean isValid() {
        if (root.isNil()) {
            return true;
        } else {
            return getRoot().isBSTValid() &&
                    getRoot().isBlackValid() &&
                    getRoot().isRedValid();
        }
    }

    // Zwraca najdluzsza glebokosc
    public int maxDepth() {
        if (empty()) {
            return -1;
        } else {
            return getRoot().maxDepth();
        }
    }

    // Zwraca minimalna glebokosc liscia
    public int minLeafDepth() {
        if (empty()) {
            return -1;
        } else {
            return getRoot().minLeafDepth();
        }
    }

    // Zwraca ilosc wezlow
    public int size() {
        return size;
    }

    // Zwraca string reprezentujacy drzewo
    public String toString() {
        if (!empty()) {
            return String.format("<Tree %s>", root);
        } else {
            return "<Tree empty>";
        }
    }

    // //////////////////////////////////////////////////////////////////////////////////////
    // LEFT-ROTATE //////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////
    private void leftRotate(RBNode x) {
        RBNode y = x.getRightChild();
        x.setRightChild(y.getLeftChild());

        if (y.hasLeftChild()) {
            y.getLeftChild().setParent(x);
        }

        y.setParent(x.getParent());

        if (!x.hasParent()) {
            setRoot(y);
        } else if (x == x.getParent().getLeftChild()) {
            x.getParent().setLeftChild(y);
        } else {
            x.getParent().setRightChild(y);
        }

        y.setLeftChild(x);
        x.setParent(y);
    }

    // //////////////////////////////////////////////////////////////////////////////////////
    // RIGHT-ROTATE /////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////
    private void rightRotate(RBNode x) {
        RBNode y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());

        if (y.hasRightChild()) {
            y.getRightChild().setParent(x);
        }

        y.setParent(x.getParent());

        if (!x.hasParent()) {
            setRoot(y);
        } else if (x == x.getParent().getRightChild()) {
            x.getParent().setRightChild(y);
        } else {
            x.getParent().setLeftChild(y);
        }

        y.setRightChild(x);
        x.setParent(y);
    }

    // //////////////////////////////////////////////////////////////////////////////////////
    // RB-DELETE ////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////
    public void delete(int i) {
        RBNode z = getRoot().search(i);

        // i nie znaleziono
        if (z == null) {
            return;
        } else {
            RBNode x, y;

            if (!z.hasLeftChild() || !z.hasRightChild()) {
                // z nie ma dwoch synow
                y = z;
            } else {
                // z ma dwoch synow
                y = successor(z);
            }

            if (y.hasLeftChild()) {
                x = y.getLeftChild();
            } else {
                x = y.getRightChild();
            }

            x.setParent(y.getParent());
            if (getRoot() == y) {
                setRoot(x);
            } else if (y == y.getParent().getLeftChild()) {
                y.getParent().setLeftChild(x);
            } else {
                y.getParent().setRightChild(x);
            }

            // zamienia wartosci y i z
            if (y != z) {
                z.setKey(y.getKey());
            }

            if (y.isBlack()) {
                deleteFixup(x);
            }

            // poprawia wielkosc
            this.size--;

            // poprawia wartosci min i max
            if (this.size == 0) {
                this.min = EmptyMinMaxValue;
                this.max = EmptyMinMaxValue;
            } else {
                if (this.min == i) {
                    this.min = getRoot().minValue();
                }
                if (this.max == i) {
                    this.max = getRoot().maxValue();
                }
            }
        }
    }

    // //////////////////////////////////////////////////////////////////////////////////////
    // RB-DELETE-FIXUP //////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////
    private void deleteFixup(RBNode x) {
        RBNode w;

        while (getRoot() != x && x.isBlack()) {
            if (x == x.getParent().getLeftChild()) {
                w = x.getParent().getRightChild();

                // Przypadek 1
                if (w.isRed()) {
                    w.setBlack();
                    x.getParent().setRed();
                    leftRotate(x.getParent());
                    w = x.getParent().getRightChild();
                }

                // Przypadek 2
                if (w.getLeftChild().isBlack() && w.getRightChild().isBlack()) {
                    w.setRed();
                    x = x.getParent();
                } else {
                    // Przypadek 3
                    if (w.getRightChild().isBlack()) {
                        w.getLeftChild().setBlack();
                        w.setRed();
                        rightRotate(w);
                        w = x.getParent().getRightChild();
                    }

                    // Przypadek 4
                    w.setBlack(x.getParent().isBlack());
                    x.getParent().setBlack();
                    w.getRightChild().setBlack();
                    leftRotate(x.getParent());
                    x = getRoot();
                }
            } else {
                w = x.getParent().getLeftChild();

                // Przypadek 1
                if (w.isRed()) {
                    w.setBlack();
                    x.getParent().setRed();
                    rightRotate(x.getParent());
                    w = x.getParent().getLeftChild();
                }

                // Przypadek 2
                if (w.getRightChild().isBlack() && w.getLeftChild().isBlack()) {
                    w.setRed();
                    x = x.getParent();
                } else {
                    // Przypadek 3
                    if (w.getLeftChild().isBlack()) {
                        w.getRightChild().setBlack();
                        w.setRed();
                        leftRotate(w);
                        w = x.getParent().getLeftChild();
                    }

                    // Przypadek 4
                    w.setBlack(x.getParent().isBlack());
                    x.getParent().setBlack();
                    w.getLeftChild().setBlack();
                    rightRotate(x.getParent());
                    x = getRoot();
                }
            }
        }
        x.setBlack();
    }

    // //////////////////////////////////////////////////////////////////////////////////////
    // RB-NODE //////////////////////////////////////////////////////////////////////////////
    // //////////////////////////////////////////////////////////////////////////////////////
    public class RBNode {
        // Klucz wezla
        private int key;
        // True, jezeli wezel jest czarny
        private boolean isBlack;
        // Wskaznik na lewego syna
        private RBNode leftChild;
        // Wskaznik na prawego syna
        private RBNode rightChild;
        // Wskaznik na ojca
        private RBNode parent;

        // Tworzy nowy wezel
        public RBNode(int key, boolean isBlack) {
            this.key = key;
            this.isBlack = isBlack;

            if (!isNil()) {
                setLeftChild(new RBNode());
                setRightChild(new RBNode());
            }
        }

        // Tworzy wezel z danym kluczem
        public RBNode(int key) {
            this(key, true);
        }

        // Tworzy pusty lisc
        public RBNode() {
            this(RBBST.NilValue, true);
        }

        // Zwraca true, jezeli wezel jest pustym lisciem
        private boolean isNil() {
            return this.key == RBBST.NilValue;
        }

        // Wskaznik na rodzica
        public RBNode getParent() {
            return this.parent;
        }

        // Zwraca true, jezeli wezel ma ojca
        public boolean hasParent() {
            return parent != null;
        }

        // Zwraca dziadka
        public RBNode getGrandParent() {
            return getParent().getParent();
        }

        // Zwraca true, jezeli wezel ma dziadka.
        public boolean hasGrandParent() {
            return hasParent() && getParent().hasParent();
        }

        // Ustaw ojca
        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        // Zwraca wartosc
        public int getKey() {
            return key;
        }

        // Ustaw wartosc
        public void setKey(int key) {
            this.key = key;
        }

        // Zwraca true, jezeli wezel jest czarny
        public boolean isBlack() {
            return isBlack;
        }

        // Ustaw czarny kolor wezla
        public void setBlack() {
            this.isBlack = true;
        }

        // True dla czarnego wezla, false dla czerwonego
        public void setBlack(boolean isBlack) {
            this.isBlack = isBlack;
        }

        // Zwraca true, jezeli wezel jest czerwony
        public boolean isRed() {
            return !isBlack();
        }

        // Ustaw czerwony kolor wezla
        public void setRed() {
            this.isBlack = false;
        }

        // Zwraca wskaznik na lewego syna
        public RBNode getLeftChild() {
            return leftChild;
        }

        // Ustaw lewego syna
        public void setLeftChild(RBNode leftChild) {
            this.leftChild = leftChild;

            if (hasLeftChild()) {
                leftChild.setParent(this);
            }
        }

        // Zwraca wskaznik na prawego syna
        public RBNode getRightChild() {
            return rightChild;
        }

        // Ustaw prawego syna
        public void setRightChild(RBNode rightChild) {
            this.rightChild = rightChild;

            if (hasRightChild()) {
                rightChild.setParent(this);
            }
        }

        // Zwraca true, jezeli wezel jest lisciem (jezeli jego lewy i prawy syn
        // sa NILami
        public boolean isLeaf() {
            return !hasLeftChild() && !hasRightChild();
        }

        // Zwraca true, jezeli wezel posiada lewego syna
        public boolean hasLeftChild() {
            try {
                return !leftChild.isNil();
            } catch (NullPointerException npe) {
                return false;
            }
        }

        // Zwraca true, jezeli wezel podiada prawego syna
        public boolean hasRightChild() {
            try {
                return !rightChild.isNil();
            } catch (NullPointerException npe) {
                return false;
            }
        }

        // Zwraca wskaznik na wezel, ktory zawiera szukana wartosc
        public RBNode search(int i) {
            if (isNil()) {
                return null;
            } else if (getKey() == i) {
                return this;
            } else {
                if (i < getKey() && hasLeftChild()) {
                    return getLeftChild().search(i);
                } else if (hasRightChild()) {
                    return getRightChild().search(i);
                }
            }
            return null;
        }

        // Zwraca true, jezeli szukany klucz znajduje sie w wezle, albo
        // galeziach nizej
        public boolean contains(int i) {
            return search(i) != null;
        }

        // Wstaw wezel ponizej
        public boolean insert(RBNode newNode) {
            if (newNode.getKey() < getKey()) {
                if (hasLeftChild()) {
                    return getLeftChild().insert(newNode);
                } else {
                    setLeftChild(newNode);
                    return true;
                }
            } else if (newNode.getKey() > getKey()) {
                if (hasRightChild()) {
                    return getRightChild().insert(newNode);
                } else {
                    setRightChild(newNode);
                    return true;
                }
            } else { // Klucz aktualnie istnieje
                return false;
            }
        }

        // Zwraca wskaznik na wezel z najmniejsza wartoscia
        private RBNode minNode() {
            if (hasLeftChild()) {
                return getLeftChild().minNode();
            } else {
                return this;
            }
        }

        // Zwraca najmniejsza wartosc w najmniejszym wezle
        private int minValue() {
            return minNode().getKey();
        }

        // Zwraca wskaznik do wezla zawierajacy najwieksza wartosc
        private RBNode maxNode() {
            if (hasRightChild()) {
                return getRightChild().maxNode();
            } else {
                return this;
            }
        }

        // Zwraca maksymalna wartosc w wezle
        private int maxValue() {
            return maxNode().getKey();
        }

        // Rekurencyjnie wypelnia drzewo
        // loc - pozycja w tablicy
        public int fillIntArray(int[] arr, int loc) {
            if (hasLeftChild()) {
                loc = getLeftChild().fillIntArray(arr, loc);
            }

            arr[loc++] = getKey();

            if (hasRightChild()) {
                loc = getRightChild().fillIntArray(arr, loc);
            }

            return loc;
        }

        // Zwraca strin z reprezentacja wezla i jego galezi
        public String toString() {
            String leftString = hasLeftChild() ? getLeftChild().toString()
                    : "x";
            String rightString = hasRightChild() ? getRightChild().toString()
                    : "x";

            return String.format("[ %d%s %s %s ]", getKey(), isBlack() ? "b"
                    : "r", leftString, rightString);
        }

        // Zwraca maksymallna glebokosc w drzewie
        public int maxDepth() {
            if (isLeaf()) {
                return 0;
            } else {
                if (hasLeftChild() && hasRightChild()) {
                    return 1 + Math.max(getLeftChild().maxDepth(),
                            getRightChild().maxDepth());
                } else if (hasLeftChild()) {
                    return 1 + getLeftChild().maxDepth();
                } else { // hasRightChild()
                    return 1 + getRightChild().maxDepth();
                }
            }
        }

        // Zwraca minimalna glebokosc w drzewie
        public int minLeafDepth() {
            if (isLeaf()) {
                return 0;
            } else {
                if (hasLeftChild() && hasRightChild()) {
                    return 1 + Math.min(getLeftChild().minLeafDepth(),
                            getRightChild().minLeafDepth());
                } else if (hasLeftChild()) {
                    return 1 + getLeftChild().minLeafDepth();
                } else { // hasRightchild()
                    return 1 + getRightChild().minLeafDepth();
                }
            }
        }

        // Zwraca true, wtedy i tylko wtedy, gdy wartosc w wezle jest wieksza niz
        // w lewym synu, oraz mniejsza niz w prawym synu
        private boolean isBSTValid() {
            if (isNil()) {
                return true;
            } else {
                if (hasLeftChild() && getKey() < getLeftChild().getKey()) {
                    return false;
                } else if (hasRightChild()
                        && getKey() > getRightChild().getKey()) {
                    return false;
                } else {
                    return getLeftChild().isBSTValid()
                            && getRightChild().isBSTValid();
                }
            }
        }

        // Zwraca true, jezeli wezel jest zgodny z zasada czerwona
        // zaden czerwony wezel nie jest poprzedzony innym czerwonym
        private boolean isRedValid() {
            if (isLeaf()) {
                return true;
            } else {
                if (isBlack()) {
                    if (hasLeftChild() && hasRightChild()) {
                        return getLeftChild().isRedValid()
                                && getRightChild().isRedValid();
                    } else if (hasLeftChild()) {
                        return getLeftChild().isRedValid();
                    } else { // hasRightChild()
                        return getRightChild().isRedValid();
                    }
                } else { // isRed()
                    if (hasLeftChild() && hasRightChild()) {
                        return getLeftChild().isBlack()
                                && getLeftChild().isRedValid()
                                && getRightChild().isBlack()
                                && getRightChild().isRedValid();
                    } else if (hasLeftChild()) {
                        return getLeftChild().isBlack()
                                && getLeftChild().isRedValid();
                    } else { // hasRightChild()
                        return getRightChild().isBlack()
                                && getRightChild().isRedValid();
                    }
                }
            }
        }

        // Czarna glebokosc
        private int blackDepth() {
            int me = isBlack() ? 1 : 0;
            if (hasLeftChild()) {
                return me + getLeftChild().blackDepth();
            } else {
                return me;
            }
        }


        // Zwraca true, jezeli wezel jest zgodny z zasada czarna
        // kazda sciezka od korzenia do liscia przechodzi przez
        // taka sama ilosc czarnych wezlow
        private boolean isBlackValid() {
            if (isLeaf()) {
                return true;
            } else {
                if (hasRightChild() && hasLeftChild()) {
                    return getRightChild().blackDepth() == getLeftChild()
                            .blackDepth();
                } else if (hasLeftChild()) {
                    return getLeftChild().blackDepth() == 0;
                } else { // hasRightChild()
                    return getRightChild().blackDepth() == 0;
                }
            }
        }
    }
}
