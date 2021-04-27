import java.util.*;
/* 
1.Iterate the expression string one character at a time. Since we are reading the expression character by character, we need to be careful when we are reading digits and non-digits.
2.The operands could be formed by multiple characters. A string "123" would mean a numeric 123, which could be formed as: 123 >> 120 + 3 >> 100 + 20 + 3. Thus, if the character read is a digit we need to form the operand by multiplying 10 to the previously formed continuing operand and adding the digit to it.
3.Whenever we encounter an operator such as + or - we first evaluate the expression to the left and then save this sign for the next evaluation.
4.If the character is an opening parenthesis (, we just push the result calculated so far and the sign on to the stack (the sign and the magnitude) and start a fresh as if we are calculating a new expression.
5.If the character is a closing parenthesis ), we first calculate the expression to the left. The result from this would be the result of the expression within the set of parenthesis that just concluded. This result is then multiplied with the sign, if there is any on top of the stack. Remember we saved the sign on top of the stack when we had encountered an open parenthesis? This sign is associated with the parenthesis that started then, thus when the expression ends or concludes, we pop the sign and multiply it with result of the expression. It is then just added to the next element on top of the stack.
*/
class BasicCalculaterI {
    //https://leetcode.com/problems/basic-calculator/
    public int calculate(String s) {
        Stack<Integer> st = new Stack<Integer>();
        int operand = 0;
        int result = 0;
        int sign = 1;
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                //below step for more than one digit operand
                operand = operand*10+ch-'0';
            } else if(ch == '+') {
                //use existing sign and add operand to result
                result += sign*operand;
                //update sign for future operand
                sign = 1;
                //reset operand
                operand = 0;
            } else if(ch == '-') {
                //use existing sign and add operand to result
                result += sign*operand;
                //update sign for future operand
                sign = -1;
                //reset operand
                operand = 0;
            } else if(ch == '(') {
                // new expression push existing result and sign
                st.push(result);
                st.push(sign);
                // new expression reset result
                sign=1;
                result =0;
            } else if(ch == ')') {
                // closing expression evaluated result
                result +=sign*operand;
                // ')' marks end of expression within a set of parenthesis
                 // Its result is multiplied with sign on top of stack
                // as stack.pop() is the sign before the parenthesis
                result*=st.pop();
                // Then add to the next operand on the top.
                // as stack.pop() is the result calculated before this parenthesis
                // (operand on stack) + (sign on stack * (result from parenthesis))
                result += st.pop();
                // Reset the operand
                operand = 0;
            }
        }
        
        return result + (sign * operand);
    }

}