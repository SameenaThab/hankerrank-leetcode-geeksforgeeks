/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode t1 = l1;
        ListNode t2 = l2;
        int cary = 0;
        ListNode result = new ListNode(0);
        ListNode t3 = result;
        while(t1 != null && t2 != null) {
            int sum = t1.val+t2.val+cary;
            t3.next = new ListNode((sum)%10);
            cary = sum/10;
            t1=t1.next;
            t2=t2.next;    
            t3=t3.next;
        }
        while(t1 != null) {
            int sum = t1.val+cary;
            t3.next = new ListNode((sum)%10);
            cary = sum/10;
            t1=t1.next;
            t3=t3.next;
        }
        while(t2 != null) {
            int sum = t2.val+cary;
            t3.next = new ListNode((sum)%10);
            cary = sum/10;
            t2=t2.next;
            t3=t3.next;
        }
        if(cary != 0)
            t3.next = new ListNode(cary);
        return result.next;
    }
}