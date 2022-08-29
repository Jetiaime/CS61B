package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad01 = new ArrayDeque<>();

        assertTrue("An Initialize Deque should be empty!", ad01.isEmpty());

        ad01.addLast("Front");
        assertFalse("ad should contains 1 items", ad01.isEmpty());
        assertEquals(1, ad01.size());

        ad01.addLast("Middle");
        assertEquals(2, ad01.size());

        ad01.addLast("Tail");
        assertEquals(3, ad01.size());

        System.out.println("The Dequeue now is: ");
        ad01.printDeque();
    }

    @Test
    public void randomAdd() {
        ArrayDeque<Integer> ad02 = new ArrayDeque<>();

        for (Integer i = 0; i < 128; ++i) {
            assertEquals(i.intValue(), ad02.size());
            int k = StdRandom.uniform(0, 2);
            if (k == 0) {
                ad02.addFirst(i);
                assertEquals(i, ad02.get(0));
            } else if (k == 1) {
                ad02.addLast(i);
                assertEquals(i, ad02.get(ad02.size() - 1));
            }
        }
    }

    @Test
    public void removeEmptyTest() {
        ArrayDeque<Integer> ad03 = new ArrayDeque<>();

        ad03.addFirst(1);
        assertEquals(Integer.valueOf(1), ad03.removeLast());

        ad03.addLast(2);
        assertEquals(Integer.valueOf(2), ad03.removeFirst());

        assertEquals(0, ad03.size());
    }

    @Test
    public void removeFirstAndLast() {
        ArrayDeque<Integer> ad04 = new ArrayDeque<>();
        for (Integer i = 0; i < 128; ++i) {
            ad04.addFirst(i);
        }
        for (Integer i = 127; i >= 0; --i) {
            assertEquals(i, ad04.removeFirst());
        }
        //
        for (Integer i = 0; i < 128; ++i) {
            ad04.addLast(i);
        }
        for (Integer i = 127; i >= 0; --i) {
            assertEquals(i, ad04.removeLast());
        }
        //
        for (Integer i = 0; i < 128; ++i) {
            ad04.addLast(i);
        }
        for (Integer i = 0; i < 128; ++i) {
            assertEquals(i, ad04.removeFirst());
        }
        //
        for (Integer i = 0; i < 128; ++i) {
            ad04.addFirst(i);
        }
        for (Integer i = 0; i < 128; ++i) {
            assertEquals(i, ad04.removeLast());
        }
    }

    @Test
    public void bigLLDequeTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> ad05 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            ad05.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad05.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad05.removeLast(), 0.0);
        }
    }

    @Test
    public void iteratorTest() {
        ArrayDeque<Integer> ad06 = new ArrayDeque<>();
        for (int i = 0; i < 100; ++i) {
            ad06.addLast(i);
        }
        int ind = 0;
        for (int i : ad06) {
            assertEquals(i, ind++);
        }
    }
}
