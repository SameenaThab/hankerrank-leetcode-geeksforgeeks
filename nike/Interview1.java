// Natarajan  Annamalai to Everyone (3:05 PM)
import java.util.*;
public class Interview1 {
    /* Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid. An input string is valid if:
        a. Open brackets must be closed by the same type of brackets.
        b. Open brackets must be closed in the correct order.
        Example: Input: s = "()" Output: true
    * */
    public static void main(String... args) {
        String[] inputs = {"()", "[{()}]", "[]{}()", "[[{{{}}}()(())]]", "[[{{{}]}()(())]]", "[[{{{}}}()(()]]", "[]{}())", "[]{{}()", "" };
        for (String input: inputs) {
            System.out.println("input: "+input);
            printIsValid(input);
        }
    }

    private static void printIsValid(String input) {
        Stack<Character> stack = new Stack<Character>();
        for(char ch : input.toCharArray()) {
            if(isOpen(ch)) {
                stack.push(ch);
            } else {
                if(stack.isEmpty()) {
                    System.out.println("False");
                    return;                    
                }
                char popped = stack.pop();
                if(!isMatch(popped,ch))   {
                    System.out.println("False");
                    return;
                }
            }
        }
        if(stack.isEmpty())
             System.out.println("True");
        else
             System.out.println("False");
    }

    static boolean isOpen(char ch) {
        return ch=='('||ch=='['||ch=='{';
    }

    static boolean isMatch(char brac1,char brac2) {
        return (brac1=='('&&brac2==')') || (brac1=='['&&brac2==']') || (brac1=='{'&&brac2=='}');
    }

}