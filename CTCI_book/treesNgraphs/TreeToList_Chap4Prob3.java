import java.util.*;
import java.text.*;
import java.math.*;

import java.io.*;

class Node {
    int data;
    Node left;
    Node right;
    Node(int data)
    {
    	this.data=data;
    	this.left=null;
    	this.right=null;
    }
 }
public class  TreeToList_Chap4Prob3{

	// static LinkedList<LinkedList<Node>> treeToList(Node root)
	// {
	// 	LinkedList<LinkedList<Node>> lists=new LinkedList<LinkedList<Node>>();
	// 	lists.add(new LinkedList<Node>());
	// 	lists.get(0).add(root);
	// 	LinkedList<Node> current=lists.get(0);
	// 	while(current.size()!=0)
	// 	{
	// 		LinkedList<Node> temp=new LinkedList();
	// 		for(Node n:current)
	// 		{
	// 			if(n.left!=null) temp.add(n.left);
	// 			if(n.right!=null) temp.add(n.right);
	// 		}
	// 		current=temp;
	// 		lists.add(temp);
	// 	}
	// 	return lists;
	// }

	static LinkedList<LinkedList<Node>> treeToList(Node root) {
		LinkedList<LinkedList<Node>> lists=new LinkedList<LinkedList<Node>>();
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while(!queue.isEmpty()){
			int len = queue.size();
			LinkedList<Node> list = new LinkedList<Node>();
			for(int i=0;i<len;i++){
				Node curr = queue.poll();
				list.add(curr);
				if(curr.left != null) queue.add(curr.left);
				if(curr.right != null) queue.add(curr.right);
			}
			lists.add(list);
		}
		return lists;
	}
        
    public static void main(String[] args) {
              Node root=new Node(4);
              Node left=new Node(2);
              left.left=new Node(1);
              left.right=new Node(3);
              root.left=left;
              Node right=new Node(6);
              right.left=new Node(5);
              Node last_but_one=new Node(7);
              last_but_one.right=new Node(9);
              right.right=last_but_one;
              root.right=right;
              LinkedList<LinkedList<Node>> lists=treeToList(root);
              for(LinkedList<Node> list:lists)
              {
	              for(Node n:list)
	              {
	              	System.out.print(n.data+" ");
	              }
	              System.out.println();
              }
        }
}
