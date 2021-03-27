import java.util.*;
/* 
https://leetcode.com/problems/merge-two-sorted-lists/
*/

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class MergeSortedLists {

    public static void main(String[] args) {
        // int[] arr1 = new int[]{2,3,9,19,20};
        // int[] arr2 = new int[]{1,3,8,17};
        int[] arr1 = new int[]{-9,3};
        int[] arr2 = new int[]{5,7};
        ListNode l1 = new ListNode(arr1[0]);
        ListNode t1 = l1;
        for(int i=1;i<arr1.length;i++){
            ListNode temp = new ListNode(arr1[i]);
            t1.next = temp;
            t1=t1.next;
        }
        ListNode l2 = new ListNode(arr2[0]);
        ListNode t2 = l2;
        for(int i=1;i<arr2.length;i++){
            ListNode temp = new ListNode(arr2[i]);
            t2.next = temp;
            t2=t2.next;
        }
        MergeSortedLists sol = new MergeSortedLists();
        ListNode l = sol.mergeTwoLists(l1,l2);
        ListNode t=l;
        System.out.println("Merged List");
        while(t!=null) {
            System.out.print(t.val+" ");
            t=t.next;
        }
    }

    /* 
    Time complexity : O(n + m)
    Because exactly one of l1 and l2 is incremented on each loop iteration, the while loop runs for a number of iterations equal to the sum of the lengths of the two lists. All other work is constant, so the overall complexity is linear.
    Space complexity : O(1)
    */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode t3 = result;
        ListNode t1=l1;
        ListNode t2=l2;
        while(t1!=null && t2!=null){
            if(t1.val <= t2.val){
                t3.next = new ListNode(t1.val);
                t1=t1.next;
            } else {
                t3.next = new ListNode(t2.val);
                t2=t2.next;
            }
            t3=t3.next;
        }
        while(t1 != null){
            t3.next = new ListNode(t1.val);
            t1=t1.next;
            t3=t3.next;
        }
        while(t2 != null){
            t3.next = new ListNode(t2.val);
            t2=t2.next;
            t3=t3.next;
        }
        return result.next;
    }
}