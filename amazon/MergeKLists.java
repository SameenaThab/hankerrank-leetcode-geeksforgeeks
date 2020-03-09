/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class MergeKLists {
    //https://leetcode.com/explore/interview/card/amazon/77/linked-list/512/
    // another solution - put all values in heap/prorityQueue and create a new list from queue.
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        if(lists.length == 1)
            return lists[0];
        int n = lists.length;
        ListNode result = lists[0];
        for(int i=1;i<n;i++) {
            result = merge(result,lists[i]);
        }
        return result;
    }
    
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode t1 = l1;
        ListNode t2 = l2;
        ListNode result = new ListNode(0);
        ListNode t3 = result;
        while(t1 != null && t2 != null) {
            if(t1.val <= t2.val) {
                t3.next = t1;
                t1 = t1.next;
            } else {
                t3.next = t2;
                t2 = t2.next;
            }
            t3 = t3.next;
        }
        if(t1!=null)
            t3.next = t1;
        if(t2!=null)
            t3.next = t2;
        return result.next;
    }
}