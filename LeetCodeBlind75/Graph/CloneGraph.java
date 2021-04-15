import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

// Definition for a Node.
//https://leetcode.com/problems/clone-graph/

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
    public void addEdge(Node node) {
        neighbors.add(node);
        node.neighbors.add(this);
    }
}

class CloneGraph {
    Map<Node,Node> map = new HashMap<Node,Node>();
    public Node cloneGraphDFS(Node node) {
        if(node == null)
            return null;
        if(map.containsKey(node))
            return map.get(node);
        Node clonedNode = new Node(node.val);
        map.put(node,clonedNode);
        for(Node adjNode : node.neighbors) {
            if(!map.containsKey(adjNode))
                clonedNode.neighbors.add(cloneGraphDFS(adjNode));
            else
                clonedNode.neighbors.add(map.get(adjNode));
        }
        return clonedNode;
    }

    public Node cloneGraphBFS(Node node) {
        if(node == null)
            return null;
        Queue<Node> queue = new LinkedList<Node>();
        Map<Node,Node> map = new HashMap<Node,Node>();
        queue.add(node);
        Node clonedNode = new Node(node.val);
        map.put(node,clonedNode);
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            Node clonedCurr = map.get(curr);
            for(Node adjNode : curr.neighbors) {
                if(!map.containsKey(adjNode)) {
                    queue.add(adjNode);
                    Node clonedAdjNode = new Node(adjNode.val);
                    clonedCurr.neighbors.add(clonedAdjNode);
                    map.put(adjNode,clonedAdjNode);
                } else {
                    clonedCurr.neighbors.add(map.get(adjNode));
                }
            }
        }
        return clonedNode;
    }


    public static void main(String[] args) {
        CloneGraph sol = new CloneGraph();
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        one.addEdge(two);
        one.addEdge(three);
        two.addEdge(three);
        Node clonedOne = sol.cloneGraph(one);
    }
}