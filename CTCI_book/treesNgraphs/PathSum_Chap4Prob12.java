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
public class  PathSum_Chap4Prob12 {
  Node root;
  int count;
  PathSum_Chap4Prob12(Node root) {
      this.root = root;
      this.count = 0;
  }
  public static void main(String[] args) {
    /*
            10
        -1       3
       5   1    2    6
    4  
    */
    Node root=new Node(10);
    Node left=new Node(-1);
    Node leftschild1=new Node(5);
    leftschild1.left = new Node(4);
    left.left=leftschild1;
    Node leftschild2=new Node(1);
    left.right=leftschild2;
    root.left=left;
    Node right=new Node(3);
    Node rightsleft=new Node(2);
    right.left=rightsleft;
    Node rightsright=new Node(6);
    right.right=rightsright;
    root.right=right;
    PathSum_Chap4Prob12 sol = new PathSum_Chap4Prob12(root);
    sol.pathSum(root, 0, 9);
    System.out.println("No of Paths: "+sol.count);
  }

  void pathSum(Node root,int sum,int path) {
    if(root == null)
        return;
    if(sum+root.data == path)
        count++;
    pathSum(root.left,sum+root.data,path);
    pathSum(root.right,sum+root.data,path);
    pathSum(root.left,sum,path);
    pathSum(root.right,sum,path);
  }

}
