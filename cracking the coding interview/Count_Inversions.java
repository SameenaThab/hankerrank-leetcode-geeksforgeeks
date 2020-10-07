// https://www.hackerrank.com/challenges/ctci-merge-sort/problem

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Count_Inversions {

    int[] arr;
    int count;

    Count_Inversions(int[] arr,int count) {
        this.arr=arr;
        this.count=count;
    }

    int countInversions_timeExceeded() {
        int n = arr.length;
        int[] temp = Arrays.copyOf(arr,n);
        // mergeSort(0,n-1,temp);
        // System.out.println("no. of inversions: "+count);
        return mergeSort(0,n-1,temp);
    }

    int mergeSort(int l,int r,int[] temp) {
        if(l>=r)
            return 0;
        int mid = (l+r)/2;
        int count = mergeSort(l,mid,temp)+mergeSort(mid+1,r,temp);
        int lp = l;
        int rp = mid+1;
        int lEnd = mid;
        int rEnd = r;
        int index = l;
        while(lp <= lEnd && rp <= rEnd) {
            if(arr[lp] <= arr[rp]) {
                temp[index]=arr[lp];
                lp++;
            }
            else {
                temp[index] = arr[rp];
                rp++;
                //lEnd is inclusive limit , meaning it arr[lEnd] is part of left array
                count=count+(lEnd-lp+1); // The No. of swaps will be The No. of left Elements remainiing, because we are swapping with all those numbers
            }
            index++;
        }
        while(lp<=lEnd) {
            // count++;
            temp[index++]=arr[lp++];
        }
        while(rp<=rEnd) {
            temp[index++]=arr[rp++];
        }
        // System.out.println("recursion l:"+l+" "+" mid: "+mid+" r: "+r);
        // System.out.println("count: "+count);
        // for(int i=0;i<temp.length;i++)
        //     arr[i]=temp[i];
        return count;
    }

    static long countInversions_easyToUnderStand(int[] arr) {
        int n=arr.length;
        if(n<=1)
            return 0;
        int mid=n/2;
        int[] leftarr=Arrays.copyOfRange(arr,0,mid);
        int[] rightarr=Arrays.copyOfRange(arr,mid,n);
        long inversions=countInversions(leftarr)+countInversions(rightarr);
        
        int l=0;
        int r=0;
        int rlimit=n-mid;
        int index=0;

        while(l < mid && r < rlimit) {
            if(leftarr[l] <= rightarr[r]) {
                arr[index]=leftarr[l];
                l++;
            }
            else {
                arr[index] = rightarr[r];
                r++;
                //mid is exclusive limit , meaning it arr[mid] is NOT part of left array
                inversions=inversions+(mid-l); // The No. of swaps will be The No. of left Elements remainiing, because we are swapping with all those numbers
            }
            index++;
        }
        while(l<mid) {
            // count++;
            arr[index++]=leftarr[l++];
        }
        while(r<rlimit) {
            arr[index++]=rightarr[r++];
        }
        
        return inversions;
    }

    static long countInversions(int[] arr) {
        int n=arr.length;
        if(n<=1)
            return 0;
        int mid=n/2;
        int[] leftarr=Arrays.copyOfRange(arr,0,mid);
        int[] rightarr=Arrays.copyOfRange(arr,mid,n);
        long inversions=countInversions(leftarr)+countInversions(rightarr);
        
        int l=0;
        int r=0;
        int rlimit=n-mid;
        for(int i=0;i<n;i++)
        {
            if((l<mid) && (r>=rlimit||leftarr[l]<=rightarr[r]))
            {
                arr[i]=leftarr[l++];
                inversions+=r;
            }
            else
            {
                if(r<rlimit)
                    arr[i]=rightarr[r++];
            }
        }
        
        return inversions;
    }

    public static void main(String[] args) {
        // int[] arr = new int[]{2,1,3,1,2};
        // int[] arr = new int[]{7,5,3,1};
        int[] arr = new int[]{1,5,3,7};
        Count_Inversions sol = new Count_Inversions(arr,0);
        int result = sol.countInversions_timeExceeded();
        // for(int i=0;i<n;i++) {
        //     System.out.print(arr[i]+" ");
        // }
        System.out.println(result);
        arr = new int[]{1,5,3,7};
        System.out.println(countInversions(arr));
        arr = new int[]{1,5,3,7}; // because previous method will sort
        System.out.println(countInversions_easyToUnderStand(arr));
    }
}
