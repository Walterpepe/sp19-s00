package bearmaps;

import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private PriorityNode[] items;        // store items at indices 1 to n
    private int n;                       // number of items on priority queue

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public ArrayHeapMinPQ(int initCapacity) {
//        items = (PriorityNode[]) new Object[initCapacity + 1];
//        items = new PriorityNode[initCapacity + 1]
        items = new ArrayHeapMinPQ.PriorityNode[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public ArrayHeapMinPQ() {
        this(1);
    }


    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     * {@code false} otherwise
     */
    private boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns true if the PQ contains the given item.
     *
     * @return {@code true} if this priority queue contains the given item;
     * {@code false} otherwise
     */
    @Override
    public boolean contains(T item) {
        return get(item) != -1;
    }

    private int get(T item){
        for(int i =1; i <= n; i++){
            if (items[i].getItem().equals(item)) return i;
        }
        return  -1;
    }

    /**
     * Adds an item of type T with the given priority.
     * Edit (3/13/19): If the item already exists, throw an IllegalArgumentException.
     * You may assume that item is never null.
     *
     * @param item     the item to add to this priority queue
     * @param priority the item's priority
     */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) throw new NoSuchElementException("item already exists");

        // double size of array if necessary
        if (n == items.length - 1) resize(2 * items.length);

        // add x, and percolate it up to maintain heap invariant
        items[++n] = new PriorityNode(item, priority);
        swim(n);
        assert isMinHeap();
    }

    // helper function to double the size of the heap array
    private void resize(int capacity) {
        assert capacity > n;
        ArrayHeapMinPQ.PriorityNode[] temp = new ArrayHeapMinPQ.PriorityNode[capacity];

//        System.arraycopy(items, 0, temp, 0, items.length);
        for (int i = 1; i <= n; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    /**
     * Returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public T getSmallest() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return items[1].getItem();
    }

    /**
     * Removes and returns the item with smallest priority.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public T removeSmallest() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        PriorityNode min = items[1];
        exch(1, n--);
        sink(1);
        items[n + 1] = null;     // to avoid loiterig and help with garbage collection
        if ((n > 0) && (n == (items.length - 1) / 4)) resize(items.length / 2);
        assert isMinHeap();
        return min.getItem();
    }


    /**
     * Returns the number of items on this priority queue.
     *
     * @return the number of items on this priority queue
     */
    @Override
    public int size() {
        return n;
    }

    /**
     *  Sets the priority of the given item to the given value.
     *
     * @param item     the item to add to this priority queue
     * @param priority the item's priority
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public void changePriority(T item, double priority) {
        int index = get(item);
        if (index == -1) throw new NoSuchElementException("item doesn't exists");

        items[index].setPriority(priority);

        if( ( 2*index < n && greater(index, 2*index)) || ( 2* index +1 < n && greater(index, 2*index + 1))){
            sink(index);
        }else if( index/2 > 0 && greater(index/2, index)){
            swim(index);
        }
        assert isMinHeap();
    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }


    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/
    private boolean greater(int i, int j) {
        return items[i].compareTo(items[j]) > 0;
    }

    private void exch(int i, int j) {
        PriorityNode swap = items[i];
        items[i] = items[j];
        items[j] = swap;
    }

    // is pq[1..n] a min heap?
    private boolean isMinHeap() {
        for (int i = 1; i <= n; i++) {
            if (items[i] == null) return false;
        }
        for (int i = n + 1; i < items.length; i++) {
            if (items[i] != null) return false;
        }
        if (items[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && greater(k, left)) return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }
}
