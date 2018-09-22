/*
Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

A Node is defined as: 
    class Node {
        int data;
        Node next;
    }
*/

public class HasCycle{

 boolean hasCycle(Node head) {
    Node t=head;
    ArrayList<Node> al=new ArrayList<Node>();

    while(t!=null)
    {
        if(al.contains(t))
            return true;
        al.add(t);
        t=t.next;
    }
    
    return false;
        
}
   
}
