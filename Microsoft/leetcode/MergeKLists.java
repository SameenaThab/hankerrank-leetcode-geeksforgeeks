/* 
Time complexity : O(Nlogk) where k is the number of linked lists.
We can merge two sorted linked list in O(n) time where nn is the total number of nodes in two lists.
Sum up the merge process (from range 1 to logK as we divide in halves) and we can get: O(Nlogk)
Space complexity : O(1). Merge2Lists also takes O(1) space
*/
class MergeKLists {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        int interval = 1;
        while(interval < lists.length) {
             for(int i=0;i<lists.length-interval;i+=2*interval) {
                lists[i] = merge2Lists(lists[i],lists[i+interval]);
            } 
            interval*=2;
        }
        return lists[0];
    }
    
    public ListNode merge2Lists(ListNode l1,ListNode l2) {
        ListNode t1 = l1;
        ListNode t2 = l2;
        ListNode result = new ListNode(-1);
        ListNode l3 = result;
        while(t1!=null && t2!=null) {
            if(t1.val<=t2.val) {
                l3.next = t1;
                t1 = t1.next;
            } else {
                l3.next = t2;
                t2 = t2.next;
            }
            l3=l3.next;
        }
        while(t1 != null) {
            l3.next = t1;
            t1 = t1.next;
            l3 = l3.next;
        }
        while(t2 != null) {
            l3.next = t2;
            t2 = t2.next;
            l3 = l3.next;
        }
        return result.next;
    }
}