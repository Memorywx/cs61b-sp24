package hashmap;

import org.checkerframework.checker.units.qual.C;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private void fillBuckets(Collection<Node>[] buckets) {
        for (int i=0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    private int hash(K key, int length) {
        return Math.floorMod(key.hashCode(), length);
    }

    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    private boolean shouldResize() {
        if ((double)size / buckets.length >= loadFactor) {
            return true;
        }
        return false;
    }

    private void resize(int resizeFactor) {
        Collection<Node>[] newBuckets = new Collection[buckets.length * resizeFactor];
        fillBuckets(newBuckets);


        for (Collection<Node> currentBucket : buckets) {
//            Iterator<Node> iterator = currentBucket.iterator();
//            Node currentNode;
//
//            while (iterator.hasNext()) {
//                currentNode = iterator.next();
//                int keyHash = hash(currentNode.key, newBuckets.length);
//                newBuckets[keyHash].add(currentNode);
            for (Node currentNode : currentBucket) {
                int keyHash = hash(currentNode.key, newBuckets.length);
                newBuckets[keyHash].add(currentNode);
            }
        }
        buckets = newBuckets;
    }
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        int keyHash = hash(key, buckets.length);

//        Iterator<Node> iterator = buckets[keyHash].iterator();
//        Node currentNode;
//
//        while (iterator.hasNext()) {
//            currentNode = iterator.next();
//
//            if (currentNode.key.equals(key)) {
//                currentNode.value = value;    // 找到了，更新value
//                return;
//            }
//        }
        for (Node currentNode : buckets[keyHash]) {
            if (currentNode.key.equals(key)) {
                currentNode.value = value;
                return;
            }
        }
        // 走到这里说明没有key, 要新添加
        if (shouldResize()) {
            resize(RESIZE_FACTOR);
            keyHash = hash(key, buckets.length);
        }
        buckets[keyHash].add(createNode(key, value));
        size++;

    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int keyHash = hash(key, buckets.length);

//        Iterator<Node> iterator = buckets[keyHash].iterator();
//        Node currentNode;
//
//        while (iterator.hasNext()) {
//            currentNode = iterator.next();
//
//            if (currentNode.key.equals(key)) {
//                return currentNode.value;
//            }
//        }
        for (Node p : buckets[keyHash]) {
            if (p.key.equals(key)) {
                return p.value;
            }
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int keyHash = hash(key, buckets.length);

        for (Node currentNode : buckets[keyHash]) {
            if (currentNode.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        size = 0;
        buckets = new Collection[DEFAULT_INIT_CAPACITY];
        fillBuckets(buckets);
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }


    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
   private int size;
   private double loadFactor;
   private static final int DEFAULT_INIT_CAPACITY = 16;
   private static final int RESIZE_FACTOR = 2;
   private static final double DEFAULT_LF = 0.75;



    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INIT_CAPACITY, DEFAULT_LF);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LF);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        size = 0;
        buckets = new Collection[initialCapacity];
        this.loadFactor = loadFactor;
        fillBuckets(buckets);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
