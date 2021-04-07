import java.util.*;

/* 
Followup question for SubarraySum.java
Find the total number of subarrays that sums up to target.
We follow the same approach , but instead of putting index as value in prefix Map , count of prefix formed
*/
class SubarraysFormingSum {
    public static int subarraySumTotal(List<Integer> arr, int target) {
        Map<Integer,Integer> prefixSum = new HashMap<Integer,Integer>();
        int currSum = 0;
        int count = 0;
        // including count of a subarray btw [0 to some index] which sums to target
        prefixSum.put(0,1);
        for(int i=0;i<arr.size();i++) {
            currSum+=arr.get(i);
            int complement = currSum-target;
            if(prefixSum.containsKey(complement)) {
                count+= prefixSum.get(complement);
            }
            prefixSum.put(currSum,prefixSum.getOrDefault(currSum,0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        SubarraysFormingSum sol = new SubarraysFormingSum();
        List<Integer> arr = new ArrayList(Arrays.asList(2,3,5,-5,10));
        System.out.println("Input: "+arr);
        System.out.println("For Target 5 no of subarrays formed = "+subarraySumTotal(arr, 5));
        System.out.println("For Target 8 no of subarrays formed =  "+subarraySumTotal(arr, 8));


        List<Integer> arr1 = new ArrayList(Arrays.asList(0,1,-20,-3,30,5,4));
        System.out.println("Input: "+arr1);
        System.out.println("For Target 8 no of subarrays formed =  "+subarraySumTotal(arr1, 8));
    }
}