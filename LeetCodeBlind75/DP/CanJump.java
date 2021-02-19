import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class CanJump {
    //time limit exceeded
    public boolean canJumpBFS(int[] nums) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        queue.add(0);
        while(!queue.isEmpty()) {
            int curr = queue.poll();
            // System.out.println(curr);
            if(curr == nums.length-1)
                return true;
            int value = nums[curr];
            while(value!=0) {
                curr++;
                if(!visited.contains(curr) && curr<nums.length)
                    queue.add(curr);
                value--;
            }
        }
        return false;
    }

    public boolean canJumpRecursive(int[] nums) {
        int[] mem = new int[nums.length];
        Arrays.fill(mem,-1);
        mem[nums.length-1]=1;
        // System.out.println(Arrays.toString(mem));
        return canJumpRecursive(nums,0,mem);
    }

    /* 
    Recursive without mem will have time complexity of 2^n
    If index is nums.length-1 , then we reached.
    For every index we try all possible index from currIndex+1 to currIndex+nums[currIndex]
    Optimization here would -> but doesnot make mucuh difference
    instead of trying indexes from currIndex+1 to currIndex+nums[currIndex]
    we do currIndex+nums[currIndex] to currIndex+1, the farthest the better chance of reaching
   
    Time complexity : O(n^2).
    For every element in the array, say i, we are looking at the next nums[i] elements to its right aiming to find a GOOD index.
     nums[i] can be at most n, where n is the length of array nums.
    Space complexity : O(2n). First n originates from recursion. Second n comes from the usage of the memo table.
   
    */
    private boolean canJumpRecursive(int[] nums, int index, int[] mem) {
        // System.out.println(index);
        // if(index == nums.length-1) // we set mem[nums.len-1]=1
        //     return true;
        if(mem[index] != -1)
            return mem[index]==1?true:false;
        int farthest = Math.min(index+nums[index],nums.length-1);
        // for(int i=index+1;i<=farthest;i++) {
        //     if(canJumpRecursive(nums,i,mem)) {
        //         mem[index]= 1;
        //         return true;
        //     }
        // }

        // Optmization
        for(int i=farthest;i>index;i--) {
            if(canJumpRecursive(nums,i,mem)) {
                mem[index]= 1;
                return true;
            }
        }
        mem[index]=0;
        return false;
    }

    /* 
    Iterative Time = O(n^2), space = O(n), Since no recursion stack    
    */

    public boolean canJumpIterative(int[] nums) {
        int[] mem = new int[nums.length];
        Arrays.fill(mem,-1);
        mem[nums.length-1]=1;
        for(int curr = nums.length-2;curr>=0;curr--) {
            int farthest = Math.min(nums[curr]+curr,nums.length-1);
            for(int newPos=farthest;newPos>curr;newPos--) {
                if(mem[newPos] == 1) {
                    mem[curr] = 1;
                    break;
                }
            }
        }
        return mem[0]==1;
    }

    /* 
    Greedy Approach - best and optimal
    Time = O(n)
    Space = O(1)
    Once we have our code in the bottom-up state, 
    we can make one final, important observation.
    From a given position, when we try to see if we can jump to a GOOD position,
    we only ever use one - the first one (see the break statement).
    In other words, the left-most one.
    If we keep track of this left-most GOOD position as a separate variable, we can avoid searching for it in the array. Not only that, but we can stop using the array altogether.

    Iterating right-to-left, 
    for each position we check if there is a potential jump that reaches a GOOD index,
    (currPosition + nums[currPosition] >= leftmostGoodIndex).
    If we can reach a GOOD index, then our position is itself GOOD.
    Also, this new GOOD position will be the new leftmost GOOD index.
    Iteration continues until the beginning of the array.
    If first position is a GOOD index then we can reach the last index from the first position.
    */
    public boolean canJumpGreedy(int[] nums) {
        int lastBest = nums.length-1;
        for(int curr = nums.length-2;curr>=0;curr--) {
            if(curr+nums[curr]>=lastBest) {
                lastBest = curr;
            }
        }
        return lastBest==0;
    }

    public static void main(String[] args) {
        CanJump sol = new CanJump();

        // System.out.println("Solution: "+sol.canJumpBFS( new int[] {2,3,1,1,4}));
        // System.out.println("Solution: "+sol.canJumpBFS( new int[] {3,2,1,0,4}));

        System.out.println("Solution: "+sol.canJumpRecursive( new int[] {2,0,0}));
        System.out.println("Solution: "+sol.canJumpRecursive( new int[] {3,2,1,0,4}));

        System.out.println("Solution: "+sol.canJumpIterative( new int[] {2,0,0}));
        System.out.println("Solution: "+sol.canJumpIterative( new int[] {3,2,1,0,4}));
        
        System.out.println("Solution: "+sol.canJumpGreedy( new int[] {2,0,0}));
        System.out.println("Solution: "+sol.canJumpGreedy( new int[] {3,2,1,0,4}));
    }
}