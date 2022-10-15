package priorityqueue;

public class HeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue61B<E> {

    private static final int INITIAL_CAPACITY = 33;

    private static final double EXPAND_FACTOR = 0.75;

    private E[] queue;

    private int size = 0;

    private int parent(int x) {
        return x >>> 1;
    }

    private int leftChild(int x) {
        return x << 1;
    }

    private int rightChild(int x) {
        return x << 1 | 1;
    }

    /** Swap the item of index x and the item of index y in the priority queue. */
    private void swap(int x, int y) {
        E temp = queue[x];
        queue[x] = queue[y];
        queue[y] = temp;
    }

    /** Resizing the queue. */
    private boolean resize(int newCapacity) {
        int oldCapacity = size;

        return oldCapacity == size;
    }

    public HeapPriorityQueue() {
        queue = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void clear() {
        queue = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E peek() {
        return queue[1];
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public void add(E item) {

    }
}
