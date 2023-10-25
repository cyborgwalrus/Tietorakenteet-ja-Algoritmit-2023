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
      //Based on pseudocode from https://en.wikipedia.org/wiki/Insertion_sort
      for(int i = fromIndex; i < toIndex;i++){
         int j = i;
         while(j > 0 && (array[j-1].compareTo(array[j]) > 0)){
            T temp = array[j-1];
            array[j-1] = array[j];
            array[j] = temp;

            j--;
         }
      }

   }

   ///////////////////////////////////////////
   // Insertion Sort for the whole array
   ///////////////////////////////////////////
   public static <T extends Comparable<T>> void insertionSort(T[] array) {
      insertionSort(array,0,array.length);
   }

   ////////////////////////////////////////////////////////////
   // Insertion Sort for slice of the array using a Comparator
   ////////////////////////////////////////////////////////////
   public static <T> void insertionSort(T[] array, int fromIndex, int toIndex, Comparator<T> comparator) {
       //Based on pseudocode from https://en.wikipedia.org/wiki/Insertion_sort
      for(int i = fromIndex; i < toIndex;i++){
         int j = i;
         while(j > 0 && (comparator.compare(array[j-1],array[j]) > 0)){
            T temp = array[j-1];
            array[j-1] = array[j];
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
   // Reversing an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array) {
      // TODO: Student, implement this.
   }

   ///////////////////////////////////////////
   // Reversing a slice of an array
   ///////////////////////////////////////////

   public static <T> void reverse(T[] array, int fromIndex, int toIndex) {
      // TODO: Student, implement this.
   }




   ///////////////////////////////////////////
   // Binary search bw indices
   ///////////////////////////////////////////

   public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
      return -1;
   }

   ///////////////////////////////////////////
   // Binary search using a Comparator
   ///////////////////////////////////////////

   public static <T> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex, Comparator<T> comparator) {
      return -1;
   }

   public static <E extends Comparable<E>> void fastSort(E [] array) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E [] array, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

   public static <E> void fastSort(E [] array, int fromIndex, int toIndex, Comparator<E> comparator) {
      // TODO: Student, implement this.
   }

}
