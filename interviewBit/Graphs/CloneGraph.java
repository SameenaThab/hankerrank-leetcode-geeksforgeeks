import java.util.*;

// https://www.interviewbit.com/problems/clone-graph/

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}

public class CloneGraph {

    public static void main(String[] args) {
        CloneGraph sol = new CloneGraph();
        UndirectedGraphNode root = new UndirectedGraphNode(1);
        UndirectedGraphNode n2 = new UndirectedGraphNode(2);
        UndirectedGraphNode n3 = new UndirectedGraphNode(3);
        UndirectedGraphNode n4 = new UndirectedGraphNode(4);
        UndirectedGraphNode n5 = new UndirectedGraphNode(5);
        UndirectedGraphNode n6 = new UndirectedGraphNode(6);
        sol.addEdge(root,n2);
        sol.addEdge(root,n3);
        sol.addEdge(root,n4);
        sol.addEdge(n2,n5);
        sol.addEdge(n3,n5);
        sol.addEdge(n4,n5);
        sol.addEdge(n5,n6);
        System.out.println("real graph");
        sol.print(root);
        UndirectedGraphNode clone = sol.cloneGraph(root);
        System.out.println("clone graph");
        sol.print(clone);
    }


    public UndirectedGraphNode cloneGraph(UndirectedGraphNode root) {
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(root);
        Map<Integer,UndirectedGraphNode> visited = new HashMap<Integer,UndirectedGraphNode> ();
        UndirectedGraphNode cloneRoot = new UndirectedGraphNode(root.label);
        visited.put(cloneRoot.label,cloneRoot);
        while(!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();
            UndirectedGraphNode cloneCurr = visited.get(curr.label);
            for(UndirectedGraphNode neighbor : curr.neighbors) {
                UndirectedGraphNode cloneNeighbor = visited.getOrDefault(neighbor.label,new UndirectedGraphNode(neighbor.label));
                if(!visited.containsKey(neighbor.label)) {
                    visited.put(cloneNeighbor.label,cloneNeighbor);
                    queue.add(neighbor);
                }
                cloneCurr.neighbors.add(cloneNeighbor);
            }
        }
        return cloneRoot;
    }

    void addEdge(UndirectedGraphNode node1, UndirectedGraphNode node2) {
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
    }

    void levelOrder(UndirectedGraphNode root) {
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(root);
        Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();  
        visited.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                UndirectedGraphNode curr = queue.poll();
                System.out.print(curr.label+" ");
                for(UndirectedGraphNode neighbor:curr.neighbors) {
                    if(!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
            System.out.println();
        }
    }

    void print(UndirectedGraphNode root) {
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.add(root);
        Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();  
        visited.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                UndirectedGraphNode curr = queue.poll();
                System.out.print(curr.label+" - ");
                for(UndirectedGraphNode neighbor:curr.neighbors) {
                    System.out.print(neighbor.label+", ");
                    if(!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
                System.out.println();
            }
        }
    }
}
