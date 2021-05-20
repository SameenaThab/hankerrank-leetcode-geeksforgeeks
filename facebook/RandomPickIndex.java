/* 
Intuition
Given a list of positive values, we are asked to randomly pick up a value based on the weight of each value. To put it simple, the task is to do sampling with weight.
Let us look at a simple example. Given an input list of values [1, 9], when we pick up a number out of it, the chance is that 9 times out of 10 we should pick the number 9 as the answer.
In other words, the probability that a number got picked is proportional to the value of the number, with regards to the total sum of all numbers.
To understand the problem better, let us imagine that there is a line in the space, we then project each number into the line according to its value, i.e. a large number would occupy a broader range on the line compared to a small number.
For example, the range for the number 9 should be exactly nine times as the range for the number 1.
Now, let us throw a ball randomly onto the line, then it is safe to say there is a good chance that the ball will fall into the range occupied by the number 9. In fact, if we repeat this experiment for a large number of times, then statistically speaking, 9 out of 10 times the ball will fall into the range for the number 9.

First of all, let us construct the line in the experiment by chaining up all values together.

Let us denote a list of numbers as [w_1, w_2, w_3, ..., w_n].Starting from the beginning of the line, we then can represent the offsets for each range K as offset_k = sum[0 to k],offset_(k+1) = sum [0 to k+1]

As many of you might recognize now, the offsets of the ranges are actually the prefix sums from a sequence of numbers. For each number in a sequence, its corresponding prefix sum, also known as cumulative sum, is the sum of all previous numbers in the sequence plus the number itself.
As an observation from the definition of prefix sums, one can see that the list of prefix sums would be strictly monotonically increasing, if all numbers are positive.
To throw a ball on the line is to find an offset to place the ball. Let us call this offset target.
Once we randomly generate the target offset, the task is now boiled down to finding the range that this target falls into.
Let us rephrase the problem now, given a list of offsets (i.e. prefix sums) and a target offset, our task is to fit the target offset into the list so that the ascending order is maintained.
*/
class RandomPickIndex {
    private int[] prefixSums;
    private int totalSum;
    public RandomPickIndex(int[] w) {
        this.prefixSums = new int[w.length];
    
        int prefixSum = 0;
        for (int i = 0; i < w.length; ++i) {
            prefixSum += w[i];
            this.prefixSums[i] = prefixSum;
        }
        this.totalSum = prefixSum;        
    }
    
    /* 

    Let N be the length of the input list.
    Time Complexity
    For the constructor function, the time complexity would be O(N), which is due to the construction of the prefix sums.
    For the pickIndex() function, its time complexity would be O(N) as well, since we did a linear search on the prefix sums.
    Space Complexity
    For the constructor function, the space complexity would be O(N), which is again due to the construction of the prefix sums.
    For the pickIndex() function, its space complexity would be O(1), since it uses constant memory. Note, here we consider the prefix sums that it operates on, as the input of the function.    
   
    Concerning the above problem, arguably the most intuitive solution would be linear search. Many of you might have already thought one step ahead, by noticing that the input list is sorted, which is a sign to apply a more advanced search algorithm called binary search.

    We now should have all the elements at hand for the implementation.

    Algorithm
    First of all, before picking an index, we should first set up the playground, by generating a list of prefix sums from a given list of numbers. The best place to do so would be in the constructor of the class, so that we don't have to generate it again and again at the invocation of pickIndex() function.
    In the constructor, we should also keep the total sum of the input numbers, so that later we could use this total sum to scale up the random number.
    For the pickIndex() function, here are the steps that we should perform.
    Firstly, we generate a random number between 0 and 1. We then scale up this number, which will serve as our target offset.

    We then scan through the prefix sums that we generated before by linear search, to find the first prefix sum that is larger than our target offset.
    And the index of this prefix sum would be exactly the right place that the target should fall into. We return the index as the result of pickIndex() function.
    */
    public int pickIndexLinear() {
        double target = this.totalSum * Math.random();
        int i = 0;
        // run a linear search to find the target zone
        for (; i < this.prefixSums.length; ++i) {
            if (target < this.prefixSums[i])
                return i;
        }
        // to have a return statement, though this should never happen.
        return i - 1;       
    }

    /* 
    
    Let NN be the length of the input list.

    Time Complexity
    For the constructor function, the time complexity would be O(N), which is due to the construction of the prefix sums.
    For the pickIndex() function, this time its time complexity would be O(logN), since we did a binary search on the prefix sums.

    Space Complexity
    For the constructor function, the space complexity remains O(N), which is again due to the construction of the prefix sums.
    For the pickIndex() function, its space complexity would be O(1), since it uses constant memory. Note, here we consider the prefix sums that it operates on, as the input of the function.
    */
    public int pickIndex() {
        //Math.random() returns value btw 0.0 to 1.0
        double target = this.totalSum * Math.random();

        // run a binary search to find the target zone
        int low = 0, high = this.prefixSums.length;
        while (low < high) {
            // better to avoid the overflow
            int mid = low + (high - low) / 2;
            if (target > this.prefixSums[mid])
                low = mid + 1;
            else
            // bcoz we dnt know if target<prefixSum[mid-1] 
                high = mid;
        }
        return low;
    }

}

