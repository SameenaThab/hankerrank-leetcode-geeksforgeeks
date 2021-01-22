import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* 
Array A has buffer at the end of array. So no extra space is needed. 
If we start comparing elements from index 0 and swap, we need to shift and adjust rest of array
eg: a=4,5,-,- b= 1,2 swapping 4 and 1 will make b=4,2 {not sorted need adjustments}and a=1,5,-,-
Also need to shift the elements of a to make room.
So start with last indexes
*/

public class  SortedMerge_Chap10Prob1 {
      
    public static void main(String[] args) {
        int[] a = new int[]{7,8,9,11,12,0,0,0,0,0};
        int[] b = new int[]{1,2,3,7,10};
        SortedMerge_Chap10Prob1 solution = new SortedMerge_Chap10Prob1();
        solution.sortedMerge(a,b);
        System.out.println("Solution array A: "+Arrays.toString(a));
    }

    private void sortedMerge(int[] a, int[] b) {
        int bIndex = b.length-1;
        int mergedIndex = a.length-1;
        int aIndex = a.length-b.length-1;
        while(bIndex >= 0) {
            if(aIndex>=0 && a[aIndex]>b[bIndex]) {
                a[mergedIndex] = a[aIndex];
                aIndex--;
            } else {
                a[mergedIndex] = b[bIndex];
                bIndex--;
            }
            mergedIndex--;
        }
    }
}
