import java.util.*;

class GraphNode {
    String name;
    Status status;
    List<GraphNode> neighbors;
    GraphNode(String name) {
        this.name = name;
        this.neighbors = new ArrayList<GraphNode>();
        this.status = Status.BLANK;
    }
    public void addEdge(GraphNode node){
        neighbors.add(node);
    }
}

enum Status {
    PROCESSING,COMPLETED,BLANK
}

class Graph {
    HashMap<String,GraphNode> nodes;
    public Graph(String[] project_names,String[][] dependencies) {
        this.nodes = new HashMap<String,GraphNode>();
        for(String name:project_names) {
            nodes.put(name,new GraphNode(name));
        }
        for(String[] dependency:dependencies) {
            this.nodes.get(dependency[0]).addEdge(this.nodes.get(dependency[1]));
        }
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    // int size;
    TreeNode(int data){
        this.data=data;
        this.left=null;
        this.right=null;
        // this.size=1;
    }
    TreeNode(int data,TreeNode parent){
        this.data=data;
        this.left=null;
        this.right=null;
        // this.parent=parent;
    }
    void inOrderTraversal() {
        if(this.left != null)
            this.left.inOrderTraversal();
        System.out.print(this.data+" ");
        if(this.right != null)
            this.right.inOrderTraversal();
    }
}


class Result {
    TreeNode node;
    boolean isanncestor;
    Result(TreeNode node,boolean isanncestor) {
        this.node=node;
        this.isanncestor=isanncestor;
    }
}

public class Practise {
    public static void main(String[] args) {
        TreeNode root=new TreeNode(4);
        TreeNode left=new TreeNode(3);
        left.left=new TreeNode(1);
        left.right=new TreeNode(2);
        root.left=left;
        TreeNode right = new TreeNode(6);
        right.left=new TreeNode(5);
        right.right=new TreeNode(7);
        root.right=right;
        ArrayList<LinkedList<Integer>> result = getSequences(root);
        for(LinkedList<Integer> list : result) {
            System.out.println(list.toString());
        }
    }

    public static ArrayList<LinkedList<Integer>> getSequences(TreeNode root) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
        if(root == null){
            result.add(new LinkedList<Integer>());
            return result;
        }
        LinkedList<Integer>  prefix = new LinkedList<Integer>();
        prefix.addFirst(root.data);
        ArrayList<LinkedList<Integer>> leftSeq = getSequences(root.left);
        ArrayList<LinkedList<Integer>> rightSeq = getSequences(root.right);
        for(LinkedList<Integer> left:leftSeq) {
            for(LinkedList<Integer> right:rightSeq) {
                weave(left,right,result,prefix);
            }
        }
        return result;
    }

    public static void weave(LinkedList<Integer> left,LinkedList<Integer> right,ArrayList<LinkedList<Integer>> results,LinkedList<Integer> prefix) {
        if(left.size()==0||right.size()==0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(left);
            result.addAll(right);
            results.add(result);
            return;
        }
        int leftHead = left.removeFirst();
        prefix.addLast(leftHead);
        weave(left,right,results,prefix);
        prefix.removeLast();
        left.addFirst(leftHead);

        int rightHead = right.removeFirst();
        prefix.addLast(rightHead);
        weave(left,right,results,prefix);
        prefix.removeLast();
        right.addFirst(rightHead);
    }
}
