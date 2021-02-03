import java.util.*;
/* 
https://leetcode.com/problems/linked-list-cycle/solution/
*/
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class LinkedListCycle {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node3;
        ListNode t=head;
        // System.out.println("before reverse");
        // while(t!=null) {
        //     System.out.print(t.val+" ");
        //     t=t.next;
        // }
        System.out.println();
        LinkedListCycle sol = new LinkedListCycle();
        System.out.println("Has Cycle? "+sol.hasCycleFloyds(head));
        System.out.println("Has Cycle? "+sol.hasCycleUsingHashSet(head));
    }

    public boolean hasCycleFloyds(ListNode head) { 
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast) { // if there a a cycle fast will be equal to slow at some point
            if(fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    //More Space
    public boolean hasCycleUsingHashSet(ListNode head) { 
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode t = head;
        while(t!=null) {
            if(visited.contains(t))
                return true;
            visited.add(t);
            t=t.next;
        }
        return false;
    }
}