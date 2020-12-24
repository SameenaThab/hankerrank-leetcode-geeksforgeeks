
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.interviewbit.com/problems/convert-sorted-list-to-binary-search-tree/ */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class ListNode {
    public int val;
    public ListNode next;
    ListNode(int x) { val = x; next = null; }
}

public class SortedListToBST {

    public static void main(String[] args) {
        SortedListToBST sol = new SortedListToBST();    
        int[] arr = new int[]{1,2,3};
        ListNode list = new ListNode(arr[0]);
        ListNode temp = list;
        for(int i=1;i<arr.length;i++) {
            temp.next = new ListNode(arr[i]);
            temp = temp.next;
        }
        System.out.println("list: ");
        sol.print(list);
        System.out.println();
        TreeNode root = sol.sortedListToBST(list);
        System.out.println("tree: ");
        sol.inOrder(root);
    }
    public TreeNode sortedListToBST(ListNode a) {
        // ListNode t = a;
        // ArrayList<Integer> list = new ArrayList<Integer>();
        // while(t!=null) {
        //     list.add(t.val);
        //     t=t.next;
        // }
        // int length = list.size();
        // // TreeNode root = build(list,0,length-1);
        TreeNode root = build(a);
        return root;
    }

    // without using list
    TreeNode build(ListNode a) {
        if(a == null)
            return null;
        if(a.next == null)
            return new TreeNode(a.val);
        ListNode mid = findMid(a);
        TreeNode root = new TreeNode(mid.val);
        root.left = build(a);
        root.right = build(mid.next);
        return root;
    }

    ListNode findMid(ListNode a) {
        ListNode prev = a;
        ListNode slow = a;
        ListNode fast = a;
        while(fast != null) {
            fast = fast.next;
            if(fast == null)
                break;
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        // cutting right part of the list for line 63 convinience
        prev.next = null;
        return slow;
    }


    public TreeNode build(ArrayList<Integer> list,int left,int right) {
        if(left>right) 
            return null;
        int mid = (left+right)/2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = build(list,left,mid-1);
        root.right = build(list,mid+1,right);
        return root;
    }

    public void inOrder(TreeNode root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.print(root.val+", ");
        inOrder(root.right);
    }

    public void print(ListNode list) {
        ListNode temp = list;
        while(temp!=null) {
            System.out.print(temp.val+", ");
            temp=temp.next;
        }
    }

}
