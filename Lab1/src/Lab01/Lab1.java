//Chengxiang Zhang
//czhang88@u.rochester.edu

import java.util.function.Function;

public class lab {
//Main method
public static void main(String[] args) {
    // Arrays from the instruction
    Integer[] intArry = {1, 2, 3, 4, 5};
    Double[] doubArry = {1.1, 2.2, 3.3, 4.4};
    Character[] charArray = {'H', 'E', 'L', 'L', 'O'};
    String[] strArray = {"once", "upon", "a", "time"};

    // Printing each array
    System.out.println("Integer Array:");
    printArray(intArry);

    System.out.println("Double Array:");
    printArray(doubArry);

    System.out.println("Character Array:");
    printArray(charArray);

    System.out.println("String Array:");
    printArray(strArray);
    
    //Codes added from instruction-Part 4
    //Typo in the instruction which caused errors(Arry and Array)
    System.out.println("max Integer is: " + getMax(intArry));
    System.out.println("max Double is: " + getMax(doubArry));
    System.out.println("max Character is: " + getMax(charArray));
    System.out.println("max String is: " + getMax(strArray));
    
//Part 6
    Function<Character[], Character> findMax = (array) -> {
        if (array == null || array.length == 0) {
            return null;
        }
        Character max = array[0];

        for (Character c : array) {
            if (c.compareTo(max) > 0) {
                max = c;
            }
        }

        return max;
    };
    
    Character maxChar = findMax.apply(charArray);
    System.out.println("Maximum Character is: " + maxChar);
}

//Part 1
//	public static void printArray(Object[] array) {
//        for (Object element : array) {
//            System.out.print(element+" ");
//        }
//        System.out.println();
//    }

//Part 2
// Overloaded method for Integer arrays
//public static void printArray(Integer[] array) {
//    for (Integer element : array) {
//        System.out.print(element+" ");
//    }
//    System.out.println();
//}
//
//// Overloaded method for Double arrays
//public static void printArray(Double[] array) {
//    for (Double element : array) {
//        System.out.print(element+" ");
//    }
//    System.out.println();
//}
//
//// Overloaded method for Character arrays
//public static void printArray(Character[] array) {
//    for (Character element : array) {
//        System.out.print(element+" ");
//    }
//    System.out.println();
//}
//
//// Overloaded method for String arrays
//public static void printArray(String[] array) {
//    for (String element : array) {
//        System.out.print(element+" ");
//    }
//    System.out.println();
//}

//Part 3
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.print(element+" ");
    }
    System.out.println();
}

//Part 4
//public static Comparable getMax(Comparable[] array) {
//    if (array == null || array.length == 0) {
//        return null;
//    }
//
//    Comparable max = array[0];
//
//    for (int i = 1; i < array.length; i++) {
//        if (array[i].compareTo(max) > 0) {
//            max = array[i];
//        }
//    }
//
//    return max;
//}

//Part 5
public static <T extends Comparable<T>> T getMax(T[] array) {
    if (array == null || array.length == 0) {
        return null; 
    }

    T max = array[0]; 

    for (int i = 1; i < array.length; i++) {
        if (array[i].compareTo(max) > 0) {
            max = array[i];
        }
    }

    return max;
}


}



