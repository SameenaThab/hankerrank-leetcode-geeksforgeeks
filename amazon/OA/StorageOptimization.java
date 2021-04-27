import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
// https://algo.monster/problems/amazon_oa_storage_optimization
/* 
You may have already noticed that the largest volume is length of the largest horizontal gap multiplied by the length of the largest vertical gap. There are a few ways to find the largest gap in each direction:

Naively, we can make a boolean array holding the presence of the separator at each index, and then iterate through it to find the largest gap. This requires both O(n) auxiliary space and O(n) time for n separators. (Note: n here is the value, not size, of input! See pseudo-polynomial).

However, since we are provided with the indices of separators that are removed, it suggests that the removed separators may be sparse.
Therefore, to reduce the space (but not time) complexity of the above approach, we can use a set to store the removed separators.
This would use O(|h|) space, where |h| is the size of the input array h, and |h| <= n since you can't remove more separators than the total number of them.

To further improve the solution: If we have a sorted array of all removed separators, then we don't need to traverse through 1..n to find the consecutive ones, we can simply go through the sorted array. Sorting the array takes O(|h| * log(|h|)) time, and going through the sorted array to find the largest gap takes O(|h|) time. The auxiliary space required is constant if we modify the input arrays; otherwise O(|h|) is needed to store the sorted values. Note that if the input array h is already sorted, this is a strictly better solution. If not, it will depend on the size of h and the value of n. The less percentage of the separators removed (i.e. the more sparse the "removed separators" are), the more efficient this approach is compared to the previous one.

Below we present the solution using the last approach, i.e. sorting and going through the array. This approach is not much slower in the worst case, but much faster in many other cases.
*/
class StorageOptimization {
    public static int storageOptimization(int n, int m, List<Integer> h, List<Integer> v) {
        return (longestConsecutiveLength(h)+1)*(longestConsecutiveLength(v)+1);
    }
    
    public static int longestConsecutiveLength(List<Integer> h) {
        // dividers are 1 based , so we have start with -1
        h.sort(null);
        int prev = -1;
        int longest = 0;
        int currLong = 0;
        for(int num : h) {
            if(num != prev+1) {        
                currLong = 0;
            }
            prev = num;
            currLong++;
            longest = Math.max(longest,currLong);
        }
        return longest;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int m = Integer.parseInt(scanner.nextLine());
        List<Integer> h = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> v = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int res = storageOptimization(n, m, h, v);
        System.out.println(res);
    }
}
