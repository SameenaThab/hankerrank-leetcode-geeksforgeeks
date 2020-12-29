import java.util.*;

/* https://www.interviewbit.com/problems/subset/ */
public class AscendingSubset {

    public static void main(String[] args) {
        AscendingSubset sol = new AscendingSubset();
        ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.asList(1,2,3));
        System.out.println("Solution: "+sol.subsets(nums));

    }

    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>> ();
        result.add(new ArrayList<Integer>());
        if(nums.size() == 0)
            return result;
        Collections.sort(nums);
        ArrayList<Integer> subset = new ArrayList<Integer>();
        subsetsHelper(nums, result, subset, 0);
        return result;
    }

    public void subsetsHelper(ArrayList<Integer> nums,ArrayList<ArrayList<Integer>> result,ArrayList<Integer> subset,int index) {
        for(int i = index;i<nums.size();i++) {
            subset.add(nums.get(i));
            result.add(new ArrayList<Integer>(subset));
            subsetsHelper(nums,result,subset,i+1);
            subset.remove(subset.size()-1);
        }
    }

    /* 
    Below solution doesnot make the list lexigraphically sorted
    */
    // public ArrayList<ArrayList<Integer>> subsetsHelper(ArrayList<Integer> nums,int lastIndex) {
    //     ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>> ();
    //     if(lastIndex == -1) {
    //         result.add(new ArrayList<>());
    //         return result;
    //     }
    //     int lastElement = nums.get(lastIndex);
    //     ArrayList<ArrayList<Integer>> subResult = subsetsHelper(nums, lastIndex-1);
    //     for(ArrayList<Integer> subset : subResult) {
    //         result.add(subset);
    //         ArrayList<Integer> newSubset = new ArrayList<Integer>(subset);
    //         newSubset.add(lastElement);
    //         result.add(newSubset);
    //     }
    //     return result;
    // }

}