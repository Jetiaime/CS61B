package hashmap;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author Jetiaime
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Some Constant Values */
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_MAX_LOAD_FACTOR = 0.75;
    private static final int GROW_FACTOR = 2;

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private int capacity;
    private final double maxLoad;


    /**
     * Constructors
     */
    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_MAX_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.size = 0;
        this.capacity = initialSize;
        this.maxLoad = maxLoad;
        buckets = createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * <p> 1. Insert items (`add` method)
     * <p> 2. Remove items (`remove` method)
     * <p> 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = new Collection[tableSize];
        for (int i = 0; i < tableSize; ++i) {
            table[i] = createBucket();
        }
        return table;
    }

    private int getIndex(K key) {
        return key.hashCode() & (capacity - 1);
    }

    @Override
    public void clear() {
        buckets = createTable(capacity);
        size = 0;
    }

    private Node getNode(K key) {
        for (Node node : buckets[getIndex(key)]) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize(int newInitialSize) {
        Collection<Node>[] newBuckets = createTable(newInitialSize);
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                newBuckets[node.hashCode() % newInitialSize].add(node);
            }
        }
        this.capacity = newInitialSize;
        this.buckets = newBuckets;
    }

    @Override
    public void put(K key, V value) {
        if (size * 1.0 / capacity >= maxLoad) {
            resize(GROW_FACTOR * capacity);
        }
        Node node = getNode(key);
        if (node == null) {
            ++size;
            buckets[getIndex(key)].add(createNode(key, value));
        } else {
            node.value = value;
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                keySet.add(node.key);
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    private class KeyIterator implements Iterator<K> {
        int current;
        Node next;

        private KeyIterator() {
            next = null;
            current = 0;
            for (; current < buckets.length && (next = buckets[current++].iterator().next()) == null; ) ;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public K next() {
            Node e = next;
            if ((next = buckets[current].iterator().next()) == null) {
                for (; current < buckets.length && (next = buckets[current++].iterator().next()) == null; ) ;
            }
            return e.key;
        }
    }


    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

}
