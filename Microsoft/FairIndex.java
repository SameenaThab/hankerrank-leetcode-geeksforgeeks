class FairIndex {
    /* 
    
    You are given two arrays A and B consisting of N integers each.

    Index K is named fair if the four sums (A[0] + ... + A[K-1]), (A[k] + ... + A[N-1]), (B[0] + ... + B[k-1]) and (B[K] + ... + B[N-1]) are all equal. In other words, K is the index where the two arrays, A and B, can be split (into two non-empty arrays each) in such a way that the sums of the resulting arrays' elements are equal.

    write a function:

    int fairIndexes(vector<int> &A, vector<int> &B);
    which, given two arrays of integers A and B, returns the number of fair indexes.
    */
    int fairIndex(int[] a,int[] b) {
        int sumA=0;
        int sumB=0;
        for(int i=0;i<a.length;i++) {
            sumA+=a[i];
            sumB+=b[i];
        }

        int count=0;
        int currSumA = a[0];
        int currSumB = b[0];
        for(int i=1;i<a.length;i++) {
            if(currSumA == currSumB && 2*currSumA == sumA && 2*currSumB == sumB) {
                count++;
            }
            currSumA+=a[i];
            currSumB+=b[i];
        }
        return count;
    }
    
    int fairIndexAnotherSolution(int[] a, int[] b) {
            int sumA = 0;
            int sumB = 0;
            for(int i=0;i<a.length;i++){
                sumA+=a[i];
                sumB+=b[i];
            }
            int countA = 0;
            int countB = 0;
            int fairIndexes = 0;

        //traverse only till a-length-1 becoz we do not spilt into empty arrays
            for(int i=0;i<a.length-1;i++) {
                countA+=a[i];
                countB+=b[i];
                if(countA==countB && countA == sumA-countA && countB == sumB-countB) {
                    fairIndexes++;
                }
            }
            return fairIndexes;
    }

    public static void main(String[] args) {
        FairIndex sol = new FairIndex();

        //Input: A = [4, -1, 0, 3], B = [-2, 5, 0, 3] Output: 2
        // Explanation:
        // The fair indexes are 2 and 3. In both cases, the sums of elements the subarrays are equal to 3.
        // For index = 2;
        // 4 + (-1) = 3; 0 + 3 = 3;
        // -2 + 5 = 3; 0 + 3 = 3;
        System.out.println("should be 2: "+sol.fairIndex(new int[]{4, -1, 0, 3}, new int[]{-2, 5, 0, 3}));
        
        // Input: A = [2, -2, -3, 3], B = [0, 0, 4, -4] Output: 1
        // Explanation: The only fair index is 2.
        System.out.println("should be 1: "+sol.fairIndex(new int[]{2, -2, -3, 3}, new int[]{0, 0, 4, -4}));

        // Input: A = [2,-1,3], B = [2,5,-3] Output: 1
        // Explanation: The only fair index is 1.
        System.out.println("should be 1: "+sol.fairIndex(new int[]{2, -2, -3, 3}, new int[]{0, 0, 4, -4}));
    }
}