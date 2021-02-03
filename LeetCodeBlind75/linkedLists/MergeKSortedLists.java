import java.util.*;
/* 
https://leetcode.com/problems/merge-k-sorted-lists/

Divide And conquer
[node1,node2,node3,node4,node5]
interval = 1, merge nodes with 1 interval and save it in same element 
     -> [node1+node2,node2,node3+node4,node4,node5]
interval = 1*2 = 2, merge nodes with 2 interval
    -> [node1+node2+node3+node4, node2, node3+node4, node4, node5]
interval = 2*2 = 4, merge nodes with 4 interval
    -> [node1+node2+node3+noed4+node5, node2, node3+node4, node4, node5]
interval = 4*2 = 8 > n, therefore we stop
return lists[0]
*/

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class MergeKSortedLists {

    public static void main(String[] args) {
        int[] arr1 = new int[]{2,3,9,19,20};
        int[] arr2 = new int[]{1,3,8,17};
        int[] arr3 = new int[]{-9,3};
        ListNode l1 = new ListNode(arr1[0]);
        ListNode t1 = l1;
        for(int i=1;i<arr1.length;i++){
            ListNode temp = new ListNode(arr1[i]);
            t1.next = temp;
            t1=t1.next;
        }
        ListNode l2 = new ListNode(arr2[0]);
        ListNode t2 = l2;
        for(int i=1;i<arr2.length;i++){
            ListNode temp = new ListNode(arr2[i]);
            t2.next = temp;
            t2=t2.next;
        }

        ListNode l3 = new ListNode(arr3[0]);
        ListNode t3 = l3;
        for(int i=1;i<arr3.length;i++){
            ListNode temp = new ListNode(arr3[i]);
            t3.next = temp;
            t3=t3.next;
        }

        MergeKSortedLists sol = new MergeKSortedLists();
        ListNode[] lists = new ListNode[3];
        lists[0]=l1;
        lists[1]=l2;
        lists[2]=l3;
        // ListNode l = sol.mergeKLists(lists);
        // ListNode t=l;
        // System.out.println("Merged List");
        // while(t!=null) {
        //     System.out.print(t.val+" ");
        //     t=t.next;
        // }
        ListNode l = sol.mergeKListsDivideAndConquer(lists);
        ListNode t=l;
        System.out.println("Merged List");
        while(t!=null) {
            System.out.print(t.val+" ");
            t=t.next;
        }
    }

    //time complexity = O(nlogk) {k lists, n = total no of elements in two list}
    // time complexity = O(1)
    public ListNode mergeKListsDivideAndConquer(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        int n = lists.length;
        int interval = 1;
        while(interval < n) {
            for(int i=0;i<n-interval;i+=interval*2){
                lists[i] = mergeTwoLists(lists[i], lists[i+interval]); 
            }
            interval*=2;
        }
        return lists[0];
    }

     //time complexity = O(kn) {k lists, n = total no of elements in two list}
    // time complexity = O(1)
    public ListNode mergeKLists(ListNode[] lists) { 
        if(lists.length == 0)
            return null;
        if(lists.length == 1)
            return lists[0];
        ListNode result = lists[0];
        for(int i=1;i<lists.length;i++){
            result = mergeTwoLists(result,lists[i]);
        }
        return result;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode t3 = result;
        ListNode t1=l1;
        ListNode t2=l2;
        while(t1!=null && t2!=null){
            if(t1.val <= t2.val){
                t3.next = new ListNode(t1.val);
                t1=t1.next;
            } else {
                t3.next = new ListNode(t2.val);
                t2=t2.next;
            }
            t3=t3.next;
        }
        while(t1 != null){
            t3.next = new ListNode(t1.val);
            t1=t1.next;
            t3=t3.next;
        }
        while(t2 != null){
            t3.next = new ListNode(t2.val);
            t2=t2.next;
            t3=t3.next;
        }
        return result.next;
    }
}