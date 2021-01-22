import java.util.*;

class ConcatenatedWords {
    //https://leetcode.com/problems/concatenated-words/submissions/
    TrieNode root;
    Set<String> set;
    public class TrieNode {
        Map<Character,TrieNode> alphabets;
        boolean isWord;
        public TrieNode() {
            alphabets = new HashMap<Character,TrieNode>();
            isWord = false;
        }
    }

/** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = this.root;
        for(int i=0;i<word.length();i++) {
            char ch = word.charAt(i);
            if(!node.alphabets.containsKey(ch))
                node.alphabets.put(ch,new TrieNode());
            node = node.alphabets.get(ch);
        }
        node.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = this.root;
        for(int i=0;i<word.length();i++) {
            char ch = word.charAt(i);
            if(node.alphabets.containsKey(ch))
                node = node.alphabets.get(ch);
            else
                return false;
        }
        return node.isWord;       
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = this.root;
        for(int i=0;i<prefix.length();i++) {
            char ch = prefix.charAt(i);
            if(node.alphabets.containsKey(ch))
                node = node.alphabets.get(ch);
            else
                return false;
        }
        return true;               
    }
    
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        this.set = new HashSet<String>();
        for(String word:words) {
            set.add(word);
        }
        //this.set = new HashSet<String>(Arrays.asList(words));
        List<String> result = new ArrayList<String>();
        for(String word:words) {
            if(helper(word))
                result.add(word);
        }
        return result;
    }
    
    public boolean helper(String word) {
        for(int i=1;i<=word.length()-1;i++) {
            int n = word.length();
            String sub1 = word.substring(0,i);
            String sub2 = word.substring(i,n);
            if(set.contains(sub1) && set.contains(sub2))
            {
                return true;               
            }
            if(set.contains(sub1) && helper(sub2)) {
                return true;
            }
            else {
               continue;
            }                    
        }   
        return false;
    }
    
}