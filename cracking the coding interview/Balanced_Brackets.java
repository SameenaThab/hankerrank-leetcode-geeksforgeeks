import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Balanced_Brackets {
    
    public static boolean isBalanced(String expression) {
        if(expression.length()%2==1)
            return false;
        Stack<Character> st=new Stack<Character>();
        for(char c:expression.toCharArray())
        {
            if(isOpen(c))
                st.push(c);
            else
            {
                if(st.empty())
                    return false;
                if(areSame(st.pop(),c))
                    continue;
                else
                    return false;
            }
        }
        return st.empty();        
    }
    
    public static boolean isOpen(char c)
    {
        if(c=='(' || c=='['||c=='{')
            return true;
        else
            return false;
    }
    
    public static boolean areSame(char c1,char c2)
    {
        if((c1=='(' && c2 ==')') || (c1=='[' && c2 ==']') || (c1=='{' && c2 =='}'))
            return true;
        else
            return false;
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }
    }
}
