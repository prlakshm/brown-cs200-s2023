package sol;

import src.IDictionary;
import src.KeyAlreadyExistsException;
import src.KeyNotFoundException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class that implements hash tables using chaining.
 */
public class Chaining<K, V> implements IDictionary<K, V> {

    private LinkedList<KVPair<K,V>>[] chain;
    private int size;
    /**
     * A class that represents key-value pairs.
     */
    private static class KVPair<K, V> {
        public K key;
        public V value;

        /**
         * @param key - key in the pair
         * @param value - value in the pair
         */
        public KVPair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Constructor for hash table
     * @param size Size of the hash table
     */
    public Chaining(int size) {
            this.chain = (LinkedList<KVPair<K,V>>[]) new LinkedList[size];
            if(size > 0) {
                for (int i = 0; i < size; i++) {
                    this.chain[i] = new LinkedList<KVPair<K, V>>();
                }
            }
            else{
                throw new IllegalArgumentException("Invalid size");
            }
    }

    /**
     * Finds a key in the hashtable.
     *
     * @param key - the key to look up
     *
     * @return the key-value pair found
     * @throws KeyNotFoundException if the key is not found
     */
    private KVPair<K, V> findKVPair(K key) throws KeyNotFoundException {
        int hashcode = key.hashCode();
        int index = Math.abs(hashcode)%this.size;
        for(KVPair<K,V> pair : this.chain[index]){
            if(pair.key.equals(key)){
                return pair;
            }
        }
        throw new KeyNotFoundException("Key not in hashtable");
    }

    @Override
    public V lookup(K key) throws KeyNotFoundException {
        KVPair<K, V> pair = this.findKVPair(key);

        return pair.value;
    }

    @Override
    public V update(K key, V value) throws KeyNotFoundException {
        KVPair<K, V> pair = this.findKVPair(key);

        V oldValue = pair.value;
        pair.value = value;

        return oldValue;
    }

    @Override
    public void insert(K key, V value) throws KeyAlreadyExistsException {

    }

    @Override
    public V delete(K key) throws KeyNotFoundException {
        return null;
    }

    // Feel free to write additional helper methods :)

    @Override
    public boolean equals(Object ht) {
        return false;
    }

    @Override
    public String toString() {
        return "implement me!";
    }

}
