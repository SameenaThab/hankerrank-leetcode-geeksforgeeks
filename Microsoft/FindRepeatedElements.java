import java.util.*;

/* Given an integer array A[], find the number of elements in the array for which,
 A[i] = number of times A[i] occurs in the array.
 For example, if A[] = [2,2,4,5,3,3,3] we need to return 2. 
 The number 2 occurs 2 times and 3 occurs 3 times and hence the answer is 2. */

 class  FindRepeatedElements {
    int findReapeatedElements(int[] arr) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int count=0;
        for(int ele:arr) {
            map.put(ele,map.getOrDefault(ele, 0)+1);
        }
        for(int key : map.keySet()) {
            if(key == map.get(key))
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        FindRepeatedElements sol = new FindRepeatedElements();
        System.out.println("number of same repeated elements: "+sol.findReapeatedElements(new int[]{2,2,4,5,3,3,3}));
        System.out.println("number of same repeated elements: "+sol.findReapeatedElements(new int[]{0,5,5,5,5,5,1}));
        System.out.println("number of same repeated elements: "+sol.findReapeatedElements(new int[]{2,3,4,5}));
    }
 }
