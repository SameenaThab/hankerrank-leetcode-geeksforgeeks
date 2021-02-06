// https://leetcode.com/problems/number-of-1-bits/submissions/
class HammingWeight {
    public static void main(String[] args) {
        HammingWeight sol = new HammingWeight();
        System.out.println("Solution: "+sol.hammingWeight(00000000000000000000000000001011));
        System.out.println("Solution: "+sol.hammingWeight(00000000000000000000000010000000));
        // System.out.println("Solution: "+sol.hammingWeight(11111111111111111111111111111101));
        System.out.println("Solution: "+sol.hammingWeight2(00000000000000000000000000001011));
        System.out.println("Solution: "+sol.hammingWeight2(00000000000000000000000010000000));
        // System.out.println("Solution: "+sol.hammingWeight2(11111111111111111111111111111101));
    }

/* 
for every bit in n . do & operation with 1
bit&1 = 1 count++;

*/

// Time = O(1) and space = O(1)

public int hammingWeight(int n) {
    int mask = 1;
    int count = 0;
    for(int i=0;i<32;i++) //integer is stored in 4bytes = 32 bits
    {
        if((n & mask) != 0) {
            count++;
        }
        mask<<=1;
    }
    return count;    
}

/* 
In the binary representation, the least significant 1-bit in n always corresponds to a 0-bit in n - 1.
Therefore, anding the two numbers n and n - 1 always flips the least significant 1-bit in n to 0,
and keeps all other bits the same
eg: n = 4 = 100 , n-1 = 3 = 011
    So n&n-1 gives 
The run time depends on the number of 1-bits in n. In the worst case, all bits in nn are 1-bits. In case of a 32-bit integer, the run time is O(1).
The space complexity is O(1)O(1), since no additional space is allocated.
*/

public int hammingWeight2(int n) {
    int count = 0;
    while(n!=0) {
        count++;
        n = n & n-1;
    }
    return count;
}

}

