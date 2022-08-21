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

class Index{
	public int val=0;
}

public class Chap2Prob2 {

	// Time = O(n) Space=O(1)
	public static Node findKthFromLastOptimal(Node head,int k) {
		Node slow = head;
		Node fast = head;
		int i=0;
		for(i=0;i<k&&fast!=null;i++) {
			fast = fast.next;
		}
		if(i!=k){
			return new Node(-1);
		}
		while(fast!=null){
			slow=slow.next;
			fast=fast.next;
		}
		return slow;
	}

//straightforward way O(n)
	static int kFromLast(Node head,int k)
	{
		int n=0;
		Node t=head;
		while(t!=null)
		{
			n++;
			t=t.next;
		}
		int index=n-k+1;
		t=head;
		int count=1;
		if(index>n)
			return -1;
		while(t!=null && count!=index)
		{
			t=t.next;
			count++;
		}
		return t.data;
	}

//recursive way O(n) space O(n/2)
	static Node kFromLast2(Node head,int k,Index ind)
	{
		if(head == null)
			return null;
		Node h=kFromLast2(head.next,k,ind);  // eg: this will return last element when k = 1;
		ind.val++;
		//System.out.println(ind);
		if(ind.val == k) 
			return head;
		return h;
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
    	System.out.println(kFromLast(head,3));
    	Index ind = new Index();
    	Node h=kFromLast2(head,3,ind);
    	System.out.println(h.data);
	}
}