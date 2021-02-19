import java.util.*;

class ValidTreeIterativeSolution {


// https://leetcode.com/problems/graph-valid-tree/submissions/
    
    private List<List<Integer>> adjacencyList = new ArrayList<>();
    private Set<Integer> seen = new HashSet<>();

    /* 

    For a graph , to tree, it must be fully connected and no cycles
    For the graph to be a valid tree, it must have exactly n - 1 edges.
    Any less, and it can't possibly be fully connected.
    Any more, and it has to contain cycles. Additionally, if the graph is fully connected
    and contains exactly n - 1 edges, it can't possibly contain a cycle, and therefore must be a tree!

    For all the below methods , same time complexity and Space
    Time Complexity : O(N).
    When E ≠ N - 1, we simply return false. Therefore, the worst case is when E=N−1. Because E is proportional to N, we'll say E = N to simplify the analysis.
    Creating an adjacency list has a time complexity of O(N+E). Because E is now bounded by N, we can reduce this slightly to O(N+N)=O(N).
    The iterative breadth-first search and depth-first search are almost identical. Each node is put onto the queue/stack once, ensured by the seen set. Therefore, the inner "neighbour" loop runs once for each node. Across all nodes, the number of cycles this loop does is the same as the number of edges, which is simply N. Therefore, these two algorithms have a time complexity of O(N).
    The recursive depth-first search's "neighbour" loop runs only once for each node. Therefore, in total, the function is called once for each edge. So it is called $E = N times, and N of those times, it actually enters the "neighbour" loop. Collectively, the total number of iterations of the "neighbour" loop is E=N. So we get O(N), as these all simply add.

    Space Complexity : O(N).
    Previously, we determined that the adjacency list took O(E+N) space. We now know this is simply O(N).
    In the worst case, the search algorithms will require an additional O(N) space; this is if all nodes were on the stack/queue at the same time.
    So again we get a total of O(N).
    */
    
    public boolean validTreeRecursiveDFS(int n, int[][] edges) {
        
        if (edges.length != n - 1) return false;
        
        // Make the adjacency list.
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        
        // Carry out depth first search.
        dfs(0);
        System.out.println(seen.size());
        // Inspect result and return the verdict.
        return seen.size() == n;   
    }
    
    public void dfs(int node) {
        if (seen.contains(node)) return;
        seen.add(node);
        for (int neighbour : adjacencyList.get(node)) {
            dfs(neighbour);
        }
    }

    public boolean validTreeIterativeDFS(int n, int[][] edges) {
        
        if (edges.length != n - 1) return false;
        
        // Make the adjacency list.
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        Set<Integer> visited = new HashSet<Integer>(); 
        stack.push(0);
        visited.add(0);
        while(!stack.isEmpty()) {
            int curr = stack.pop();
            for(int adjNode : adjacencyList.get(curr)) {
                if(!visited.contains(adjNode)) {
                    stack.push(adjNode);
                    visited.add(adjNode);
                }
            }
        }
        return visited.size() == n;
    }

    public boolean validTreeIterativeBFS(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        
        // Make the adjacency list.
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>(); 
        queue.add(0);
        visited.add(0);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(int adjNode : adjacencyList.get(curr)) {
                if(!visited.contains(adjNode)) {
                    queue.add(adjNode);
                    visited.add(adjNode);
                }
            }
        }
        return visited.size() == n;  
    }

    public static void main(String[] args) {
        ValidTreeIterativeSolution sol = new ValidTreeIterativeSolution();
        int[][] edges = new int[][] {{0,1}, {0,2}, {0,3}, {1,4}}; 
        System.out.println("solution: "+sol.validTreeRecursiveDFS(5,edges)); // edges = n-1 but there is cycle
        System.out.println("solution: "+sol.validTreeRecursiveDFS(3,new int[][] {{0,1}, {1,2}, {0,2}})); // cycle
        System.out.println("solution: "+sol.validTreeRecursiveDFS(4,new int[][] {{0,1},{2,3}}));


        ValidTreeIterativeSolution sol2 = new ValidTreeIterativeSolution();
        System.out.println("solution: "+sol2.validTreeIterativeDFS(5,edges)); // edges = n-1 but there is cycle
        System.out.println("solution: "+sol2.validTreeIterativeDFS(3,new int[][] {{0,1}, {1,2}, {0,2}})); // cycle
        System.out.println("solution: "+sol2.validTreeIterativeDFS(4,new int[][] {{0,1},{2,3}}));


        ValidTreeIterativeSolution sol3 = new ValidTreeIterativeSolution();
        System.out.println("solution: "+sol3.validTreeIterativeBFS(5,edges)); // edges = n-1 but there is cycle
        System.out.println("solution: "+sol3.validTreeIterativeBFS(3,new int[][] {{0,1}, {1,2}, {0,2}})); // cycle
        System.out.println("solution: "+sol3.validTreeIterativeBFS(4,new int[][] {{0,1},{2,3}}));
    }
}