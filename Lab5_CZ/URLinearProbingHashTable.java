// Chengxiang Zhang
// czhang88@u.rochester.edu
// Lab 05

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


abstract class UR_HashTable<Key, Value> {
    abstract public void put(Key key, Value val);
    abstract public Value get(Key key);
    abstract public void delete(Key key);
    abstract public int size();
    abstract public boolean isEmpty();
    abstract public boolean contains(Key key);
    abstract public Iterable<Key> keys();
}

// URLinearProbingHashTable class
public class URLinearProbingHashTable<Key, Value> extends UR_HashTable<Key, Value> {
    private static final int INIT_CAPACITY = 5;
    protected int n = 0;
    protected int m;
    protected Key[] keys;
    protected Value[] vals;
    int inserts = 0, collisions = 0;

    // Constructor
    public URLinearProbingHashTable() {
        this(INIT_CAPACITY);
    }

    // Constructor with custom capacity
    public URLinearProbingHashTable(int capacity) {
        m = capacity;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }

    // Insert a key-value pair
    @Override
    public void put(Key key, Value val) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
            collisions++;
        }
        keys[i] = key;
        vals[i] = val;
        n++;
        inserts++;
    }

    // Get value associated with key
    @Override
    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) return vals[i];
        }
        return null;
    }

    // Delete key-value pair
    @Override
    public void delete(Key key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % m;
        }
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % m;
        while (keys[i] != null) {
            Key tempKey = keys[i];
            Value tempVal = vals[i];
            keys[i] = null;
            vals[i] = null;
            n--;
            put(tempKey, tempVal);
            i = (i + 1) % m;
        }
        n--;
    }

    // Check if hash table contains a key
    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    // Return size of hash table
    @Override
    public int size() {
        return n;
    }

    // Check if hash table is empty
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    // Return an iterable for keys
    @Override
    public Iterable<Key> keys() {
        List<Key> keyList = new ArrayList<>();
        for (Key key : keys) {
            if (key != null) keyList.add(key);
        }
        return keyList;
    }

    // Hash function
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }
}