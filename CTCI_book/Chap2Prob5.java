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

public class Chap2Prob5 {

	public static Node add(Node head1,Node head2)
	{
		int cary=0;
		int data=(head1.data+head2.data+cary)%10;
		Node sum=new Node(data);
		cary=(head1.data+head2.data+cary)/10;
		head1=head1.next;
		head2=head2.next;
		Node t=sum;
		while(head1!=null && head2!=null)
		{
			data=(head1.data+head2.data+cary)%10;
			cary=(head1.data+head2.data+cary)/10;
			t.next=new Node(data);
			t=t.next;
			head1=head1.next;
			head2=head2.next;
		}
		if(head1!=null)
			t.next=new Node(head1.data+cary);
		if(head2!=null)
			t.next=new Node(head2.data+cary);
		return sum;
	}
	    	
    public static void main(String[] args)
    {
    	Node head1 =new Node(1);
    	head1.insert(2);
    	head1.insert(3);
    	head1.insert(4);
    	Node head2 =new Node(5);
    	head2.insert(6);
    	head2.insert(7);
    	// Node t1=head;
    	// while(t1!=null)
    	// {
    	// 	System.out.print(t1.data+"->");
    	// 	t1=t1.next;
    	// }
    	System.out.println();
    	Node sum=add(head1,head2);
		Node t2=sum;
    	while(t2!=null)
    	{
    		System.out.print(t2.data+"->");
    		t2=t2.next;
    	}
	}
}