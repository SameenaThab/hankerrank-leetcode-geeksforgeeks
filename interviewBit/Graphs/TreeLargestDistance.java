import java.util.*;
import java.text.*;
import java.math.*;
import java.security.KeyStore.Entry;
import java.util.regex.*;
import java.io.*;

class Node {
    int index;
    List<Node> children;

    Node(int index) {
        this.index = index;
        this.children = new ArrayList<Node>();
    }
}

/* 
        0
    1   2   3
                4
For above tree largest distance will be for path(1-0-3-4) = 3.

We know that largest distance has to pass from root.
We will have a global  variable 'node' and we keep updating node to popped TreeNode from the queue as we do BFS
First we call BFS on root, then node will become the last leaf node,
In the above example bfs(0) => node = 4
Then we do BFS on node bfs(4) , we update node = 1, as it is last leaf node.

Basically we travel to the last leaf node, then traverse back to leaf node on other side
*/

public class TreeLargestDistance {

    int n;
    int root;
    int node;
    Map<Integer,ArrayList<Integer>> nodes;

    public static void main(String[] args) {
        TreeLargestDistance sol = new TreeLargestDistance();
        /* 
         A = [-1, 0, 0, 0, 3]
        */
        
        ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(-1, 0, 0, 0, 3));
        System.out.println("Solution: "+sol.solve(arr));
    }

    public int bfs(HashMap<Integer, ArrayList<Integer>> hm, int root) {
        int dist[] = new int[hm.size()];
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(root);
        dist[root] = 1; // we substract 1 from final ans
        while(!q.isEmpty()) {
            node = q.remove();
            ArrayList<Integer> temp = hm.get(node);
            for(int i=0; i<temp.size(); i++) {
                if(dist[temp.get(i)] == 0) {
                    q.add(temp.get(i));
                    dist[temp.get(i)] = dist[node] + 1;
                }
            }
        }
        return dist[node];
    }
    public int solve(ArrayList<Integer> A) {
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<Integer, ArrayList<Integer>>();
        for(int i=0; i<A.size(); i++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            hm.put(i, temp);
        }
        int root = 0;
        for(int i=0; i<A.size(); i++) {
            if(A.get(i) == -1) {
                root = i;
            }
            else {
                hm.get(i).add(A.get(i));
                hm.get(A.get(i)).add(i);
            }
        }
        node = 0;
        bfs(hm, root);
        return bfs(hm,node) - 1;
    }

    // public int solve(ArrayList<Integer> arr) {
    //     this.n = arr.size();
    //     if(arr.size() == 0)
    //         return 0;
    //     this.nodes = new HashMap<Integer,ArrayList<Integer>>();
    //     for(int i=0;i<n;i++) {
    //         nodes.put(i,new ArrayList<Integer>());
    //     }
    //     for(int i=0;i<n;i++) {
    //         if(arr.get(i) == -1) {
    //             this.root = i;
    //         }
    //         else {
    //             nodes.get(i).add(arr.get(i));
    //             nodes.get(arr.get(i)).add(i);
    //         }
    //     }   
    //     return bfs(root)-1;        
    // }

    // int bfs(int root) {
    //     int[] dist = new int[n];
    //     Queue<Integer> queue = new LinkedList<Integer>();
    //     queue.add(root);
    //     dist[root]=1;
    //     while(!queue.isEmpty()) {
    //         node = queue.poll(); 
    //         for(int child:nodes.get(node)) {
    //             if(dist[child] == 0) {
    //                 dist[child]=dist[node]+1; 
    //                 queue.add(child);
    //             }
    //         }
    //     }
    //     return dist[node];
    // }


    // public int solve(ArrayList<Integer> arr) {
    //     this.n = arr.size();
    //     if(arr.size() == 0)
    //         return 0;
    //     Map<Integer,Node> nodes = new HashMap<Integer,Node>();
    //     for(int i=0;i<n;i++) {
    //         nodes.put(i,new Node(i));
    //     }
    //     Node root = null;
    //     for(int i=0;i<n;i++) {
    //         if(arr.get(i) == -1) {
    //             root = nodes.get(i);
    //         }
    //         else {
    //             int parentIndex = arr.get(i);
    //             Node parent = nodes.get(parentIndex);
    //             Node child = nodes.get(i);
    //             parent.children.add(child);
    //         }
    //     }
    //     // levelOrder(root);
    //     // ArrayList<Integer> childHeights = new ArrayList<Integer>();
    //     // for(Node child:root.children) {
    //     //     childHeights.add(1+height(child));
    //     // }
    //     // Collections.sort(childHeights, Collections.reverseOrder());
    //     // return childHeights.get(0)+childHeights.get(1);
    //     return bfs(root);
    // }

    int height(Node root) {
        int max = 0;
        if(root == null)
            return 0;
        System.out.println("index: "+root.index);
        for(Node child:root.children) {
            int childHeight = height(child);
            System.out.println("child: "+child.index+" height: "+childHeight);
            max=Math.max(max,1+childHeight);
        }
        return max;
    }

    // int bfs(Node node) {
    //     int[] dist = new int[n];
    //     if(node == null)
    //         return 0;
    //     Queue<Node> queue = new LinkedList<Node>();
    //     queue.add(node);
    //     while(!queue.isEmpty()) {
    //         node = queue.poll(); 
    //         for(Node child:node.children) {
    //             if(dist[child.index] == 0) {
    //                 dist[child.index]=dist[node.index]+1; 
    //                 queue.add(child);
    //             }
    //         }
    //     }
    //     return dist[node.index];
    // }

    // void levelOrder(Node node) {
    //     if(node == null)
    //         return;
    //     Queue<Node> queue = new LinkedList<Node>();
    //     queue.add(node);
    //     while(!queue.isEmpty()) {
    //         Node curr = queue.poll(); 
    //         System.out.print(curr.index+" ");
    //         for(Node child:curr.children) {
    //             queue.add(child);
    //         }
    //     }
    // }
      
}