abstract public class UR_BST<Key extends Comparable<Key>, Value> {

    private UR_Node root; // root of BST

    private class UR_Node {
        private Key key;       // sorted by key
        private Value val;     // associated data
        private UR_Node left, right; // left and right subtrees
        private int size;      // number of nodes in subtree
    }

    abstract public boolean isEmpty();
    abstract public int size();
    abstract public boolean contains(Key key);
    abstract public Value get(Key key);
    abstract public void put(Key key, Value val);
    abstract public void deleteMin();
    abstract public void deleteMax();
    abstract public void delete(Key key);
    abstract public Iterable<Key> keys();
    abstract public int height();
    abstract public Iterable<Key> levelOrder();
}