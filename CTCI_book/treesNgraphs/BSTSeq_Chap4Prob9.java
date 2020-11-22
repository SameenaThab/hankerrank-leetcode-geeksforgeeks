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
public class  BSTSeq_Chap4Prob9 {

  Node root;

  public List<LinkedList<Integer>> bSTSeq(Node root)
  {
    List<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
    if(root == null) {
      result.add(new LinkedList<>());
      return result;
    }
    LinkedList<Integer> prefix = new LinkedList<Integer>();
    prefix.add(root.data);
    List<LinkedList<Integer>> leftLists = bSTSeq(root.left);
    List<LinkedList<Integer>> rightLists = bSTSeq(root.right);
    for(LinkedList<Integer> left:leftLists) {
      for(LinkedList<Integer> right:rightLists) {
        weaveLists(left,right,result,prefix);
      }
    }
    return result;
  }

  public void weaveLists(LinkedList<Integer> first,LinkedList<Integer> second, List<LinkedList<Integer>> result,LinkedList<Integer> prefix) {
    if(first.size() == 0 || second.size() == 0) {
      LinkedList<Integer> prefixClone = (LinkedList<Integer>) prefix.clone();
      prefixClone.addAll(first);
      prefixClone.addAll(second);
      result.add(prefixClone);
      return;
    }
    Integer firstHead = first.removeFirst();
    prefix.addLast(firstHead);
    weaveLists(first,second,result,prefix);
    first.addFirst(firstHead);
    prefix.removeLast();

    Integer secondHead = second.removeFirst();
    prefix.addLast(secondHead);
    weaveLists(first,second,result,prefix);
    second.addFirst(secondHead);
    prefix.removeLast();
  }
        
  public static void main(String[] args) {
            Node root=new Node(4);
            Node left=new Node(2);
            left.left=new Node(1);
            left.right=new Node(3);
            root.left=left;
            Node right=new Node(6);
            // right.left=new Node(5);
            // Node last_but_one=new Node(7);
            // last_but_one.right=new Node(9);
            // right.right=last_but_one;
            root.right=right;
            BSTSeq_Chap4Prob9 sol = new BSTSeq_Chap4Prob9();
            sol.root = root;
            List<LinkedList<Integer>> result = sol.bSTSeq(root);
            for(LinkedList<Integer> list:result) {
              System.out.println(list.toString());
            }
  }
}
