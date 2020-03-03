import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

import edu.princeton.cs.algs4.BST;

// In your implementation you should assume that generic keys K in BSTMap<K,V> extend Comparable.
// 观察这块extend的写法
public class BSTMap<K extends Comparable<K>, V> extends BST<K, V> implements Map61B<K, V> {

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        for (K k : keys()) {
            super.delete(k);
        }
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return contains(key);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return super.get(key);
    }


    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return super.size();
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        super.put(key, value);
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> hashSet = new HashSet<>();
        for (K k : keys()) {
            hashSet.add(k);
        }
        return hashSet;
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    // prints out your BSTMap in order of increasing Key.
    public void printInOrder() {
        for (K k : keys()) {
            System.out.println(k);
        }
    }
}
