import java.util.*;
/*
https://www.interviewbit.com/problems/redundant-braces/
 */

/* Notes:
Each braces pair should be associated with only one operator
((a+b)) "+" operator has two paranthesis -> so redundant
((a/b)*((a+b)))

push all open braces and operators
when closed brace encountered, pop the stack , 
if popped element not operator then redundant , therefore return 1
pop the stack again , in order to remove corresponding open brace
eg: ((a+b))
stack : (
        ((
        ((+
        pop '+' and '('
*/
public class RedundantBraces {

    public static void main(String[] args) {
        RedundantBraces sol = new RedundantBraces();

        System.out.println("Solution: "+sol.braces("((a+b))"));
        System.out.println("Solution: "+sol.braces("(a*b)+(b*(d+(a)))"));
        System.out.println("Solution: "+sol.braces("(a + (a + b))"));

    }

    public int braces(String expression) {
        Stack<Character> stack = new Stack<Character>();
        for(char ch:expression.toCharArray()) {
            if(isOperator(ch) || isOpenBraces(ch)) {
                stack.push(ch);
            }
            if(isClosedBraces(ch)) {
                if(isOpenBraces(stack.pop())) //must operator for non redundant
                    return 1; // redundant
                stack.pop(); // remove the corresponding open brace
            }
        }
        return 0;
    }

    private boolean isOpenBraces(char ch) {
        return ch == '(' || ch == '{' || ch == '[' || ch == '<';
    }

    private boolean isClosedBraces(char ch) {
        return ch == ')' || ch == '}' || ch == ']' || ch == '>';
    }

    private boolean isOperator(char ch) {
        return ch == '*' || ch == '+' || ch == '-' || ch == '/';
    }
}
