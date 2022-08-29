package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public LinkedListDeque() {
        size = 0;
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
    }

    /**
     * the helper function for getRecursive.
     *
     * @param index the index that you want to get.
     * @param cur   cur item.
     * @return the item at the given index.
     */
    private T getRecursive(int index, Node<T> cur) {
        return index == 0 ? cur.item : getRecursive(index - 1, cur.next);
    }

    /**
     * Gets the item at the given index, but recursively.
     *
     * @param index the index that you want to get.
     * @return the item at the given index.
     */
    public T getRecursive(int index) {
        return !(0 <= index && index <= size) ? null : getRecursive(index, head.next);
    }


    public void addFirst(T item) {
        Node<T> cur = new Node<>(item, head, head.next);
        head.next.prev = cur;
        head.next = cur;
        ++size;
    }

    public void addLast(T item) {
        Node<T> cur = new Node<>(item, tail.prev, tail);
        tail.prev.next = cur;
        tail.prev = cur;
        ++size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }


    public int size() {
        return this.size;
    }


    public void printDeque() {
        for (Node<T> cur = head.next; cur != tail; cur = cur.next) {
            System.out.print(cur.item + " ");
        }
    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<T> cur = head.next;
        head.next = cur.next;
        cur.next.prev = head;
        --size;
        return cur.item;
    }


    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node<T> cur = tail.prev;
        tail.prev = cur.prev;
        cur.prev.next = tail;
        --size;
        return cur.item;
    }


    public T get(int index) {
        if (!(0 <= index && index < size)) {
            return null;
        }
        Node<T> cur = head.next;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        return cur.item;
    }


    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof LinkedListDeque<?>) {
            LinkedListDeque<T> otherDeque = (LinkedListDeque<T>) o;
            if (otherDeque.size() != size) {
                return false;
            }
            for (int i = 0; i < size; ++i) {
                if (otherDeque.get(i) != get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node<T> cur = head.next;

        @Override
        public boolean hasNext() {
            return cur != tail;
        }

        @Override
        public T next() {
            T item = cur.item;
            cur = cur.next;
            return item;
        }
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> prev;

        private Node() {
        }

        private Node(T item) {
            this.item = item;
        }

        private Node(T item, Node<T> prev, Node<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

}
