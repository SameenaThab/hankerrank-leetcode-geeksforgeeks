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
public class  SubTree_Chap4Prob10 {

    public boolean subTree(Node node1, Node node2) {
        if(node1 == null || node2 == null)
            return false;
        if(isIdentical(node1, node2))
            return true;
        else
            return subTree(node1.left,node2) || subTree(node1.right,node2);
    }

    public boolean isIdentical(Node node1, Node node2) {
        if(node1 == null && node2 == null)
            return true;
        if(node1 == null || node2 == null)
            return false;
        if(node1.data != node2.data)
            return false;
        return isIdentical(node1.left,node2.left) && isIdentical(node1.right,node2.right);
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

            Node root2 = new Node(6);
            root2.left = new Node(5);
            root2.right = new Node(7);
            SubTree_Chap4Prob10 sol = new SubTree_Chap4Prob10();
            System.out.println(sol.subTree(root,root2));
  }
}
