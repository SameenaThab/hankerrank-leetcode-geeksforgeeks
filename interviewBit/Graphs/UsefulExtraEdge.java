import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.interviewbit.com/problems/knight-on-chess-board/

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

class Graph {
    LinkedList<Edge>[] adj;
    int n;
    int MAX = Integer.MAX_VALUE;

    Graph(int n) {
        this.n = n;
        this.adj = new LinkedList[n+1];
        for(int i=0;i<=n;i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int src,int dest,int weight) {
        // System.out.println("size: "+adj.length+" src: "+src+" dest: "+dest);
        Edge edge1 = new Edge(src,dest,weight);
        adj[src].add(edge1);
        Edge edge2 = new Edge(dest,src,weight);
        adj[dest].add(edge2);
    }

    public int[] dijsktras(int src) { 
        boolean[] visited = new boolean[n+1];
        int[] minDist = new int[n+1];
        for(int i=0;i<=n;i++) {
            minDist[i]=MAX;
        }
        minDist[src]=0;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return p1.dist-p2.dist;
            }
        });
        pq.add(new Pair(src,0));
        while(!pq.isEmpty()) {
            Pair pair = pq.poll();
            int u = pair.ver;
            if(!visited[u]) {
                visited[u] = true;
                for(Edge edge:adj[u]) {
                    int v = edge.dest;
                    if(!visited[v] && minDist[v]>minDist[u]+edge.weight) {
                        int newDist = minDist[u]+edge.weight;
                        pq.add(new Pair(v,newDist));
                        minDist[v]=newDist;
                    }
                }
            }
        }
        return minDist;
    }
}

class Edge {
    int src;
    int dest;
    int weight;
    Edge(int src,int dest,int weight) {
        this.src=src;
        this.dest=dest;
        this.weight=weight;
    }
}

class Pair {
    int ver;
    int dist;
    Pair(int v,int d) {
        this.ver=v;
        this.dist = d;
    }
}

public class UsefulExtraEdge {
    int n;
    Graph graph;

    public static void main(String[] args) {
        UsefulExtraEdge sol = new UsefulExtraEdge();
        ArrayList<ArrayList<Integer>> B = new ArrayList<ArrayList<Integer>>();
        B.add(new ArrayList<Integer>(Arrays.asList(1,5,5)));
        ArrayList<ArrayList<Integer>> E = new ArrayList<ArrayList<Integer>>();
        E.add(new ArrayList<Integer>(Arrays.asList(6,7,4)));
        // E.add(new ArrayList<Integer>(Arrays.asList(1,2,6)));
        // E.add(new ArrayList<Integer>(Arrays.asList(3,4,7)));
        // E.add(new ArrayList<Integer>(Arrays.asList(2,3,9)));;
        int C=2;
        int D=4;
        System.out.println("Solution: "+sol.solve(6,B,C,D,E));
    }

    public int solve(int n, ArrayList<ArrayList<Integer>> B, int src, int dest, ArrayList<ArrayList<Integer>> E) {
        this.n = n;
        this.graph = new Graph(n);
        int MAX = Integer.MAX_VALUE;


        for(ArrayList<Integer> edge : B) {
            graph.addEdge(edge.get(0), edge.get(1), edge.get(2));
        }
        int[] minDistSrc = graph.dijsktras(src);
        int[] minDistDest = graph.dijsktras(dest);
        System.out.println("src: "+(src)+" minDist: "+Arrays.toString(minDistSrc));
        System.out.println("src: "+(dest-1)+" minDist: "+Arrays.toString(minDistDest));
        int minDist = minDistSrc[dest];
        for(ArrayList<Integer> edge : E) {
            int u = edge.get(0);
            int v = edge.get(1);
            int dist = edge.get(2);
            if(u <= n && v <= n) {
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
        }   
        return minDist == MAX? -1:minDist;
        // return -1;
    }

}
