https://leetcode.com/problems/add-two-numbers/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class AddLinkedLists {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int cary =0;
        ListNode l3 = null;
        ListNode t = null;
        while(l1!=null && l2!=null) {
            int val = (l1.val+l2.val+cary);
            cary = val<10?0:1;
            val = val%10;
            ListNode temp = new ListNode(val);
            if(t==null) {
                t = temp;
                l3=t;
            }
            else {
                t.next=temp;
                t=t.next;
            }
            l1=l1.next;
            l2=l2.next;
        }
        if(l1 == null && l2 == null && cary == 0) {
            return l3;
        }
        while(l1!=null) {
            int val = (l1.val+cary);
            cary = val<10?0:1;
            val = val%10;
            ListNode temp = new ListNode(val);
            if(t==null) {
                t = temp;
                l3=t;
            }
            else {
                t.next=temp;
                t=t.next;
            }
            l1=l1.next; 
        }
        while(l2!=null) {
            int val = (l2.val+cary);
            cary = val<10?0:1;
            val = val%10;
            ListNode temp = new ListNode(val);
            if(t==null) {
                t = temp;
                l3=t;
            }
            else {
                t.next=temp;
                t=t.next;
            }
            l2=l2.next; 
        }
        if(cary == 1) {
            ListNode temp = new ListNode(1);
            if(t==null) {
                t = temp;
                l3=t;
            }
            else {
                t.next=temp;
                t=t.next;
            } 
        }  
        return l3;
    }
}