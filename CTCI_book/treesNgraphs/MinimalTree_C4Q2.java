import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
0: 1
1: 2
2: 0,3
3: 2
4: 6
5: 4
6: 5
*/

public class MinimalTree_C4Q2 {

    int[] arr;
    int n;

    TreeNode buildMinimalBST(int[] arr,int left,int right) {
        if(right<left) {
            return null;
        }
        else {
           int mid = (left+right)/2;
           TreeNode root =  new TreeNode(arr[mid]);
           root.left = buildMinimalBST(arr,left,mid-1);
           root.right = buildMinimalBST(arr,mid+1,right);
           return root;
        }
    }

    void preOrder(TreeNode root) {
        if(root != null) {
            System.out.print(root.value);
            System.out.print(" ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    void inOrder(TreeNode root) {
        if(root != null) {
            inOrder(root.left);
            System.out.print(root.value);
            System.out.print(" ");
            inOrder(root.right);
        }
    }
    
        
    public class TreeNode {
        int value;
        TreeNode left,right;

        TreeNode(int val) {
            this.value = val;
            this.left = null;
            this.right = null;
        }
    }
        
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        MinimalTree_C4Q2 sol = new MinimalTree_C4Q2();
        System.out.print("Enter n: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        System.out.print("Enter array: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        
        sol.n = n;
        sol.arr = arr;
        TreeNode root = sol.buildMinimalBST(arr, 0, n-1);
        // sol.preOrder(root);
        sol.inOrder(root);
        scanner.close();
    }
}


