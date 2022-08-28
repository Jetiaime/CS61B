package deque;

import java.util.Iterator;

public interface Deque<T> {
    /**
     * add an item to the First of the queue
     *
     * @param item
     */
    void addFirst(T item);

    /**
     * add an item to the Last of the queue
     */
    void addLast(T item);

    /**
     * @return true if the queue is empty, otherwise return false
     */
    boolean isEmpty();

    /**
     * @return size of the queue
     */
    int size();

    /**
     * print the whole queue, like:
     */
    void printDeque();

    /**
     * remove the First item of the queue and return it if the queue have
     *
     * @return the First item of the queue, or null if the queue doesn't have
     */
    T removeFirst();

    /**
     * remove the Last item of the queue and return it if the queue have
     *
     * @return the Last item of the queue, or null if the queue doesn't have
     */
    T removeLast();

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null
     *
     * @param index the index of the item that you want to get
     * @return the item of the index, or null if no such item exists.
     */
    T get(int index);

    /**
     * @return made by the item of the queue
     */
    Iterator<T> iterator();

    /**
     * @param o Other Deque
     * @return ture if o is a Deque and contains the same contents, otherwise false.
     */
    boolean equals(Object o);


}
