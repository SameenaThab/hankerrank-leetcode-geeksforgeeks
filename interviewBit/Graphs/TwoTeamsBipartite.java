
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.interviewbit.com/problems/two-teams/ */

public class TwoTeamsBipartite {
    int n;
    ArrayList<ArrayList<Integer>> adjacentMap;

    public static void main(String[] args) {
        TwoTeamsBipartite sol = new TwoTeamsBipartite();
        /* 
        A = 5
        B = [ [1, 2],
            [2, 3],
            [1, 5],
            [2, 4] ]

        A = 4
        B = [ [1, 4],
            [3, 1],
            [4, 3],
            [2, 1] ]

        unconnected graph
        n = 5
        [1,2]
        [3,4]
        [3,5]
        [4,5]
        */
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        edges.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
        edges.add(new ArrayList<Integer>(Arrays.asList(2, 3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(2, 4)));
        // edges.add(new ArrayList<Integer>(Arrays.asList(4, 5)));
        edges.add(new ArrayList<Integer>(Arrays.asList(5, 1)));
        // edges.add(new ArrayList<Integer>(Arrays.asList(6, 1)));
        // edges.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
        // edges.add(new ArrayList<Integer>(Arrays.asList(3, 4)));
        // edges.add(new ArrayList<Integer>(Arrays.asList(3, 5)));
        // edges.add(new ArrayList<Integer>(Arrays.asList(4, 5)));
        
        System.out.println("Solution: "+sol.solve(5,edges));
    }

    public int solve(int A, ArrayList<ArrayList<Integer>> enemies) {
        this.n = A;
        this.adjacentMap = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<A;i++) {
            adjacentMap.add(new ArrayList<Integer>());
        }
        for(ArrayList<Integer> enemy:enemies) {
            adjacentMap.get(enemy.get(0)-1).add(enemy.get(1)-1);
            adjacentMap.get(enemy.get(1)-1).add(enemy.get(0)-1);
        }

        for(int i=0;i<A;i++) {
            System.out.println(i+": "+adjacentMap.get(i));
        }

        
        int[] coloring = new int[n];
        // for(int i=0;i<n;i++) {
        //     System.out.println("i: "+i);
        //     if(coloring[i] == 0) {
        //         if(!isBipartiteBFS(i,coloring))
        //             return 0;
        //     }
        // }
        for(int i=0;i<n;i++) {
            System.out.println("i: "+i);
            if(coloring[i] == 0) {
                coloring[i] = 1;
                if(!isBipartiteDFS(i,coloring))
                    return 0;
            }
        }
        return 1;
    }

    public boolean isBipartiteDFS(int node,int[] coloring) {

        System.out.println("node: "+node);
        Integer currColor = coloring[node];

        for(Integer neighbor:adjacentMap.get(node)) {
            int nextColor = currColor == 1? 2:1;
            if(coloring[neighbor] == 0) {
                coloring[neighbor] = nextColor;
                if(!isBipartiteDFS(neighbor,coloring))
                    return false;
            }
            if(coloring[neighbor] == currColor)
                return false;
        }
        return true;
    }


    public boolean isBipartiteBFS(int node , int[] coloring) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(node);
        coloring[node]=1;
        while(!queue.isEmpty()) {
            Integer currNode = queue.poll();
            Integer currColor = coloring[currNode];
            System.out.println("currNode: "+currNode+" currColor: "+currColor);
            for(Integer neighbor : adjacentMap.get(currNode)) {
                int nextColor = currColor == 1? 2:1;
                if(coloring[neighbor] == 0) {
                    coloring[neighbor] = nextColor;
                    queue.add(neighbor);
                }
                if(coloring[neighbor] == currColor) {
                    return false;
                }
            }
        }
        return true;
    }
}
