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
public class  CheckBalance_Chap4Prob4{

	static int checkBalance(Node root)
	{
		if(null == root)
			return 0;
        int leftHeight = checkBalance(root.left);
        if(leftHeight == Integer.MIN_VALUE) // whwen left tree not balanced
            return Integer.MIN_VALUE;
        int rightHeight = checkBalance(root.right);
        if(rightHeight == Integer.MIN_VALUE) // when right tree not balanced
            return Integer.MIN_VALUE; 
        if(Math.abs(leftHeight-rightHeight) > 1)
            return Integer.MIN_VALUE; // tree not balanced
        return Math.max(leftHeight,rightHeight)+1;
    }
        

    public static void main(String[] args) {
        /*
            4
        2           6
    1       3     5     7
                            9

        */
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
            boolean result = checkBalance(root)==Integer.MIN_VALUE? false:true;
            System.out.println("checkBalance: "+result);
        }
}
