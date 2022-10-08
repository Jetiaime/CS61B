package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private static final double RESIZE_FACTOR = 1.5;

    /** Keys and values are stored in a BST of Node objects.
     *  This variable stores the tree.
     */
    private BSTNode<K, V> root;

    private int size = 0;

    public BSTMap() {
        root = null;
    }

    /** Clear all the node in the tree. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Return true if the Map contains the key. */
    @Override
    public boolean containsKey(K key) {
        return get(key, root) != null;
    }

    /** Return the value for the given key. Return NULL if it's not contained.  */
    @Override
    public V get(K key) {
        BSTNode<K, V> lookup = get(key, root);
        if (lookup == null) {
            return null;
        }
        return lookup.getValue();
    }

    private BSTNode<K, V> get(K key, BSTNode<K, V> p) {
        if (p == null) {
            return null;
        } else if (p.getKey().equals(key)) {
            return p;
        } else if (p.getKey().compareTo(key) < 0) {
            return get(key, p.getLeft());
        } else {
            return get(key, p.getRight());
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private BSTNode<K, V> put(K key, V value, BSTNode<K, V> p) {
        if (p == null) {
            ++size;
            p = new BSTNode<>(key, value, null, null);
        } else if (p.getKey().equals(key)) {
            p.setValue(value);
        } else if (p.getKey().compareTo(key) < 0) {
            p.setLeft(put(key, value, p.getLeft()));
        } else {
            p.setRight(put(key, value, p.getRight()));
        }
        return p;
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

    /**
     * Represents one node in the BST that stores the k-v pairs in the dictionary.
     * @param <K> key's type
     * @param <V> value's type
     */
    private static class BSTNode<K extends Comparable<K>, V> {

        /** Stores the key of the k-v pair of this node in the BST. */
        private final K key;
        /** Stores the value of the k-v pair of this node in the BST. */
        private V val;
        /** Stores the left node in the BST. */
        private BSTNode<K, V> left;
        /** Stores the right node in the BST. */
        private BSTNode<K, V> right;

        public final K getKey() { return key; }
        public final V getValue() { return val; }
        public final BSTNode<K, V> getLeft() { return left; }
        public final BSTNode<K, V> getRight() { return right; }

        public final V setValue(V newValue) {
            V oldValue = val;
            val = newValue;
            return oldValue;
        }

        public final BSTNode<K, V> setLeft(BSTNode<K, V> newLeft) {
            BSTNode<K, V> oldLeft = left;
            left = newLeft;
            return oldLeft;
        }

        public final BSTNode<K, V> setRight(BSTNode<K, V> newRight) {
            BSTNode<K, V> oldRight = right;
            right = newRight;
            return oldRight;
        }

        BSTNode() {
            this.key = null;
            this.val = null;
            this.left = null;
            this.right = null;
        }

        BSTNode(K key, V val, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
