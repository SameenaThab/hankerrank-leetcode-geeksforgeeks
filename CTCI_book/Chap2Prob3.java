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

public class Chap2Prob3 {

	public static boolean delete(Node mid)
	{
		if(mid == null || mid.next == null) //not a middle node
			return false;
		else
		{
			mid.data=mid.next.data;
			mid.next=mid.next.next;			
		}
		return true;
	}
	    	
    public static void main(String[] args)
    {
    	Node head =new Node(1);
    	head.insert(1);
    	head.insert(2);
    	head.insert(4);
    	head.insert(3);
    	head.insert(6);
    	Node t1=head;
    	while(t1!=null)
    	{
    		System.out.print(t1.data+"->");
    		t1=t1.next;
    	}
    	System.out.println();
    	t1=head;
    	while(t1.data!=3)
    		t1=t1.next;
    	if(delete(t1))
    	{
    		Node t2=head;
	    	while(t2!=null)
	    	{
	    		System.out.print(t2.data+"->");
	    		t2=t2.next;
	    	}
    	}
	}
}