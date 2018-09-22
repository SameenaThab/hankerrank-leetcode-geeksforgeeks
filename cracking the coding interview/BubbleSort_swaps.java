
/*Given an n-element array,A=a0,a1,a2,a3... , of distinct elements, sort array  in ascending order using the Bubble Sort algorithm above. Once sorted, print the following three lines:

Array is sorted in numSwaps swaps., where numSwaps is the number of swaps that took place.
First Element: firstElement, where firstElement is the first element in the sorted array.
Last Element: lastElement, where lastElement is the last element in the sorted array.
*/
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BubbleSort_swaps {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        bubbleSort(a);
    }
    
    public static void bubbleSort(int[] a)
    {
        int n=a.length;
        int swaps=0;
        for (int i = 0; i < n; i++) {
    
         for (int j = 0; j < n - 1; j++) {
             // Swap adjacent elements if they are in decreasing order
            if (a[j] > a[j + 1]) {
                swaps++;
                int temp=a[j+1];
                a[j+1]=a[j];
                a[j]=temp;
            }
            }

        }
        System.out.println("Array is sorted in "+swaps+" swaps.");
        System.out.println("First Element: "+a[0]);
        System.out.println("Last Element: "+a[n-1]);
    }
}
