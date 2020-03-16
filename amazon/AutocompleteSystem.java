//https://leetcode.com/explore/interview/card/amazon/81/design/3000/
class Node {
    String sentence;
    int times;
    Node(String sentence,int times) {
        this.sentence = sentence;
        this.times = times;
    }
}
    
class Trie {
    Trie[] branches = new Trie[27];
    int times;      
}

class AutocompleteSystem {    
    private  String cur_sent = "";
    private Trie root;
    
    public void insert(Trie t,String st,int times) {
        for(int i=0;i<st.length();i++) {
            if (t.branches[toInt(st.charAt(i))] == null) {
                t.branches[toInt(st.charAt(i))] = new Trie();
            }
            t=t.branches[toInt(st.charAt(i))];
        }
        t.times+=times;
    }
    
    public int toInt(char c) {
            return c == ' '?26:c-'a';
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        for(int i=0;i<sentences.length;i++) {
            insert(root,sentences[i],times[i]);
        }
    }
    
    public List<Node> lookup(Trie t,String st) {
        List<Node> list = new ArrayList<Node>();
        for(int i=0;i<st.length();i++) {
            if(t.branches[toInt(st.charAt(i))] == null)
                return new ArrayList<>();
            t = t.branches[toInt(st.charAt(i))];
        }
        traverse(t,list,st);
        return list;
    }
    
    public void traverse(Trie t,List<Node> list,String st) {
        if(t.times>0) list.add(new Node(st,t.times));
        for(char i='a';i<='z';i++) {
            if(t.branches[i-'a'] != null) {
                traverse(t.branches[i-'a'],list,st+i);
            }
        }
        if(t.branches[26] != null) {
            traverse(t.branches[26],list,st+' ');
        }
    }
    
    public List<String> input(char c) {
        List<String> res = new ArrayList<String>();
       if(c == '#') {
           insert(root,cur_sent,1);
           cur_sent = "";
       } else {
           cur_sent +=c;
           List<Node> list = lookup(root,cur_sent);
           Collections.sort(list,
              (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
           for(int i=0;i<Math.min(3,list.size());i++) {
               res.add(list.get(i).sentence);
           }
       }
        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */