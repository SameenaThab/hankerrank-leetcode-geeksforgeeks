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
class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        int k = lists.length;
        int interval = 1;
        while(interval<k) {
            for(int i=0;i<k-interval;i+=2*interval) {
                lists[i]=mergeTwoLists(lists[i],lists[i+interval]);
            }
            interval*=2;
        }
        return lists[0];
    }
    
    public ListNode mergeTwoLists(ListNode l1,ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode t3=result;
        ListNode t1=l1;
        ListNode t2=l2;
        while(t1!=null && t2!= null) {
            if(t1.val<=t2.val) {
                t3.next = new ListNode(t1.val);
                t1=t1.next;
            } else {
                t3.next = new ListNode(t2.val);
                t2=t2.next;
            }
            t3=t3.next;
        }
        t3.next = t1!=null? t1:t2;
        return result.next;
    }
}