import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

class Node {
    String name;
    Status status; // instead you can maintain visited and processing hash sets in DFS method
    List<Node> children;
    Node(String name)
    {
        this.name=name;
        this.children = new ArrayList<Node>();
        this.status = Status.BLANK;
    }

    void addChild(Node node) {
        children.add(node);
    }

    void setStatus(Status status) {
        this.status = status;
    }
 }

 enum Status {
     PROCESSING,COMPLETED,BLANK;
 }

 class Graph {
    List<Node> nodes;
    Map<String,Node> map;

    Graph() {
        this.nodes = new ArrayList<Node>();
        this.map = new HashMap<String,Node>();
    }
    Node getOrCreateNode(String name) {
        if(!map.containsKey(name)) {
            Node node = new Node(name);
            nodes.add(node);
            map.put(name,node);
        }
        return map.get(name);
    }

    void addEdge(String name1,String name2) {
        Node node1 = getOrCreateNode(name1);
        Node node2 = getOrCreateNode(name2);
        node1.addChild(node2);
    }
 }


public class  DFSBuildOrder_Chap4Prob7 {

    Graph graph;

    DFSBuildOrder_Chap4Prob7(Graph graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {

        Graph graph = new Graph();
        // String[] projects = new String[]{"a","b","c","d","e","f","g","h"};
        // String[][] dependencies = new String[][]{
        //     {"f","c"},
        //     {"f","a"},
        //     {"f","b"},
        //     // {"b","f"}, //adds cycle
        //     {"b","a"},
        //     {"b","e"},
        //     {"b","h"},
        //     {"a","e"},
        //     {"d","g"}
        // };

        String[] projects = new String[]{"a","b","c","d","e","f"};
        String[][] dependencies = new String[][]{
            {"a","d"},
            {"b","d"},
            // {"b","f"}, //adds cycle
            {"d","c"},
            {"f","a"},
            {"f","b"}
        };

        for(String proj:projects){
            graph.getOrCreateNode(proj);
        }

        for(String[] dependency:dependencies) {
            graph.addEdge(dependency[0], dependency[1]);
        }

        // graph.addEdge("f", "a");
        // graph.addEdge("f", "c");
        // graph.addEdge("f", "b");
        // // graph.addEdge("b","f"); //adds cycle
        // graph.addEdge("c", "a");
        // graph.addEdge("b", "a");
        // graph.addEdge("b", "e");
        // graph.addEdge("b", "h");
        // graph.addEdge("a", "e");
        // graph.addEdge("d", "g");

        // graph.addEdge("f","a");
        // graph.addEdge("f","b");
        // graph.getOrCreateNode("e");
        // graph.addEdge("a","d");
        // graph.addEdge("b","d");
        // graph.addEdge("d","c");

        DFSBuildOrder_Chap4Prob7 sol = new DFSBuildOrder_Chap4Prob7(graph);
        try {
            List<Node> result = sol.buildOrder();
            for(Node node:result) {
                System.out.print(node.name+" ");
            }
        } catch(Exception ex) {
            System.out.println("Cycle detected");
        }

    }

    List<Node> buildOrder() throws Exception{
        Stack<Node> stack = new Stack<Node>();
        for(Node node:graph.nodes) {
            if(node.status == Status.BLANK) {
                dfs(node,stack);
            }
        }
        List<Node> result = new ArrayList<Node>();
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    void dfs(Node node,Stack<Node> stack) throws Exception {
        node.setStatus(Status.PROCESSING);
        for(Node child:node.children) {
            if(child.status == Status.PROCESSING)
                throw new Exception("Cycle Detected");
            if(child.status == Status.BLANK)
                dfs(child,stack);
        }
        // At this point all the neighbors are completed or no neighbors present for the node
        node.setStatus(Status.COMPLETED);
        stack.add(node); // note we are adding the most dependent nodes first to the stack, since stack is LIFO.
    }

}
