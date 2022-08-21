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

public class Chap2Prob8DetectLoopStart {

	public static Node getLoopStart(Node head)	{
		Node slow=head;
		Node fast=head;
		while(fast != null && fast.next != null) {
			fast=fast.next.next;
			slow=slow.next;
			if(slow == fast){
				break; // collision point
			}
		}

		if(fast== null || fast.next == null) {
			//no loop
			System.out.println("no Loop");
			return null;
		}

		/*

        Lets say Collision point is y nodes away from Loop Start ,
	        - Bcoz before collision point, if slow took x steps, fast took 2x steps, 
	        - if slow is at Loop-Start after x steps, 
	        - then fast is already in the loop with 2x steps
	        => fast is 2x-x = x steps in the loop 
	        - So fat and slow are LoopSize-x node apart

		Once slow and fast are both in the loop, they catch up at speed one step at a time
			bcoz for every 2 steps fast moves closer to slow, slow moves 1 step away => 2-1 = 1 step at a time
			So slow meets fast after after LoopSize-x steps

		Lets say slow and fast meet at a point in the loop called collision point which y nodes away from loop start
		slow covered x steps from the linkedListHead point
		x=y+C*LoopSize (C>=0)  that is fast made C rounds on the loop , before slow made x steps to get the loop start 
		x (proportional to) y for eg, if node N is 2 nodes(y) into 5 node(loop-size) loop, then we can also say is 7(2+1*5), 12(2+2*5),397(2+5*(395/5)) nodes into loop
		There x can be any of [7,12,397]

		Once Collision point is found
		- If we bring slow to the beginning of the list
        - And fast at the collision point
        - Then move both slow and fast 1 node at a time, then slow and fast will meet at Loop Start. 
		*/

        slow = head;
        while(slow==fast) { //loop start
        	slow=slow.next;
        	fast=fast.next;
        }
        return fast;
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

    	Node loop_start = getLoopStart(node1);
    	System.out.println("loop_start: "+loop_start.data);
	}
}