import java.util.*;
import java.math.*;
import java.io.*;

/* 
Sort a string according to the frequency of each character; 
if two characters have same frequency, sort in alphabetic order. e.g. given "banana"- frequencies are: b1, a3,n2; final output should then be "bnnaaa"
Map<Character,Integer>
b - 1
a - 3
n - 2
int[] arr
index = ch-'a'
value = freq
List<Pair<>>

COllections.sort(map,new Comparator<Entry>{
    int compare() {
        if(freq)
    }
})
*/



class Solution2 {
    
    class Pair{
        char alphabet;
        int freq;
        Pair(char ch, int freq){
            this.alphabet=ch;
            this.freq=freq;
        }
    }
    public static void main(String[] args) {
        Solution2 sol = new Solution2();
        System.out.println("hello world");
        String result = sol.sort("banana");
        System.out.println(result);
    }

    String sort(String word){
        Pair[] pairs = new Pair[26];
        for(int i=0;i<26;i++) {
            pairs[i] = new Pair((char)('a'+i),0);
        }
        for(char ch:word.toCharArray()) {
            Pair curr = pairs[ch-'a'];
            curr.freq++;
            pairs[ch-'a'] = curr;
        }
        Arrays.sort(pairs,new Comparator<Pair>() {
            @Override
            public int compare(Pair a,Pair b) {
                if(a.freq == b.freq)
                    return a.alphabet-b.alphabet;
                else    
                    return a.freq-b.freq;
            }
        });

        StringBuffer sb = new StringBuffer();
        for(Pair pair:pairs) {
            if(pair.freq != 0){
                int count = pair.freq;
                while(count != 0) {
                    sb.append(pair.alphabet);
                    count--;
                }
            }
        }
        return sb.toString();
    }

}
