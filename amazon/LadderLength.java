//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2982/
class LadderLength {
    Map<String,List<String>> map;
    int n ;
    
    public int helper(Queue<Pair<String,Integer>> queue, Map<String,Integer> visited, Map<String,Integer> otherVisited) {
        Pair<String,Integer> pair = queue.poll();
        String curr = pair.getKey();
        Integer level = pair.getValue();
        for(int i =0;i<n;i++) {
            String newWord = curr.substring(0,i)+"*"+curr.substring(i+1,n);
            for(String word:map.getOrDefault(newWord,new ArrayList<String>())) {
                if(otherVisited.containsKey(word)) {
                    return level+otherVisited.get(word);
                }
                if(!visited.containsKey(word)) {
                    visited.put(word,level+1);
                    queue.add(new Pair(word,level+1));
                }
            }
        }
        
        return -1;
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        if (!wordList.contains(endWord)) {
            return 0;
        }
        map = new HashMap<String,List<String>>();
        n = beginWord.length();
        for(String word:wordList) {
            for(int i =0;i<n;i++) {
                String newWord = word.substring(0,i)+"*"+word.substring(i+1,n);
                List<String> transformations = map.getOrDefault(newWord,new ArrayList<String>());
                transformations.add(word);
                map.put(newWord,transformations);
            }
        }
        
        Queue<Pair<String,Integer>> queue1 = new LinkedList<Pair<String,Integer>>();
        Queue<Pair<String,Integer>> queue2 = new LinkedList<Pair<String,Integer>>();
        Map<String,Integer> visited1 = new HashMap<String,Integer>();
        Map<String,Integer> visited2 = new HashMap<String,Integer>();
        
        queue1.add(new Pair(beginWord,1));
        queue2.add(new Pair(endWord,1));
        visited1.put(beginWord,1);
        visited2.put(endWord,1);
        
        while(!queue1.isEmpty() && !queue2.isEmpty()) {
            int value = helper(queue1,visited1,visited2);
            if(value > -1)
                return value;
            value = helper(queue2,visited2,visited1);
            if(value > -1)
                return value;
        }
        
        return 0;
    }

    // public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
    //     if(!wordList.contains(endWord))
    //         return 0;
    //     Queue<Pair<String,Integer>> queue = new LinkedList<Pair<String,Integer>>();
    //     Set<String> visited = new HashSet<String>();
    //     int n = beginWord.length();
    //     Map<String,List<String>> allComb = new HashMap<String,List<String>>();
    //     // construct all Combs
    //     for(String word: wordList) {
    //         for(int i=0;i<n;i++) {
    //             String newWord = word.substring(0,i)+"*"+word.substring(i+1,n);
    //             List<String> transformations = allComb.getOrDefault(newWord,new ArrayList<String>());
    //             transformations.add(word);
    //             allComb.put(newWord,transformations);
    //         } 
    //     }
        
    //     queue.add(new Pair(beginWord,1));
    //     while(!queue.isEmpty()) {
    //         Pair<String,Integer> pair = queue.poll();
    //         String curr = pair.getKey();
    //         int level = pair.getValue();            
    //         visited.add(curr);
    //         for(int i =0;i<n;i++) {
    //             String comb = curr.substring(0,i)+"*"+curr.substring(i+1,n);
    //             for(String word : allComb.getOrDefault(comb,new ArrayList<String>())) {
    //                 if(word.equals(endWord))
    //                     return level+1;
    //                 if(!visited.contains(word)) {
    //                    queue.add(new Pair(word,level+1)); 
    //                 }
    //             }
    //         }
    //     }
    //     return 0;
    // }
}