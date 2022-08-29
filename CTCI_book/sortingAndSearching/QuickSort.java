import java.util.*;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr1 = {4,10,2,55,9,4,15};
        quickSort(arr1,0,arr1.length-1);
        System.out.println(Arrays.toString(arr1));
    }

    public static void quickSort(int[] arr,int left,int right) {
        int index = partiton(arr,left,right);
        if(left<index-1) {
            quickSort(arr,left,index-1);
        }
        if(index < right) {
            quickSort(arr,index,right);
        }
    }

    public static int partiton(int[] arr,int left,int right) {
        int mid = (left+right)/2;
        int pivot = arr[mid];
        while(left<=right) {
            while(arr[left]<pivot) {
                left++;
            }
            while(arr[right]>pivot) {
                right--;
            }
            if(left<=right) {
                swap(arr,left,right);
                left++;
                right--;
            }
        }
        // System.out.println("left: "+left);
        return left;
    }

    public static void swap(int[] arr,int left, int right) {
        int temp = arr[left];
        arr[left]=arr[right];
        arr[right]=temp;
    }
}
