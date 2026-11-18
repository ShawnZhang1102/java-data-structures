//Chengxiang Zhang
//czhang88@u.rochester.edu

public class HeapLab7Test {

    public static void main(String[] args) {
        // Test creating a heap with a small capacity
        HeapLab7<Integer> heap = new HeapLab7<>(3);
        
        System.out.println("Inserting elements:");
        heap.insert(10);
        heap.insert(5);
        heap.insert(3);
        heap.insert(1); 
        System.out.print("Heap after insertions: ");
        heap.printHeap();

        // Test deleteMin method
        System.out.println("Deleting the minimum element: " + heap.deleteMin());
        System.out.print("Heap after deleting minimum: ");
        heap.printHeap();

        // Test if the heap is empty
        System.out.println("Is the heap empty? " + heap.isEmpty());

        // Test the size of the heap
        System.out.println("Heap size: " + heap.size());

        // Test heap creation from an array
        System.out.println("Creating a heap from an array:");
        Integer[] array = {10, 20, 5, 3, 15, 30};
        HeapLab7<Integer> heapFromArray = new HeapLab7<>(array);
        System.out.print("Heap created from array: ");
        heapFromArray.printHeap();

        // Edge case: deleting from an empty heap
        HeapLab7<Integer> emptyHeap = new HeapLab7<>();
        try {
            emptyHeap.deleteMin();
        } catch (IllegalStateException e) {
            System.out.println("Exception caught while deleting from an empty heap: " + e.getMessage());
        }
        
        // Test inserting duplicate values
        System.out.println("Inserting duplicate values:");
        heap.insert(5);
        heap.insert(5);
        System.out.print("Heap after inserting duplicates: ");
        heap.printHeap();
        
        System.out.println("Manual testing complete.");
    }
}
