import java.util.*;

public class PermutationSwaps {

    /* 
    Since A and B are permutations of numbers from 1 to n, they are distinct
    First we represent perm A and B as graphs
    GoodPairs make the edges. Since elements of A and B are distinct and range 1to n, element values act as nodes
    eg: A = {1,7,6,4,3,8,5,2}, B = {2,6,7,8,3,4,1,5}, goorPairs= {(7,8),(1,7),(4,6),(2,3)}
    Graph A:
        1-5-2, 4-8, 7-6  {4-8 is formed from goodPair(4,6) value at index 4 is 4 and value at index 6 is 8}
    Graph B:
        2-1-5, 6-7, 8-4 {8-4 is formed from goodPair(4,6) value at index 4 is 8 and value at index 6 is 4}
    Approach 1: 
        We find connected nodes of each node in graph A and B. 
        If connected arrays are not same then perm A to B is not possible
            else
        Possible
    Approach 2: Krushkals Algorithm
        Find parents of each node for Graph A and Graph B
        If parents are same for each node , then perm A to B is possible
            else
        not possible
    */

    public static void main(String[] args) {
        PermutationSwaps sol = new PermutationSwaps();
        ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(1,7,6,4,3,8,5,2));
        ArrayList<Integer> B = new ArrayList<Integer>(Arrays.asList(2,6,7,8,3,4,1,5));
        ArrayList<ArrayList<Integer>> goodPairs = new ArrayList<ArrayList<Integer>>();
        goodPairs.add(new ArrayList<Integer>(Arrays.asList(7,8)));
        goodPairs.add(new ArrayList<Integer>(Arrays.asList(1,7)));
        goodPairs.add(new ArrayList<Integer>(Arrays.asList(4,6)));
        goodPairs.add(new ArrayList<Integer>(Arrays.asList(2,3)));
        // ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(1, 3, 2, 4));
        // ArrayList<Integer> B = new ArrayList<Integer>(Arrays.asList(1, 4, 2, 3));
        // ArrayList<ArrayList<Integer>> goodPairs = new ArrayList<ArrayList<Integer>>();
        // goodPairs.add(new ArrayList<Integer>(Arrays.asList(2,4)));
        System.out.println("Solution: "+sol.solveKrushkalsApproach(A,B,goodPairs));
    }

    public int solveKrushkalsApproach(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<ArrayList<Integer>> goodPairs) {
        int n = A.size();
        int[] givenIndex = new int[n+1];
        int[] requiredIndex = new int[n+1];
        for(int i=0;i<n;i++) {
            int value = A.get(i);
            givenIndex[value] = i+1; // bcoz C is one based indexing
            value = B.get(i);
            requiredIndex[value] = i+1;
        }
        int[] parents = new int[n+1];
        int[] children = new int[n+1];
        for(int i=1;i<=n;i++) { // node are element values form 1 to n therefore n+1 size for graph
            parents[i]=i;
            children[i]=1;
        }
        for(ArrayList<Integer> goodPair:goodPairs) {
            int u = goodPair.get(0);
            int v = goodPair.get(1);
            // union(u,v,parents,children);
            unionWithOutChildren(u,v,parents);
        }

        System.out.println("givenIndex: "+Arrays.toString(givenIndex));
        System.out.println("requiredIndex: "+Arrays.toString(requiredIndex));
        System.out.println("parents: "+Arrays.toString(parents));
        System.out.println("children: "+Arrays.toString(children));
        for(int i=1;i<=n;i++) {
            int u = givenIndex[i];
            int v = requiredIndex[i];
            // int parentU = parent(u,parents);
            // int parentV = parent(v,parents);
            int parentU = parentNonRecursive(u,parents);
            int parentV = parentNonRecursive(v,parents);
            if(parentU != parentV) {
                return 0;
            }
        }
        return 1;
    }

    private void union(int u, int v, int[] parents,int[] children) {
        int parentU = parentNonRecursive(u, parents);
        int parentV = parentNonRecursive(v, parents);
        if(parentU != parentV) {
            if(children[parentU]<children[parentV]) {
                children[parentU]+=children[parentV]; // addind all children of parentU children to parentV                
                parents[parentU] = parentV; // making parent of parentV as parentU's parent     
            } else {
                children[parentU]+=children[parentV]; // addind all parentV children to parentU                
                parents[parentV] = parentU; // making parent of parentU as parentV's parent           
            }
        }
    }

    private void unionWithOutChildren(int u, int v, int[] parents) {
        int parentU = parentNonRecursive(u, parents);
        int parentV = parentNonRecursive(v, parents);
        if(parentU==u && parentU==v){
            parents[u] = parentU;
            parents[v] = parentU;
        }
        else if(parentU==u)
            parents[parentU]=parentV;
        else if(parentV==v)
            parents[parentV]=parentU;
        else{
            parents[parentV]=parentU;
        }
    }

    private int parent(int v,int[] parents) {
        // if(parents[u] != u) {
        //     int parentU = parent(parents[u],parents);
        //     parents[u] = parentU;
        // }
        // return parents[u];
        if(parents[v]==v){
            return v;
        }
        int p=parent(parents[v],parents);
        parents[v]=p;
        return p;
    }

    private int parentNonRecursive(int x,int[] parents) {
        while(parents[x]!=x)
            x= parents[x];
        
        return parents[x];
    }

    public int solveConnectedApproach(ArrayList<Integer> A, ArrayList<Integer> B,
            ArrayList<ArrayList<Integer>> goodPairs) {
        ArrayList<ArrayList<Integer>> graphA = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> graphB = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<=A.size();i++) { // node are element values form 1 to n therefore n+1 size for graph
            graphA.add(new ArrayList<Integer>());
            graphB.add(new ArrayList<Integer>());
        }
        for(ArrayList<Integer> goodPair:goodPairs) {
            int index1 = goodPair.get(0)-1;
            int index2 = goodPair.get(1)-1;
            graphA.get(A.get(index1)).add(A.get(index2)); // node are values not indexes
            graphA.get(A.get(index2)).add(A.get(index1));
            graphB.get(B.get(index1)).add(B.get(index2));
            graphB.get(B.get(index2)).add(B.get(index1));
        }
        for(int i=0;i<A.size();i++) {
            ArrayList<Integer> connectedNodes = new ArrayList<Integer>();
            boolean[] visited = new boolean[A.size()+1];
            getConnectedNode(graphA,connectedNodes,A.get(i),visited);
            ArrayList<Integer> connectedNodesA = connectedNodes;
            connectedNodes = new ArrayList<Integer>();
            visited = new boolean[A.size()+1];
            getConnectedNode(graphB,connectedNodes,B.get(i),visited);
            ArrayList<Integer> connectedNodesB = connectedNodes;
            Collections.sort(connectedNodesA);
            Collections.sort(connectedNodesB);
            if(!connectedNodesA.equals(connectedNodesB))
                return 0;
        }
        return 1;
    }

    private void getConnectedNode(ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> connectedNodes, int src,boolean[] visited) {
        visited[src]=true;
        connectedNodes.add(src);
        for(Integer child:graph.get(src)) {
            if(!visited[child]) {
                getConnectedNode(graph,connectedNodes,child,visited);
            }
        }
    }
}