package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void sanityContainsTest() {
        ArrayHeapMinPQ<String> t = new ArrayHeapMinPQ();
        for (int i = 0; i < 455; i++) {
            t.add("hi" + i, i);
            //make sure put is working via contains
            assertTrue(t.contains("hi" + i));
        }
        for (int i = 0; i < 455; i++) {
            assertTrue(t.contains("hi" + i));
        }
    }

    @Test
    public void sanityChangePriorityTest() {
        ExtrinsicMinPQ<String> t = new ArrayHeapMinPQ();
        for (int i = 0; i < 10; i++) {
            t.add("hi" + i, i);
        }
        ((ArrayHeapMinPQ)t).printMinHeap();

        t.changePriority("hi0", 11);

        ((ArrayHeapMinPQ)t).printMinHeap();
    }


}
