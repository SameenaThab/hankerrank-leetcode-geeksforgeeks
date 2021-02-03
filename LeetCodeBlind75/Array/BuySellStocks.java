import java.util.*;

class BuySellStocks {

    public static void main(String[] args) {
        BuySellStocks sol = new BuySellStocks();
        System.out.println("solution: "+sol.maxProfitUsingStack(new int[]{7,1,5,3,6,4}));
        System.out.println("solution: "+sol.maxProfitUsingStack(new int[]{7,6,4,3,1}));
        System.out.println("solution: "+sol.maxProfitOptimal(new int[]{7,1,5,3,6,4}));
        System.out.println("solution: "+sol.maxProfitOptimal(new int[]{7,6,4,3,1}));
    }

    /* 
    like find the smallest nearest integer problem, we maintain a increasing stack(top is greatest number)
    We also maintain a min value(initially first element of array) and maxProfit variable
    traverse the stack
        if stack is empty or top is less than element of array,push
        else 
            pop the element and update maxprofit if necessary max(max,(popped price-minvalue))
            push the element if stack is empty or top is less than  elemet
            update the minValue
    until stack is empty update the maxProfit value max(max,(popped price-minvalue))
    */
    public int maxProfitUsingStack(int[] prices) {
        Stack<Integer> stack = new Stack<Integer>();
        int maxProfit = 0;
        int min = prices[0];
        for(int i=0;i<prices.length;i++) {
            if(stack.isEmpty() || prices[i]>stack.peek()) {
                stack.push(prices[i]);
            } else {
                while(!stack.isEmpty() && prices[i] < stack.peek()) {
                    int top = stack.pop();
                    maxProfit = Math.max(maxProfit,top-min);
                }
                stack.push(prices[i]);
                min = Math.min(min,prices[i]); // upate min after updating profits, bcoz future minvalue doesnot apply to popped elements
            }
        }
        while(!stack.isEmpty()) {
            int top = stack.pop();
            maxProfit = Math.max(maxProfit,top-min);            
        }
        return maxProfit;  
    }

    /* 
    Same as stack but we dont maintain a stack simply minimum element(initially first element)
    traverse the array
        if element<min update min
        else update profit Math.max(maxProfit,prices[i]-min);
    */
    public int maxProfitOptimal(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];
        for(int i=1;i<prices.length;i++) {
            if(prices[i]<min)
                min = prices[i];
            else
                maxProfit = Math.max(maxProfit,prices[i]-min);
        }
        return maxProfit;                                                                 
    }
}