import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private int size;
    private Node bstMap;

    public BSTMap() {
        size = 0;
        bstMap = null;
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
        bstMap = putHelper(key, value, bstMap);

    }

    private Node putHelper(K key, V value, Node T) {
        if (T == null) {
            size++;
            return new Node(key, value);
        }

        int compareResult = key.compareTo(T.key);

        /* 千万不能写成bstMap.left = putHelper(key,value, bstMap.left)
        * 变量名是T就写T  */
        if (compareResult > 0) {
            T.right = putHelper(key, value, T.right);
        }else if (compareResult < 0) {
            T.left = putHelper(key, value, T.left);
        } else {
            T.value = value;   // key == this.key 的情况 ，则更新value
        }

        return T;


    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        Node returnNode = getHelper(key, bstMap);
        return returnNode == null ? null : returnNode.value;
    }

    public Node getHelper(K k, Node T) {
        if (T == null) {
            return null;
        }
        int compareResult = k.compareTo(T.key);
        if ( compareResult > 0) {
            return getHelper(k, T.right);
        }

        if (compareResult < 0) {
            return getHelper(k, T.left);
        }

        // key == T.key 的情况，则返回value
        return T;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return getHelper(key, bstMap) != null;
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
        this.bstMap = null;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
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
}
