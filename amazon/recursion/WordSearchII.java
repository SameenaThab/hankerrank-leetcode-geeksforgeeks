import java.util.*;

class WordSeachII {

    //https://leetcode.com/problems/word-search-ii/
        TrieNode root;
        char[][] board;
        public class TrieNode {
            Map<Character,TrieNode> alphabets;
            String word;
            public TrieNode() {
                alphabets = new HashMap<Character,TrieNode>();
                word = null;
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
            node.word = word;
            System.out.println(node.word);
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
            return node.word==word;       
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

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
    public List<String> findWords(char[][] board, String[] words) {
        this.root = new TrieNode();
        this.board = board;
        List<String> result = new ArrayList<String>();
        for(String word:words){
            insert(word);
        }
        TrieNode node = this.root;
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                if(node.alphabets.containsKey(board[i][j]))
                    backTrack(i,j,result,node);
            }
        }
        
        return result;
    }
    
    public void backTrack(int row,int col,List<String> result, TrieNode parent) { 
        //System.out.println("character at "+row+" "+col+" "+board[row][col]);
        char ch = board[row][col];
        TrieNode currNode = parent.alphabets.get(ch);
        //System.out.println(node.word);
        if(currNode.word != null) {
            result.add(currNode.word);
            /*inorder to delete duplicates in lists eg: board = [['a','a']] , dict=["a"]*/
            currNode.word = null;
        }
        board[row][col] = '#';
        int[] rows = new int[]{0,0,-1,1};
        int[] cols = new int[]{1,-1,0,0};
        for(int i=0;i<4;i++) {
            int x = rows[i];
            int y = cols[i];
            if(row+x<0 || row+x>=board.length || col+y<0 || col+y>=board[0].length)
            {
                continue;
            }
            //System.out.println("character at "+(row+x)+" "+(col+y)+" "+board[row+x][col+y]);
            if(currNode.alphabets.containsKey(board[row+x][col+y]))
                backTrack(row+x,col+y,result,currNode);
        }
        board[row][col] = ch;
        
        /*pruning , Cut the tree when it is leaf node . Because end of tree already traversed and found words*/
        if(currNode.alphabets.isEmpty()) {
            parent.alphabets.remove(ch);
        }
        
    }
}