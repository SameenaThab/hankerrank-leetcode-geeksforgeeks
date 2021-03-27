
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class DirectedCycle {
    ArrayList<ArrayList<Integer>> adjacentMap;

    public static void main(String[] args) {
        DirectedCycle sol = new DirectedCycle();
        /* 
        A = 5
        B = [  [1, 2] 
                [4, 1] 
                [2, 4] 
                [3, 4] 
                [5, 2] 
                [1, 3] ]

        A = 5
        B = [  [1, 2]
                [2, 3] 
                [3, 4] 
                [4, 5] ]

        A : 5
        B : 
            [
            [1, 2]
            [1, 3]
            [2, 3]
            [1, 4]
            [4, 3]
            [4, 5]
            [3, 5]
            ]
        */
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        edges.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1, 3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(2, 3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1, 4)));
        edges.add(new ArrayList<Integer>(Arrays.asList(4, 3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(4, 5)));
        edges.add(new ArrayList<Integer>(Arrays.asList(3, 5)));
        // edges.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
        // edges.add(new ArrayList<Integer>(Arrays.asList(2, 3)));
        // edges.add(new ArrayList<Integer>(Arrays.asList(3, 4)));
        // edges.add(new ArrayList<Integer>(Arrays.asList(4, 5)));
        
        System.out.println("Solution: "+sol.solve(5,edges));
    }

    public int solve(int A, ArrayList<ArrayList<Integer>> edges) {
        this.adjacentMap = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<A;i++) {
            adjacentMap.add(new ArrayList<Integer>());
        }
        for(ArrayList<Integer> edge:edges) {
            adjacentMap.get(edge.get(0)-1).add(edge.get(1)-1);
        }

        for(int i=0;i<A;i++) {
            System.out.println(i+": "+adjacentMap.get(i));
        }


        boolean[] visited = new boolean[A];
        boolean[] processing = new boolean[A];
        for(int i=0;i<A;i++) {
            System.out.println("i: "+i);
            if(!visited[i]) {
                // dfs2 willnot mark visited until all adj nodes are completed
                if(dfs2(i,visited,processing))
                    return 1;
            }
        }
        return 0;
    }

    public boolean dfs(int node,boolean[] visited,boolean[] processing) {

        System.out.println("node: "+node);
        if(processing[node])
            return true;
        visited[node] = true;
        processing[node] = true;
        for(Integer edge:adjacentMap.get(node)) {
            if(dfs(edge,visited,processing))
                return true;
        }
        processing[node] = false;
        return false;
    }

    public boolean dfs2(int node,boolean[] visited,boolean[] processing) {

        // System.out.println("node: "+node);
        if(processing[node])
            return true; // cycle detected
        processing[node] = true;
        for(Integer edge:adjacentMap.get(node)) {
            if(!visited[node] && dfs(edge,visited,processing))
                return true;
        }
        visited[node] = true;
        processing[node] = false; // reset for others
        return false;
    }
}
