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

	public void print() {
		Node t = this;
		while(t!=null){
			System.out.print(t.data+"->");
			t=t.next;
		}
		System.out.println();
	}
}

public class Chap2Prob5 {

	public static Node sum(Node head1,Node head2)
	{
		Node t1=head1;
		Node t2=head2;
		Node result = new Node(-1);
		Node t3=result;
		int cary=0;
		while(t1!=null && t2 != null) {
			int sum=t1.data+t2.data+cary;
			Node temp=new Node(sum%10);
			cary=sum/10;
			t3.next=temp;
			t3=t3.next;
			t1=t1.next;
			t2=t2.next;
		}
		while(t1!=null){
			int sum=t1.data+cary;
			Node temp=new Node(sum%10);
			cary=sum/10;
			t3.next=temp;
			t3=t3.next;		
			t1=t1.next;	
		}
		while(t2!=null){
			int sum=t2.data+cary;
			Node temp=new Node(sum%10);
			cary=sum/10;
			t3.next=temp;
			t3=t3.next;	
			t2=t2.next;		
		}
		return result.next;
	}
	    	
    public static void main(String[] args)
    {
		//617
		Node n1 = new Node(7);
		n1.insert(1);
		n1.insert(6);
		//295
		Node n2 = new Node(5);
		n2.insert(9);
		n2.insert(2);
		Node n3 = sum(n1,n2);
		n3.print();
	}
}