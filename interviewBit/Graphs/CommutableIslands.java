import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

// https://www.interviewbit.com/problems/commutable-islands/

public class CommutableIslands {

    ArrayList<ArrayList<Integer>> bridges;

    int numIslands;

    int[] parent;
    // int[] rank;
    public static void main(String[] args) {
        CommutableIslands sol = new CommutableIslands();
        /* 
        A = 4
        B = [   [1, 2, 1]
                [2, 3, 4]
                [1, 4, 3]
                [4, 3, 2]
                [1, 3, 10]  ]

A : 3
B : 
[
  [1, 2, 10]
  [2, 3, 5]
  [1, 3, 9]
]
        */
        int numIslands = 4;
        ArrayList<ArrayList<Integer>> bridges = new ArrayList<ArrayList<Integer>>();
        bridges.add(new ArrayList<Integer>(Arrays.asList(1, 2, 1)));
        bridges.add(new ArrayList<Integer>(Arrays.asList(2, 3, 2)));
        bridges.add(new ArrayList<Integer>(Arrays.asList(3, 4, 4)));
        bridges.add(new ArrayList<Integer>(Arrays.asList(1, 4, 3)));
        // bridges.add(new ArrayList<Integer>(Arrays.asList(1, 4, 3)));
        System.out.println("Solution: "+sol.solve(4,bridges));
    }
    

    int minKey(int key[], Boolean mstSet[],int V) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int v = 0; v < V; v++) 
            if (mstSet[v] == false && key[v] < min) { 
                min = key[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 

    public int solve(int numIslands, ArrayList<ArrayList<Integer>> bridges) {
        this.bridges=bridges;
        this.numIslands=numIslands;
        Collections.sort(bridges,new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> a,ArrayList<Integer> b) {
                return a.get(2)-b.get(2);
            }
        });

        for(ArrayList<Integer> bridge : bridges) {
            System.out.println(bridge);
        }

        this.parent = new int[numIslands];
        // this.rank = new int[numIslands];

        for(int i=0;i<numIslands;i++) {
            parent[i] = i;
            // rank[i] = 1;
        }

        int count = 0;
        int edges = 0;
        for(ArrayList<Integer> bridge : bridges) {
            int u = bridge.get(0)-1;
            int v = bridge.get(1)-1;
            if(findParent(u)!= findParent(v)) { // different parents no cycle
            // if(parentNonRecursive(u)!= parentNonRecursive(v)) { 
                unionbest(u,v);
                // unionWithOutChildren(u,v);
                count+=bridge.get(2);
                edges+=1;
            }
            if(edges == numIslands-1)
                break;
        }

        return count;
    }

    void unionbest(int u,int v) {
        int parent1 = findParent(u);
        int parent2 = findParent(v);
        // updates parents 
        parent[parent1] = parent2;
    }

    int findParent(int u) {

        if(u != parent[u]) {
            u = parent[u];
        }
        return parent[u];
    }

    private void unionWithOutChildren(int u, int v) {
        int parentU = parentNonRecursive(u);
        int parentV = parentNonRecursive(v);
        if(parentU==u && parentV==v){
            parent[u] = parentU;
            parent[v] = parentU;
        }
        else if(parentU==u)
            parent[parentU]=parentV;
        else if(parentV==v)
            parent[parentV]=parentU;
        else{
            parent[parentV]=parentU;
        }
    }

    private int parentNonRecursive(int x) {
        while(parent[x]!=x)
            x= parent[x];
        
        return parent[x];
    }

}