public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;
    private static final int RFACTOR = 2;

    /* Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 0;
    }

    /* Creates a deep copy of other. */
    public ArrayDeque(ArrayDeque other) {
        System.arraycopy(other,0, items, 0, other.size());
        size = other.size();
        head = 0;
        tail = items.length - 1;
    }

    public int minusOne(int index){
        if ( index == 0) {
            return items.length - 1;
        }else{
            return index - 1;
        }
    }

    public int addOne(int index){
        if ( index == items.length - 1) {
            return 0;
        }else{
            return index + 1;
        }
    }


    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, tail + 1);
        System.arraycopy(items, head, a, head, items.length - head);
        items = a;
    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length ) {
            resize( size * RFACTOR);
        }

        head = minusOne(head);
        items[head] = item;
        size++;
        return;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length ) {
            resize( size * RFACTOR);
        }

        items[tail] = item;
        tail = addOne(tail);
        size++;

        return;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /* Prints the items in the deque from first to last,
     * separated by a space. Once all the items have been printed,
     * print out a new line.
     */
    public void printDeque() {

        int nextHead = head;

        while(nextHead != tail){
            System.out.print(items[nextHead] + " ");
            nextHead = addOne(nextHead);
        }

        System.out.println();
    }

    /* Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if( size == 0 ){
            return null;
        }

        if (size >= 16 && (float)size / items.length < 0.25){
            resize( items.length / RFACTOR );
        }

        T removedItem = items[head];
        items[head] = null;
        head = addOne(head);
        size--;
        return removedItem;
    }

    /* Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if( size == 0 ){
            return null;
        }

        if (size >= 16 && (float)size / items.length < 0.25){
            resize( items.length / RFACTOR );
        }

        T removedItem = items[tail];
        items[tail] = null;
        tail = minusOne(tail);
        size--;
        return removedItem;
    }

    /* Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index < 0 || index >= size ){
            return null;
        }

        int actualIndex;
        if (items.length - 1 - head  >= index) {
            actualIndex = head + index;
        }else{
            actualIndex = index - (items.length - head ) - 1;
        }

        return items[actualIndex];
    }
}