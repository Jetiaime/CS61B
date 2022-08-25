package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddAndThreeRemove() {
        AListNoResizing<Integer> pass = new AListNoResizing<>();
        BuggyAList<Integer> test = new BuggyAList<>();
        for (int i = 4; i <= 6; ++i) {
            pass.addLast(i);
            test.addLast(i);
        }
        assertEquals(pass.size(), test.size());
        for (int i = 0; i < 3; ++i) {
            assertEquals(pass.removeLast(), test.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                assertEquals(L.getLast(), BL.getLast());
            } else if (operationNumber == 1) {
                // size
                assertEquals(L.size(), BL.size());
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() > 0) {
                    assertEquals(L.getLast(), BL.getLast());
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() > 0) {
                    assertEquals(L.removeLast(), BL.removeLast());
                }
            }
        }
    }
}
