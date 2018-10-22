import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
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

class Result
  {
    Node t;
    boolean isanncestor;
    Result(Node t,boolean isanncestor)
    {
      this.t=t;
      this.isanncestor=isanncestor;
    }
  }
public class  CommonAncestor_Chap4Prob8{



/*
    4
  2   5
  */
  static Result commonAncestor2(Node root,Node node1,Node node2)
  {
    if(root==null) return new Result(null,false);
    if(root==node1 && root==node2)
      return new Result(root,true);
    Result leftResult=commonAncestor2(root.left,node1,node2);
    if(leftResult.isanncestor)
      return leftResult;
    Result rightResult=commonAncestor2(root.right,node1,node2);
    if(rightResult.isanncestor)
      return rightResult;
    if(leftResult.t!=null && rightResult.t!=null) //in this case root will be the common ancestor
      return new Result(root,true);
    else if(root==node1 || root == node2) //one node is subtree of other
      {
        boolean isanncestor=leftResult.t!=null||rightResult.t!=null;
        return new Result(root,isanncestor);
      }
    else
    {
      return new Result(leftResult.t!=null?leftResult.t:rightResult.t,false);//when both are nulls
    }

  }

  static Node commonAncestor(Node root,Node node1,Node node2)
  {
    if(descendent(node1,node2))
      return node1;
    else if(descendent(node2,node1))
      return node2;
    if(descendent(root.left,node1) && descendent(root.left,node2))
      return commonAncestor(root.left,node1,node2);
    else if(descendent(root.right,node1) && descendent(root.right,node2)) 
      return commonAncestor(root.right,node1,node2);
    return root;
  }

	static boolean descendent(Node parent,Node child)
	{
    if(parent==null)
      return false;
    else if(parent==child)
      return true;
    else
      return descendent(parent.left,child) || descendent(parent.right,child);
	}
        
    public static void main(String[] args) {
              Node root=new Node(4);
              Node left=new Node(2);
              Node leftschild1=new Node(1);
              left.left=leftschild1;
              Node leftschild2=new Node(3);
              left.right=leftschild2;
              root.left=left;
              Node right=new Node(6);
              Node rightsleft=new Node(5);
              right.left=rightsleft;
              Node rightsright=new Node(7);
              right.right=rightsright;
              root.right=right;
              Node last=new Node(9);
              rightsright.right=last;
              System.out.println(commonAncestor(root,root,left).data);
              System.out.println(commonAncestor2(root,root,left).t.data);
        }
}
