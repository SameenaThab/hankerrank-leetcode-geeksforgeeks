import java.util.*;
import java.text.*;
import java.math.*;
import java.security.KeyStore.Entry;
import java.util.regex.*;
import java.io.*;

public class CoursesPrereq {
    Map<Integer,ArrayList<Integer>> graph;

    public static void main(String[] args) {
        CoursesPrereq sol = new CoursesPrereq();
        /* 
        A = 3
        B = [1, 2]
        C = [2, 3]

        A = 2
        B = [1, 2]
        C = [2, 1]

        A : 3
        B : [ 1, 2, 3 ]
        C : [ 2, 3, 1 ]
        */
        ArrayList<Integer> B = new ArrayList<Integer>(Arrays.asList(1, 1, 2));
        ArrayList<Integer> C = new ArrayList<Integer>(Arrays.asList(2, 3, 3));
        System.out.println("Solution: "+sol.solve(3,B,C));
    }

    public int solve(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
        this.graph = new HashMap<Integer,ArrayList<Integer>>();
        for(int i=0;i<B.size();i++) {
            ArrayList<Integer> prereq = graph.getOrDefault(B.get(i), new ArrayList<Integer>());
            prereq.add(C.get(i));
            graph.put(B.get(i),prereq);                        
        }
        System.out.println(graph);
        int[] mem = new int[A+1];
        Arrays.fill(mem,-1);
        for(int i=0;i<A;i++) {
            Set<Integer> processing = new HashSet<Integer>();
            if(dfs(i,processing,mem) == 0)
                return 0;
        }
        return 1;
    }

    int dfs(Integer course,Set<Integer> processing,int[] mem) {
        // System.out.println("course: "+course);
        // System.out.println("processing: "+processing);
        // System.out.println(processing.contains(course));
        if(mem[course] != -1)
            return mem[course];
        if(processing.contains(course)) {
            // System.out.println("here");
            return 0;
        }

        processing.add(course);
        if(graph.containsKey(course)) {
            for(Integer prereq:graph.get(course)) {
                if(dfs(prereq,processing,mem) == 0) {
                    mem[course] = 0;
                    return 0;
                }          
            }
        }
        processing.remove(course);
        mem[course] = 1;
        return 1;
    }

// NO memorization

    // public int solve(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
    //     this.graph = new HashMap<Integer,ArrayList<Integer>>();
    //     for(int i=0;i<B.size();i++) {
    //         ArrayList<Integer> prereq = graph.getOrDefault(B.get(i), new ArrayList<Integer>());
    //         prereq.add(C.get(i));
    //         graph.put(B.get(i),prereq);                        
    //     }
    //     // System.out.println(graph);
    //     // System.out.println(dfs(1,processing));
    //     for(int i=0;i<A;i++) {
    //         Set<Integer> processing = new HashSet<Integer>();
    //         if(!dfs(i,processing))
    //             return 0;
    //     }
    //     return 1;
    // }

    // boolean dfs(Integer course,Set<Integer> processing) {
    //     // System.out.println("course: "+course);
    //     // System.out.println("processing: "+processing);
    //     // System.out.println(processing.contains(course));
    //     if(processing.contains(course)) {
    //         // System.out.println("here");
    //         return false;
    //     }

    //     processing.add(course);
    //     if(graph.containsKey(course)) {
    //         for(Integer prereq:graph.get(course)) {
    //             if(!dfs(prereq,processing))
    //                 return false;
    //         }
    //     }
    //     processing.remove(course);
    //     return true;
    // }
}