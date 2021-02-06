import java.util.*;

class ClimbStairs {

    public static void main(String[] args) {
        ClimbStairs sol = new ClimbStairs();
        System.out.println("Solution: "+sol.climbStairs(2));
        System.out.println("Solution: "+sol.climbStairs(3));

        System.out.println("Solution: "+sol.climbStairsMemRecursive(2));
        System.out.println("Solution: "+sol.climbStairsMemRecursive(3));

        System.out.println("Solution: "+sol.climbStairsMemIterative(2));
        System.out.println("Solution: "+sol.climbStairsMemIterative(3));

        System.out.println("Solution: "+sol.climbStairsNoArray(2));
        System.out.println("Solution: "+sol.climbStairsNoArray(3));
    }

    public int climbStairs(int n) {
        if(n==0)
            return 1;
        else if(n<0)
            return 0;
        else
            return climbStairs(n-1)+climbStairs(n-2);
    }

    //climbStairsMemIterative and climbStairsMemRecursive same time O(n) and space complexity O(n)
    public int climbStairsMemIterative(int n) {
        int[] mem = new int[n+1];
        // Arrays.fill(mem,-1);
        mem[0] = 1;
        mem[1] = 1;
        for(int i=2;i<=n;i++) {
            mem[i] = mem[i-1]+mem[i-2];
        }
        return mem[n];
    }

    public int climbStairsNoArray(int n) {
        if(n<=1)
            return 1;
        int a = 1;
        int b = 1;
        for(int i=2;i<=n;i++) {
            int c = a+b;
            a = b;
            b = c;
        }
        return b;
    }

    public int climbStairsMemRecursive(int n) {
        int[] mem = new int[n+1];
        Arrays.fill(mem,-1);
        mem[0] = 1;
        mem[1] = 1;
        return climbStairsMem(n,mem);
    }

    private int climbStairsMem(int n,int[] mem) {
        if(n<0)
            return 0;
        if(mem[n] != -1)
            return mem[n];
        else {
            mem[n] = climbStairsMem(n-1,mem)+climbStairsMem(n-2,mem);
        }
        return mem[n];
    }

}