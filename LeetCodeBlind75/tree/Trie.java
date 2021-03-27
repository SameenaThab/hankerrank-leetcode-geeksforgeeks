import java.util.*;
//https://leetcode.com/problems/implement-trie-prefix-tree/
class Trie {

    TrieNode root;

    class TrieNode {

        // R links to node children
        private TrieNode[] links;
        // Map<Character,TrieNode> alphabets; can use map instead of array
        private final int R = 26;
        private boolean isWord;
    
        public TrieNode() {
            links = new TrieNode[R];
        }
    
        public boolean containsKey(char ch) {
            return links[ch -'a'] != null;
        }
        public TrieNode get(char ch) {
            return links[ch -'a'];
        }
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
        }
        public void setWord() {
            isWord = true;
        }
        public boolean isWord() {
            return isWord;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
    }
    
    /* 
    Time complexity : O(m) m = length of word
    Space complexity : O(m)
    */
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = this.root;
        for(char ch : word.toCharArray()) {
            if(!node.containsKey(ch)) {
                node.put(ch,new TrieNode());
            }
            node = node.get(ch);
        }
        node.setWord();
    }
    
    /* 
        Time complexity : O(m) m = length of word
        Space complexity : O(1)

    */
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = this.root;
        for(char ch : word.toCharArray()) {
            if(!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return node.isWord();        
    }
    
    /* 
        Time complexity : O(m) m = length of prefix
        Space complexity : O(1)
    */
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = this.root;
        for(char ch : prefix.toCharArray()) {
            if(!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return true;              
    }
}