import java.util.*;
import java.text.*;
import java.math.*;
import java.security.KeyStore.Entry;
import java.util.regex.*;
import java.io.*;

class Node {
    int no;
    List<Node> children;
    Status status;

    Node(int no) {
        this.no = no;
        children = new ArrayList<Node>();
        this.status = Status.INITIAL;
    }

    void addChild(Node node) {
        this.children.add(node);
    }

    void print() {
        System.out.print(no+"-");
        for(Node child:children) {
            System.out.print(child.no+", ");
        }
        System.out.println();
    }
}

enum Status {
    INITIAL,VISITED;
}

class Graph {
    int numNodes;
    Map<Integer,Node> nodes;

    Graph(int numNodes) {
        this.numNodes = numNodes;
        this.nodes = new HashMap<Integer,Node>();
    }

    void print() { 
        for(Map.Entry<Integer,Node> entry : this.nodes.entrySet()) {
            Node node = entry.getValue();
            node.print();
        }
    }

    void addEdge(int u,int v) {
        Node nodeU = nodes.getOrDefault(u, new Node(u));
        Node nodeV = nodes.getOrDefault(v, new Node(v));
        nodeU.addChild(nodeV);
        nodeV.addChild(nodeU);
        nodes.put(u,nodeU);
        nodes.put(v,nodeV);
    }
}

public class UndirectedGraphCycle {
    Graph graph;

    public static void main(String[] args) {
        UndirectedGraphCycle sol = new UndirectedGraphCycle();
        /* 
        A = 5
        B = [  [1, 2]
                [1, 3]
                [2, 3]
                [1, 4]
                [4, 5]
            ]

        A : 4
        B : 
        [
        [1, 2]
        [2, 3]
        [3, 4]
        ]

        A = 3
        B = [  [1. 2]
                [1, 3]
            ]
        */
        
        ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>();
        edges.add(new ArrayList<Integer>(Arrays.asList(1,2)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1,3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(2,3)));
        edges.add(new ArrayList<Integer>(Arrays.asList(1,4)));
        edges.add(new ArrayList<Integer>(Arrays.asList(4,5)));
        System.out.println("Solution: "+sol.solve(5,edges));
    }

    public int solve(int n, ArrayList<ArrayList<Integer>> edges) {
        this.graph = new Graph(n);
        for(ArrayList<Integer> edge:edges) {
            graph.addEdge(edge.get(0), edge.get(1));  
        }
        graph.print();
        // Set<Integer> visited = new HashSet<Integer>();
        for(Map.Entry<Integer,Node> entry : graph.nodes.entrySet()) {
            Node node = entry.getValue();
            if(node.status != Status.VISITED) {
                if(hasCycle(node, -1))
                    return 1;
            }
        }
        return 0;
    }

/* 
    visited but not parent of child , then there is cycle
    1 - 2
    2 - 1,3
    3 - 1
    cycle(1,-1) -> cycle(2,1) -> 1 is parent and visited , cycle(3,2)
         cycle(3,2) -> 2 is parent and visited,
              -> 1 is not parent but visited -> therefore cycle  
*/
      
    boolean hasCycle(Node node,int parent) {
        System.out.println("Node: "+node.no+" parent: "+parent);
        node.status = Status.VISITED;
        for(Node child:node.children) {
            if(child.status == Status.VISITED && child.no != parent)
                return true;
            if(child.status != Status.VISITED) {
                if(hasCycle(child, node.no))
                    return true;
            }
                
        }
        return false;
    }
}