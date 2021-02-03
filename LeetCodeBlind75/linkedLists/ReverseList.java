
class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

class ReverseList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode t = head;
        int n=2;
        for(int i=2;i<=n;i++){
            ListNode temp = new ListNode(i);
            t.next = temp;
            t=t.next;
        }
        t=head;
        System.out.println("before reverse");
        while(t!=null) {
            System.out.print(t.val+" ");
            t=t.next;
        }
        System.out.println();
        ReverseList sol = new ReverseList();
        ListNode rev = sol.reverseList(head);
        t=rev;
        System.out.println("after reverse");
        while(t!=null) {
            System.out.print(t.val+" ");
            t=t.next;
        }
    }

    public ListNode reverseList(ListNode head) { 
        if(head == null)
            return null;
        ListNode curr = head;
        ListNode prev = null;
        ListNode next = head.next;
        while(curr != null) {
            curr.next = prev;
            prev=curr;
            curr=next;
            if(next!=null)
                next = next.next;
        }
        return prev;
    }
}