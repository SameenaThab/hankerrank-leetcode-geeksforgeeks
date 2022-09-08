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

public class UndirectedGraphCycleKrushals {
    int[] parents;

    public static void main(String[] args) {
        UndirectedGraphCycleKrushals sol = new UndirectedGraphCycleKrushals();
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
        // edges.add(new ArrayList<Integer>(Arrays.asList(3,4)));
        System.out.println("Solution: "+sol.solve(5,edges));
    }

    public int solve(int n, ArrayList<ArrayList<Integer>> edges) {
        this.parents = new int[n+1];
        for(int i=0;i<=n;i++) {
            parents[i]=i;
        }
        System.out.println("egnognkoenf");
        System.out.println(Arrays.toString(parents));
        for(ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int parentU = findParent(u);
            int parentV = findParent(v);
            // int parentU = parentNonRecursive(u);
            // int parentV = parentNonRecursive(v);
            System.out.println("u: "+u+" v: "+v+" parentU: "+parentU+" parentV: "+parentV);
            if(parentU == parentV)
                return 1;
            union(u,v);
            // unionWithOutChildren(u,v);
            System.out.println(Arrays.toString(parents));
        }
        return 0;
    }

    public int solveWithKrushkals(int A, ArrayList<ArrayList<Integer>> B) {
        this.parents = new int[A];
        for(int i=0;i<A;i++) {
            parents[i]=i;
        }
        for(ArrayList<Integer> edge:B) {
            int u = edge.get(0)-1;
            int v = edge.get(1)-1;
            int parentU = findParentIterative(u);
            int parentV = findParentIterative(v);
            if(parentU == parentV) {
                return 1;
            } else {
                int small = parentU<parentV?parentU:parentV;
                int large = parentU<parentV?parentV:parentU;
                parents[large]=small;
            }
        }
        return 0;
    }
    
    int findParentIterative(int u) {
        while(u != parents[u]) {
            u=parents[u];
        }
        return parents[u];
    }

    int union(int u,int v) {
        int parentU = findParent(u);
        int parentV = findParent(v);
        if(u == parentU) {
            parents[u] = parentV;
            return parents[u];
        }
        parents[u] = union(parents[u],parentV);
        return parents[u];
    }
    int findParent(int u) {
        if(parents[u] != u) {
            parents[u] = findParent(parents[u]);
        }
        return parents[u];
    }

    private void unionWithOutChildren(int u, int v) {
        int parentU = parentNonRecursive(u);
        int parentV = parentNonRecursive(v);
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

    private int parentNonRecursive(int x) {
        while(parents[x]!=x)
            x= parents[x];
        
        return parents[x];
    }      
}