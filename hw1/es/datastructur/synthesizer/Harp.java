package es.datastructur.synthesizer;

public class Harp {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    private static final int FACTOR = 2;

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public Harp(double frequency) {

        int capacity = (int) Math.round(SR / frequency) ;
        buffer = new ArrayRingBuffer<>(capacity * FACTOR);

        while (!buffer.isFull()){
            buffer.enqueue(0.0 );
        }

    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {

        int fillCount = buffer.fillCount();
        while(fillCount-- != 0){
            buffer.dequeue();
            buffer.enqueue(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        buffer.enqueue(- ( buffer.dequeue() + buffer.peek() ) / 2.0 * DECAY);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.peek();
    }
}