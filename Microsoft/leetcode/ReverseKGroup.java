class ReverseKGroup {
// check pdfs for approach
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    // Time: O(n)
    // Space: O(1)
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode ptr = head;
        // this is the final result
        ListNode newHead = null;
        ListNode kTail = null;
        while(ptr != null)  {
            int count = 0;
            while(count<k && ptr != null) {
                ptr=ptr.next;
                count++;
            }
            // if k nodes exist then reverse them
            if(count == k) {
                // reverse k nodes
                ListNode revNode = reverseLinkedList(head,k);
                // new_head is the head of the final linked list
                if(newHead == null)
                    newHead = revNode;
                // ktail is the tail of the previous block of 
                // reversed k nodes
                if(kTail != null) {
                    kTail.next = revNode;
                }
                kTail = head;
                head = ptr;
            }
        }
         // attach the final, possibly un-reversed portion
        if(kTail != null)
            kTail.next = head;
        return newHead == null? head:newHead;
    }

    public ListNode reverseLinkedList(ListNode head, int k) {
        ListNode curr = head;
        ListNode prev = null;
        while(curr != null && k>0) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            k--;
        }
        return prev;
    }

    // TIme : O(n)
    //Space : O(n) recursive stack
    public ListNode reverseKGroupRecursive(ListNode head, int k) {
        
        int count = 0;
        ListNode ptr = head;
        
        // First, see if there are atleast k nodes
        // left in the linked list.
        while (count < k && ptr != null) {
            ptr = ptr.next;
            count++;
        }
            
        
        // If we have k nodes, then we reverse them
        if (count == k) {
            
            // Reverse the first k nodes of the list and
            // get the reversed list's head.
            ListNode reversedHead = this.reverseLinkedList(head, k);
            
            // Now recurse on the remaining linked list. Since
            // our recursion returns the head of the overall processed
            // list, we use that and the "original" head of the "k" nodes
            // to re-wire the connections.
            head.next = this.reverseKGroup(ptr, k);
            return reversedHead;
        }
            
        return head;
    }
}