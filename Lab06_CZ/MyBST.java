//Chengxiang Zhang
//czhang88@u.rochester.edu
//Lab 06

import java.util.*;

public class MyBST<Key extends Comparable<Key>, Value> extends UR_BST<Key, Value> {

    private UR_Node root;

    private class UR_Node {
        private Key key;
        private Value val;
        private UR_Node left, right;
        private int size;

        public UR_Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public MyBST() {
        root = null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(UR_Node node) {
        return (node == null) ? 0 : node.size;
    }

    @Override
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return get(key) != null;
    }

    @Override
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return get(root, key);
    }

    private Value get(UR_Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.val;
    }

    @Override
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        root = put(root, key, val);
    }

    private UR_Node put(UR_Node node, Key key, Value val) {
        if (node == null) return new UR_Node(key, val, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, val);
        else if (cmp > 0) node.right = put(node.right, key, val);
        else node.val = val;
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST is empty");
        root = deleteMin(root);
    }

    private UR_Node deleteMin(UR_Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST is empty");
        root = deleteMax(root);
    }

    private UR_Node deleteMax(UR_Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    @Override
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        root = delete(root, key);
    }

    private UR_Node delete(UR_Node node, Key key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            UR_Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private UR_Node min(UR_Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    @Override
    public Iterable<Key> keys() {
        List<Key> keyList = new ArrayList<>();
        inOrder(root, keyList);
        return keyList;
    }

    private void inOrder(UR_Node node, List<Key> keyList) {
        if (node == null) return;
        inOrder(node.left, keyList);
        keyList.add(node.key);
        inOrder(node.right, keyList);
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(UR_Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    @Override
    public Iterable<Key> levelOrder() {
        List<Key> keys = new ArrayList<>();
        Queue<UR_Node> queue = new LinkedList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            UR_Node node = queue.poll();
            keys.add(node.key);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return keys;
    }
}