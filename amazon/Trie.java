//https://leetcode.com/problems/concatenated-words/

class Trie {
    TrieNode root;
    public class TrieNode {
        Map<Character,TrieNode> alphabets;
        boolean isword;
        public TrieNode() {
            alphabets = new HashMap<Character,TrieNode>();
            isword = false;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode();
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
        node.isword = true;
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
        return node.isword;       
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
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */