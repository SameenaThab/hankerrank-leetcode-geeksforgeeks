import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// https://www.geeksforgeeks.org/vertical-order-traversal-in-strings/

public class VerticalWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter String");     
        String st = scanner.nextLine();
        List<String> result = printVertically(st);
        for(String s:result)
            System.out.println(s);
    }

    public static List<String> printVertically(String s) {
        List<String> newWords = new ArrayList<String>();
        String[] words = s.split(" ");
        int max = 0;
        for(String word:words) {
            max = Math.max(word.length(),max);
        }
        for(int i=0;i<max;i++) {
            StringBuffer sb = new StringBuffer();
            int count = 0;
            for(String word:words) {
                if(word.length()<=i) {
                    count++;
                } else {
                    while(count!=0) {
                       sb.append(" ");
                        count--;
                    }
                    sb.append(word.charAt(i));
                }
            }
            String st = sb.toString();
            newWords.add(st);
        }
        return newWords;
    }
}