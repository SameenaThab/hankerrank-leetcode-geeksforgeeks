

import java.util.*;
/* 
https://leetcode.com/problems/reorder-list/solution/
 1->2->3->4->5->6
1* Find middle of linkedlist Using slow and fast pointer
        slow = slow.next
        fast = fast.next.next . By the time fast is null slow is in middle
    For two middle node find the second one 
2* reverse the middle part of linked list
    1->2->3, 6->5->4 <-|
          |------------|
    Note*: 3 still has link to 4

3* merge two lists, head and reversed mid(midreverse) one by one like we did in mergeSortedLists
    We merge by saving successor of of the t1(head) and t2(midreverse) pointers
    So that when we change the next link we can move forward using these saved successors
    1(t1)->2(tmp1) , 4(t2)->3(tmp2) (2 has link to 3)
    1->4->2(t1) -> 3(t2) (2 already has link to 3)
    So we stop the while loop when t2.next == null
*/
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class ReorderList {

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,3,4,5};
        ListNode head = new ListNode(arr1[0]);
        ListNode t = head;
        for(int i=1;i<arr1.length;i++){
            ListNode temp = new ListNode(arr1[i]);
            t.next = temp;
            t=t.next;
        }
        ReorderList sol = new ReorderList();
        t=head;
        System.out.println("before reorder");
        while(t!=null) {
            System.out.print(t.val+" ");
            t=t.next;
        }
        System.out.println();
        sol.reorderList(head);
        t=head;
        System.out.println("after reorder");
        while(t!=null) {
            System.out.print(t.val+" ");
            t=t.next;
        }
    }


    public void reorderList(ListNode head) {
        if(head == null)
            return;
        ListNode mid = findMiddle(head);
        // System.out.println("Mid value");
        // System.out.println(mid.val);
        ListNode midReverse = reverse(mid);
        // System.out.println("head now");
        // ListNode t = head;
        // while(t!=null) {
        //     System.out.print(t.val+" ");
        //     t=t.next;
        // }
        // System.out.println();
        // t = midReverse;
        // System.out.println("midReverse");
        // while(t!=null) {
        //     System.out.print(t.val+" ");
        //     t=t.next;
        // }
        // System.out.println();
        // ListNode merged = merge(head,midReverse);
        merge(head,midReverse);
        // head = merged;
        // t = head;
        // System.out.println("merged");
        // while(t!=null) {
        //     System.out.print(t.val+" ");
        //     t=t.next;
        // }
    }

    private void merge(ListNode head, ListNode rev) {
        ListNode t1 = head;
        ListNode t2 = rev;
        while(t2.next != null) {
            // System.out.println("t1: "+t1.val+" t2: "+t2.val);
            ListNode temp1 = t1.next;
            ListNode temp2 = t2.next;
            t1.next = t2;
            t2.next = temp1;
            t2=temp2;
            t1=temp1;
        }
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;
        ListNode fast = curr.next;
        while(curr != null) {
            curr.next = prev;
            prev=curr;
            curr=fast;
            if(fast != null)
                fast=fast.next;
        }
        return prev;
    }

    private ListNode findMiddle(ListNode head) {
        ListNode  slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}