import java.util.*;

public class CombinationSumII {

    /* 
    https://www.interviewbit.com/problems/combination-sum-ii/
    */
    public static void main(String[] args) {
        CombinationSumII sol = new CombinationSumII();
        ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.asList(8, 10, 6, 11, 1, 16, 8));
        int sum = 28;
        System.out.println("Solution: "+sol.combinationSum(nums,sum));

    }

    public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> list, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>> ();
        ArrayList<Integer> subset = new ArrayList<Integer>();  
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        ArrayList<Integer[]> countMap = new ArrayList<Integer[]>();  
        Collections.sort(list); 
        int i = 0;
        while(i < list.size() ) {
            int value = list.get(i);
            int count=0;
            while(i < list.size() && list.get(i) == value) {
                count++;
                i++;
            }
            countMap.add(new Integer[]{value,count});
        }
        // for(Integer i:list) {
        //     if (map.containsKey(i))
        //         map.put(i, map.get(i) + 1);
        //     else
        //         map.put(i, 1);
        // }
        // System.out.println(map);
        // map.forEach((key,value) -> countMap.add(new Integer[]{key,value}));
        // for(Integer[] pair:countMap) {
        //     System.out.println(pair[0]+" "+pair[1]); 
        // }
        combinationSumHelper(result,subset,countMap,sum,0);
        return result;
    }

    private void combinationSumHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> subset, ArrayList<Integer[]> countMap, int sum,int index) {
        if(sum == 0) {
            result.add(new ArrayList<Integer>(subset));
            return;
        }
        //with optimization
        // if(sum < 0) {
        //     return;
        // }
        // System.out.println("index: "+index);
        // for(Integer[] pair:countMap) {
        //     System.out.println(Arrays.toString(pair)); 
        // }
        for(int i=index;i<countMap.size();i++) {
            Integer[] pair = countMap.get(i);
            int curr = pair[0];
            int count = pair[1];    
            if(count <= 0)
                continue;
            int rem = sum-curr;
            if(rem<0)
                break;
            // optimization no need to continue if remaing becomes < 0 loop ends
            subset.add(curr);
            countMap.set(i, new Integer[]{curr,count-1});
            combinationSumHelper(result, subset, countMap,rem,i);
            countMap.set(i, new Integer[]{curr,count});
            subset.remove(subset.size()-1);
        }
    }
  
}