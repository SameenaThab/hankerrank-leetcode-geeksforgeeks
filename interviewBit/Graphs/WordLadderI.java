import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;

public class WordLadderI {
    Set<String> dict;
    public static void main(String[] args) {
        WordLadderI sol = new WordLadderI();
        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        String src="hit";
        String dest="cog";
        System.out.println("Solution: "+sol.solve(src,dest,wordList));
    }
    
    public int solve(String src, String dest, ArrayList<String> wordList) {
        this.dict = new HashSet<String>();
        for(String word:wordList) {
            dict.add(word);
        }
        return bfs(src,dest);
    }

    int bfs(String src,String dest) {
        Queue<String> queue = new LinkedList<String>();
        queue.add(src);
        int count = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++)
            {
                String curr = queue.poll();
                System.out.println("curr: "+curr);
                for(int j=0;j<curr.length();j++) {
                    for(char c='a';c<='z';c++) {
                        String newString = curr.substring(0,j)+c+curr.substring(j+1);
                        if(newString.equals(dest))
                            return count+1;
                        if(dict.contains(newString)) {
                            queue.add(newString);
                        }
                        dict.remove(newString);
                    }
                }
            }
            System.out.println("count: "+count);
            count++;
        }
        return count;
    }
}