import java.util.*;


/**
 * In the Partition method, 
 * Everytime, arr undergoes partition
 * all elements smaller than pivot come before pivot_index
 * All elements larger than pivot go after pivot_index
 *  That means pivot_index is placed by the correct element like it should be if the arr is sorted.
 * but the left index returned by partition will not signify anything
 * 
 * 
 * In quickSelectPartition
 * Everytime, arr undergoes quickSelectPartition
 * it partitioned such that 
 * all  elements smaller than pivot come before pivot
 * all  elements larger than pivot come after pivot
 * That means pivot elment is placed at the correct index like it should be if the arr is sorted.
 * And it returns the index of the pivot element.
 * that mean the index returned lets call it k will have  kth smallest element 
 * 
 * TIme Complexity of QucikSelect
 * Like quicksort, quickselect has good average performance, but is sensitive to the pivot that is chosen. If good pivots are chosen, meaning ones that consistently decrease the search set by a given fraction, then the search set decreases in size exponentially and by induction (or summing the geometric series) one sees that performance is linear, as each step is linear and the overall time is a constant times this (depending on how quickly the search set reduces). However, if bad pivots are consistently chosen, such as decreasing by only a single element each time, then worst-case performance is quadratic: {\displaystyle O(n^{2}).}{\displaystyle O(n^{2}).} This occurs for example in searching for the maximum element of a set, using the first element as the pivot, and having sorted data. The probability of the worst-case occurring decreases exponentially with {\displaystyle n,}{\displaystyle n,} so quickselect has almost certain {\displaystyle O(n)}O(n) time complexity.
*/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr1 = {3,2,1,5,6,4};
        quickSort(arr1,0,arr1.length-1);
        System.out.println("quickSort result: "+Arrays.toString(arr1));
        int[] arr2 = {3,2,1,5,6,4};
        quickSelectSort(arr2,0,arr2.length-1);
        System.out.println("quickSelectSort result: "+Arrays.toString(arr2));
    }

    public static void quickSort(int[] arr,int left,int right) {
        int index = partiton(arr,left,right);
        if(left<index-1) { // this is to make sure left<right in the next recursion 
            quickSort(arr,left,index-1);
        }
        if(index < right) {
            quickSort(arr,index,right);
        }
    }

    public static void quickSelectSort(int[] arr,int left,int right) {
        int index = quickSelectPartition(arr,left,right);
        if(left<index-1) {
            quickSelectSort(arr,left,index-1);
        }
        if(index+1 < right) {
            quickSelectSort(arr,index+1,right);
        }
    }

    public static int partiton(int[] arr,int left,int right) {
        int mid = (left+right)/2;
        int pivot = arr[mid];
        System.out.println("left: "+left+" right:"+right+" mid: "+mid+" pivot: "+pivot);
        System.out.println("before: "+Arrays.toString(arr));
        while(left<=right) {
            while(arr[left]<pivot) {
                left++;
            }
            while(arr[right]>pivot) {
                right--;
            }
            System.out.println("In while loop left: "+left+" right:"+right);
            if(left<=right) {
                swap(arr,left,right);
                left++;
                right--;
            }
        }
        System.out.println("after: "+Arrays.toString(arr));
        System.out.println("returned pivot: "+left);
        return left;
    }

    public static int quickSelectPartition(int[] arr, int left, int right) {
        int mid = (left+right)/2;
        int pivot = arr[mid];
        swap(arr,mid,right);
        int indexForSmallElements = left;
        for(int i=left;i<=right;i++) {
            if(arr[i]<pivot) {
                swap(arr,indexForSmallElements,i);
                indexForSmallElements++;
            }
        }
        // At this point indexForSmallElements will have the first element encountered that is larger than pivot
        swap(arr,indexForSmallElements,right); // swap back the pivot element that was initially stored at right index
        // This will place the pivot element at the correct index as it should be in sorted list 
        return indexForSmallElements; 
    }

    public static void swap(int[] arr,int left, int right) {
        int temp = arr[left];
        arr[left]=arr[right];
        arr[right]=temp;
    }
}
