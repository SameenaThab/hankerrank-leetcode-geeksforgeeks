import java.util.*;

public class SubsetII {

    /* https://www.interviewbit.com/problems/subsets-ii/
     */
    
     public static void main(String[] args) {
        SubsetII sol = new SubsetII();
        ArrayList<Integer> nums = new ArrayList<Integer>(Arrays.asList(1,2,2));
        System.out.println("Solution: "+sol.subsetsWithDup(nums));

    }

    public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> list) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>> ();
        ArrayList<Integer[]> map = new ArrayList<Integer[]>();
        Collections.sort(list);
        int i=0;
        result.add(new ArrayList<Integer>());
        if(list.size() == 0)
            return result;
        while(i<list.size()) {
            int value = list.get(i);
            int count = 0;
            while(i<list.size() && value == list.get(i)) {
                i++;
                count++;
            }
            map.add(new Integer[]{value,count});
        }
        subsetsHelper(result,map,new ArrayList<Integer>(),0);
        return result;
    }

    public void subsetsHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer[]> map, ArrayList<Integer> subset, int index) {
        if(index == map.size()) {
            result.add(new ArrayList<Integer>(subset));
            return;
        }
        for(int i=index;i<map.size();i++) {
            Integer[] pair = map.get(i);
            int curr = pair[0];
            int freq = pair[1];
            if(freq <= 0)
                continue;
            subset.add(curr);
            result.add(new ArrayList<Integer>(subset));
            map.set(i,new Integer[]{curr,freq-1});
            subsetsHelper(result, map, subset, i);
            subset.remove(subset.size()-1);
            map.set(i,new Integer[]{curr,freq});
        }
    }   
}