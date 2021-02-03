import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* 
rank of element in the stream = # of elements less than given element
We use BST where we keep track of # of left nodes lets call it leftSize
if elment == node.data return leftSize as rank
if element is on left side recurse rank(node.left)
if element is on right side node.leftSize+1+rank(node.right) {+1 bcoz we also count the root}
eg:
         3(leftsize = 1)
    2(0)       4(0)
Rank of 4 = 3.leftsize+1 = 2 , 2 element are less than 4
implement track() method to insert and getRankOfNumber()
*/

class RankNode {
    int data;
    RankNode left;
    RankNode right;
    int leftSize=0;
    RankNode(int data) {  
        this.data = data;
    }

    void insert(int newData) {
        if(newData<=data) {
            if(left == null)
                left = new RankNode(newData);
            else
                left.insert(newData);
            leftSize++;
        } else {
            if(right == null)
                right = new RankNode(newData);
            else
                right.insert(newData);           
        }
    }

    public int getRankOfNumber(int element) {
        if(data == element)
            return leftSize;
        else if(element < data) {
            return left == null? -1 : left.getRankOfNumber(element);
        } else {
            return right == null? -1 : leftSize+1+right.getRankOfNumber(element);
        }
    }

    void inOrder() {
        if(left!=null)
            left.inOrder();
        System.out.print(data+" ");
        if(right!=null)
            right.inOrder();
    }
}
public class  RankStream_Chap10Prob10 {
      
    RankNode root;
    public static void main(String[] args) {
        int[] stream = new int[]{20,15,10,5,13,25,23,24};
        RankStream_Chap10Prob10 sol = new RankStream_Chap10Prob10();
        for(int element : stream) {
            sol.track(element);
        }
        sol.inOrder();
        int a = 20,b=5,c=24;

        System.out.println("rank of "+a+" : "+sol.getRankOfNumber(a));
        System.out.println("rank of "+b+" : "+sol.getRankOfNumber(b));
        System.out.println("rank of "+c+" : "+sol.getRankOfNumber(c));
    }

    private void inOrder() {
        System.out.print("Inorder: ");
        root.inOrder();
        System.out.println();
    }

    private void track(int element) {
        if(root == null) {
            root = new RankNode(element);
        } else {
            root.insert(element);
        }
    }

    int getRankOfNumber(int element) {
        return root.getRankOfNumber(element);
    }
  
}
