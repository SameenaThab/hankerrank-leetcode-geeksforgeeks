/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
//https://leetcode.com/explore/interview/card/amazon/77/linked-list/2978/
class CopyRandomList {
    Map<Node,Node> map = new HashMap<Node,Node>();
    public Node copyRandomList(Node head) {
        //return helper(head);
        return noMapSolution(head);
    }
    
    public Node noMapSolution(Node head) {
        //weaved nodes A->A'->B->B'
        Node t = head;
        if(head == null)
            return null;
        while(t != null) {
            Node newNode = new Node(t.val);
            newNode.next = t.next;
            t.next = newNode;
            t = newNode.next;
        }
        
        //weaved linked lists with random points set A(random C) = A'(random C')
        t = head;
        while(t != null) {
            //ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            Node clonedNode = t.next;
            clonedNode.random = t.random!=null? t.random.next:null; // cloned node of random node is stored aside it
            t = t.next.next;            
        }
        
       // Now unweave all the cloned node A->A'->B->B' = A->A' and B->B'
        Node original = head;
        Node clonedTraversal = head.next;
        Node clonedNode = head.next; // saving the clonedNodes head before traversal
        
        while(original != null) {
            original.next = original.next.next;
            clonedTraversal.next = (clonedTraversal.next != null)? clonedTraversal.next.next : null;
            clonedTraversal = clonedTraversal.next;
            original = original.next;
        }
        
        return clonedNode;
    }
    
    public Node helper(Node head) {
        if(head == null)
            return null;
        if(map.containsKey(head))
            return map.get(head);
        else {
            Node cloneNode = new Node(head.val);
            map.put(head,cloneNode);
            cloneNode.next = helper(head.next);
            cloneNode.random = helper(head.random);
            return cloneNode;
        }
    }
}