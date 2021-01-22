import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import javax.lang.model.util.ElementScanner6;

import java.io.*;

/* 
peak = element greater than or equal to adjacent elements
valley = element less than or equal to adjacent elements
Approach 1: sort all the elements
then swap odd elements with preceeding elements
1 2 3 4 5 -> 2 1 4 3 5 -> O(nlogn) due to sorting
Approach 2: no sorting required
replace all odd elements with largest of adjacent elements
6 3 4 0 2 
idx 1 -> 3 6 4 0 2
idx 3 -> 3 6 0 4 2 { swappng 4 with 0 will not disturb previous peak valley arrangments, 
                    as we bring a even smaller number as vallet for previous arrangment}
*/

class PeaksAndValleys_Chap10Prob11 {

    public static void main(String[] args) {
        int[] arr1 = new int[]{20,15,10,5,13,25,23,24};
        int[] arr2 = new int[]{5,3,1,2,3};
        int[] arr3 = new int[]{20,15,10,5,13,25,23,24};
        int[] arr4 = new int[]{5,3,1,2,3};
        PeaksAndValleys_Chap10Prob11 sol = new PeaksAndValleys_Chap10Prob11();
        sol.peakValleySortOptimal(arr1);
        sol.peakValleySortOptimal(arr2);
        sol.peakValleySortApp1(arr3);
        sol.peakValleySortApp1(arr4);
        System.out.println("solution: "+Arrays.toString(arr1));
        System.out.println("solution: "+Arrays.toString(arr2));
        System.out.println("solution: "+Arrays.toString(arr3));
        System.out.println("solution: "+Arrays.toString(arr4));
    }

    private void peakValleySortApp1(int[] arr) {
        Arrays.sort(arr);
        for(int i=1;i<arr.length;i+=2){
            swap(arr,i-1,i);
        }
    }

    private void peakValleySortOptimal(int[] arr) {
        for(int i=1;i<arr.length;i+=2) {
            int largestAdjIndex = maxIndex(arr,i-1,i,i+1);
            if(largestAdjIndex != i)
                swap(arr,i,largestAdjIndex);
        }
    }

    private void swap(int[] arr, int i, int largestAdjIndex) {
        int temp = arr[i];
        arr[i] = arr[largestAdjIndex];
        arr[largestAdjIndex]=temp;
    }

    private int maxIndex(int[] arr, int a, int b, int c) {
        int n = arr.length;
        int aValue = a>=0 && a<n? arr[a]:Integer.MIN_VALUE;
        int bValue = b>=0 && b<n? arr[b]:Integer.MIN_VALUE;
        int cValue = c>=0 && c<n? arr[c]:Integer.MIN_VALUE;
        int maxValue = Math.max(aValue,Math.max(bValue, cValue));
        if(maxValue == aValue)
            return a;
        else if(maxValue == bValue)
            return b;
        else
            return c;
    }
}
