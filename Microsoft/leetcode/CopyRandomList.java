import java.util.HashMap;

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
class CopyRandomList {
    /*
    Time Complexity: O(n)
    Space Complexity: O(n)
    */
    public Node copyRandomList(Node head) {
        if(head == null)
            return null;
        Map<Node,Node> visited = new HashMap<Node,Node>();
        visited.put(head,new Node(head.val));
        // Queue<Node> queue = new LinkedList<Node>();
        // queue.add(head);
        Node t = head;
        while(t != null) {
            // Node t = queue.poll();
            Node clone = visited.get(t);
            if(t.next != null) {
                if(!visited.containsKey(t.next)) {
                    visited.put(t.next,new Node(t.next.val));
                    // queue.add(t.next);
                }
                clone.next = visited.get(t.next);
            }
            if(t.random != null) {
                if(!visited.containsKey(t.random)) {
                    visited.put(t.random,new Node(t.random.val));
                    // queue.add(t.random);
                }    
                clone.random = visited.get(t.random);
            }
            t=t.next;
        }
        return visited.get(head);
    }

    // Time O(n)
    // Space O(1)
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
        /*
            Node ptr_old_list = head; // A->B->C
    Node ptr_new_list = head.next; // A'->B'->C'
    Node head_old = head.next;
    while (ptr_old_list != null) {
      ptr_old_list.next = ptr_old_list.next.next;
      ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
      ptr_old_list = ptr_old_list.next;
      ptr_new_list = ptr_new_list.next;
    }
    return head_old;
    */
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
}