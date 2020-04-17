import java.io.File;
 class TestSolution {
    public static void main(String[] args) {
        int[] arr = new int[]{34,56,2,9,0,5,3,13,54};
        int n = arr.length;
        File file = new File("Hello.txt");
        quickSort(arr,0,n-1);
        for(int i=0;i<n;i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        int kLargest = findKLargest(arr,3);
        System.out.println("3rd largest index: "+kLargest+" Kth Largest element: "+arr[kLargest]);
    }

    public static int findKLargest(int[] arr,int k) {
        int n  = arr.length;
        // k largest is n-k smallest
        int kSmallest = n-k;
        int left = 0;
        int right = n-1;
        while(left <= right) {
            int index = partition(arr, left,right);
            if(kSmallest == index)
                return index;
            else if(index < kSmallest)
                left = index+1;
            else
                right = index-1;   
        }

        return -1;
    }

    public static void quickSort(int[] arr, int left, int right) {
        if(left <= right) {
            int pivot = partition(arr, left,right);
            quickSort(arr,left,pivot-1);
            quickSort(arr,pivot+1,right);
        }
    }

    public static int partition(int[] arr, int left,int right) {
        int pivot = arr[right];
        int i = left-1;
        for(int j=left;j<=right;j++) {
            if(arr[j]<pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;
        return i;
    }
}