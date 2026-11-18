import java.util.Arrays;
import java.util.Collection;

public class Test {
    public static void main(String[] args) {
        URLinkedList<String> list = new URLinkedList<>();

        // add and addAll
        System.out.println("Testing add and addAll:");
        list.add("A");
        list.add("B");
        list.add(1, "C");
        list.addAll(Arrays.asList("D", "E", "F"));
        System.out.println("List after additions: " + Arrays.toString(list.toArray()));
        System.out.println("Size: " + list.size());

        // get and set
        System.out.println("\nTesting get and set:");
        System.out.println("Element at index 2: " + list.get(2));
        list.set(2, "X");
        System.out.println("List after set: " + Arrays.toString(list.toArray()));

        // remove
        System.out.println("\nTesting remove:");
        list.remove(1);
        list.remove("E");
        System.out.println("List after removals: " + Arrays.toString(list.toArray()));

        // contains and containsAll
        System.out.println("\nTesting contains and containsAll:");
        System.out.println("Contains 'X': " + list.contains("X"));
        System.out.println("Contains all ['A', 'X']: " + list.containsAll(Arrays.asList("A", "X")));

        // subList
        System.out.println("\nTesting subList:");
        URList<String> subList = list.subList(1, 3);
        System.out.println("SubList: " + Arrays.toString(subList.toArray()));

        // addFirst, addLast, peekFirst, peekLast, pollFirst, pollLast
        System.out.println("\nTesting URLinkedList specific methods:");
        list.addFirst("First");
        list.addLast("Last");
        System.out.println("List after addFirst and addLast: " + Arrays.toString(list.toArray()));
        System.out.println("peekFirst: " + list.peekFirst());
        System.out.println("peekLast: " + list.peekLast());
        System.out.println("pollFirst: " + list.pollFirst());
        System.out.println("pollLast: " + list.pollLast());
        System.out.println("List after polls: " + Arrays.toString(list.toArray()));

        // clear and isEmpty
        System.out.println("\nTesting clear and isEmpty:");
        list.clear();
        System.out.println("Is empty after clear: " + list.isEmpty());

        // edge cases
        System.out.println("\nTesting edge cases:");
        try {
            list.get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Correctly threw exception for get on empty list");
        }

        try {
            list.add(-1, "Invalid");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Correctly threw exception for invalid index in add");
        }

        // a large number of elements
        System.out.println("\nTesting with a large number of elements:");
        for (int i = 0; i < 10000; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Size after adding 10000 elements: " + list.size());
        System.out.println("First element: " + list.get(0));
        System.out.println("Last element: " + list.get(list.size() - 1));

        System.out.println("\nAll tests completed.");
    }
}