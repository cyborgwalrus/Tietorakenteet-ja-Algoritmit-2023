package oy.interact.tira.student;

import java.util.Comparator;

public class Algorithms {

   private Algorithms() {
      // nada
   }

   ///////////////////////////////////////////
   // Insertion Sort for a slice of the array
   ///////////////////////////////////////////
   public static <T extends Comparable<T>> void insertionSort(T[] array, int fromIndex, int toIndex) {
      // Based on pseudocode from https://en.wikipedia.org/wiki/Insertion_sort
      for (int i = fromIndex; i < toIndex; i++) {
         int j = i;
         while (j > 0 && (array[j - 1].compareTo(array[j]) > 0)) {
            T temp = array[j - 1];
            array[j - 1] = array[j];
            array[j] = temp;

            j--;
         }
      }

   }

   ///////////////////////////////////////////
   // Insertion Sort for the whole array
   ///////////////////////////////////////////
   public static <T extends Comparable<T>> void insertionSort(T[] array) {
      insertionSort(array, 0, array.length);
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////
   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
      // Based on pseudocode from https://en.wikipedia.org/wiki/Insertion_sort
      for (int i = fromIndex; i < toIndex; i++) {
         int j = i;
         while (j > 0 && (comparator.compare(array[j - 1], array[j]) > 0)) {
            T temp = array[j - 1];
            array[j - 1] = array[j];
            array[j] = temp;

            j--;
         }
      }
   }

   //////////////////////////////////////////////////////////
   // Insertion Sort for the whole array using a Comparator
   //////////////////////////////////////////////////////////
   public static <T> void insertionSort(T[] array, Comparator<T> comparator) {
      insertionSort(array, 0, array.length, comparator);
   }

   ///////////////////////////////////////////
   // Reversing a slice of an array
   ///////////////////////////////////////////
   public static <T> void reverse(T[] array, int fromIndex, int toIndex) {
      int headIndex = fromIndex;
      int tailIndex = toIndex - 1;
      while (headIndex < tailIndex) {
         T temp = array[headIndex];
         array[headIndex] = array[tailIndex];
         array[tailIndex] = temp;

         headIndex++;
         tailIndex--;
      }
   }

   ///////////////////////////////////////////
   // Reversing an array
   ///////////////////////////////////////////
   public static <T> void reverse(T[] array) {
      reverse(array, 0, array.length);
   }

   // Helper function for binary search.
   // For avoiding an infinite loop in cases where
   // (high-low)/2 rounds to 0
   private static int max(int int1, int int2) {
      if (int1 > int2)
         return int1;
      return int2;
   }

   ///////////////////////////////////////////
   // Binary search bw indices
   ///////////////////////////////////////////
   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
      // Iterative implementation
      int low = fromIndex;
      int high = toIndex - 1;
      int middle = -1;
      while (low != high) {
         middle = low + max(((high - low) / 2), 1);
         if (aValue.compareTo(fromArray[middle]) < 0)
            high = middle - 1;
         else
            low = middle;
      }

      if (aValue.equals(fromArray[low]))
         return low;
      return -1;
   }

   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////
   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      // Iterative implementation
      int low = fromIndex;
      int high = toIndex - 1;
      int middle = -1;
      while (low != high) {
         middle = low + max(((high - low) / 2), 1);
         if (comparator.compare(aValue, fromArray[middle]) < 0)
            high = middle - 1;
         else
            low = middle;
      }

      if (comparator.compare(aValue, fromArray[low]) == 0)
         return low;
      return -1;
   }

   // Helper function for quickSort
   private static <E> void arraySwap(E[] array, int firstIndex, int secondIndex) {
      E temp = array[firstIndex];
      array[firstIndex] = array[secondIndex];
      array[secondIndex] = temp;
   }

   ////////////////////////////////
   // quickSort using Comparable
   //////////////////////////////
   private static <E extends Comparable<E>> int partition(E[] array, int low, int high) {
      E pivot = array[high];
      int i = low - 1;
      for (int j = low; j < high; j++) {
         if (array[j].compareTo(pivot) <= 0) {
            i++;
            arraySwap(array, i, j);
         }
      }
      arraySwap(array, i + 1, high);
      return i + 1;
   }

   private static <E extends Comparable<E>> void quickSort(E[] array, int low, int high) {
      if (low < high) {
         int partitionIndex = partition(array, low, high);
         quickSort(array, low, partitionIndex - 1);
         quickSort(array, partitionIndex + 1, high);
      }
   }

   //////////////////////////////
   // quickSort using Comparator
   /////////////////////////////
   private static <E> int partition(E[] array, int low, int high, Comparator<E> comparator) {
      E pivot = array[high];
      int i = low - 1;
      for (int j = low; j < high; j++) {
         if (comparator.compare(array[j], pivot) <= 0) {
            i++;
            arraySwap(array, i, j);
         }
      }
      arraySwap(array, i + 1, high);
      return i + 1;
   }

   private static <E> void quickSort(E[] array, int low, int high, Comparator<E> comparator) {
      if (low < high) {
         int partitionIndex = partition(array, low, high, comparator);
         quickSort(array, low, partitionIndex - 1, comparator);
         quickSort(array, partitionIndex + 1, high, comparator);
      }
   }

   public static <E extends Comparable<E>> void fastSort(E[] array) {
      quickSort(array, 0, array.length - 1);
   }

   public static <E> void fastSort(E[] array, Comparator<E> comparator) {
      quickSort(array, 0, array.length - 1, comparator);
   }

   public static <E> void fastSort(E[] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      quickSort(array, fromIndex, toIndex, comparator);
   }

}
