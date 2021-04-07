import java.util.*;
import java.util.stream.*;

/* 
https://algo.monster/problems/remove_duplicates

Question:
Given a sorted list of numbers, remove duplicates and return the new length. You must do this in-place and without using extra memory. Input: `0, 0, 1, 1, 1, 2, 2` Your function should modify the list in place so the first 3 elements becomes `0, 1, 2`

we have two pointers fast and slow
Since the list is sorted, we can move the fast pointer until , slow and fast have same values
0(s,f) 0 0 1 1 -> 0(s) 0 0 1(f) 1 
increment the the slow pointer by one and set new fast pointer value to slow
0 1(s) 0 1(f) 1 -> 0 1(s) 0 1 1 f(endOfList)
make slow pointer as end of list . Now list with Range [0-slow] has no duplicatez
Space Complexity: 0
Time Complexity: O(n)
*/
class RemoveDuplicatesSortedList {

    public static int removeDuplicates(List<Integer> arr) {
        int fast = 0;
        int slow = 0;
        while(fast<arr.size()) {
            if(arr.get(slow) != arr.get(fast)) {
                slow++;
                arr.set(slow,arr.get(fast));
            }
            fast++;
        }
        return slow+1;
    }
    
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>(Arrays.asList(0,0,0,1,1,2,2,3,3,3));
        int res = RemoveDuplicatesSortedList.removeDuplicates(arr);
        System.out.println(arr.stream().limit(res).map(String::valueOf).collect(Collectors.joining(" ")));
    }
}