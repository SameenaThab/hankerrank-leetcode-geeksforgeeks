import java.util.*;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr1 = {4,10,2,55,9,4,15};
        mergeSort(arr1,0,arr1.length-1);
        System.out.println(Arrays.toString(arr1));
    }

    public static void mergeSort(int[] arr,int left, int right) {
        if(left<right) {
            int mid = (left+right)/2;
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr,left,mid,right);
        }
    }

    public static void merge(int[] arr, int left,int mid,int right) {
        int[] helper = arr.clone();
        int lp=left;
        int rp=mid+1;
        int curr=left;
        while(lp<=mid && rp<=right) {
            if(helper[lp]<=helper[rp]) {
                arr[curr++]=helper[lp++];
            } else {
                arr[curr++]=helper[rp++];
            }
        }
        int rem = mid-lp;
        for(int i=0;i<=rem;i++) {
            arr[curr+i]=helper[lp+i];
        }
    }
}
