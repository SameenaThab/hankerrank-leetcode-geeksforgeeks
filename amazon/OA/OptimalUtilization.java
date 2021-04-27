import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.*;

//https://algo.monster/problems/optimal_utilization
// https://algo.monster/problems/amazon_oa_prime_air_route

class OptimalUtilization {
    public static List<List<Integer>> getPairs(List<List<Integer>> a, List<List<Integer>> b, int target) {
        // a in ascending order, smaller to larger
        Collections.sort(a,new Comparator<List<Integer>>() {
            public int compare(List<Integer> x,List<Integer> y) {
                return x.get(1)-y.get(1);
            }
        });
        // b in descending order larger to smaller
        Collections.sort(b,new Comparator<List<Integer>>() {
            public int compare(List<Integer> x,List<Integer> y) {
                return y.get(1)-x.get(1);
            }
        });
        
        int maxSum = Integer.MIN_VALUE;
        ArrayList<List<Integer>> maxPairs = new ArrayList<>();
        for(int i=0,j=0;i<a.size() && j<b.size();) {
            List<Integer> x =  a.get(i);
            List<Integer> y =  b.get(j);
            int currSum  = x.get(1)+y.get(1);
            if(currSum > target) {
                //decrease j , so that lower values of b are considered
                j++;
                continue;
            }
            if(currSum > maxSum) {
                // if best currsum update maxPAirs
                maxSum = currSum;
                maxPairs.clear();
            }
            // if currSum is lower than target add all combinations with ith index of list a
            for (int k = j; k < b.size(); k++) {
                List<Integer> z = b.get(k);
                if (z.get(1) != y.get(1))
                    break;
                maxPairs.add(List.of(x.get(0), z.get(0)));
            }
            i++;
        }
        return maxPairs;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> a = new ArrayList<>();
        for (int i = 0; i < aLength; i++) {
            a.add(Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        }
        int bLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> b = new ArrayList<>();
        for (int i = 0; i < bLength; i++) {
            b.add(Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList()));
        }
        int target = Integer.parseInt(scanner.nextLine());
        scanner.close();
        List<List<Integer>> res = getPairs(a, b, target);
        for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
