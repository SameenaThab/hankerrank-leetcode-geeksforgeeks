/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode t = result;
        //ListNode t = result;
        while(l1 !=null && l2 != null) {
            if(l1.val <= l2.val) {
                t.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            else if(l1.val > l2.val) {
                t.next = new ListNode(l2.val);
                l2 = l2.next;
            }
           t = t.next;
        }
        t.next = (l1 == null)? l2:l1; 
        return result.next;        
    }
}