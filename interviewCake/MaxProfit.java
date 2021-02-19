public class MaxProfit {
/* 
Brutefore :
for i:0 to n
    for j: i+1 to n
        // maxprofit = MAth.max(maxProfit,stock[j]-stock[i])
this is n^2, try to do in O(n) time complexity
minProce = stock[0], maxProfit = 0;
traverse the array 
maintain minPrice, keep updating Math.min(minPrice,currPrice)
update maxProfit Math.max(maxProfit,currPrice-minPrice)
edge Case 1: all the stocks have smae price , maxProfit = 0 ,above algo will take care of that
edge Case 2: prices are plummeting , 4,3,2,1, we need to return the min loss
    for edge case two we start initialize maxProfit = stock[1]-stock[0]
    then follow same algo , but travese from 1 to n
*/
    public static int getMaxProfit(int[] stockPrices) throws Exception {

        // calculate the max profit
        if(stockPrices.length <2)
            throw new Exception("stockPrices size should be atleast 2");
        int minPrice = stockPrices[0];
        int maxProfit = stockPrices[1]-stockPrices[0];
        
        for(int i=1;i<stockPrices.length;i++) {
            maxProfit = Math.max(maxProfit,stockPrices[i]-minPrice);
            minPrice = Math.min(minPrice,stockPrices[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        MaxProfit solution = new MaxProfit();
 
    }

    //Tests
    // public void priceGoesUpThenDownTest() throws Exception {
    //     final int actual = getMaxProfit(new int[] {1, 5, 3, 2});
    //     final int expected = 4;
    //     assertEquals(expected, actual);
    // }

    // public void priceGoesDownThenUpTest() throws Exception {
    //     final int actual = getMaxProfit(new int[] {7, 2, 8, 9});
    //     final int expected = 7;
    //     assertEquals(expected, actual);
    // }

    // public void priceGoesUpAllDayTest() throws Exception {
    //     final int actual = getMaxProfit(new int[] {1, 6, 7, 9});
    //     final int expected = 8;
    //     assertEquals(expected, actual);
    // }

    // public void priceGoesDownAllDayTest() throws Exception {
    //     final int actual = getMaxProfit(new int[] {9, 7, 4, 1});
    //     final int expected = -2;
    //     assertEquals(expected, actual);
    // }

    // public void priceStaysTheSameAllDayTest() throws Exception {
    //     final int actual = getMaxProfit(new int[] {1, 1, 1, 1});
    //     final int expected = 0;
    //     assertEquals(expected, actual);
    // }

    // public void exceptionWithOnePriceTest() throws Exception {
    //     getMaxProfit(new int[] {5});
    // }

    // public void exceptionWithEmptyPricesTest() throws Exception {
    //     getMaxProfit(new int[] {});
    // }


}