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

      if (aValue.equals(fromArray[low]))
         return low;
      return -1;
   }

   public static <E extends Comparable<E>> void fastSort(E[] array) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E[] array, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E[] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

}
