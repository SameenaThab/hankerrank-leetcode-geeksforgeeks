import java.util.*;
//https://leetcode.com/problems/find-k-closest-elements/solution/
/* 
Intuitively, we can sort the elements in list arr by their absolute difference values to the target x. Then the sublist of the first k elements is the result after sorting the elements by the natural order.

Time complexity : O(nlogn). Collections.sort() uses binary sort so it has a O(nlogn) complexity.
Space complexity : O(k). The in-place sorting does not consume any extra space. However, generating a k length sublist will take some space.
*/
class FindClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();
        for(int ele:arr) {
            result.add(ele);
        }
        Collections.sort(result,new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                return Math.abs(a-x)-Math.abs(b-x);     
            }
        });
        result = result.subList(0,k);
        Collections.sort(result);
        return result;
    }
/* 
Algorithm
1. If the target x is less or equal than the first element in the sorted array, the first k elements are the result.
2. Similarly, if the target x is more or equal than the last element in the sorted array, the last k elements are the result.
3. Otherwise, we can use binary search to find the index of the element, which is equal (when this list has x) or a little bit larger than x (when this list does not have it). Then set low to its left k-1 position, and high to the right k-1 position of this index as a start. The desired k numbers must in this rang [index-k-1, index+k-1]. So we can shrink this range to get the result using the following rules.
4 .If low reaches the lowest index 0 or the low element is closer to x than the high element, decrease the high index.
5. If high reaches to the highest index arr.size()-1 or it is nearer to x than the low element, increase the low index.
6. The looping ends when there are exactly k elements in [low, high], the subList of which is the result.

Time complexity :O(logn+k).O(logn) is for the time of binary search, while O(k) is for shrinking the index range to k elements.
Space complexity : O(k). It is to generate the required sublist.
*/
    public List<Integer> findClosestElementsBetter(int[] arr, int k, int x) {
        
        List<Integer> result = new ArrayList<Integer>();
        if(x<=arr[0]) {
            // add elements from 0 to k
            for(int i=0;i<k;i++){
                result.add(arr[i]);
            }
            return result;
        }
        int n = arr.length;
        if(x>=arr[n-1]) {            
            // add last k elements
            for(int i=n-k;i<n;i++){
                result.add(arr[i]);
            }
            return result;
        }
        int index = Arrays.binarySearch(arr,x);
        if(index < 0) {
            //x not found but binary search return -insertionPoint-1
            //to Get index of element little bit larger then x 
            // In current array insertion is where the bit larger number resides
            index = -(index+1);
        }
        // Since index has element already the closer or equal to x, we search k-1 elements on both left and right
        int low = Math.max(0,index-k-1);
        int high = Math.min(n-1,index+k-1);
        // no of element btw high and low = high-low+1;
        while(high-low+1 > k) {
            //element at pointer low is closer so move high
            if(low < 0 || x-arr[low] <= arr[high]-x) {
                high--;
            }
            else if(high>arr.length-1 || arr[high]-x <= x-arr[low]) {
                low++;
            }
        }
        for(int i=low;i<=high;i++) {
            result.add(arr[i]);
        }
        return result;
        
    }
}