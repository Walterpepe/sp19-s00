package es.datastructur.synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);

        assertEquals((Integer) 1, arb.dequeue());
        assertEquals((Integer) 2, arb.dequeue());
        assertEquals((Integer) 3, arb.dequeue());
    }

    @Test
    public void EqualTest() {
        ArrayRingBuffer<Integer> arb1 = new ArrayRingBuffer<>(10);
        arb1.enqueue(1);
        arb1.enqueue(2);
        arb1.enqueue(3);

        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(10);
        arb2.enqueue(1);
        arb2.enqueue(2);
        arb2.enqueue(3);

        assertEquals(arb1, arb2);
    }

}
