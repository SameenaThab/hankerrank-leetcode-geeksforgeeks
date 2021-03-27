/* 
https://leetcode.com/problems/verifying-an-alien-dictionary/
*/

class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        int[] alphabetOrder = new int[26];
        for(int i=0;i<order.length();i++) {
            alphabetOrder[order.charAt(i)-'a']=i;
        }
        /*
        transitive property a<=b and b<=c , then a<=c 
        therefore we compare each word with its next word, no need to compare every other word with each word
        we compare words by comparing each character of both words until  char(word1)>char(word2) => (word1>word2)returns false
        we need to take care of case app and apple where null is compared with 'l'
        */
        for(int i=0;i<words.length-1;i++) {
            String word1 = words[i];
            String word2 = words[i+1];
            int first=0,second=0;
            while(first<word1.length() && second<word2.length()) {
                if(alphabetOrder[word1.charAt(first)-'a'] > alphabetOrder[word2.charAt(second)-'a'])
                    return false;  
                else if(alphabetOrder[word1.charAt(first)-'a'] < alphabetOrder[word2.charAt(second)-'a'])
                    break;
                first++;
                second++;
            }
            // word1=apple , word2=app => false bcoz app<apple , app cannot be word1
            if(first<word1.length() && second==word2.length())
                return false;
        }
        return true;
    }
}