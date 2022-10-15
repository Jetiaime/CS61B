package priorityqueue;

public interface PriorityQueue61B<E> {
    /**
     * Clear all items of the priority queue.
     */
    void clear();

    /**
     * Returns the numbers of items in the priority queue.
     */
    int size();

    /**
     * Returns the smallest item in the priority queue, but do not remove it.
     */
    E peek();

    /**
     * Removes the smallest item in the priority queue, and returns it as a returned value.
     */
    E poll();

    /**
     * Adds the new item into the priority queue.
     */
    void add(E item);
}
