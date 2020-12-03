import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class  BuildOrderDFSNoGraph_Chap4Prob7 {  

    Map<String,List<String>> parentChild;

    List<String> projects;

    public static void main(String[] args) {
        List<String> projects = new ArrayList<String>() {
            {
                add("a");
                add("b");
                add("c");
                add("d");
                add("e");
                add("f");
            }
        };   
        // dependency matrix a->{b,c} => b and c are dependent on a
        Map<String,List<String>> parentChild = new HashMap<String, List<String>>() {{
            put("f", new ArrayList<String>());
            put("e", new ArrayList<String>());
            put("a", new ArrayList<String>( 
                Arrays.asList("f")));
            put("b", new ArrayList<String>( 
                Arrays.asList("f")));
            put("c", new ArrayList<String>( 
                Arrays.asList("d")));
            put("d", new ArrayList<String>( 
                Arrays.asList("a","b")));
            // put("d", new ArrayList<String>( 
            //         Arrays.asList("a","b","c"))); //cycle detected
        }};

        BuildOrderDFSNoGraph_Chap4Prob7 sol = new BuildOrderDFSNoGraph_Chap4Prob7();
        sol.parentChild = parentChild;
        sol.projects = projects;
        try {
            List<String> result = sol.buildOrder();
            for(String st:result) {
                System.out.print(st+" ");
            }
        } catch(Exception ex) {
            System.out.println("Cycle detected");
        }
    }

    List<String> buildOrder() throws Exception {
        List<String> result = new ArrayList<String>();
        Set<String> visited = new HashSet<String>();
        Set<String> processing = new HashSet<String>();
        for(String st:projects) {
            if(!visited.contains(st))
                dfs(st,result,visited,processing,parentChild); 
        }
        return result;
    }

    void dfs(String project,List<String> result,Set<String> visited, Set<String> processing, Map<String,List<String>> parentChild) throws 
    Exception {
        processing.add(project);
        for(String st:parentChild.get(project)){
            if(processing.contains(st))
                throw new Exception("Not possible to build order");
            if(!visited.contains(st)) {
                dfs(st,result,visited,processing,parentChild);
            }
        }
        visited.add(project);
        result.add(project);
        processing.remove(project);
    }

}
