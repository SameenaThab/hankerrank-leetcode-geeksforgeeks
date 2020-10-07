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
public class  CheckBST_Chap4Prob5{

  static boolean checkBST(Node root,Integer min,Integer max)
  {
    if(null==root)
      return true;
    if((min!=null && root.data<=min)||(max!=null && root.data > max))
      return false;
    return checkBST(root.left,min,root.data) && checkBST(root.right,root.data,max);
  }

  static boolean checkBST2(Node root,Integer min,Integer max) {
    if(null==root)
      return true;
    if(root.data<=min||root.data > max)
      return false;
    return checkBST(root.left,min,root.data) && checkBST(root.right,root.data,max);
  }

	static boolean checkBST(Node root)
	{
    // return checkBST(root,null,null);
    return checkBST2(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
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
              System.out.println(checkBST(root));
        }
}
