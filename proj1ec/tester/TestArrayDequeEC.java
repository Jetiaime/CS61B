package tester;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

import static org.junit.Assert.assertEquals;

public class TestArrayDequeEC {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> std = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        StringBuilder msg = new StringBuilder("\n");
        for (int i = 0; i < 128; ++i) {
            int random = StdRandom.uniform(0, 4);
            if (random == 0) {
                int k = StdRandom.uniform(10);
                std.addFirst(k);
                sol.addFirst(k);
                msg.append("addFirst(").append(k).append(")\n");
            } else if (random == 1) {
                int k = StdRandom.uniform(10);
                std.addLast(k);
                sol.add(k);
                msg.append("addLast(").append(k).append(")\n");
            } else if (random == 2) {
                if (!std.isEmpty()) {
                    Integer actual = std.removeFirst();
                    Integer expected = sol.removeFirst();
                    msg.append("removeFirst()\n");
                    assertEquals(msg.toString(), expected, actual);
                }
            } else if (random == 3) {
                if (!std.isEmpty()) {
                    Integer actual =  std.removeLast();
                    Integer expected = sol.removeLast();
                    msg.append("removeLast()\n");
                    assertEquals(msg.toString(), expected, actual);
                }
            }
        }
    }
}
