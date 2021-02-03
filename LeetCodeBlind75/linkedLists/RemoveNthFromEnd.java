import java.util.*;
/* 
https://leetcode.com/problems/remove-nth-node-from-end-of-list/
*/

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class RemoveNthFromEnd {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode t = head;
        int n=6;
        for(int i=2;i<=n;i++){
            ListNode temp = new ListNode(i);
            t.next = temp;
            t=t.next;
        }
        t=head;
        System.out.println("before removal");
        while(t!=null) {
            System.out.print(t.val+" ");
            t=t.next;
        }
        System.out.println();
        RemoveNthFromEnd sol = new RemoveNthFromEnd();
        ListNode rev = sol.removeNthFromEndOnePass(head,4);
        t=rev;
        System.out.println("after removal");
        while(t!=null) {
            System.out.print(t.val+" ");
            t=t.next;
        }
    }
/* 
One pass is optimal
we have two pointers first and second
make first traverse n+1 times, 
Now move both first and second to next nodes normally maitaining gap of n nodes between them
byt the time first reaches to null, second wil; be at preceeding node of deleting node
add a dummy node in the beginning , makes it easy to remove nth element form last/0th element from beginning
eg: delete node 2 from last
    dummy(f,s)->1->2->3->4->5
    dummy(s)->1->2->3(f)->4->5
    dummy->1(s)->2->3->4(f)->5
    dummy->1->2(s)->3->4->5(f)
    dummy->1->2->3(s)->4->5->null(f)
    remove 3->next
*/
    public ListNode removeNthFromEndOnePass(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for(int i=1;i<=n+1;i++) {
            first=first.next;
        }
        while(first!=null) {
            first=first.next;
            second = second.next;
        }
        second.next = second.next.next;  
        return dummy.next;
    }

    public ListNode removeNthFromEndTwoPass(ListNode head, int n) {
        
        ListNode t = head;
        int length =0;
        // find length
        while(t!=null){
            t=t.next;
            length++;
        }
        // dummy->1->2->3->4 len =4,n =2,len-n = 2
        // traverse from dummy till len = 0(decrement everytime)
        // at len=0 we will be at previous node of node we want to delete
        // add a dummy node in the beginning , makes it easy to remove nth element form last/0th element from beginning
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        length = length-n;
        t = dummy;
        while(length>0){
            length--;
            t=t.next;
        }
        t.next = t.next.next;  
        return dummy.next;
    }
}