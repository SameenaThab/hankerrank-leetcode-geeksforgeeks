import java.util.*;

class FindDuplicate {
    /* 
    Floyd's algorithm consists of two phases and uses two pointers, usually called tortoise and hare.
    In phase 1, hare = nums[nums[hare]] is twice as fast as tortoise = nums[tortoise].
    Since the hare goes fast, it would be the first one who enters the cycle and starts to run around the cycle.
    At some point, the tortoise enters the cycle as well, and since it's moving slower the hare catches the tortoise up at some intersection point.
    Now phase 1 is over, and the tortoise has lost.
    Note: that the intersection point is not the cycle entrance in the general case.
    To compute the intersection point, let's note that the hare has traversed twice as many nodes as the tortoise, i.e. 2d(tortoise)=d(hare), that means
    2(F + a) = F+nC+a, where n is some integer.
    Hence the coordinate of the intersection point is F + a = nC.
    In phase 2, we give the tortoise a second chance by slowing down the hare, 
    so that it now moves with the speed of tortoise: tortoise = nums[tortoise], hare = nums[hare]. The tortoise is back at the starting position, and the hare starts from the intersection point.
    Time: O(n)
    SPace: O(1)
    */
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        //[1(slow,fast),3,4,2,2] -> [1,3(slow),4,2(fast),2] -> [1,3,4,2(slow),2(fast)]
        // need a do while loop bcoz initially slow and fast are same and condition fails before entering normal while loop
        do{
            // System.out.println("slow: "+slow+" fast: "+fast);
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast) ;
        // At this point fat pointer is truck in cycle it loops thru values 4(index 2) and 2(index 4)
        slow = nums[0];
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }

    /* 
    Sorting Appraoch Time: O(nlogn),Space = O(1)
    */
    public int findDuplicateSorting(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }

        return -1;
    }

    /* 
    Using set Time: O(n), Space = O(n)
    */
    public int findDuplicateUsingSet(int[] nums) {
        Set<Integer> seen = new HashSet<Integer>();
        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }

        return -1;
    }
}