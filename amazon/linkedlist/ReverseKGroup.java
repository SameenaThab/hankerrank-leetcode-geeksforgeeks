/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//https://leetcode.com/explore/interview/card/amazon/77/linked-list/2977/
class ReverseKGroup {
    
//     Node reverse(Node head, int k) 
//     { 
//        Node current = head; 
//        Node next = null; 
//        Node prev = null; 
         
//        int count = 0; 
  
//        /* Reverse first k nodes of linked list */
//        while (count < k && current != null)  
//        { 
//            next = current.next; 
//            current.next = prev; 
//            prev = current; 
//            current = next; 
//            count++; 
//        } 
  
//        /* next is now a pointer to (k+1)th node  
//           Recursively call for the list starting from current. 
//           And make rest of the list as next of first node */
//        if (next != null)  
//           head.next = reverse(next, k); 
  
//        // prev is now head of input list 
//        return prev; 
//     }  
    
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = null;
        ListNode curr = head;
        ListNode prev = null;
        ListNode t = head;
        int size = 0;
        while(t != null) {
            t = t.next;
            size++;
        }
        if(size<k)
            return head;
        int j = 0;
        while(j<k && curr!=null) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            j++;
        }
        ListNode t2 = head;
        while(t2!=null){
            System.out.print(t2.val+" ");
            t2=t2.next;
        }       
        System.out.println();
        // t2 = prev;
        // while(t2!=null){
        //     System.out.print(t2.val+" ");
        //     t2=t2.next;
        // }
        // System.out.println();      
        t2 = temp;
        while(t2!=null){
            System.out.print(t2.val+" ");
            t2=t2.next;
        }
        System.out.println();
        if(temp != null) {
            head.next = reverseKGroup(temp,k);
            // ListNode t3 = head;
            // while(t3!=null){
            //     System.out.print(t3.val+" ");
            //     t3=t3.next;
            // }       
            // System.out.println();
        }        
    return prev;
    }
}