import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

/* https://www.youtube.com/watch?v=vZNFOBEfib4&ab_channel=happygirlzt
First we build adjacency matrix for all word in dictionary and then do DFS

hit -> {hot} -> {lot,dot} -> {log} -> {cog}
                          -> {dog} -> {cog}
                          
*/

public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII sol = new WordLadderII();
        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        // ArrayList<String> wordList = new ArrayList<String>(Arrays.asList("hot","dog"));
        // ArrayList<String> wordList = new ArrayList<String>(Arrays.asList("a", "b", "c"));
        // String src="a";
        // String dest="b";
        String src="hit";
        String dest="cog";
        List<List<String>> paths = sol.findLadders(src,dest,wordList);
        System.out.println("Solution");
        for(List<String> path : paths) {
            System.out.println(path);
        }
    }
    
    public List<List<String>> findLadders(String src, String dest, List<String> wordList) {
        Set<String> dict = new HashSet<String>();
        dict.addAll(wordList);
        List<List<String>> paths = new ArrayList<List<String>>();
        if(!dict.contains(dest))
            return paths;
        Map<String,ArrayList<String>> adjMatrix = new HashMap<String,ArrayList<String>>();
        Set<String> startSet = new HashSet<String>();
        startSet.add(src);
        // buildAdjMatrixbfs(startSet,dest,dict,adjMatrix);
        bfs(src,dest,dict,adjMatrix);
        /*  buildAdjMatrixbfsOptimized failed for leetcode */
        // Set<String> endSet = new HashSet<String>();
        // endSet.add(dest);
        // buildAdjMatrixbfsOptimized(startSet,endSet,dest,dict,adjMatrix,false);
        // System.out.println("length: "+adjMatrix.size());
        for(String key:adjMatrix.keySet()) {
            System.out.println("key: "+key+" neighbors: "+adjMatrix.get(key));
        }
        List<String> singlePath = new ArrayList<String>();
        singlePath.add(src);
        dfs(src,dest,singlePath,paths,adjMatrix);
        return paths;
    }

    void dfs(String src,String dest,List<String> singlePath, List<List<String>> paths,Map<String,ArrayList<String>> adjMatrix) {
        
        // System.out.println("src: "+src);
        if(src.equals(dest)) {
            // System.out.println(singlePath);
            paths.add(new ArrayList<String>(singlePath));
            return;
        }
        if(adjMatrix.containsKey(src)){
            for(String neighbor : adjMatrix.get(src)) {
                singlePath.add(neighbor);
                dfs(neighbor,dest,singlePath,paths,adjMatrix);
                singlePath.remove(neighbor); //backTRack
            }
        }
        return;
    }

    void buildAdjMatrixbfs(Set<String> startSet,String dest,Set<String> dict,Map<String,ArrayList<String>> adjMatrix) {
        // System.out.println("before: "+startSet);
        if(startSet.size() == 0)
            return;
        dict.removeAll(startSet);
        // System.out.println("after: "+startSet);
        boolean found = false;
        Set<String> temp = new HashSet<String>();
        for(String curr:startSet) {
            for(int j=0;j<curr.length();j++) {
                for(char c='a';c<='z';c++) {
                    String newString = curr.substring(0,j)+c+curr.substring(j+1);
                    if(dict.contains(newString)) {
                        // System.out.println("newString: "+newString);
                        if(newString.equals(dest)){
                            // System.out.println("here here..");
                            found = true;
                        }
                        temp.add(newString);
                        ArrayList<String> neighbors = adjMatrix.getOrDefault(curr, new ArrayList<String>());
                        neighbors.add(newString);
                        adjMatrix.put(curr,neighbors);
                    }
                }
            }
        }
        if(!found)
            buildAdjMatrixbfs(temp,dest,dict,adjMatrix);
    }

    void bfs(String src,String dest,Set<String> dict,Map<String,ArrayList<String>> adjMatrix) {
        Set<String> currSet = new HashSet<String>();
        currSet.add(src);
        boolean found = false;
        Set<String> nextSet = new HashSet<String>();
        while(true) {
            dict.removeAll(currSet);
            for(String curr:currSet) {
                // System.out.println("curr: "+curr);
                for(int j=0;j<curr.length();j++) {
                    for(char c='a';c<='z';c++) {
                        String newString = curr.substring(0,j)+c+curr.substring(j+1);
                        if(dict.contains(newString)) {
                            // System.out.println("newString: "+newString);
                            if(newString.equals(dest)){
                                // System.out.println("here here..");
                                found = true;
                            }
                            nextSet.add(newString);
                            ArrayList<String> neighbors = adjMatrix.getOrDefault(curr, new ArrayList<String>());
                            neighbors.add(newString);
                            adjMatrix.put(curr,neighbors);
                        }
                    }
                }
            }
            System.out.println(nextSet);
            if(found || nextSet.isEmpty()) return;
            Set<String> temp = currSet;
            currSet = nextSet;
            nextSet = temp;
            // nextSet.clear();
        }
    }

// did not work in leetcode
    // void buildAdjMatrixbfsOptimized(Set<String> startSet,Set<String> endSet,String dest,Set<String> dict,Map<String,ArrayList<String>> adjMatrix,boolean reverse) {
    //     System.out.println("before: "+startSet);
    //     if(startSet.size() == 0)
    //         return;
    //     if(startSet.size() > endSet.size()) {
    //         buildAdjMatrixbfsOptimized(endSet,startSet,dest,dict,adjMatrix,!reverse);
    //         return;
    //     }
    //     dict.removeAll(startSet);
    //     // System.out.println("after: "+startSet);
    //     boolean found = false;
    //     Set<String> temp = new HashSet<String>();
    //     for(String curr:startSet) {
    //         for(int j=0;j<curr.length();j++) {
    //             for(char c='a';c<='z';c++) {
    //                 String newString = curr.substring(0,j)+c+curr.substring(j+1);
    //                 if(dict.contains(newString)) {
    //                     // System.out.println("newString: "+newString);
    //                     if(endSet.contains(curr)){ //it means we are reaching middle
    //                         // System.out.println("here here..");
    //                         found = true;
    //                     }
    //                     temp.add(newString);
    //                     String key = reverse? newString:curr;
    //                     String val = reverse? curr:newString;
    //                     ArrayList<String> neighbors = adjMatrix.getOrDefault(key, new ArrayList<String>());
    //                     neighbors.add(val);
    //                     adjMatrix.put(key,neighbors);
    //                 }
    //             }
    //         }
    //     }
    //     if(!found)
    //         buildAdjMatrixbfsOptimized(temp,endSet,dest,dict,adjMatrix,reverse);
    // }
}