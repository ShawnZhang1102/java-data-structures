/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Arrays;
import java.util.Random;
import java.io.IOException;
import java.util.Date;
import java.io.PrintWriter;
import java.io.FileWriter;



public class Sorting {


 /**
     * 
     * Sorts the numbers present in the file based on the algorithm provided.
     * 0 = Arrays.sort() (Java Default)
     * 1 = Bubble Sort
     * 2 = Selection Sort 
     * 3 = Insertion Sort 
     * 4 = Mergesort
     * 5 = Quicksort
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args)  throws IOException{
        In in = new In(args[0]);
        
          // Storing file input in an array
        int[] a = in.readAllInts();
        int[] b = a.clone();
        Arrays.sort(b);

        int[] c = b.clone();
        reverseArray(c);

        int[] d = b.clone();
        createAlmostSorted(d);

        // Step 2: Select algorithm based on second argument and apply it to a, b, c, d
        int algorithm = Integer.parseInt(args[1]);
        sortAndPrint("a", a, algorithm, args[0]);
        sortAndPrint("b", b, algorithm, args[0]);
        sortAndPrint("c", c, algorithm, args[0]);
        sortAndPrint("d", d, algorithm, args[0]);

        // TODO: Generate 3 other arrays, b, c, d where
        // b contains sorted integers from a (You can use Java Arrays.sort() method)
        // c contains all integers stored in reverse order 
        // (you can have your own O(n) solution to get c from b
        // d contains almost sorted array 
        //(You can copy b to a and then perform (0.1 * d.length)  many swaps to acheive this.   
       
        //TODO: 
        // Read the second argument and based on input select the sorting algorithm
        //  * 0 = Arrays.sort() (Java Default)
        //  * 1 = Bubble Sort
        //  * 2 = Selection Sort 
        //  * 3 = Insertion Sort 
        //  * 4 = Mergesort
        //  * 5 = Quicksort
        //  Perform sorting on a,b,c,d. Pring runtime for each case along with timestamp and record those. 
        // For runtime and priting, you can use the same code from Lab 4 as follows:
        
         // TODO: For each array, a, b, c, d:  
        Stopwatch timer = new Stopwatch();
        // TODO: Perform Sorting and store the result in an  array

        double time = timer.elapsedTimeMillis();
        
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
          //TODO: Replace with your own netid
        String netID = "czhang88";
          //TODO: Replace with the algorithm used 
        String algorithmUsed = "insertion sort";
          //TODO: Replace with the  array used 
        String arrayUsed = "a";
          StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
          // Write the resultant array to a file (Each time you run a program 4 output files should be generated. (one for each a,b,c, and d)
        
  }

    private static void reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    private static void createAlmostSorted(int[] array) {
        Random rand = new Random();
        int swaps = (int) (0.1 * array.length);  // Swap 10% of elements
        for (int i = 0; i < swaps; i++) {
            int idx1 = rand.nextInt(array.length);
            int idx2 = rand.nextInt(array.length);
            int temp = array[idx1];
            array[idx1] = array[idx2];
            array[idx2] = temp;
        }
    }

    private static void sortAndPrint(String arrayName, int[] array, int algorithm, String inputFile) throws IOException {
        System.out.println("Sorting array: " + arrayName);
        Stopwatch timer = new Stopwatch();

        switch (algorithm) {
            case 0: Arrays.sort(array); break;
            case 1: bubbleSort(array); break;
            case 2: selectionSort(array); break;
            case 3: quickSort(array, 0, array.length - 1); break;
            default: System.out.println("Invalid algorithm choice!"); return;
        }

        double time = timer.elapsedTimeMillis();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String netID = "czhang88"; // Replace with your NetID
        StdOut.printf("%s Sort %s %8.1f ms   %s  %s  %s\n", getAlgorithmName(algorithm), arrayName, time, timeStamp, netID, inputFile);
        //Save sorted file
        saveArrayToFile(array, arrayName + ".txt");
    }

    private static void saveArrayToFile(int[] array, String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(fileName));
        for (int num : array) {
            writer.println(num);
        }
        writer.close();
        System.out.println("File saved: " + fileName);
    }

    private static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j + 1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Selection
    private static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = array[minIdx];
            array[minIdx] = array[i];
            array[i] = temp;
        }
    }

    // Quick
    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // pi is partitioning index
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);  // Recursively sort elements before partition
            quickSort(array, pi + 1, high); // Recursively sort elements after partition
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }


    private static String getAlgorithmName(int algorithm) {
        switch (algorithm) {
            case 0: return "Arrays.sort";
            case 1: return "Bubble";
            case 2: return "Selection";
            case 3: return "Quick";
            default: return "Unknown";
        }
    }


    }