
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.interviewbit.com/problems/useful-extra-edges/

    A = 3
    B = [   [1, 2, 1]
            [2, 3, 2]
        ]
    C = 1
    D = 3
    E = [   [1, 3, 2]
        ]

    A = 4
    B = [   [1, 2, 1]
            [2, 3, 2]
            [3, 1, 4]
        ]
    C = 1
    D = 4
    E = [   [1, 3, 2]
        ]

    A : 6
    B : 
    [
    [1, 5, 5]
    ]
    C : 2
    D : 4
    E : 
    [
    [6, 7, 4]
    ]

    A : 7
    B : 
    [
    [1, 2, 1]
    [2, 3, 1]
    [3, 4, 1]
    [4, 5, 1]
    [5, 6, 1]
    [6, 7, 1]
    ]
    C : 1
    D : 6
    E : 
    [
    [1, 4, 1]
    ]
*/

public class Djikstras {
    int n;
    // ArrayList<ArrayList<Integer>> adjMatrix;
    int[][] graph;
    int[][] weights;
    int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Djikstras sol = new Djikstras();
        ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
        B.add(new ArrayList<Integer>(Arrays.asList(1,2,1)));
        B.add(new ArrayList<Integer>(Arrays.asList(2,3,1)));
        B.add(new ArrayList<Integer>(Arrays.asList(3,4,1)));
        B.add(new ArrayList<Integer>(Arrays.asList(4,5,1)));
        B.add(new ArrayList<Integer>(Arrays.asList(5,6,1)));
        B.add(new ArrayList<Integer>(Arrays.asList(6,7,1)));
        ArrayList<ArrayList<Integer>> E = new ArrayList<ArrayList<Integer>>();
        E.add(new ArrayList<Integer>(Arrays.asList(1,4,1)));
        // E.add(new ArrayList<Integer>(Arrays.asList(1,2,6)));
        // E.add(new ArrayList<Integer>(Arrays.asList(3,4,7)));
        // E.add(new ArrayList<Integer>(Arrays.asList(2,3,9)));;
        int C=1;
        int D=6;
        System.out.println("Solution: "+sol.solve(7,B,C,D,E));
    }

    public int solve(int n, ArrayList<ArrayList<Integer>> B, int src, int dest, ArrayList<ArrayList<Integer>> E) {
        this.n = n;
        // this.adjMatrix  = new ArrayList<ArrayList<Integer>>();
        this.weights = new int[n][n];
        this.graph = new int[n][n];
        // for(int i=0;i<n;i++) {
        //     adjMatrix.add(new ArrayList<Integer>());
        // }
        for(int[] weight:weights){
            Arrays.fill(weight, MAX);
        }
        for(ArrayList<Integer> edge : B) {
            graph[edge.get(0)-1][edge.get(1)-1] = 1;
            graph[edge.get(1)-1][edge.get(0)-1] = 1;
            // adjMatrix.get(edge.get(0)-1).add(edge.get(1)-1);
            // adjMatrix.get(edge.get(1)-1).add(edge.get(0)-1);
            weights[edge.get(0)-1][edge.get(1)-1] = edge.get(2);
            weights[edge.get(1)-1][edge.get(0)-1] = edge.get(2);
        }
        boolean[] visited = new boolean[n];
        int[] minDistSrc = new int[n];
        for(int i=0;i<n;i++) {
            minDistSrc[i]=MAX;
        }
        // for(int i=0;i<adjMatrix.size();i++) {
        //     System.out.println("Node: "+i+" edges: "+adjMatrix.get(i));
        // }
        // System.out.println("weights: ");
        // for(int[] weight:weights) {
        //     System.out.println(Arrays.toString(weight));
        // }

        System.out.println("graph: ");
        for(int[] row:graph) {
            System.out.println(Arrays.toString(row));
        }
        dijsktras(src-1,minDistSrc,visited);
        System.out.println("src: "+(src-1)+" minDist: "+Arrays.toString(minDistSrc));
        boolean[] visited2 = new boolean[n];
        int[] minDistDest = new int[n];
        for(int i=0;i<n;i++) {
            minDistDest[i]=MAX;
        }
        dijsktras(dest-1,minDistDest,visited2);
        System.out.println("src: "+(dest-1)+" minDist: "+Arrays.toString(minDistDest));
        int minDist = minDistSrc[dest-1];
        for(ArrayList<Integer> edge : E) {
            int u = edge.get(0)-1;
            int v = edge.get(1)-1;
            int dist = edge.get(2);
            if(u >= n || v >= n)
                continue;
            int dist1 = MAX;
            int dist2 = MAX;
            if(minDistSrc[u]!=MAX && minDistDest[v]!=MAX) {
                dist1 = minDistSrc[u]+dist+minDistDest[v];
            }
            if(minDistSrc[v]!=MAX && minDistDest[u]!=MAX) {
                dist2 = minDistSrc[v]+dist+minDistDest[u];
            }
            minDist = Math.min(minDist, Math.min(dist1,dist2));
        }   
        return minDist == MAX? -1:minDist;
        // return -1;
    }

    public void dijsktras(int src,int[] minDist,boolean[] visited) {
        minDist[src]=0;
        // loop runs only n-1 bcoz after n-1 runs all the vertices have their mindist updated
        for(int i=0;i<n-1;i++) {
            int u = nextLeastVertice(minDist,visited);
            if(u == -1)
                continue;
            visited[u] = true;
            System.out.println("u: "+u+" visited: "+Arrays.toString(visited));
            for(int v=0;v<n;v++) {
                if(graph[u][v]==1 && !visited[v]) {
                    System.out.println("v: "+v+" weight: "+weights[u][v]);
                    if(minDist[u] != MAX && minDist[v]>minDist[u]+weights[u][v]){
                        minDist[v] = minDist[u]+weights[u][v];
                    }
                }
            }
        }
    }

    int nextLeastVertice(int[] minDist,boolean[] visited) {
        int min = MAX;
        int minIndex = -1;
        for(int i=0;i<n;i++) {
            if(!visited[i] && min>minDist[i]) {
                min=minDist[i];
                minIndex = i;
            } 
        }
        return minIndex;
    }

}
