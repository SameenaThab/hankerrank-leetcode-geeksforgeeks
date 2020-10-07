import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// 1010

// sameena
// https://medium.com/better-programming/binary-pattern-search-in-javascript-3612860428a3


public class BinaryPatternMatching {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter Binary pattern");     
        String bin = scanner.nextLine();
        System.out.println("Enter String");     
        String st = scanner.nextLine();
        System.out.println(binaryPatternMatching(bin,st));
   }

   public static int binaryPatternMatching(String pattern,String st) {
       String binaryString = convert(st);
    //    System.out.println(binaryString);
    //    int inc = pattern.length();
       int count = 0;
       int diff = binaryString.length()-pattern.length();
       for(int i=0;i<diff;i++) {
           int end = i+pattern.length();
        //    System.out.println(binaryString.substring(i, end));
            if(pattern.equals(binaryString.substring(i, end)))
                count++;
       }
       return count;
   }

   public static String convert(String st) {
       StringBuffer sb = new StringBuffer();
       for(char ch: st.toCharArray()) {
            if(vowel(ch))
             sb.append('0');
            else
             sb.append('1');
       }
       return sb.toString();
   }

    // public static List<String> binaryPatternMatching(String pattern,String st) {
    //     List<String> result = new ArrayList<String>();
    //     int l = 0;
    //     int inc = pattern.length();
    //     int r = l+inc;
    //     while(l<st.length() && r<=st.length()) {
    //         if(match(pattern,st.substring(l,r))) {
    //             result.add(st.substring(l,r));
    //         }
    //         l++;
    //         r=l+inc;
    //     }
    //     return result;
    // }

    // public static boolean match(String p,String st) {
    //     if(p.length() != st.length()) 
    //         return false;
    //     char[] pArr = p.toCharArray();
    //     char[] stArr = st.toCharArray();
    //     for(int i=0;i<pArr.length;i++) {
    //         if( (pArr[i]=='0' && !vowel(stArr[i])) || (pArr[i]=='1' && vowel(stArr[i])) )
    //             return false;
    //     }
    //     return true;
    // }

    public static boolean vowel(char ch) {
        return ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u' ;
    }

}