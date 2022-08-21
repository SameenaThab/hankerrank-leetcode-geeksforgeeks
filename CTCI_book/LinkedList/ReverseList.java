import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//chapter 1 Question 7

class Node
{
	int data;
	Node next;
	Node(int data)
	{
		this.data=data;
		this.next=null;
	}

	void insert(int data)
	{
		Node t=this;
		while(t.next!=null)
		{
			t=t.next;
		}
		Node n=new Node(data);
		t.next=n;
	}
}

public class ReverseList {

	public static Node reverse(Node head)
	{
        Node prev = null; 
        Node current = head; 
        Node next = null; 
        while (current != null) { 
            next = current.next; 
            current.next = prev; 
            prev = current; 
            current = next; 
        } 
        return prev;
	}
	    	
    public static void main(String[] args)
    {
    	Node head =new Node(1);
    	head.insert(2);
    	head.insert(3);
    	// head.insert(4);
    	// head.insert(6);
    	Node t1=head;
    	while(t1!=null)
    	{
    		System.out.print(t1.data+"->");
    		t1=t1.next;
    	}
    	System.out.println();
    	head=reverse(head);
		Node t2=head;
    	while(t2!=null)
    	{
    		System.out.print(t2.data+"->");
    		t2=t2.next;
    	}
	}
}