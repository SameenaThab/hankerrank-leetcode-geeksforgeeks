import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach/problem*/

public class ShortestPath_BFS {
        
public static class Graph {
    ArrayList<HashSet<Integer>> edges;
    int n;
    
        public Graph(int size) {
        this.n=size;
        this.edges = new ArrayList<HashSet<Integer>>();
        for(int i = 0; i < this.n; i++) 
            this.edges.add(new HashSet<Integer>());
        }

        public void addEdge(int first, int second) {
            this.edges.get(first).add(second);
            this.edges.get(second).add(first);
        }
        
        public int[] shortestReach(int s) {
            // 0 indexed
            int[] paths=new int[this.n];
            Arrays.fill(paths,-1);
            paths[s]=0;
            
            Queue<Integer> q= new LinkedList<Integer>();
            q.add(s);
            while(!q.isEmpty())
            {
                int u=q.poll();
                for(int v:this.edges.get(u))
                {
                    if(paths[v]==-1)
                    {
                        paths[v]=paths[u]+6;
                        q.add(v);
                    }
                }
            }
            return paths;
        }
}
        
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
      
        int queries = scanner.nextInt();
        
        for (int t = 0; t < queries; t++) {
            
            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();
            
            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;
                
                // add each edge to the graph
                graph.addEdge(u, v);
            }
            
            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);
 
            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();            
        }
        
        scanner.close();
    }
}
