
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
class Chap2Prob5Extension {

/*
	Digits are in the correct order in the linked list, one lists can be of different lenghts
	eg: n=123 => linked list = 1->2->3
	Make the linked lists equal in length by padding 0's to the shorter list.No. of zeros = difference in length
	eg: h1= 1->2->3->4, h2= 7->8 => h2 = 0->0->7->8
	As digits at 1's place are at end of the list , we do the sum recursively so that, 
	we get the cary from the recursive call of the next node (digits at lower 10's place)
	We insert the current sum of nodes and cary to the beginning of the list returned from recursive call
	We use wrapper class SumNode to get both the cary and sum linkedList Node
*/
	public static Node sum(Node head1,Node head2)
	{
		int l1=len(head1);
		int l2=len(head2);
		if(l1<l2) {
			head1=pad(head1,l2-l1);
		} else {
			head2=pad(head2,l1-l2);
		}
		head1.print();
		head2.print();
		// l1<l2?pad(head1,l2-l1):pad(head2,l1-l2);
		SumNode sumNode = sumRecursive(head1,head2);
		if(sumNode.cary!=0) {
			sumNode.sum=insertAtStart(sumNode.sum,sumNode.cary);
		}
		return sumNode.sum;
	}

	public static SumNode sumRecursive(Node head1,Node head2) {
		if( head1==null || head2==null) {
			return new SumNode(null,0);
		}
		SumNode prev_sum = sumRecursive(head1.next,head2.next);
		int curr_sum = head1.data+head2.data+prev_sum.cary;
		Node curr_sum_node=insertAtStart(prev_sum.sum,curr_sum%10);
		return new SumNode(curr_sum_node,curr_sum/10) ;
	}
	public static int len(Node node) {
		int count=0;
		Node t = node;
		while(t!=null){
			t=t.next;
			count++;
		}
		return count;
	}

	public static Node pad(Node node,int padding) {
		for(int i=0;i<padding;i++){
			node = insertAtStart(node,0);
		}
		return node;
	}

	public static Node insertAtStart(Node node,int val) {
		Node head = new Node(val);
		head.next=node;
		return head;		
	}

	public static void main(String[] args) {
		//617
		Node n1 = new Node(9);
		n1.insert(9);
		n1.insert(9);
		n1.insert(9);
		//295
		Node n2 = new Node(1);
		// n2.insert(6);
		// n2.insert(7);
		Node n3 = sum(n1,n2);
		n3.print();
	}
}