// Chengxiang Zhang
// czhang88@u.rochester.edu
// Lab 05

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class URLinearProbingHashTableTest {
    public static void main(String[] args) {
        URLinearProbingHashTable<String, Integer> hashTable = new URLinearProbingHashTable<>();

        // Test insertion
        hashTable.put("apple", 10);
        hashTable.put("banana", 20);
        hashTable.put("cherry", 30);
        System.out.println("Value for 'apple': " + hashTable.get("apple"));
        System.out.println("Value for 'banana': " + hashTable.get("banana"));

        // Test update of existing key
        hashTable.put("apple", 15);
        System.out.println("Updated value for 'apple': " + hashTable.get("apple"));

        // Test deletion
        hashTable.delete("banana");
        System.out.println("Value for 'banana' after deletion: " + hashTable.get("banana"));

        // Test size and isEmpty
        System.out.println("Current size: " + hashTable.size());
        System.out.println("Is hashTable empty? " + hashTable.isEmpty());

        // Clear all elements
        hashTable.delete("apple");
        hashTable.delete("cherry");
        System.out.println("Is hashTable empty after deletion? " + hashTable.isEmpty());

        // Test contains method
        hashTable.put("date", 40);
        System.out.println("Contains 'date': " + hashTable.contains("date"));
        hashTable.delete("date");
        System.out.println("Contains 'date' after deletion: " + hashTable.contains("date"));

        // Test keys() method
        hashTable.put("apple", 10);
        hashTable.put("banana", 20);
        hashTable.put("cherry", 30);
        System.out.println("All keys in hashTable:");
        for (String key : hashTable.keys()) {
            System.out.println(key);
        }
    }
}