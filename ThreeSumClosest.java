// Given array nums = [-1, 2, 1, -4], and target = 1.

// The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
import java.util.Arrays;
class ThreeSumClosest {
    public static void main(String[] args) {
        int[] arr = new int[]{-1, 2, 1, -4};
        int n = arr.length;
        int result = threeSumClosest(arr,2);
        System.out.println("Closset sum: "+result);
    }

    public static int threeSumClosest(int[] arr,int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int sum = Integer.MAX_VALUE;
        for(int i=0;i<n-2;i++) {
            int j = i+1;
            while(j<n-1) {
                int k = j+1;
                int temp = arr[i]+arr[j]+arr[k];
                sum = Math.abs(target-temp)<Math.abs(target-sum)?temp:sum;
                j++;
            }
        }

        return sum;
    }
}