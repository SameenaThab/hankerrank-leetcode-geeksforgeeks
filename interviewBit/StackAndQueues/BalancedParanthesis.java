import java.util.*;

public class BalancedParanthesis {

    public static void main(String[] args) {
        BalancedParanthesis sol = new BalancedParanthesis();

        System.out.println("Solution: "+sol.solve("(())"));
        System.out.println("Solution: "+sol.solve("(("));

    }

    public int solve(String st) {
        Stack<Character> stack = new Stack<Character>();
        for(char ch:st.toCharArray()) {
            if(ch == '(') {
                stack.push(ch);
            }
            else {
                if(stack.isEmpty())
                    return 0;
                else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty()?  1: 0;
    }
}
