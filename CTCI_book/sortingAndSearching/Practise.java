import java.util.*;

class TreeNode {
    TreeNode left;
    TreeNode right;
    int data;
    int rank;

    TreeNode(int data) {
        this.data=data;
        this.left=null;
        this.right=null;
        this.rank=0;
    }

    void insert(int x) {
        if(x==this.data) {
            this.rank++;
            return;
        }
        if(x<this.data) {
            this.rank++;
            if(this.left!=null)
                this.left.insert(x);
            else
                this.left = new TreeNode(x);
        } else {
            if(this.right!=null)
                this.right.insert(x);
            else
                this.right = new TreeNode(x);           
        }
    }

    int getRank(int x) {
        // System.out.println("data: "+data+" rank: "+rank);
        if(x==this.data) {
            return this.rank;
        }
        if(x<this.data && this.left!=null) {
            return this.left.getRank(x);
        } 
        if(x>this.data && this.right!=null) {
            int xRank = this.right.getRank(x);
            return rank == -1?rank:1+this.rank+xRank;
        }
        return -1;
    }
}

public class Practise {

    public static void main(String[] args) {
        Practise p = new Practise();
        int[] arr = {0,1,4,4,7,8,9};
        int[] arr1 = new int[]{20,15,10,5,13,25,23,24};
        int[] arr2 = new int[]{5,3,1,2,3};
        int[] arr3 = new int[]{20,15,10,5,13,25,23,24};
        int[] arr4 = new int[]{5,3,1,2,3};
        getPeaks(arr);
        getPeaks(arr1);
        getPeaks(arr2);
        getPeaks(arr3);
        getPeaks(arr4);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr4));
    }

    public static void getPeaks(int[] arr){
        for(int i=1;i<arr.length;i+=2) {
            int newL = findLargestIndex(arr,i);
            if(newL != i)
                swap(arr,i,newL);
        }
    }

    public static int findLargestIndex(int[] arr,int index) {
        int curr = arr[index];
        int prev = index-1 < 0? Integer.MIN_VALUE : arr[index-1];
        int next = index+1 == arr.length? Integer.MIN_VALUE : arr[index+1];
        int maxValue = Math.max(curr,Math.max(next, prev));
        if(maxValue == curr)
            return index;
        else if(maxValue == prev)
            return index-1;
        else
            return index+1;
    }

    private static int maxIndex(int[] arr, int a, int b, int c) {
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

    public static void swap(int[] arr,int index,int largeIndex) {
        int temp = arr[index];
        arr[index]=arr[largeIndex];
        arr[largeIndex]=temp;
    }
}
