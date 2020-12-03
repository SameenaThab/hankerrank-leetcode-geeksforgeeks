import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class  MagicIndex_Chap8Prob3 {
    /*
    -10 -5 -1   1  2  3  5  7  9  12  13
     0   1  2   3  4  5  6  7  8  9   10  
    -10 ,-5, 2, 2, 2, 3, 4, 7, 9, 12, 13
    */
    int[] arr;
    int[] arrWithDups;
    int n;
        
    public static void main(String[] args) {
        MagicIndex_Chap8Prob3 sol = new MagicIndex_Chap8Prob3();
        int[] array = {-10 ,-5, -1, 1, 2, 3, 5, 7, 9, 12, 13};
        int[] arrayWithDups = {-10 ,-5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
        sol.arr = array;
        sol.arrWithDups=arrayWithDups;
        sol.n=11;
        System.out.println("MagicIndex: "+sol.findMagicIndex(0,10));
        System.out.println("MagicIndex with duplicates: "+sol.findMagicIndexWithDupElements(0,10));
    }

    public int findMagicIndex(int left,int right) {
        if(right<left) {
            return -1;
        }
        int mid = (left+right)/2;
        if(arr[mid] == mid) 
            return mid;
        else if(arr[mid] > mid) {
            return findMagicIndex(left,mid-1);
        }
        else {
            return findMagicIndex(mid+1,right);
        }
    }

    public int findMagicIndexWithDupElements(int left,int right) {
        if(right<left) {
            return -1;
        }
        int mid = (left+right)/2;
        int midValue = arrWithDups[mid];
        if(midValue == mid) 
            return mid;

        int leftIndex = Math.min(midValue,mid-1);
        int leftResult = findMagicIndexWithDupElements(left,leftIndex);
        if(leftResult >= 0)
            return leftResult;
        int rightIndex = Math.max(mid+1,midValue);
        int rightResult = findMagicIndexWithDupElements(rightIndex,right);
        return rightResult;
    }

}
