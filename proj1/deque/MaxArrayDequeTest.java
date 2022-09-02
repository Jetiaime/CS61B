package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest {
    @Test
    public void testMax() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(String::compareTo);
        String[] ss = new String[]{"a", "bc", "c", "adc", "bb"};
        for (String s : ss) {
            mad.addFirst(s);
        }
        assertEquals("c", mad.max());
        assertEquals("adc", mad.max(Comparator.comparingInt(String::length)));
    }
}
