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


public class Chap2Prob7Intersection {

	public static Node isIntersect(Node head1,Node head2)	{
		int len1=0;
		int len2=0;
		Node t1=head1;
		Node t2=head2;
		while(t1.next!=null)
		{
			t1=t1.next;
			len1++;
		}
		while(t2.next!=null)
		{
			t2=t2.next;
			len2++;
		}
		if(t1!=t2)
			return null;
		Node longer=len1>len2?head1:head2;
		Node shorter=len1<len2?head1:head2;
		//move longer to len2-len1 times,making longer and shorter equal
		int diff=Math.abs(len1-len2);
		while(diff!=0)
		{
			longer=longer.next;
			diff--;
		}
		while(shorter!=longer)
		{
			shorter=shorter.next;
			longer=longer.next;
		}
		return longer;
	}
	    	
    public static void main(String[] args)
    {
    	Node node1 =new Node(1);
    	Node node2 =new Node(2);
    	Node node3 =new Node(3);
    	Node node4 =new Node(4);
    	Node node5 =new Node(5);
    	Node node6 =new Node(6);
    	Node node7 =new Node(7);
    	Node node8 =new Node(8);
    	Node head1=node1;
    	node1.next=node2;
    	node2.next=node3;
    	node3.next=node4;
    	node4.next=node7;
    	node7.next=node8;
    	Node head2=node5;
    	node5.next=node6;
    	node6.next=node7;
    	Node in=isIntersect(head1,head2);
    	Node t2=in;
    	while(t2!=null)
    	{
    		System.out.print(t2.data+"->");
    		t2=t2.next;
    	}
	}
}