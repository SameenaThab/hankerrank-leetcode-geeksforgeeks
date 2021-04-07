/* 
Specifically, the steps we need to do are:

Find the end of the first half.
Reverse the second half.
Determine whether or not there is a palindrome.
Restore the list.
Return the result.
*/
class LinkedListIsPalindrome {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public boolean isPalindrome(ListNode head) {
       ListNode firstHalfEnd = findFirstHalfEnd(head);  
       ListNode reversedSecondHalf = reverseList(firstHalfEnd.next); 
       ListNode t1 = head;
       ListNode t2 = reversedSecondHalf;     
       boolean result = true; 
       while(result && t2 != null) {
           if(t1.val != t2.val) {
               result = false;
           }
            t1 = t1.next;
            t2 = t2.next;
       }
       firstHalfEnd.next = reverseList(reversedSecondHalf);
       return result;
    }


    // 1(c)->(n)2->3->4 => null<-1<-2
    private ListNode reverseList(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    /* 
    1->2->3->4->5 outputs 3
    1->2->3->4 outputs 2
    */
    private ListNode findFirstHalfEnd(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // will be mid 
        return slow;
    }
}