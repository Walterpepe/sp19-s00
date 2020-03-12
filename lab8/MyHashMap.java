import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;

public class MyHashMap<K, V> implements Map61B<K, V> {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    static final double DEFAULT_LOAD_FACTOR = 0.75;

    private int initialSize;
    private double loadFactor;
    private int size;
    /**
     * Number of items currently stored
     */
    private ArrayList<Entry<K, V>>[] table;  // array of linked-list symbol tables

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    /**
     * An empty map with default initial bins and load factor 0.75.
     */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    /**
     * An empty map with INITIALBINS initial bins and load factor 0.75.
     */
    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    /**
     * A new, empty mapping using a hash table that initially has
     * INITIALBINS bins, and maintains a load factor <= LOADFACTOR.
     */
    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize < 1 || loadFactor <= 0.0)
            throw new IllegalArgumentException();
//        table = (ArrayList<Entry<K, V>>[]) new Object[initialSize];
        table = new ArrayList[initialSize];
        for(int i =0; i < initialSize; i++ ){
            table[i] = new ArrayList();
        }
        this.loadFactor = loadFactor;
        size = 0;
    }


    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        for(int i =0; i < table.length; i++ ){
            table[i] = new ArrayList();
        }
        size = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     * You can assume null keys will never be inserted.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        Entry e = find(key, table[hash(key)]);
        return (e == null) ? null : (V) e.value; // 没搞懂这块
    }

    /**
     * Return a value in the range 0 .. bins.size ()-1, based on
     * the hash code of KEY.
     */
    private int hash(K key) {

        return (key == null) ? 0
                : (0x7fffffff & key.hashCode()) % table.length;
    }

    /**
     * The Entry in the list BIN whose key is KEY, or null if none.
     */
    private Entry<K, V> find(Object key, ArrayList<Entry<K, V>> chain) {

        for (Entry<K, V> e : chain) {
            if (key.equals(e.key)) return e;
        }
        return null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        int h = hash(key);
        Entry<K, V> e = find(key, table[h]);
        if (e == null) {
            table[h].add(new Entry<>(key, value));
            size += 1;
            if (size > table.length * loadFactor) grow();

        } else {
            e.value = value;
        }
    }

    private void putAll(MyHashMap<K, V> m) {
        for (ArrayList<Entry<K, V>> chain : m.table) {
            for (Entry<K, V> e : chain) {
                put(e.key, e.value);
            }
        }
    }

    private void copyFrom(MyHashMap<K, V> m) {
        table = m.table;
    }

    /**
     * Increase number of bins.
     */
    private void grow() {
        MyHashMap<K, V> newMap = new MyHashMap((table.length * 2), loadFactor);
        newMap.putAll(this);
        copyFrom(newMap);
    }

    /**
     * Returns a Set view of the keys contained in this map.
     */
    @Override
    public Set<K> keySet() {
        Set s = new HashSet();
        for(K k: this){
            s.add(k);
        }
        return s;
//        return new MyHashSet();
    }

    // 不弄了，这么搞太复杂
//    private class MyHashSet implements Set<K>{
//
//        @Override
//        public int size(){
//            return size;
//        }
//
//        @Override
//        public boolean isEmpty(){
//            return size == 0;
//        }
//
//        @Override
//        public boolean contains(Object o){
//            return containsKey((K)o);
//        }
//
//        @Override
//        public Iterator<K> iterator(){
//            return MyHashMap.this.iterator();
//        }
//
//        public Object[] toArray(){
//
//        }
//
//    }



    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIter();
    }

    /** An iterator that iterates over the keys of the dictionary. */
    private class MyHashMapIter implements Iterator<K> {

        /**
         * Create a new ULLMapIter by setting cur to the first node in the
         * linked list that stores the key-value pairs.
         */
        MyHashMapIter() {
            curBucket = 0;
            curChain =  0;
            cur = 0;
        }

        @Override
        public boolean hasNext() {
            return cur < size;
        }

        @Override
        public K next() {

            while(curChain >= table[curBucket].size()){
                curBucket++;
                curChain = 0;
            }

            Entry<K,V> ret = table[curBucket].get(curChain);

            curChain++;
            cur++;

            return ret.key;
        }

        /** Stores the current key-value pair. */
        private  int curBucket;
        private int curChain;
        private int cur;

    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);

        Entry<K, V> e = find(key, table[i]);

        if (e == null) {
            return null;
        }

        table[i].remove(e);
        size--;

        return e.value;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        Entry<K, V> e = find(key, table[i]);

        if (e == null || !e.value.equals(value) ) {
            return null;
        }

        table[i].remove(e);
        size--;

        return e.value;
    }




    public static void main(String[] args ){
        MyHashMap<String, String> dictionary = new MyHashMap<>();
        dictionary.put("hello1", "world");
        dictionary.put("hello2", "world");
        dictionary.put("hello3", "world");
        dictionary.put("hello4", "world");

//        System.out.println(dictionary.get("hello"));

        for(String key: dictionary){
            System.out.println(key);
        }

    }
}
