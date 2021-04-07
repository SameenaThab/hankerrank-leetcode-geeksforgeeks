/* 
https://algo.monster/problems/middle_of_linked_list
*/

class MiddleOfLinkedList {

    /** Driver code, do not change **/
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
    // 1 2 3 4

   public static ListNode middleOfLinkedList(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
       while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
       }
       return slow;
   }
}