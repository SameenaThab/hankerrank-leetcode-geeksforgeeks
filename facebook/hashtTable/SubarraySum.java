import java.util.*;

/* 
https://leetcode.com/problems/subarray-sum-equals-k/
*/
class SubarraySum {

/*  
    arr = 1 2 3, k=3
    sums = 1 3 6
    maintains sums array such that sums[i]=sum of element btw [0 to i]
    for each sums[i]-sum[j] will give sum of elements btw [i to j]
    Time: O(n)
    Space: O(n)     
    */
    public int subarraySum1(int[] nums, int k) {
        int[] sums = new int[nums.length+1];
        sums[0]=nums[0]; 
        for(int i=1;i<nums.length;i++) {
            sums[i]=sums[i-1]+nums[i];
        }
        int count = 0;
        for(int start=0;start<sums.length;start++) {
            for(int end=start+1;end<sums.length;end++) {
                if(sums[end]-sums[start] == k)  
                    count++;
            }
        }
        return count;
    }

/*  
    arr = 1 2 3, k=3
    Same approach as above but we donot maitain sums array, instead we calculate on the go
    for each start element and end element we have sum=sum(start to end) if sum ==k then subarray with k sum exists
    Time: O(n)
    Space: O(1)     
    */
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for(int start=0;start<nums.length;start++) {  
            int sum = 0;
            for(int end=start;end<nums.length;end++) {
                sum+=nums[end];
                if(sum == k)  
                    count++;
            }
        }
        return count;
    }

    /* 
    If the cumulative sum(represented by sum[i] (for sum from 0 to ith index) up to two indices is the same, the sum of the elements lying in between those indices is zero.
    eg: arr = 1 1 -1 1 sums = 1 2 1 2  
    Extending the same thought further, if the cumulative sum up to two indices, say i and j is at a difference of k i.e. if sum[i]−sum[j]=k, the sum of elements lying between indices i and j is k.
    Based on these thoughts, we make use of a hashmap which is used to store the cumulative sum up to all the indices possible along with the number of times the same sum occurs.
    We store the data in the form: (sum_i, no. of occurrences of sum_i)
    We traverse over the array numsnums and keep on finding the cumulative sum.
    Every time we encounter a new sum, we make a new entry in the hashmap corresponding to that sum. If the same sum occurs again, we increment the count corresponding to that sum in the hashmap.
    Further, for every sum encountered, we also determine the number of times the sum−k has occurred already, since it will determine the number of times a subarray with sum k has occurred up to the current index. We increment the count by the same amount.
    After the complete array has been traversed, the countcount gives the required result.
    */
    public int subarraySumWithMap(int[] nums, int k) {
        Map<Integer,Integer> sums = new HashMap<Integer,Integer>();
        int sum = 0;
        int count = 0;
        sums.put(0,1); // so that if sum = k  exists btw 0 to ith element
        for(int num:nums) {
            sum+=num;
            if(sums.containsKey(sum-k))
                count+=sums.get(sum-k);
            sums.put(sum,sums.getOrDefault(sum, 0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarraySum sol = new SubarraySum();
        System.out.println("solution: "+sol.subarraySum1(new int[]{-1,-1,1},0));
    }
}