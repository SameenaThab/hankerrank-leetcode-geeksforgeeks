import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.interviewbit.com/problems/delete-edge/ */

public class DeleteEdge {
    ArrayList<Integer> weights;
    ArrayList<ArrayList<Integer>> edges;
    ArrayList<ArrayList<Integer>> adjacentMap;
    long MOD = 1000000009;
    long max = 0;

    public static void main(String[] args) {
        DeleteEdge sol = new DeleteEdge();
        /* 
        A = [10, 5, 12, 6]
        B = [

                [1, 2]
                [1, 4]
                [4, 3]
            ]

        A : [ 42, 468, 335, 501, 170, 725, 479, 359, 963, 465, 706, 146, 282 ]
        B : 
        [
        [10, 6]
        [3, 2]
        [12, 7]
        [9, 5]
        [2, 1]
        [8, 3]
        [7, 1]
        [4, 2]
        [6, 3]
        [11, 4]
        [5, 3]
        [13, 11]
        ]
        */
        ArrayList<Integer> weights = new ArrayList<Integer>(Arrays.asList(42, 468, 335, 501, 170, 725, 479, 359, 963, 465, 706, 146, 282));
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        edges.add(new ArrayList<Integer>(Arrays.asList(10, 6)));
        edges.add(new ArrayList<Integer>(Arrays.asList(3, 2)));
        edges.add(new ArrayList<Integer>(Arrays.asList(12, 7)));
        edges.add(new ArrayList<Integer>(Arrays.asList(9, 5)));
        edges.add(new ArrayList<Integer>(Arrays.asList(2, 1)));
        edges.add(new ArrayList<Integer>(Arrays.asList(8, 3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(7, 1)));
        edges.add(new ArrayList<Integer>(Arrays.asList(4, 2)));
        edges.add(new ArrayList<Integer>(Arrays.asList(6, 3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(11, 4)));
        edges.add(new ArrayList<Integer>(Arrays.asList(5, 3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(13, 11)));
        System.out.println("Solution: "+sol.deleteEdge(weights,edges));
    }
 
    public long deleteEdge(ArrayList<Integer> weights, ArrayList<ArrayList<Integer>> edges) {
        this.edges = edges;
        this.weights = weights;
        this.adjacentMap = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<weights.size();i++) {
            adjacentMap.add(new ArrayList<Integer>());
        }
        for(ArrayList<Integer> edge:edges) {
            adjacentMap.get(edge.get(0)-1).add(edge.get(1)-1);
            adjacentMap.get(edge.get(1)-1).add(edge.get(0)-1);
        }

        // System.out.println(adjacentMap);
        long prod  = Long.MIN_VALUE;
        long[] sums = new long[weights.size()];
        long totalTreeSum = 0;
        for(Integer weight:weights) {
            totalTreeSum=(totalTreeSum+weight)%MOD;
        }
        dfs(0,-1,totalTreeSum);
        return max;
        // for(ArrayList<Integer> edge : edges) {
        //     long subtree2 = dfs(edge.get(1)-1,edge.get(0)-1,sums);
        //     long subtree1 = totalTreeSum-subtree2;

        //     // System.out.println("Node: "+edge.get(1)+" sum: "+subtree1);
        //     // System.out.println("Node: "+edge.get(0)+" sum: "+subtree2);
        //     // System.out.println("root: "+totalTreeSum);
        //     prod = Math.max(prod,(subtree1*subtree2)%MOD);
        // }

        // return prod;
    }



    public long dfs(Integer node,Integer parent,long[] sums) {
        if(sums[node] != 0)
            return sums[node];
        long sum = weights.get(node);
        for(Integer edge:adjacentMap.get(node)) {
            if(edge != parent) {
                sum=(sum+dfs(edge,node,sums))%MOD;
            }
        }
        sums[node]=sum;
        return sum;
    }

    public long dfs(Integer node,Integer parent,long totalSum) {
        long sum = weights.get(node);
        //leaf node will have only parent as adject node, therefor it will return sum as zero
        for(Integer edge:adjacentMap.get(node)) { 
            if(edge != parent) {
                long subtree = dfs(edge,node,totalSum);
                sum=(sum+subtree)%MOD;
                max = Math.max(max,(subtree*(totalSum-subtree))%MOD);
            }
        }
        return sum;
    }

}