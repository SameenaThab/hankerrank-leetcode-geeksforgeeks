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
public class  BSTSeq_Chap4Prob9{

  static void BSTSeq(Node root)
  {
    if(root == null) return;
    System.out.print(root.data+" ");
    BSTSeq(root.left);
    BSTSeq(root.right);
    // //System.out.println();
    // System.out.print(root.data+" ");
    // BSTSeq(root.right);
    // BSTSeq(root.left);
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
              BSTSeq(root);
        }
}
