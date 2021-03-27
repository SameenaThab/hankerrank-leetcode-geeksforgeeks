import java.util.*;

//https://leetcode.com/problems/design-add-and-search-words-data-structure/
class WordDictionary {
    TrieNode root;
    class TrieNode {
        boolean isWord;
        Map<Character,TrieNode> alphabets;
        TrieNode() {
            isWord=false;
            alphabets = new HashMap<Character,TrieNode>();
        }
    }
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /* 
    Time complexity: O(M), where MM is the key length. At each step, we either examine or create a node in the trie. That takes only M operations.
    Space complexity: O(M). In the worst-case newly inserted key doesn't share a prefix with the keys already inserted in the trie. We have to add M new nodes, which takes O(M) space.
    */
    public void addWord(String word) {
        TrieNode node = this.root;
        for(char ch:word.toCharArray()) {
            if(!node.alphabets.containsKey(ch)) {
                node.alphabets.put(ch,new TrieNode());
            }
            node = node.alphabets.get(ch);
        }
        node.isWord = true;
    }
    
    public boolean search(String word) {
        return search(word,root);
    }
    
    /* 
    Time complexity: O(M) for the "well-defined" words without dots, where M is the key length, and N is a number of keys, and O(N⋅26^M) for the "undefined" words. 
    That corresponds to the worst-case situation of searching an undefined word {.........}->{M times} which is one character longer than all inserted keys.
    Space complexity: O(1) for the search of "well-defined" words without dots, and up to O(M) for the "undefined" words, to keep the recursion stack.
    */
    public boolean search(String word, TrieNode node) {
        for(int i=0;i<word.length();i++) {
            char ch = word.charAt(i);
            if(ch == '.') {
               for(char key : node.alphabets.keySet()) {
                    TrieNode child = node.alphabets.get(key);
                    if(search(word.substring(i+1), child))
                        return true;
               } 
               return false;
            } else {
                if(!node.alphabets.containsKey(ch)) {
                    return false;
                }
                node = node.alphabets.get(ch);
            }
        }
        return node.isWord;       
    }
}
