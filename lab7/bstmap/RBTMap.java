package bstmap;

import java.util.*;

public class RBTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private transient int size = 0;

    private Entry<K, V> root;

    @Override
    public void clear() {

    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        put(key, value, true);
    }

    private V put(K key, V value, boolean replaceOld) {
        Entry<K, V> t = root;
        if (t == null) {
            addEntryToEmptyMap(key, value);
            return null;
        }
        int cmp;
        Entry<K, V> parent;
        do {
            parent = t;
            cmp = key.compareTo(t.key);
            if (cmp < 0) {
                t = leftOf(t);
            } else if (cmp > 0) {
                t = rightOf(t);
            } else {
                V oldValue = t.getValue();
                if (replaceOld || oldValue == null) {
                    t.setValue(value);
                }
                return oldValue;
            }
        } while (t != null);
        addEntry(key, value, parent, cmp < 0);
        return null;
    }

    private void addEntryToEmptyMap(K key, V value) {
        root = new Entry<>(key, value, null);
        size = 1;
    }

    private void addEntry(K key, V value, Entry<K, V> parent, boolean addToLeft) {
        ++size;
        Entry<K, V> e = new Entry<>(key, value, parent);
        if (addToLeft) {
            parent.left = e;
        } else {
            parent.right = e;
        }
        fixAfterInsertion(e);
    }

    private void fixAfterInsertion(Entry<K, V> e) {
        setColor(e, RED);
        while (e != null && e != root && colorOf(parentOf(e)) == RED) {
            if (parentOf(e) == leftOf(parentOf(parentOf(e)))) {
                // LL / LR
                Entry<K, V> p = rightOf(parentOf(parentOf(e)));
                if (colorOf(p) == RED) {
                    setColor(parentOf(e), BLACK);
                    setColor(p, BLACK);
                    setColor(parentOf(parentOf(e)), RED);
                    e = parentOf(parentOf(e));
                } else {
                    if (e == rightOf(parentOf(e))) {
                        e = parentOf(e);
                        rotateLeft(e);
                    }
                    setColor(parentOf(e), BLACK);
                    setColor(parentOf(parentOf(e)), RED);
                    rotateRight(parentOf(parentOf(e)));
                }
            } else {
                // RR / RL
                Entry<K, V> p = leftOf(parentOf(parentOf(e)));
                if (colorOf(p) == RED) {
                    setColor(parentOf(e), BLACK);
                    setColor(p, BLACK);
                    setColor(parentOf(parentOf(e)), RED);
                    e = parentOf(parentOf(e));
                } else {
                    if (e == leftOf(parentOf(e))) {
                        e = parentOf(e);
                        rotateRight(e);
                    }
                    setColor(parentOf(e), BLACK);
                    setColor(parentOf(parentOf(e)), RED);
                    rotateLeft(parentOf(parentOf(e)));
                }
            }
        }
        setColor(root, BLACK);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    private void rotateLeft(Entry<K, V> p) {
        if (p != null) {
            Entry<K, V> r = p.right;
            p.right = r.left;
            if (r.left != null) {
                r.left.parent = p;
            }
            if (p.parent == null) {
                root = r;
            } else if (p.parent.left == p) {
                p.parent.left = r;
            } else {
                p.parent.right = r;
            }
            r.left = p;
            p.parent = r;
        }
    }

    private void rotateRight(Entry<K, V> p) {
        if (p != null) {
            Entry<K, V> l = p.left;
            p.left = l.right;
            if (l.right != null) {
                l.right.parent = p;
            }
            if (p.parent == null) {
                root = l;
            } else if (p.parent.left == p) {
                p.parent.left = l;
            } else {
                p.parent.right = l;
            }
            l.right = p;
            p.parent = l;
        }
    }

    private static <K, V> Entry<K, V> leftOf(Entry<K, V> p) {
        return (p == null) ? null : p.left;
    }

    private static <K, V> Entry<K, V> parentOf(Entry<K, V> p) {
        return (p == null) ? null : p.parent;
    }

    private static <K, V> Entry<K, V> rightOf(Entry<K, V> p) {
        return (p == null) ? null : p.right;
    }

    private static <K, V> boolean colorOf(Entry<K, V> p) {
        return (p == null) ? BLACK : p.color;
    }

    private static <K, V> boolean setColor(Entry<K, V> p, boolean newColor) {
        boolean oldColor = p.color;
        p.color = newColor;
        return oldColor;
    }

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    static final class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;
        boolean color = BLACK;

        Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Entry<?, ?> e)) {
                return false;
            }
            return valueEquals(key, e.getKey()) && valueEquals(value, e.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, left, right, parent, color);
        }
    }

    /*  Little Util method  */

    static final boolean valueEquals(Object o1, Object o2) {
        return (o1 == null ? o2 == null : o1.equals(o2));
    }
}
