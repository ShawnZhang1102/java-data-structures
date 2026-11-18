//Chengxiang Zhang
//czhang88@u.rochester.edu
//Lab 06

public class MyBSTTest {
    public static void main(String[] args) {
        MyBST<Integer, String> bst = new MyBST<>();
        
        // Test `put` and `get`
        bst.put(5, "Five");
        bst.put(3, "Three");
        bst.put(7, "Seven");
        bst.put(2, "Two");
        bst.put(4, "Four");
        bst.put(6, "Six");
        bst.put(8, "Eight");
        
        System.out.println("Testing get method:");
        System.out.println("Get 5: " + bst.get(5)); // Expected: "Five"
        System.out.println("Get 3: " + bst.get(3)); // Expected: "Three"
        System.out.println("Get 10 (not in tree): " + bst.get(10)); // Expected: null
        
        // Test `contains`
        System.out.println("\nTesting contains method:");
        System.out.println("Contains 4: " + bst.contains(4)); // Expected: true
        System.out.println("Contains 10: " + bst.contains(10)); // Expected: false

        // Test `size`
        System.out.println("\nTesting size method:");
        System.out.println("Size of BST: " + bst.size()); // Expected: 7

        // Test `deleteMin`
        bst.deleteMin();
        System.out.println("\nAfter deleting min:");
        System.out.println("Contains 2: " + bst.contains(2)); // Expected: false
        System.out.println("Size of BST: " + bst.size()); // Expected: 6
        
        // Test `deleteMax`
        bst.deleteMax();
        System.out.println("\nAfter deleting max:");
        System.out.println("Contains 8: " + bst.contains(8)); // Expected: false
        System.out.println("Size of BST: " + bst.size()); // Expected: 5

        // Test `delete`
        bst.delete(5);
        System.out.println("\nAfter deleting key 5:");
        System.out.println("Contains 5: " + bst.contains(5)); // Expected: false
        System.out.println("Size of BST: " + bst.size()); // Expected: 4
        
        // Test `height`
        System.out.println("\nTesting height method:");
        System.out.println("Height of BST: " + bst.height()); // Height will depend on the structure
        
        // Test `keys` (in-order traversal)
        System.out.println("\nKeys in BST (In-order): " + bst.keys());
        
        // Test `levelOrder` (level-order traversal)
        System.out.println("Level Order: " + bst.levelOrder());
    }
}