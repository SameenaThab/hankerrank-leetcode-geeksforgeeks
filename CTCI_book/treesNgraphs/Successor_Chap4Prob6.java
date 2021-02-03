import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

class Node {
    int data;
    Node parent;
    Node left;
    Node right;
    Node(int data)
    {
    	this.data=data;
    	this.left=null;
    	this.right=null;
      this.parent=null;
    }
 }
public class  Successor_Chap4Prob6{        
  public static void main(String[] args) {
    /*
          4
      2       6
    1   3   5   7
    */
    Node root=new Node(4);
    Node left=new Node(2);
    Node leftschild1=new Node(1);
    left.left=leftschild1;
    leftschild1.parent=left;
    Node leftschild2=new Node(3);
    left.right=leftschild2;
    leftschild2.parent=left;
    root.left=left;
    left.parent=root;
    Node right=new Node(6);
    Node rightsleft=new Node(5);
    right.left=rightsleft;
    rightsleft.parent=right;
    Node rightsright=new Node(7);
    right.right=rightsright;
    rightsright.parent=right;
    root.right=right;
    right.parent=root;
    Node result2 = inOrderTraversal(left);
    System.out.println(result2==null?null:result2.data);
  }


/* 
inorder: left-curr-right
for any given node its next node/successor is the leftmost node of right subtree
But if the right subtree is null then
case 1: given node is a leftsubtree of its parent p, then p will be the next node
case 2: given node is a rightsubtree of its parent p
  So the node n is full traversed, next node has to be unvisited node on the upward direction
  We know we reach unvisited node when we go from leftNode to parent. node.data<parent.data
*/
  static Node inOrderTraversal(Node n) {
    if(n.right != null)
      return leftMostNode(n.right);
    else {
      while(n.parent != null && n.data>n.parent.data)//right subTree or not root
      {
        n=n.parent;
      }
      if(n.parent == null) // reached root, but be rightmost tree
        return null;
      return n.parent;
    }
  }

  static Node leftMostNode(Node n) {
    while(n.left != null)
      n=n.left;
    return n;
  }

}
