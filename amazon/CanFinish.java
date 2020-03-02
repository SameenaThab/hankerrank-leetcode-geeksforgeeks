//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2983/
class CanFinish {
    int numCourses;
    Map<Integer,List<Integer>> graph;
    
    public boolean canFinish(int numCourses, int[][] prereq) {
        this.numCourses = numCourses;
        graph = new HashMap<Integer,List<Integer>>();
        
        for(int i = 0 ;i<prereq.length;i++) {
            int node = prereq[i][0];
            int adj = prereq[i][1];
            List<Integer> neighbors = graph.getOrDefault(node,new ArrayList());
            neighbors.add(adj);
            graph.put(node,neighbors);
        }
        
        //----------- calling BFS ------------
        if(hasCycleBFS())
            return false;
        
        return true;
            
        // ---------call DFS----------------
        
//         boolean[] inrecur = new boolean[numCourses];
//         boolean[] visited = new boolean[numCourses];
        
//         for(int i=0;i<numCourses;i++) {
//             if(hasCycleDFS(i,inrecur,visited,graph))
//                 return false;
//         }
        
//         return true;
    }
    
    boolean hasCycleDFS(int node,boolean[] inrecur,boolean[] visited, Map<Integer,List<Integer>> graph) {
        if(inrecur[node])
            return true;
        if(visited[node])
            return false;
        inrecur[node] = true;
        visited[node] = true;
        for(int u : graph.getOrDefault(node,new ArrayList<Integer>())) {
            if(hasCycleDFS(u,inrecur,visited,graph))
                return true;
        }
        
        //backtrack. Since recursion is over
        inrecur[node] = false;
        
        return false;
    }
    
    boolean hasCycleBFS() {
        int visitedCount = 0;
        int[] inwards = new int[numCourses];
        Arrays.fill(inwards,0);
        for(int i=0;i<this.numCourses;i++) {
            for(int u:graph.getOrDefault(i,new ArrayList<Integer>())) {
                inwards[u]++;
            }
        } 
        
        Queue<Integer> queue = new LinkedList<Integer>();       
        for(int i=0;i<numCourses;i++) {
            if(inwards[i] == 0)
                queue.add(i);
        }
        
        while(!queue.isEmpty()) {
            visitedCount++;
            int node = queue.poll();
            for(int u:graph.getOrDefault(node,new ArrayList<Integer>())) {
                inwards[u]--;
                if(inwards[u] == 0)
                    queue.add(u);
            }
        }
        
        if(visitedCount == numCourses)
            return false;
        else 
            return true;
    }
}