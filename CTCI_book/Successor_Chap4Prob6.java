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

  static Node successor(Node node)
  {
    if(node.parent==null)
      return leftMost(node.right);
    Node q=node;
    Node x=q.parent;
    while(x!=null && x.left!=q)
    {
      q=x;
      x=x.parent;
    }
    return x;
  }

  static Node leftMost(Node node)
  {
    while(node.left!=null)
      node=node.left;
    return node;
  }

        
    public static void main(String[] args) {
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
              System.out.println(successor(leftschild2).data);
        }
}
