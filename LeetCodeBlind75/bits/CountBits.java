import java.util.*;

class CountBits {

    public static void main(String[] args) {
        CountBits sol = new CountBits();
        System.out.println("Solution: "+Arrays.toString(sol.countBits(2)));
        System.out.println("Solution: "+Arrays.toString(sol.countBits(5)));

        System.out.println("Solution: "+Arrays.toString(sol.countBitsDP1(2)));
        System.out.println("Solution: "+Arrays.toString(sol.countBitsDP1(5)));

        System.out.println("Solution: "+Arrays.toString(sol.countBitsDP2(2)));
        System.out.println("Solution: "+Arrays.toString(sol.countBitsDP2(5)));

        System.out.println("Solution: "+Arrays.toString(sol.countBitsDP3(2)));
        System.out.println("Solution: "+Arrays.toString(sol.countBitsDP3(5)));
    }

    /* 
    not optimal others are optimal
    Time = O(n*k) = k is time of hamming weight
    space = O(n)
    */
    public int[] countBits(int num) {
        int[] result = new int[num+1];
        for(int i=0;i<=num;i++) {
            result[i] = hammingWeight(i);
        }
        return result;
    }

    private int hammingWeight(int n) {
        int count = 0;
        while(n != 0) {
            count++;
            n &= n-1;
        }
        return count;
    }

    /* 
    DP and most significant bit(leftmost bit )
    0 - 0
    1 - 1
    2 - 10
    3 - 11
    2 can be obtained by adding 1 to 0 , and 3 can be obtained by adding 1 to 1.
    Similarly, we can generate the results for [4, 7] using [0, 3] as blueprints.
    the hamming weight for such pairs difference by 1. we can use this for DP
    W is hamming weight
    W(x+b) = W(x)+1 where b = 2^k (k is some constant). But b > x 
    eg: W(1) = W(0)+1 => W(0+1) = W(0)+1 => b=2^0 = 1 b>x
        W(2) = W(0)+1 => W(0+2) = W(0)+1 => b=2 b>x
    Time = O(n) 
    space = O(n)
    */
    public int[] countBitsDP1(int num) {
        int[] result = new int[num+1];
        int b = 1, i =0;
        while(b<=num) {
            while(i<b && i+b <= num) {
                result[i+b] = result[i]+1;
                i++;
            }
            b<<=1; // multiple of 2
            i=0;
        }
        return result;
    }

    /* 
    DP and least significant bit(rightmost bit )
    relation between x and x/2
    eg 1: 
    x = 4 = 100
    x/2 = 2 = 10
    eg 2: 
    x = 5 = 101
    x/2 = 2 = 10
    Only diff is the rightmost significant bit and it is x%2 
    for 5 x%2 is 1
    Time = O(n) 
    space = O(n)
    */
    public int[] countBitsDP2(int num) {
        int[] result = new int[num+1];
        for(int i=1;i<=num;i++) {
            result[i] = result[i/2] + i%2; 
        }
        return result;
    }

    /* 
    DP with last set bit (rightmost bit that is set)
    we know from hamming weight problem, that
    x&x-1 gives all the bits of x except the last set bit
    so result[x] = result[x&x-1]+1;
    Time = O(n) 
    space = O(n)
    */
    public int[] countBitsDP3(int num) {
        int[] result = new int[num+1];
        for(int i=1;i<=num;i++) {
            result[i] = result[i & i-1] + 1;
        }
        return result;
    }
}