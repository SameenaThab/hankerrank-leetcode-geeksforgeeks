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

	public void insert(int data) {
		Node t = this;
		while(t.next!=null){
			t=t.next;
		}
		t.next=new Node(data);
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

public class Chap2Prob1 {

	//O(n) time and O(n) space
	public static void removeDuplicates(Node head) {
		HashSet<Integer> hs=new HashSet<Integer>();
		Node t = head;
		Node prev = null;
		while(t!=null){
			if(hs.contains(t.data)) {
				// No need to increment prev to next because, if the next element is duplicate then we need to remove again i.e., change prev.next=t.next
				// else it gets incremented in else case
				prev.next=t.next;
			} else {
				hs.add(t.data);
				prev=t;
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
		Node head = new Node(4);
    	head.insert(2);
    	head.insert(1);
    	head.insert(3);
    	head.insert(4);
    	head.insert(3);
    	head.print();
    	System.out.println("after Removal of Duplicates");
    	removeDuplicates(head);
    	head.print();
	}
}