import java.util.*;
/* 
https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/
Solution: https://medium.com/algorithm-and-datastructure/reorder-routes-to-make-all-paths-lead-to-the-city-zero-6057559f5fb6
    0->1->3<-2
    |<-4->5
Output = 3
    In the above graph we need to change edges 0->1 and 1->3 and 4->5 to make all nodes reach 0
    Lets start my finding all nodes that can reached from node 0, i.e., 1,3
    Find all the violations to visit all the cities from the capital city(‘0’).

    What is the violations? 
    Violations are direction from city to the capital direction or child to parent. eg: 4->1 and 2->3
    connections-violations is the answer
    To find violations we treat whole graph as bidirectional and start traversing from 0 using bfs
    Everytime we visit a child and it doesnot belong to paths from the directional graph (pathsOrder is set of all directional edges)
        it is a violation, so we increment violations
    Once we come out of bfs we have all violations and we can get the output
*/

class ReorderRoutes {
    int violations;

    public int minReorder(int n, int[][] connections) {
        violations=0;
        Set<String> pathsOrder=new HashSet<>();
        for(int[] p:connections){
            int a=p[0];
            int b=p[1];
            pathsOrder.add(a +"#"+ b);
        }
        // System.out.println("pathsOrder: "+pathsOrder);

        List<Integer>[] graph=new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i]=new ArrayList<>();
        }
        buildGraph(graph,connections);
        for(int i=0;i<n;i++) {
            // System.out.println("i: "+i);
            // System.out.println(graph[i]);

        }
        boolean[] visited=new boolean[n];

        bfs(graph,visited,pathsOrder);

        // System.out.println("connections.length: "+connections.length);
        // System.out.println("violations: "+violations);
        return connections.length-violations;
    }

    private void bfs(List<Integer>[] graph, boolean[] visited,Set<String> paths) {
        Queue<Integer> queue=new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()){
            int v=queue.poll();
            // System.out.println("v: "+v);
            if(!visited[v]){
                List<Integer> childs=graph[v];
                for(int child:childs){
                    if(!visited[child]){
                        // System.out.println("child: "+child);
                        String str= v +"#"+ child;
                        if(!paths.contains(str)) {
                            // System.out.println("str: "+str);
                            violations++;
                        }
                        queue.add(child);
                    }

                }
                visited[v]=true;
            }
        }

    }

    private void buildGraph(List<Integer>[] graph,int[][] con) {
        for(int[] v:con){
            graph[v[0]].add(v[1]);
            graph[v[1]].add(v[0]);
        }
    }

    public static void main(String[] args) {
        ReorderRoutes sol = new ReorderRoutes();
        int[][] connections = new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}};
        System.out.println(sol.minReorder(6, connections));
    }
}