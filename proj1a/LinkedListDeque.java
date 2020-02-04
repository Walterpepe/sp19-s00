public class LinkedListDeque<T> {
    private StuffNode sentinel;
    private int size;

    private class StuffNode {
        public StuffNode prev;
        public T item;
        public StuffNode next;

        public StuffNode(T i, StuffNode p, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }


    /* Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /* Creates a deep copy of other. */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        //TODO: 没写出来
//        for( int i = 0; i < other.size(); i++){
//            sentinel.prev = new StuffNode(other.get(i), sentinel.prev,sentinel);
//            sentinel.prev.prev.next = sentinel.prev;
//            size += 1;
//        }

    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        sentinel.next = new StuffNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        sentinel.prev = new StuffNode(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
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

        StuffNode ptr = sentinel.next;

        while (ptr.next != sentinel.next) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }

        System.out.println();
    }

    /* Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        StuffNode removedNode = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;

        return removedNode.item;
    }

    /* Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        StuffNode removedNode = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;

        return removedNode.item;
    }

    /* Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int i = 0;
        StuffNode ptr = sentinel.next;
        while (true) {
            if (i++ == index) {
                return ptr.item;
            }
            ptr = ptr.next;
        }
    }

    /* Same as get, but uses recursion. */
    // @source 参考书4.1的inheritance1代码
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        return getRecursive(index, sentinel.next);
    }

    public T getRecursive(int index, StuffNode p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);

    }

}
