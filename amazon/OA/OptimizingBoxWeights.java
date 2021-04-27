import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import java.util.*;

/* 
If we sort the array, the subset A with maximal total weight is the shortest trailing subarray with sum greater than half of the total sum, e.g. for the example above,

sorted(arr) = [1, 2, 2, 3, 4, 5]
sum(arr) / 2 = 17 / 2 = 8.5
A = [4, 5]
sum(A) = 9
Now, we can just sort the array and extract the subarray and call it a day, but there is a more efficient approach. Notice that we don't need the whole array in sorted order — we only need to extract the largest elements in descending order, which seems like a perfect opportunity for a priority queue.

Therefore, we put all the values into a heap through heapify (note: inserting one by one would defeat the purpose, as that would take O(n log n) time which is on the same magnitude as sorting). We then pop one element at a time into a separate array, until the sum of the removed elements is larger than half the total sum. The removed elements are in descending order, so we reverse the result array before returning.

For input of size n and output of size m, this approach takes O(n + m log n) as opposed to O(n log n) by sorting, which is faster in all cases and especially so if the output size is small.
*/
// https://algo.monster/problems/optimizing_box_weights
class OptimizingBoxWeights {
    public static List<Integer> optimizingBoxWeights(List<Integer> arr) {
        int sum = arr.stream().reduce(0,Integer::sum)/2;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        int currSum = 0;
        pq.addAll(arr);
        List<Integer> res = new ArrayList<>();
        while(currSum<=sum) {
            int num = pq.poll();
            currSum+=num;
            res.add(num);
            //sum-=num;
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> arr = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        List<Integer> res = optimizingBoxWeights(arr);
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
