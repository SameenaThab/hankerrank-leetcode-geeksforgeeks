class AlienOrder {
    /**
     * 3 Steps
     * Step 1: 
     * - Each Character is a node in the graph. 
     * Step 2:
     * - Compare two words adjacent to each other in the sorted list
     * - Given two words algorithm, alphabet traverse until the chars do not match 
     * - Create edge between first mismatched character g->p , it means g comes first then p
     * -  When all the pairs of adjacent lists are compared , we get a graph of characters, with edges if one character preceeds other
     * Step 3:
     * Do BFS or DFS to find the order
     * BFS
     *  - In Bfs first vist the node with no inbound edges that means the characters which do not have any preceeding characters
     *  - ONce the node is visited,decrement the inbound count for child/proceeding characters
     *  - Add visited node/char to the result
     * DFS
     *  - Since In DFS we mark the node completely visited , when all children are visited
     *  - So we will created edges btw graphs node in reverse order
     *  - eg: algorithm, alphabet we create p->g
     *  - SO when we start dfs(p), result will be "gp" as ,children are visited first.
    */
    public String alienOrder(String[] words) {
      Map<Character,List<Character>> adjList = new HashMap<>();
        Map<Character,Integer> counts = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(String word:words) {
            for(char ch:word.toCharArray()) {
                adjList.put(ch,new ArrayList<Character>());
                counts.put(ch,0);
            }
        }
        
        for(int i=0;i<words.length-1;i++) {
            String word1 = words[i];
            String word2 = words[i+1];
            // geeksforgeeks,geeks are lexigraphically ordered, larger word comes first
            if(word1.length()>word2.length() && word1.startsWith(word2))
                return ""; //difficult to order
            
            for(int j=0;j<Math.min(word1.length(),word2.length());j++) {
                if(word1.charAt(j) != word2.charAt(j)) {
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    counts.put(word2.charAt(j),counts.get(word2.charAt(j))+1);
                    break;
                }
            }
        }
        
        Queue<Character> queue = new LinkedList<Character>();
        // count value = 0 implies parent node with out any parent to it
        //bfs
        for(Character ch:counts.keySet()) {
            if(counts.get(ch) == 0)
                queue.add(ch);
        }
        while(!queue.isEmpty()) {
            Character curr = queue.poll();
            sb.append(curr);
            for(Character next:adjList.get(curr)) {
                counts.put(next,counts.get(next)-1);
                if(counts.get(next) == 0)
                    queue.add(next);
            }
        }
        if(sb.length()<counts.size())
            return "";
        return sb.toString();
    }

    public String alienOrderUsingDFS(String[] words) {
        Map<Character,List<Character>> reverseList = new HashMap<>();
          for(String word:words) {
              for(char ch:word.toCharArray()) {
                  reverseList.put(ch,new ArrayList<Character>());
              }
          }
          
          for(int i=0;i<words.length-1;i++) {
              String word1 = words[i];
              String word2 = words[i+1];
              // geeksforgeeks,geeks are lexigraphically ordered, larger word comes first
              if(word1.length()>word2.length() && word1.startsWith(word2))
                  return ""; //difficult to order
              
              for(int j=0;j<Math.min(word1.length(),word2.length());j++) {
                  if(word1.charAt(j) != word2.charAt(j)) {
                      reverseList.get(word2.charAt(j)).add(word1.charAt(j));
                      // counts.put(word2.charAt(j),counts.get(word2.charAt(j))+1);
                      break;
                  }
              }
          }
          StringBuilder sb = new StringBuilder("");
          HashMap<Character,Boolean> seen = new HashMap<Character,Boolean>();
          for(char ch:reverseList.keySet()) {
              if(!dfs(ch,reverseList,seen,sb))
                  return "";
          }
          return sb.toString();
      }
      
      private boolean dfs(char node,Map<Character,List<Character>> reverseList,HashMap<Character,Boolean> seen,StringBuilder sb ) {
          if(seen.containsKey(node))
              return seen.get(node);
          seen.put(node,false);
          for(char prev:reverseList.get(node)) {
              if(!dfs(prev,reverseList,seen,sb))
                  return false;
          }
          seen.put(node,true);
          sb.append(node);
          return true;
      }
}