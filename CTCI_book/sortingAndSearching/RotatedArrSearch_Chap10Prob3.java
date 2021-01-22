import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* 
eg: arr1: 10,15,20,0,5 find num=5
    arr2: 50,5,20,30,40 find num=5
do binary search
if(mid == num) return mid
if(a[left]<a[mid]) , left part is sorted normally and if num in btw left and mid go left
else right 
    for arr1 we go right
if(a[right]>a[mid]) , right part is sorted normally and if num in btw mid and right go right
else left
    for arr2 we go left 
if a[left]=a[mid], and a[right] != a[mid] go right else search both arrays
{2,2,2,3,4,2 } num = 4 -> we search whole arr
worst case o(n) else o(logn)
*/

public class  RotatedArrSearch_Chap10Prob3 {
      
    public static void main(String[] args) {
        int[] arr = new int[]{10,15,20,0,5};
        RotatedArrSearch_Chap10Prob3 sol = new RotatedArrSearch_Chap10Prob3();        
        System.out.println("Solution array arr1: "+sol.search(arr,5));
        int[] arr2 = new int[]{2,1,2,2,2};
        System.out.println("Solution array arr2: "+sol.search(arr2,1));
    }

    private int search(int[] arr, int k) {
        return binarySearch(arr,0,arr.length-1,k);
    }

    private int binarySearch(int[] arr, int left, int right, int k) {
        int mid = (left+right)/2;
        if(arr[mid] == k)
            return mid;
        if(left>right)
            return -1;
        if(arr[left]<arr[mid]) { // left normally sorted
            if(k>=arr[left] && k<arr[mid]) { //k in the left part
                return binarySearch(arr, left, mid-1, k);
            } else {
                return binarySearch(arr, mid+1, right, k);
            }
        }
        if(arr[right]>arr[mid]) { // right normally sorted
            if(k<=arr[right] && k>arr[mid]) { //k in the right part
                return binarySearch(arr, mid+1, right, k);
            } else {
                return binarySearch(arr, left, mid-1, k);
            }
        }
        if(arr[left] == arr[mid]) {
            if(arr[right] != arr[left]) { // arr will be like this 2,2,2,3,1 {k=1} so we know for sure left side only has 2
               return binarySearch(arr, mid+1, right, k);                           
            }
            else {
                int result = binarySearch(arr, left, mid-1, k); 
                if(result == -1) {
                    return binarySearch(arr, mid+1, right, k); 
                } else {
                    return result;
                }

            }
        }
        return -1;
    }

}
