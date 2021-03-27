import java.util.*;

public class CombinationSum {

    /* 
    https://www.interviewbit.com/problems/combination-sum/
    */
    public static void main(String[] args) {
        CombinationSum sol = new CombinationSum();
        ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.asList(2,3,6,7,7));
        int sum = 7;
        System.out.println("Solution: "+sol.combinationSum(nums,sum));

    }

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> list, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>> ();
        ArrayList<Integer> subset = new ArrayList<Integer>();  
        Collections.sort(list); 
        combinationSumHelper(result,subset,list,sum,0);
        return result;
    }

    private void combinationSumHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> subset, ArrayList<Integer> list, int sum,int index) {
        if(sum == 0) {
            result.add(new ArrayList<Integer>(subset));
            return;
        }
        // with optimization
        // if(sum < 0) {
        //     return;
        // }
        for(int i=index;i<list.size();i++) {
            //continue on duplicate elements
            if(i!=list.size()-1 && list.get(i) == list.get(i+1))
                continue;
            // optimization - no need to continue if remaing becomes < 0 loop ends
            if(sum-list.get(i) < 0)
                return;
            subset.add(list.get(i));
            combinationSumHelper(result, subset, list, sum-list.get(i),i);
            subset.remove(subset.size()-1);
        }
    }
  
}