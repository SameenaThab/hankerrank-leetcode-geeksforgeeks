class Sum {

    public static void main(String[] args) {
        Sum sol = new Sum();
        System.out.println("Solution: "+sol.getSum(15,2));
        System.out.println("Solution: "+sol.getSum(15,-2));
        System.out.println("Solution: "+sol.getSum(-15,2));
        System.out.println("Solution: "+sol.getSum(-15,-2));
        System.out.println("Solution: "+sol.getSum(2,-3));
        System.out.println("Solution: "+sol.getSumApp2(15,2));
        System.out.println("Solution: "+sol.getSumApp2(15,-2));
        System.out.println("Solution: "+sol.getSumApp2(-15,2));
        System.out.println("Solution: "+sol.getSumApp2(-15,-2));
        System.out.println("Solution: "+sol.getSumApp2(2,-3));
    }

/* 
for signed x,y they are 2*2*2 = 8 combinations [x(-ve,+ve),y(-ve,+ve)=2*2][x>y,x<y =2]
We reduce it to x>y .
eg: x=15,y=2
For Addition:
15          -> 0 0 0 0 1 1 1 1
2           -> 0 0 0 0 0 0 1 0
15^2=13     -> 0 0 0 0 1 1 0 1 =>  x^y is basically sum without carry
15&2<<1=4   -> 0 0 0 0 0 1 0 0 => to get carry we need to do x&y and right shift
continue until carry is 0
13              -> 0 0 0 0 1 1 0 1 
4               -> 0 0 0 0 0 1 0 0 
13^4 = 9        -> 0 0 0 0 1 0 0 1
13&4<<1 = 8     -> 0 0 0 0 1 0 0 0
9^8 = 1         -> 0 0 0 0 0 0 0 1
9&8<<1 = 16     -> 0 0 0 1 0 0 0 0
1^16 = 17       -> 0 0 0 1 0 0 0 1
1&16<<1 = 0     -> 0 0 0 0 0 0 0 0
ANswer is 16
For Substraction:
15          -> 0 0 0 0 1 1 1 1
2           -> 0 0 0 0 0 0 1 0
15^2=13     -> 0 0 0 0 1 1 0 1
x^y is difference without borrow. And borrow = (~x&y)<<1
~15 = -16    -> 0 0 0 1 0 0 0 0  (considering 5 bits)
~x&y<<1      -> 0 0 0 0 0 0 0 0
Stop when borrow is zero

Algorithm:
Simplify problem down to two cases: sum or subtraction of two positive integers: where x > y. Save down the sign of the result.

While carry is nonzero: y != 0:
Current answer without carry is XOR of x and y: answer = x^y.
Current carry is left-shifted AND of x and y: carry = (x & y) << 1.
Job is done, prepare the next loop: x = answer, y = carry.

Return x * sign.

If one has to compute the difference:
While borrow is nonzero: y != 0:
Current answer without borrow is XOR of x and y: answer = x^y.
Current borrow is left-shifted AND of NOT x and y: borrow = ((~x) & y) << 1.
Job is done, prepare the next loop: x = answer, y = borrow.

Return x * sign.
*/

// Time = O(1) and space = O(1)

    public int getSum(int a, int b) {
        int x = Math.abs(a);
        int y = Math.abs(b);
        if(x<y) return getSum(b, a);
        int sign = a>0? 1:-1;
        if(a*b >= 0) {// checking signs
            // both positive or negative, addition
            while(y != 0) {
                int answer = x^y;
                int carry = (x & y)<<1;
                x = answer;
                y = carry;
            }
        } else {
            // difference of two positive integers x - y
            // where x > y
            int answer = x ^ y;
            int borrow = ((~x) & y) << 1;
            x = answer;
            y = borrow;
        }
        return x*sign;
    }

    /* 
    Follow up do not use * for determining sign, then your solution becomes language-specific.
    Different languages represent negative numbers differently.
    Java integer is a number of 32 bits. 31 bits are used for the value. The first bit is used for the sign: if it's equal to 1, the number is negative, if it's equal to 0, the number is positive.
    It uses two's compliment for negative sign (not like 31st bit is 1 for negative number)
    two' compliment of 3 = 101
    To get the two's complement of a negative binary number, the bits are inverted, or "flipped", by using the bitwise NOT operation; 
    the value of 1 is then added to the resulting value, ignoring the overflow which occurs when taking the two's complement of 0.

    two's complement has the advantage that the fundamental arithmetic operations of addition, subtraction, 
    and multiplication are identical to those for unsigned binary numbers (as long as the inputs are represented in the same number of bits as the output, and any overflow beyond those bits is discarded from the result).
    */

    // Time = O(1) and space = O(1)

    public int getSumApp2(int a, int b) {
        while(b != 0) { // no checing signs, as all operations of neg and positive number are same in java due to two's complement
            int answer = a^b;
            int carry = (a & b)<<1;
            a = answer;
            b = carry;
        }
        return a;
    }

}

