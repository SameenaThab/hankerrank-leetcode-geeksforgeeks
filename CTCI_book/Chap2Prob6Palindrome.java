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

class Result{
	boolean val;
	Node node;
	Result(Node node,boolean val)
	{
		this.node=node;
		this.val=val;
	}
}

public class Chap2Prob6Palindrome {

	public static boolean isPalindrome(Node head)	{
		Node slow=head;
		int length = 0;
		while(slow!=null)
		{
			slow=slow.next;
			length++;
		}

		Result r=isPalindrome(head,length);
		return r.val;
	}

	public static Result isPalindrome(Node head,int length)
	{
		if(head==null || length<=0) //even nodes head==null case for list of 1 length
			{
				return new Result(head,true);
			}
		if(length == 1)
			return new Result(head.next,true);
		Result result=isPalindrome(head.next,length-2);
		if(result.val == false || result.node == null)
			return result;
		result.val=result.node.data==head.data;
		result.node=result.node.next;
		return result;
	}
	    	
    public static void main(String[] args)
    {
    	Node head1 =new Node(3);
    	head1.insert(2);
    	head1.insert(3);
    	Node head2 =new Node(3);
    	head2.insert(2);
    	head2.insert(1);
    	System.out.println(isPalindrome(head1));
	}
}