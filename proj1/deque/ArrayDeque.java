package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {

    private static final int FACTOR = 2;
    private static final double FOLD = 0.25;

    private static final int BOUND = 16;

    private int size;
    private int head;
    private int tail;

    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[BOUND];
        size = 0;
        head = 0;
        tail = 1;
    }

    /**
     * @param num number to add
     * @param mod
     * @return the number add one and mod the g
     */
    private int backward(int num, int mod) {
        return (num + 1) % mod;
    }

    private int backward(int num) {
        return backward(num, items.length);
    }

    /**
     * @param num
     * @param mod
     * @return
     */
    private int forward(int num, int mod) {
        return (num - 1 + mod) % mod;
    }

    private int forward(int num) {
        return forward(num, items.length);
    }

    /**
     * resize the queue
     *
     * @param new_size new size of the queue
     */
    private void resize(int new_size) {
        T[] new_items = (T[]) new Object[new_size];
        int actHead = backward(head), actTail = forward(tail);
        if (actHead <= actTail) {
            System.arraycopy(items, actHead, new_items, 0, size);
        } else {
            System.arraycopy(items, actHead, new_items, 0, items.length - actHead);
            System.arraycopy(items, 0, new_items, items.length - actHead, tail);
        }
        items = new_items;
        head = forward(0);
        tail = size;
    }

    /**
     * check the size and narrow it if its length is too large.
     */
    private void checkAndNarrow() {
        if (items.length > BOUND && 1.0 * size / items.length < FOLD) {
            resize(items.length / FACTOR);
        }
    }

    /**
     * check the size and expand it if its length is too small.
     */
    private void checkAndExpand() {
        if (size == items.length) {
            resize(size * FACTOR);
        }
    }

    @Override
    public void addFirst(T item) {
        checkAndExpand();
        items[head] = item;
        head = forward(head);
        ++size;
    }

    @Override
    public void addLast(T item) {
        checkAndExpand();
        items[tail] = item;
        tail = backward(tail);
        ++size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = backward(head); i < tail; i = backward(i)) {
            System.out.print(items[i] + " ");
        }
    }

    @Override
    public T removeFirst() {
        checkAndNarrow();
        if (isEmpty()) {
            return null;
        }
        head = backward(head);
        --size;
        return items[head];
    }

    @Override
    public T removeLast() {
        checkAndNarrow();
        if (isEmpty()) {
            return null;
        }
        tail = forward(tail);
        --size;
        return items[tail];
    }

    @Override
    public T get(int index) {
        return (!(0 <= index && index < size)) ? null : items[backward((head + index) % items.length)];
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
