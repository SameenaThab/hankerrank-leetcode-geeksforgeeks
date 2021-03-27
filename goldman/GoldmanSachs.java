import java.util.Arrays;

class GoldmanSachs {
    /* 
    given non-negative array of numbers and target
    [1,2,3,4] target = 6
    find the minimum length of subarray with sum of elements >=target
    if not possible return -1
    input: arr = [1,2,3,4] target = 6
    output: 2 subArr=[3,4]=3+4>=6 . Remember sub array has to be continuous [2,4] is not continous subArray

    input: arr=[1,2,3,4] target = 12
    output: -1 no such subarry will sum to 12

    input arr= [1,3,4,6,2] target = 10
    output: [4,6] ans:2
    
    input arr=[1,2,9,3,4] target = 9
    output:[9] ans:1
    */

    public int findSubArrSum(int[] arr,int target) {
        int total = 0;
        for(int num:arr) {
            total+=num;
        }
        int result = findSubArrSumHelper(arr,target,total,0,arr.length-1);
        return result == Integer.MAX_VALUE? -1 : result;
    }

    public int findSubArrSumHelper(int[] arr,int target,int total,int l,int r) {
        if(total < target || l>r)
            return Integer.MAX_VALUE;
        int sub1 = findSubArrSumHelper(arr,target,total-arr[l]-arr[r],l+1,r-1);
        int sub2 = findSubArrSumHelper(arr,target,total-arr[l],l+1,r);
        int sub3 = findSubArrSumHelper(arr,target,total-arr[r],l,r-1); 
        return Math.min(Math.min(sub1,r-l+1),Math.min(sub2,sub3));
    }

    public static void main(String[] args) {
        GoldmanSachs sol = new GoldmanSachs();
        int[] arr = new int[]{1,2,3,4};
        int target = 6;
        System.out.println("array: "+Arrays.toString(arr)+" target: "+target+" solution: "+sol.findSubArrSum(arr,target));

        target = 12;
        System.out.println("array: "+Arrays.toString(arr)+" target: "+target+" solution: "+sol.findSubArrSum(arr,target));

        arr = new int[]{1,3,4,6,2};
        target = 10;
        System.out.println("array: "+Arrays.toString(arr)+" target: "+target+" solution: "+sol.findSubArrSum(arr,target));

        arr = new int[]{1,2,9,3,4};
        target = 9;
        System.out.println("array: "+Arrays.toString(arr)+" target: "+target+" solution: "+sol.findSubArrSum(arr,target));
    }
}