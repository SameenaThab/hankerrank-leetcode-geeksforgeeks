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
public class  ListToMinHeightTree_Chap4Prob3{

	static Node listToTree(int[] arr)
	{
		int n=arr.length;
		Node root=listToTree(arr,0,n-1);
		return root;
	}

	static Node listToTree(int[] arr,int left,int right)
	{
		if(left>right)
			return null;
		int mid=(left+right)/2;
		Node root=new Node(arr[mid]);
		root.left=listToTree(arr,left,mid-1);
		root.right=listToTree(arr,mid+1,right);
		return root;
	}
        
    public static void main(String[] args) {
		int[] arr=new int[7];
		for(int i=1;i<=7;i++)
		{
			arr[i-1]=i;
		}
		Node root=listToTree(arr);
		inorderTraversal(root);
    }

    static void inorderTraversal(Node root)
    {
    	if(root!=null)
    	{
    		inorderTraversal(root.left);
    		System.out.println(root.data);
    		inorderTraversal(root.right);
    	}
    }
}
