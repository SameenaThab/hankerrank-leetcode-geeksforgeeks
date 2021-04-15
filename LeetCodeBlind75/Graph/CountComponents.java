import java.util.*;
// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
class CountComponents {
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
    The UnionFind data structure requires O(N) space to the store the arrays it uses.
    */
    public int countComponentsKrushkals(int n, int[][] edges) {
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
            }
        }
        int count = 0;
        for(int i=0;i<n;i++) {
            if(parents[i] == i)
                count++;    // counting roots
        }
        return count;
    }

    private int parent(int u, int[] parents) {
        while(parents[u] != u) {
            u = parents[u];
        }
        return parents[u];
    }

    /* using dfs */
    public int countComponentsIterativeDFS(int n, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>();
        for(int i=0;i<n;i++) {
            adjacencyList.add(new ArrayList<Integer>());
        }
        for(int[] edge:edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        int count = 0;
        Set<Integer> visited = new HashSet<Integer>();
        for(int i=0;i<n;i++) {
            if(!visited.contains(i)) {
                dfs(i,visited,adjacencyList);
                count++;
            }
            // System.out.println(visited);
        }
        return count;
    }

    private void dfs(int root, Set<Integer> visited, List<List<Integer>> adjacencyList) {
        visited.add(root);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(root);
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            for(int adjNode:adjacencyList.get(curr)) {
                if(!visited.contains(adjNode)) {
                    visited.add(adjNode);
                    stack.push(adjNode);
                }
            }
        }
    }

    public int countComponentsIterativeBFS(int n, int[][] edges) {
        List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>();
        for(int i=0;i<n;i++) {
            adjacencyList.add(new ArrayList<Integer>());
        }
        for(int[] edge:edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        int count = 0;
        Set<Integer> visited = new HashSet<Integer>();
        for(int i=0;i<n;i++) {
            if(!visited.contains(i)) {
                bfs(i,visited,adjacencyList);
                count++;
            }
            // System.out.println(visited);
        }
        return count;
    }

    private void bfs(int root, Set<Integer> visited, List<List<Integer>> adjacencyList) {
        visited.add(root);
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int adjNode:adjacencyList.get(curr)) {
                if(!visited.contains(adjNode)) {
                    visited.add(adjNode);
                    queue.add(adjNode);
                }
            }
        }
    }


    public static void main(String[] args) {
        CountComponents sol = new CountComponents();
        System.out.println("solution: "+sol.countComponentsIterativeDFS(5,new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
        System.out.println("solution: "+sol.countComponentsIterativeDFS(5,new int[][]{{0, 1}, {1, 2}, {3, 4}}));


        System.out.println("solution: "+sol.countComponentsKrushkals(5,new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
        System.out.println("solution: "+sol.countComponentsKrushkals(5,new int[][] {{0, 1}, {1, 2}, {3, 4}}));
    }
}