/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

 /* 
 We know each row is in ascending order, so we can use binary search to find least column with value 1
 define function binary_search(input_list):
    lo = the lowest possible index
    hi = the highest possible index
    while the search space contains 2 or more items:
        mid = the middle index in the remaining search space
        if the element at input_list[mid] is 0:
            lo = mid + 1 (the first 1 is *further right*, and can't be mid itself)
        else:
            hi = mid (the first 1 is either mid itself, *or is further left*)
    return the only index remaining in the search space

As always in binary search, there are a couple more key implementation details we still need to deal with:
An even-length search space has two middles. Which do we choose?
The row might be all 0's.
Let's tackle these issues one at a time.

The first issue, the choice of middle, is important, as otherwise, the search space might stop shrinking when it gets down to two elements. When the search space doesn't shrink, the algorithm does the exact same thing the next loop cycle, resulting in an infinite loop. Remember that when the search space only contains two elements, they are the ones pointed to by lo and hi. This means that the lower middle equals lo, and the upper-middle equals hi. We, therefore, need to think about which cases would shrink the search space, and which would not.
If we use the lower-middle
    If it is a 0, then we set lo = mid + 1. Because hi == mid + 1, this means that lo == hi (search space is of length-one).
    If it is a 1, then we set hi = mid. Because mid == lo, this means that hi == lo (search space is of length-one).
If we use the upper-middle
    If it is a 0, then we set lo = mid + 1. Because hi = mid, we now have hi > lo (search space is of length-zero).
    If it is a 1, then we set hi = mid. Because hi == mid was already true, the search space stays as is (of length-two).
If we use the lower-middle, we know the search space will always shrink. If we use the upper-middle, it might not. Therefore, we should go with the lower-middle. The formula for this is mid = (low + high) / 2.

The second issue, a row of all zeroes, is solved by recognizing that the algorithm always shrinks down the search space to a single element. This is supposed to be the first 1, but if that doesn't exist, then it has to be a 0. Therefore, we can detect this case by checking whether or not the last element in the search space is a 1.
Complexity Analysis
Let N be the number of rows, and M be the number of columns.
Time complexity : O(NlogM).
There are M items in each row. Therefore, each binary search will have a cost of O(logM). We are performing N of these binary searches, giving a time complexity of N⋅O(logM)=O(NlogM).

Space complexity : O(1).
We are using constant extra space.
 */
class LeftMostColumnWithOne {

    public int leftMostColumnWithOneBS(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int m = dim.get(0);
        int n = dim.get(1);
        int minCol = n;
        // we use binary search bcoz, each row in the matrix is in ascending order
        for(int i=0;i<m;i++) {
            int low = 0;
            int high = n-1;
            while(low<high) {
                int mid = (low+high)/2;
                if(binaryMatrix.get(i,mid) == 0) {
                    //the first 1 is *further right*, and can't be mid itself
                    low = mid+1;
                } else {
                    //the first 1 is either mid itself, *or is further left*
                    high = mid;
                }
            }
            // If the last element in the search space is a 1, then this row
            // contained a 1.
            if (binaryMatrix.get(i, low) == 1) {
                minCol = Math.min(minCol, low);
            }
                
        }
        return minCol == n?-1:minCol;
    }
/* 
Approach 3: Start at Top Right, Move Only Left and Down
Intuition
Did you notice in binary search Approach that we didn't need to finish searching all the rows? 
One example of this was row 3 on the example in the animation.
At the point shown in the image below, it was clear that row 3 could not possibly be better than the minimum we'd found so far.
row 0: 0 0 0 0 1 1 1 1
row 1: 0 0 0 0 0 1 0 0
row 2: 0 0 1 0 0 0 0 0
row 3: 0 0 0 0 0 0 0 0

Time complexity : O(N+M).
At each step, we're moving 1 step left or 1 step down. Therefore, we'll always finish looking at either one of the M rows or N columns. Therefore, we'll stay in the grid for at most N+M steps, and therefore get a time complexity of O(N+M).

Space complexity :  O(1).
We are using constant extra space.
*/
    public int leftMostColumnWithOneBetter(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);

        // Set pointers to the top-right corner.
        int currentRow = 0;
        int currentCol = cols - 1;

        // Repeat the search until it goes off the grid.
        while (currentRow < rows && currentCol >= 0) {
            // already least possible column is 0 
            // that means 1 will be at even farther column for this row bcoz ascending, 
            // so search no further
            if (binaryMatrix.get(currentRow, currentCol) == 0) {
                currentRow++;
            } else {
                currentCol--; 
            }
        }

        // If we never left the last column, this is because it was all 0's.
        return (currentCol == cols - 1) ? -1 : currentCol + 1;
    }

    /* 
    This above approach can be done to binary search approach
    by making the high value to minCol for every next row.
    Same time complexity and space complexity as binary search
    */

    public int leftMostColumnWithOneBS(BinaryMatrix binaryMatrix) {
        List<Integer> dim = binaryMatrix.dimensions();
        int m = dim.get(0);
        int n = dim.get(1);
        int minCol = n;
        // we use binary search bcoz, each row in the matrix is in ascending order
        for(int i=0;i<m;i++) {
            int low = 0;
            int high = minCol-1;
            while(low<high) {
                int mid = (low+high)/2;
                if(binaryMatrix.get(i,mid) == 0) {
                    //the first 1 is *further right*, and can't be mid itself
                    low = mid+1;
                } else {
                    //the first 1 is either mid itself, *or is further left*
                    high = mid;
                }
            }
            // If the last element in the search space is a 1, then this row
            // contained a 1.
            if (binaryMatrix.get(i, low) == 1) {
                minCol = Math.min(minCol, low);
            }
                
        }
        return minCol == n?-1:minCol;
    }
}