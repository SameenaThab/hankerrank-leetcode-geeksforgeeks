
import java.util.*;

class Node {
	Node next;
	int data;
	Node(int data) {
	this.data= data;
	this.next = null;
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


class SumNode {
	Node sum;
	int cary;
	SumNode(Node node,int cary) {
		this.sum=node;
		this.cary=cary;
	}
}
class Practise {
	public static boolean isPalindrome(Node head)
	{
		Stack<Integer> st  = new Stack<Integer>();
		Node slow=head;
		Node fast=head;
		while(fast!=null && fast.next!=null) { // Will reach the mid - node
			st.push(slow.data);
			slow=slow.next;
			fast=fast.next.next;
		}
		if(fast!=null) { // odd length of nodes
			slow=slow.next;
		}
		while(slow != null) {
			if(slow.data != st.pop())
				return false;
			slow=slow.next;
		}
		return true;	
	}

	public static void main(String[] args) {
		Node n1 = new Node(1);
		n1.insert(2);
		n1.insert(2);
		n1.insert(1);
		System.out.println("should be true: "+isPalindrome(n1));
		n1 = new Node(1);
		n1.insert(2);
		n1.insert(3);
		n1.insert(2);
		n1.insert(1);
		System.out.println("should be true: "+isPalindrome(n1));
		n1 = new Node(1);
		n1.insert(2);
		n1.insert(3);
		System.out.println("should be false: "+isPalindrome(n1));
	}
}