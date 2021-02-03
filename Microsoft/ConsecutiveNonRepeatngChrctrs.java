import java.util.*;
/*
https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
*/
class ConsecutiveNonRepeatngChrctrs {

    public static void main(String[] args) {
        String string = new String("j?qg??b");
        String string2 = new String("?zs");
        String string3 = new String("?");
        String string4 = new String("a");
        ConsecutiveNonRepeatngChrctrs solution = new ConsecutiveNonRepeatngChrctrs();
        
        System.out.println("Solution: "+solution.modifyString(string));
        System.out.println("Solution: "+solution.modifyString(string2));
        System.out.println("Solution: "+solution.modifyString(string3));
        System.out.println("Solution: "+solution.modifyString(string4));
    }

    public String modifyString(String s) {
        StringBuilder result = new StringBuilder();
        int n = s.length();
        if(n == 1)
            return s.equals("?")? "a":s;
        for(int i=0;i<n;i++){
            if(s.charAt(i) == '?'){
                char replacement = 'a';
                if(i == 0 ){
                    while(replacement == s.charAt(i+1)){
                        replacement++;
                    }
                } else if(i == n-1) {
                    while(replacement == result.charAt(i-1)){
                        replacement++;
                    }                    
                } else {
                    while(replacement == result.charAt(i-1) || replacement == s.charAt(i+1)){
                        replacement++;
                    }                      
                }  
                result.append(replacement);          
            } else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }
}