import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* 
SLow approach: Maintain a array of nodes and pck node randomly .
Problem is when we delete a node , node in the array is also deleted

For n nodes the propability of choosing N should be 1/n
probalility of chissing left and right tress is not 50-50 bcoz tree is not balanced.
right tree can have more nodes than left and vice versa.
So we maintain size of node
prob of left tree = leftsize*1/n +rightsize*1/n
total = 1 , there all nodes have equal probability to be selected
*/

class Node {
    int data;
    int size;
    Node left;
    Node right;
    Node(int data)
    {
        this.data=data;
        this.size = 1;
    	this.left=null;
    	this.right=null;
    }

    void insert(int value) {
        if(value < data)   {
            if(left == null)
                left = new Node(value);
            else
                left.insert(value);
        }
        else {
            if(right == null)
                right = new Node(value);
            else
                right.insert(value);
        }
        size++;
    }

    Node find(int value) {
        if(value == data) {
            return this;
        }
        else if(value < data) {
            return left.find(value);
        }
        else {
            return right.find(value);
        }
    }

    Node randomNode() {
        Random random = new Random();
        int i = random.nextInt(size);
        System.out.println("Random: "+i);
        return getIthNode(i);
    }

    Node getIthNode(int i) {
        int leftSize = left == null?0:left.size;
        if(leftSize == i)
            return this;
        else if(i<leftSize) {
            return left.getIthNode(i);
        }
        else {
            int rightIdx = i-leftSize-1;
            return right.getIthNode(rightIdx);
        }
    }

    Node delete(int value) {
        if(value == data) {
            if(left == null && right == null) {
                return null;
            }
            else if(left == null || right == null) {
                return left == null? right:left;
            }
            else {
                Node smallNode = right.smallestNode();
                data = smallNode.data;
                size--;
                right = right.delete(smallNode.data);
            }
        }
        else if(value < data) {
            left = left.delete(value);
            size--;
        }
        else {
            right = right.delete(value);
            size--;
        }  
        return this;      
    }

    Node smallestNode() {
        if(left == null)
            return this;
        return left.smallestNode();
        
    }

    void inOrderTraversal(Node root) {
        if(root == null)
            return;
        inOrderTraversal(root.left);
        System.out.println("data: "+root.data+" size: "+root.size);
        inOrderTraversal(root.right);
    }
    
 }

public class  RandomNode_Chap4Prob11 {
    /*
          4
      2       6
    1   3   5     7
                      9      
    */
        
    public static void main(String[] args) {
        RandomNode_Chap4Prob11 tree = new RandomNode_Chap4Prob11();
        Node root  = new Node(4);
        root.insert(2);
        root.insert(1);
        root.insert(3);
        root.insert(6);
        root.insert(5);
        root.insert(7);
        root.insert(9);
        root.inOrderTraversal(root);
        System.out.println("Random value: "+root.randomNode().data);
        root.delete(6);
        System.out.println("After Deletion");
        root.inOrderTraversal(root);
    }
}
