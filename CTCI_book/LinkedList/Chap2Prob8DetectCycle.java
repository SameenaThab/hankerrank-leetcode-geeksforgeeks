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

public class Chap2Prob8DetectCycle {

	public static boolean detectCycle(Node head)	{
		Node slow=head;
		Node fast=head;
		while(slow!=null && fast !=null)
		{
			if(slow==fast)
				return true;
			slow=slow.next;
			fast=fast.next.next;
		}
		return false;
	}
	    	
    public static void main(String[] args)
    {
    	Node node1 =new Node(1);
    	Node node2=new Node(2);
    	node1.next=node2;
    	Node node3=new Node(3);
    	node2.next=node3;
    	Node node5 =new Node(5);
    	node3.next=node5;
    	node5.next=node2;
    	System.out.println(detectCycle(node1));
	}
}