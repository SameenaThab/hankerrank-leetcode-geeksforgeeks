import java.util.HashMap;
import java.util.Map;

public class ReverseBits {
    // you need treat n as an unsigned value
    // https://leetcode.com/problems/reverse-bits/solution/

    /* 
    The key idea is that for a bit that is situated at the index i, after the reversion, its position should be 31-i (note: the index starts from zero).

    1. We iterate through the bit string of the input integer, from right to left (i.e. n = n >> 1). To retrieve the right-most bit of an integer, we apply the bit AND operation (n & 1).
    2. For each bit, we reverse it to the correct position (i.e. (n & 1) << power). Then we accumulate this reversed bit to the final result.
    3. When there is no more bits of one left (i.e. n == 0), we terminate the iteration.
    */

    public static void main(String[] args) {
        ReverseBits sol = new ReverseBits();
        System.out.println("Solution: "+sol.reverseBits(010011));
        System.out.println("Solution: "+sol.reverseBits(1000));

        System.out.println("Solution: "+sol.reverseBitsApp3(010011));
        System.out.println("Solution: "+sol.reverseBitsApp3(1000));
    }

    public int reverseBits(int n) {
        int result = 0;
        int power = 31;
        while(n!=0) {
            result += (n & 1) << power;
            n>>=1;
            power -= 1;
        }  
        return result;
    }

    /* 
    The application of memoization can be considered as a response to the follow-up question posed in the description of the problem, which is stated as following:
    If this function is called many times, how would you optimize it?

    To reverse bits for a byte, one could apply the same algorithm as we show in the above approach. Here we would like to show a different algorithm which is solely based on the arithmetic and bit operations without resorting to any loop statement, as following:

    def reverseByte(byte):
        return (byte * 0x0202020202 & 0x010884422010) % 1023
    The algorithm is documented as "reverse the bits in a byte with 3 operations"(http://graphics.stanford.edu/~seander/bithacks.html#ReverseByteWith64BitsDiv) on the online book called Bit Twiddling Hacks by Sean Eron Anderson, where one can find more details.

    Algorithm
    We iterate over the bytes of an integer. To retrieve the right-most byte in an integer, again we apply the bit AND operation (i.e. n & 0xff) with the bit mask of 11111111.
    For each byte, first we reverse the bits within the byte, via a function called reverseByte(byte). Then we shift the reversed bits to their final positions.
    With the function reverseByte(byte), we apply the technique of memoization, which caches the result of the function and returns the result directly for the future invocations of the same input.
    Note that, one could opt for a smaller unit rather than byte, e.g. a unit of 4 bits, which would require a bit more calculation in exchange of less space for cache. It goes without saying that, the technique of memoization is a trade-off between the space and the computation.
    */
    int reverseByte(int byteN, Map<Integer, Integer> cache) {
        if (cache.containsKey(byteN)) {
            return cache.get(byteN);
        }
        int value = (byteN * 0x0202020202 & 0x010884422010) % 1023;
        cache.put(byteN, value);
        return value;
    }

    int reverseBitsMem(int n) {
        int ret = 0, power = 24;
        Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
        while (n != 0) {
            ret += reverseByte(n & 0xff, cache) << power;
            n = n >> 8;
            power -= 8;
        }
        return ret;
    }

    /* 
    Approach 3: Mask and Shift
    The idea can be considered as a strategy of divide and conquer, where we divide the original 32-bits into blocks with fewer bits via bit masking, then we reverse each block via bit shifting, and at the end we merge the result of each block to obtain the final result.
    We can implement the algorithm in the following steps:

    1). First, we break the original 32-bit into 2 blocks of 16 bits, and switch them.

    2). We then break the 16-bits block into 2 blocks of 8 bits. Similarly, we switch the position of the 8-bits blocks

    3). We then continue to break the blocks into smaller blocks, until we reach the level with the block of 1 bit.

    4). At each of the above steps, we merge the intermediate results into a single integer which serves as the input for the next step.
    */
    int reverseBitsApp3(int n) {
        n = (n >> 16) | (n << 16);
        n = ((n & 0xff00ff00) >> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >> 1) | ((n & 0x55555555) << 1);
        return n;
    }
}