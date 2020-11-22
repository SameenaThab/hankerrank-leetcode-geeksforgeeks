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

    TreeNode buildMinimalBST2(int[] arr,int l,int r) {
        if(r<l)
            return null;
        int mid = (l+r)/2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = buildMinimalBST2(arr,l,mid-1);
        root.right = buildMinimalBST2(arr,mid+1,r);
        return root;
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
        TreeNode root = sol.buildMinimalBST2(arr, 0, n-1);
        // sol.preOrder(root);
        System.out.println("Inorder traversal: ");
        sol.inOrder(root);
        scanner.close();
    }
}


