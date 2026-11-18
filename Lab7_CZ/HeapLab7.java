//Chengxiang Zhang
//czhang88@u.rochester.edu

import java.util.Arrays;

public class HeapLab7<T extends Comparable<T>> implements UR_Heap<T> {

    private T[] heapArray;
    private int size;
    private int capacity;

    // Constructor with default capacity
    public HeapLab7() {
        this.capacity = 10;
        this.heapArray = (T[]) new Comparable[capacity];
        this.size = 0;
    }

    // Constructor with specified capacity
    public HeapLab7(int capacity) {
        this.capacity = capacity;
        this.heapArray = (T[]) new Comparable[capacity];
        this.size = 0;
    }

    // Constructor to heapify an array
    public HeapLab7(T[] array) {
        this.capacity = array.length;
        this.size = array.length;
        this.heapArray = Arrays.copyOf(array, capacity);
        heapify();
    }

    // Insert method
    @Override
    public void insert(T item) {
        if (size >= capacity) {
            expand();
        }
        heapArray[size] = item;
        bubbleUp(size);
        size++;
    }

    // Expand capacity
    private void expand() {
        capacity *= 2;
        heapArray = Arrays.copyOf(heapArray, capacity);
    }

    // Bubble up to maintain heap property
    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heapArray[index].compareTo(heapArray[parentIndex]) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    // Delete minimum element
    @Override
    public T deleteMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = heapArray[0];
        heapArray[0] = heapArray[--size];
        heapArray[size] = null;
        bubbleDown(0);
        return min;
    }

    // Bubble down to maintain heap property
    private void bubbleDown(int index) {
        while (index < size / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;
            int smallest = leftChild;

            if (rightChild < size && heapArray[rightChild].compareTo(heapArray[leftChild]) < 0) {
                smallest = rightChild;
            }

            if (heapArray[index].compareTo(heapArray[smallest]) <= 0) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    // Swap elements
    private void swap(int i, int j) {
        T temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    // Heapify the array
    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            bubbleDown(i);
        }
    }

    // Print the heap (exclude nulls)
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heapArray[i] + " ");
        }
        System.out.println();
    }

    // isEmpty method
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // size method
    @Override
    public int size() {
        return size;
    }
}
