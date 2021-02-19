import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class CanFinish {
    enum Status {
        PROCESSING,COMPLETE,UNVISITED
    }

    class GraphNode {
        int val;
        List<GraphNode> neighbors;
        Status status;

        GraphNode(int val) {
            this.val = val;
            this.neighbors = new ArrayList<GraphNode>();
            this.status = Status.UNVISITED;
        }

        void addEdge(GraphNode node) {
            neighbors.add(node);
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer,GraphNode> nodes = new HashMap<Integer,GraphNode>();
        for(int i=0;i<numCourses;i++) {
            nodes.put(i,new GraphNode(i));
        }
        for(int[] prereq : prerequisites) {
            GraphNode course = nodes.get(prereq[0]);
            GraphNode prereqCourse = nodes.get(prereq[1]);
            course.addEdge(prereqCourse);
        }

        for(Entry<Integer,GraphNode> entry : nodes.entrySet()) {
            GraphNode node = entry.getValue();
            System.out.print("val: "+node.val);
            System.out.print(" prerequisites: ");
            for(GraphNode adjNode : node.neighbors) {
                System.out.print(adjNode.val+" "); 
            }
            System.out.println();
        }

        return !hasCycle(nodes);
    }

    private boolean hasCycle(Map<Integer, GraphNode> nodes) {
        for(Entry<Integer,GraphNode> entry : nodes.entrySet()) {
            GraphNode node = entry.getValue();
            if(node.status == Status.UNVISITED && hasCycle(node)) 
                return true;
        }
        return false;
    }

    private boolean hasCycle(GraphNode node) {
        if(node.status == Status.PROCESSING)
            return true;
        node.status = Status.PROCESSING;
        for(GraphNode prereq : node.neighbors) {
            if(prereq.status != Status.COMPLETE && hasCycle(prereq))
                return true;
        }
        node.status = Status.COMPLETE;
        return false;
    }

    public static void main(String[] args) {
        CanFinish sol = new CanFinish();
        int[][] prerequisites = new int[][]{{1,0}};
        System.out.println("Can finish: "+sol.canFinish(2, prerequisites));
        System.out.println("Can finish: "+sol.canFinish(2, new int[][]{{1,0},{0,1}}));
    }
}