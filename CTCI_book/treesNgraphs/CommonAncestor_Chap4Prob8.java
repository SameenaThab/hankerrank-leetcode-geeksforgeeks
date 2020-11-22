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
public class  CommonAncestor_Chap4Prob8 {

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
        boolean isanncestor=leftResult.t!=null||rightResult.t!=null; //to check one node is subtree of other
        return new Result(root,isanncestor);
      }
    else
    {
      return new Result(leftResult.t!=null?leftResult.t:rightResult.t,false);//when both are nulls
    }

  }
  static Node commonAncestor(Node root,Node node1,Node node2) {
    if(!descendent(root,node1) || !descendent(root,node2))
      return null;
    return  commonAncestorHelper(root,node1,node2);
  }
  static Node commonAncestorHelper(Node root,Node node1,Node node2)
  {
    if(descendent(node1,node2))
      return node1;
    else if(descendent(node2,node1))
      return node2;
    boolean node1ExistsLeft = descendent(root.left,node1);
    boolean node2ExistsLeft = descendent(root.left,node2);
    if(node1ExistsLeft != node2ExistsLeft) // both nodes exits on diff sides
      return root;
    if(node1ExistsLeft)// both exists on left
      return commonAncestorHelper(root.left,node1,node2);
    else
      return commonAncestorHelper(root.right,node1,node2);
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
      /*
          4
      2       6
    1   3   5     7
                      9      
      */
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
      Node ancestor = commonAncestor(root,rightsleft,new Node(10));
      if(ancestor != null)
        System.out.println(ancestor.data);
      Result result = commonAncestor2(root,rightsleft,new Node(10));
      System.out.println(result.t.data+" "+result.isanncestor);
    }
}
