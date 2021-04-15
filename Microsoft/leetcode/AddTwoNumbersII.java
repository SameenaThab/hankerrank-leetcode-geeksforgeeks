/* 
APPROACH:2
reverse l1 and l2
Result = add two number (AddTwoNumber.java)
Then reverse the result
*/

/* 
https://leetcode.com/problems/add-two-numbers-ii/submissions/
// Approach 1 without  reversing the lists
Algorithm
1.Implement reverseList function.
2.Reverse both input lists: l1 = reverseList(l1), l2 = reverseList(l2).
3.Initialize the result list: head = None.
4.Initialize the carry: carry = 0.
5.Loop through lists l1 and l2 until you reach both ends.
    a.Set x1 = l1.val if l1 is not finished yet, and x1 = 0 otherwise.
    b.Set x2 = l2.val if l2 is not finished yet, and x2 = 0 otherwise.
    c.Compute the current value: val = (carry + x1 + x2) % 10, and the current carry: carry = (carry + x1 + x2) / 10.
    d.Update the result by adding the current value to front.
    e.Move to the next elements in the lists.
    f.If the carry is not equal to zero, append it to frond of the result list.
6.Return the result list: return head.

Time complexity: O(N1 + N2), where N1 + N2 is a number of elements in both lists.
Space complexity: O(1) space complexity without taking the output list into account, and O(N1, N2) to store the output list.
*/
class AddTwoNumbersII {

  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n1 = 0;
        int n2 = 0;
        ListNode t1 = l1;
        ListNode t2 = l2;
        while(t1 != null){
            n1++;
            t1=t1.next;
        }
        while(t2 != null){
            n2++;
            t2=t2.next;
        }
        t1=l1;
        t2=l2;
        //We add the numbers one by one but in reverse order
        // parse both lists
        // and sum the corresponding positions 
        // without taking carry into account
        // 3->3->3 + 7->7 --> 3->10->10--> 10->10->3
        ListNode prev = null;
        while(n1>0 && n2>0) {
            int val = 0;
            if(n1>=n2) {
                //just add t1 value to val
                val+=t1.val;
                t1=t1.next;
                n1--;
            }
            if(n1<n2) {
                //just add t2 value to val
                val+=t2.val;
                t2=t2.next;
                n2--;
            }
            // update the result: add to front
            ListNode newNode = new ListNode(val);
            // This line does the reversal
            newNode.next = prev;
            prev = newNode;
        }
        // take the carry into account
        // to have all elements to be less than 10
        // 10->10->3 --> 0->1->4 --> 4->1->0
        ListNode t = prev;
        prev = null;
        int carry = 0;
        while(t!=null){
            int val = (t.val+carry)%10;
            carry = (t.val+carry)/10;
            // update the result: add to front
            ListNode newNode = new ListNode(val);
            // This line does the reversal
            newNode.next = prev;
            prev = newNode;
            // move to the next elements in the list
            t=t.next;
        }
        if(carry != 0) {
            ListNode newNode = new ListNode(carry);
            newNode.next = prev;
            prev = newNode;
        }
        return prev;
    }
}