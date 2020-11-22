import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
0: 1
1: 2
2: 0,3
3: 2
4: 6
5: 4
6: 5
*/

public class HasRoute_chap4Prob1 {
        
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
    
    public boolean hasRoute(int s,int t) {
        Queue<Integer> q=new LinkedList<Integer>();
        boolean[] visited =new boolean[n];
        visited[s]=true;
        for(Integer i:edges.get(s))
        {
            q.add(i);
        }
        while(!q.isEmpty())
        {
            int next=q.poll();
            if(next == t)
                return true;
            else
            {
                if(!visited[next])
                {
                    visited[next]=true;
                    for(Integer i:edges.get(next))
                    {
                        if(!visited[i])
                            q.add(i);
                    }
                }
            }
        }
        return false;
    }

    public boolean hasRoute2(int s,int t) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(s);
        HashSet<Integer> visited = new HashSet<Integer>();
        visited.add(s);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            for(Integer n:this.edges.get(curr)) {
                if(n == t)
                    return true;
                if(!visited.contains(n)) {
                    visited.add(n);
                    queue.add(n);
                }
            }
        }
        return false;
    }
}
        
    public static void main(String[] args) {
        Graph graph = new Graph(7);
        /*
        0: 1,2
        1: 0,2
        2: 0,1,3
        3: 2
        4: 5,6
        5: 4
        6: 4
        */
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        System.out.println("route1: "+graph.hasRoute(0,3));
        System.out.println("route1: "+graph.hasRoute(0,6));
        System.out.println("route2: "+graph.hasRoute2(0,3));
        System.out.println("route2: "+graph.hasRoute2(0,6));
        // Scanner scanner = new Scanner(System.in);
      
        // int queries = scanner.nextInt();
        
        // for (int t = 0; t < queries; t++) {
            
        //     // Create a graph of size n where each edge weight is 6:
        //     Graph graph = new Graph(scanner.nextInt());
        //     int m = scanner.nextInt();
            
        //     // read and set edges
        //     for (int i = 0; i < m; i++) {
        //         int u = scanner.nextInt() - 1;
        //         int v = scanner.nextInt() - 1;
                
        //         // add each edge to the graph
        //         graph.addEdge(u, v);
        //     }          
        // }
        
        // scanner.close();
    }
}


