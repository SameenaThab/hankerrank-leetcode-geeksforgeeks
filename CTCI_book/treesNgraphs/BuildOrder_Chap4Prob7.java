import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

class Node {
    String name;
    List<Node> children;
    int incoming;
    Node(String name)
    {
        this.name=name;
        this.children = new ArrayList<Node>();
        int incoming = 0;
    }

    void addChild(Node node) {
        children.add(node);
    }

    void addIncoming() {
        this.incoming++;
    }

    void substractIncoming() {
        this.incoming--;
    }
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
        node2.addIncoming();
    }
 }


public class  BuildOrder_Chap4Prob7 {  

    Graph graph;

    BuildOrder_Chap4Prob7(Graph graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {

        Graph graph = new Graph();

        String[] projects = new String[]{"a","b","c","d","e","f","g","h"};
        String[][] dependencies = new String[][]{
            {"f","c"},
            {"f","a"},
            {"f","b"},
            // {"b","f"}, //adds cycle
            {"b","a"},
            {"b","e"},
            {"b","h"},
            {"a","e"},
            {"d","g"}
        };

        // String[] projects = new String[]{"a","b","c","d","e","f"};
        // String[][] dependencies = new String[][]{
        //     {"a","d"},
        //     {"b","d"},
        //     // {"b","f"}, //adds cycle
        //     {"d","c"},
        //     {"f","a"},
        //     {"f","b"}
        // };

        for(String proj:projects){
            graph.getOrCreateNode(proj);
        }

        for(String[] dependency:dependencies) {
            graph.addEdge(dependency[0], dependency[1]);
        } 

        BuildOrder_Chap4Prob7 sol = new BuildOrder_Chap4Prob7(graph);
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
        List<Node> result = new ArrayList<Node>();
        for(Node node: graph.nodes) {
            if(node.incoming == 0) {
                result.add(node);
            }
        }
        // System.out.println(result.size());
        int index = 0;

        while(index < result.size()) {
            Node toBeProcessed = result.get(index);
            for(Node node:toBeProcessed.children) {
                node.substractIncoming();
                if(node.incoming == 0)
                    result.add(node);
            }
            index++;
        }
        if(result.size() == graph.nodes.size()) // all nodes have been processed
            return result;
        else // index >= result.size and not all nodes were processed, so cycled detected
            throw new Exception("Not possible to build order");
    }

}
