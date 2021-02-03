import java.util.*;

/* 
https://leetcode.com/problems/reverse-words-in-a-string/

Frist trim the string
then traverse the string 
increment left until char is space
right = left
increment left until char is not space
append substring(left,right) to result
append space
*/

class ReverseWordsInString {

    public static void main(String[] args) {
        String string = new String("the sky is blue");
        String string2 = new String("  hello   world  ");
        ReverseWordsInString solution = new ReverseWordsInString();
        
        System.out.println("Solution: "+solution.reverseWords(string));
        System.out.println("Solution: "+solution.reverseWords(string2));
    }

    // stirng buffer appends are faster
    public String reverseWords(String s) {
        s=s.trim();
        StringBuffer result = new StringBuffer();
        int left=s.length()-1;
        while(left>=0) {
            while(left>=0 && s.charAt(left)==' '){
                left--;
            }
            int right = left; 
            while(left>=0 && s.charAt(left)!=' '){
                left--;
            }
            if(left>=-1)
                result.append(s.substring(left+1,right+1));
            if(left>-1)
                result.append(" ");
        }
        return result.toString();
    }

    public String reverseWordsMoreSpace(String s) {
        s=s.trim();
        String[] words = s.split("\\s+");
        String result = "";
        int n = words.length;
        for(int i=n-1;i>=0;i--) {
            result+=words[i];
            if(i>0)
                 result+=" ";
        }
        return result;
    }
    
}