import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
0: 1
1: 2
2: 0,3
3: 2
4: 6
5: 4
6: 5
*/

class TreeNode {
    int value;
    TreeNode left,right;

    TreeNode(int val) {
        this.value = val;
        this.left = null;
        this.right = null;
    }
}
public class ListOfDepths_chap4Prob3 {

    TreeNode root;

    public  List<List<Integer>> listOfDepths() {

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        listOfDepths(root,result,0);
        return result;
    }

    public void listOfDepths(TreeNode root,List<List<Integer>> result, int level ) {
        if(root == null)
            return;
        if(result.size() == level)
            result.add(new ArrayList<Integer>());
        result.get(level).add(root.value);
        listOfDepths(root.left,result,level+1);
        listOfDepths(root.right,result,level+1); 
    }

    class Pair {
        TreeNode node;
        int level;
        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public List<List<Integer>> listOfDepthsBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        while(!queue.isEmpty()) {
            int length = queue.size();
            List<Integer> subResult = new ArrayList<Integer>();
            for(int i=0;i<length;i++) {
                TreeNode currNode = queue.poll();
                subResult.add(currNode.value);
                if(currNode.left != null) queue.add(currNode.left);
                if(currNode.right != null) queue.add(currNode.right);
            }
            result.add(subResult);
        }
        return result;
    }
        
    public static void main(String[] args) {
        /*
                        0
                1               2
            8       9       10      12
        */

        ListOfDepths_chap4Prob3 sol = new ListOfDepths_chap4Prob3();
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        TreeNode leftLeft = new TreeNode(8);
        TreeNode leftRight = new TreeNode(9);
        left.left = leftLeft;
        left.right = leftRight;
        TreeNode rightLeft = new TreeNode(10);
        TreeNode rightRight = new TreeNode(12);
        right.left = rightLeft;
        right.right = rightRight;
        root.left = left;
        root.right = right;
        sol.root = root;
        List<List<Integer>> result = sol.listOfDepths();

        for(List<Integer> list : result) {
            for(Integer ele : list) {
                System.out.print(ele+" ");
            }
            System.out.println();
        }


        List<List<Integer>> result2 = sol.listOfDepthsBFS(root);

        for(List<Integer> list : result2) {
            System.out.println(list.toString());
        }
    }
}


