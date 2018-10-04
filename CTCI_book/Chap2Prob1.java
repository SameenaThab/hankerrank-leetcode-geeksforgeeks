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

public class Chap2Prob1 {

	//O(n) time and O(n) space
	static void removeDuplicates(Node head)
	{
		HashSet<Integer> hs=new HashSet<Integer>();
		Node pre=head;
		Node t=head.next;
		hs.add(head.data);
		while(t!=null)
		{
			if(hs.contains(t.data))
			{
				pre.next=t.next;
			}
			else
			{
				hs.add(t.data);
				pre=pre.next;				
			}
			t=t.next;
		}
	}

	//O(n^2)time and O(1) space
	static void removeDuplicates2(Node head)
	{
		Node t=head;
		while(t!=null)
		{
			Node t2=t.next;
			Node pre = t;
			while(t2!=null)
			{
				if(t.data == t2.data)
				{
					pre.next=t2.next;
				}
				else
				{
					pre=pre.next;
				}
				t2=t2.next;
			}
			t=t.next;
		}
	}

    public static void main(String[] args)
    {
    	Node head =new Node(1);
    	head.insert(2);
    	head.insert(2);
    	head.insert(3);
    	head.insert(4);
    	head.insert(3);
    	Node t1=head;
    	while(t1!=null)
    	{
    		System.out.print(t1.data+"->");
    		t1=t1.next;
    	}
    	System.out.println();
    	removeDuplicates2(head);
    	Node t=head;
    	while(t!=null)
    	{
    		System.out.print(t.data+"->");
    		t=t.next;
    	}
	}
}