// https://leetcode.com/discuss/interview-question/891658/Postmates-or-OA-or-Divisor-Strings
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class DivisorString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter num");     
        String num = scanner.nextLine();
        System.out.println("Enter k");  
        int k = scanner.nextInt();
        System.out.println(divisorString(num,k));
    }

    private static int divisorString(String str, int k) {
       int i = 0;
       Set<Integer> set = new HashSet<Integer>();
       int count=0;
       int num = Integer.parseInt(str);
       int n = str.length();
       while(i<=n-k) {
            int sub = Integer.parseInt(str.substring(i,i+k));
            if(sub != 0 && num%sub == 0) {
                System.out.println(sub); 
                set.add(sub);
            }
           i++;
       }
       return set.size();
    }

}