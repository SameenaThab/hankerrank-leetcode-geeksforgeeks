import java.util.*;

class FindLadders {
    //https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/483/
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int minpath = Integer.MAX_VALUE;
        List<List<String>> allPaths = new ArrayList<List<String>>();       
        List<List<String>> result = new ArrayList<List<String>>();
        if(!wordList.contains(endWord))
            return result;
        Queue<Pair<String,List<String>>> queue = new LinkedList<Pair<String,List<String>>>();
        Set<String> visited = new HashSet<String>();
        int n = beginWord.length();
        Map<String,List<String>> allComb = new HashMap<String,List<String>>();
        // construct all Combs
        for(String word: wordList) {
            for(int i=0;i<n;i++) {
                String newWord = word.substring(0,i)+"*"+word.substring(i+1,n);
                List<String> transformations = allComb.getOrDefault(newWord,new ArrayList<String>());
                transformations.add(word);
                allComb.put(newWord,transformations);
            } 
        }
        
        // System.out.println("all strings: ");
        // System.out.println(allComb.toString());
        
        List<String> arr = new ArrayList<String>();
        arr.add(beginWord);
        queue.add(new Pair(beginWord,arr));
        while(!queue.isEmpty()) {
            Pair<String,List<String>> pair = queue.poll();
            String curr = pair.getKey(); 
            // System.out.println("Current word: "+curr);
            List<String> list = pair.getValue(); 
            // System.out.println(list);
            visited.add(curr);
            for(int i =0;i<n;i++) {
                String comb = curr.substring(0,i)+"*"+curr.substring(i+1,n);
                // System.out.println("comb: "+comb);
                for(String word : allComb.getOrDefault(comb,new ArrayList<String>())) {
                    if(word.equals(endWord)) {
                        List<String> l1 = new ArrayList(list); 
                        l1.add(word);
                        allPaths.add(l1);
                        minpath = Math.min(l1.size(),minpath);
                        // System.out.println("at endWord: ");
                        // System.out.println(l1);
                    }
                    if(!visited.contains(word)) {
                        List<String> l2 = new ArrayList(list); 
                        l2.add(word);
                        //System.out.println("at queue: ");
                        //System.out.println(l2);
                       queue.add(new Pair(word,l2)); 
                    }
                }
            }
        }
        
        for(List<String> list : allPaths) {
            if(list.size() == minpath) {
                result.add(list);
            }
        }
        return result;
    }
}