import java.util.*;

public class EvalReversePolishNotation {

    public static void main(String[] args) {
        EvalReversePolishNotation sol = new EvalReversePolishNotation();
        ArrayList<String> A = new ArrayList<String>(Arrays.asList("-11","2","+"));
        // ArrayList<String> A = new ArrayList<String>(Arrays.asList("2", "1", "+", "3", "*"));
        System.out.println("Solution: "+sol.evalRPN(A));
    }

    public int evalRPNUsingRegexNotNecessary(ArrayList<String> exp) {
        Stack<Integer> stack = new Stack<Integer>();
        for(String s : exp) {
            if(s.matches("-?\\d+")) {
                stack.push(Integer.parseInt(s));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(eval(num1,num2,s));
            }
        }
        return stack.pop();
    }

    public int evalRPN(ArrayList<String> exp) {
        Stack<Integer> stack = new Stack<Integer>();
        for(String s : exp) {
            if(isOperator(s)) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(eval(num1,num2,s));
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private int eval(int num1, int num2, String s) {
        if(s.equals("/")) {
            return num1/num2;
        } else if(s.equals("*")) {
            return num1*num2;
        } else if(s.equals("+")) {
            return num1+num2;
        } else {
            return num1-num2;
        }
    }

}