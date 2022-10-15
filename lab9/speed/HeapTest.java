package speed;

import org.junit.Test;
import priorityqueue.HeapPriorityQueue;
import priorityqueue.PriorityQueue61B;

public class HeapTest {
    @Test
    public void addTest() {
        PriorityQueue61B<Integer> pq = new HeapPriorityQueue<>();
        for (int i = 1; i <= 400; ++i) {
            pq.add(i);
            assert i == pq.size();
        }
    }

    @Test
    public void clearTest() {
        PriorityQueue61B<Integer> pq = new HeapPriorityQueue<>();
        pq.add(6);
        pq.add(3);
        pq.add(-1);
        pq.add(32);

        int expected = 3;
        int actual = pq.size();
        assert expected == actual;

        pq.clear();

        expected = 0;
        actual = pq.size();
        assert expected == actual;
    }

    @Test
    public void getSmallestTest() {

    }

    @Test
    public void randomTest() {

    }
}
