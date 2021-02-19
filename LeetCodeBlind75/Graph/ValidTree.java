import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/graph-valid-tree/submissions/
/* 
For a graph , to tree, it must be fully connected and no cycles
For the graph to be a valid tree, it must have exactly n - 1 edges.
Any less, and it can't possibly be fully connected.
Any more, and it has to contain cycles. Additionally, if the graph is fully connected
and contains exactly n - 1 edges, it can't possibly contain a cycle, and therefore must be a tree!

Create Graph for all edges and map<int,graphnode> to keep track of graphnodes
Check if root node (val = 0) has cycle
if cycle, not valid tree
Check if all the nodes status is complete, if not complete , then not a valid tree
eg: 1-2,0-3 => not a tree because two separate sparse trees

Time Complexity: O(N)
Creating the adjacency list requires initialising a map of length N, with a cost of O(N), and then iterating over and inserting E edges, for a cost of O(E).
This gives us O(E)+O(N)=O(N+E).
Each node is added to the data structure once. This means that the outer loop will run N times. For each of the N nodes, its adjacent edges is iterated over once. In total, this means that all E edges are iterated over once by the inner loop. This, therefore, gives a total time complexity of O(N+E).
Because both parts are the same, we get a final time complexity of O(N+E).
When E ≠ N - 1, we simply return false. Therefore, the worst case is when E=N−1. Because E is proportional to N, we'll say E = N to simplify the analysis.

Space Complexity : O(N).
The adjacency list is a list of length N, with inner lists with lengths that add to a total of E. This gives a total of O(N+E) space.
In the worst case, the recursive stakce will have all N nodes on it at the same time, giving a total of O(N) space.
When E ≠ N - 1, we simply return false. Therefore, the worst case is when E=N−1. Because E is proportional to N, we'll say E = N to simplify the analysis.

*/
class ValidTree {

    enum Status {
        UNVISITED,PROCESSING,COMPLETE
    }

    class GraphNode {
        int val;
        List<GraphNode> adjacentNodes;
        Status status;
        GraphNode(int val) {
            this.val = val;
            this.adjacentNodes = new ArrayList<GraphNode>();
            this.status = Status.UNVISITED;
        }
        void addEdge(GraphNode node) {
            this.adjacentNodes.add(node);
            node.adjacentNodes.add(this);
        }
    }

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        Map<Integer,GraphNode> map = new HashMap<Integer,GraphNode>();
        for(int i=0;i<n;i++) {
            map.put(i,new GraphNode(i));
        }
        for(int[] edge:edges) {
            GraphNode u = map.get(edge[0]);
            GraphNode v = map.get(edge[1]);
            u.addEdge(v);
        }
        // for(int i=0;i<n;i++) {
        //     GraphNode curr = map.get(i);
        //     System.out.println("root: "+curr.val);
        //     for(GraphNode adjNode:curr.adjacentNodes) {
        //         System.out.print(adjNode.val+" ");
        //     }
        //     System.out.println();
        // }
        return validTree(n, map);
    }

    private boolean validTree(int n, Map<Integer, GraphNode> map) {
        GraphNode root = map.get(0);
        root.status = Status.PROCESSING;
        for(GraphNode adjNode : root.adjacentNodes) {
            if(adjNode.status != Status.COMPLETE && hasCycle(root,adjNode))
                return false;
        }
        root.status = Status.COMPLETE;   
        for(int i=0;i<n;i++) {
            if(map.get(i).status != Status.COMPLETE)
                return false;
        }
        return true;
    }

    private boolean hasCycle(GraphNode parent, GraphNode node) {
        if(node.status == Status.PROCESSING)
            return true;
        node.status = Status.PROCESSING;
        for(GraphNode adjNode:node.adjacentNodes) {
            if(adjNode != parent && adjNode.status != Status.COMPLETE && hasCycle(node,adjNode)) {
                return true;
            }
        }
        node.status = Status.COMPLETE;
        return false;
    }

    /* 
    Using Krushkals
    Let E be the number of edges, and N be the number of nodes.
    α(N) is the Inverse Ackermann Function.
    Time Complexity : O(N⋅α(N)).  
    When E ≠ N - 1 we simply return false. Therefore, the worst case is when E=N−1. Because E is proportional to N, we'll say E=N to simplify the analysis.
    We are traversing each of the N edges,
    so the entire cost of calling it is dependent on the cost of the parent(...) method that it calls.
    parent(...)'s cost is dependent on how far the node it was searching for is from the root.
    this depth could be N. If this was the case for all of the calls, we'd have a final cost of O(N^2)  

    However, remember those optimizations we did? Those keep the tree depths very shallow. It turns out that parent(...) amortizes to O(α(N)), where α is the Inverse Ackermann Function.
    The incredible thing about this function is that it grows so slowly that N will never go higher than 4 in the universe as we know it! So while in "practice" it is effectively O(1), in "theory" it is not.

    Actually proving this upper bound on the depth is a very advanced proof, which I'd certainly hope you'd never need to do in an interview! If you're interested though, I recommend looking in a good algorithm's text book or paper.

    Space Complexity : O(N)
    The UnionFind data structure requires O(N)O(N) space to the store the arrays it uses.
    */
    public boolean validTreeKrushkals(int n, int[][] edges) {
        if (edges.length != n - 1) return false; //because not fully connected or has cycles
        int[] parents = new int[n];
        for(int i=0;i<n;i++) {
            parents[i] = i;
        }
        for(int[] edge:edges) {
            int u = edge[0];
            int v = edge[1];
            int parentU = parent(u,parents);
            int parentV = parent(v,parents);
            if(parentU != parentV) {
                int smallest = parentU<parentV? parentU : parentV;
                int largest = parentU>parentV? parentU : parentV;
                parents[largest] = smallest; // combining both u and v to same parent
            } else {
                return false; // cycle detected
            }
        }
        int count = 0;
        for(int i=0;i<n;i++) {
            if(parents[i] == i)
                count++;    // counting roots
        }
        return count==1;
    }

    private int parent(int u, int[] parents) {
        while(parents[u] != u) {
            u = parents[u];
        }
        return parents[u];
    }

    public static void main(String[] args) {
        ValidTree sol = new ValidTree();
        int[][] edges = new int[][] {{0,1}, {0,2}, {0,3}, {1,4}};
        System.out.println("solution: "+sol.validTree(5,edges));
        System.out.println("solution: "+sol.validTree(5,new int[][] {{0,1}, {1,2}, {2,3}, {1,3}, {1,4}}));
        System.out.println("solution: "+sol.validTree(4,new int[][] {{0,1},{2,3}}));


        System.out.println("solution: "+sol.validTreeKrushkals(5,edges));
        System.out.println("solution: "+sol.validTreeKrushkals(5,new int[][] {{0,1}, {1,2}, {2,3}, {1,3}, {1,4}}));
        System.out.println("solution: "+sol.validTreeKrushkals(4,new int[][] {{0,1},{2,3}}));
    }
}