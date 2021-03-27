/* Maximum possible value by inserting '5'
examples:
input: 1234 -> output: 51234
input: 7643 -> output 76543
input: 0 -> output 50
input: -661 -> output -5661 */

class MaxValInsertFive {
    int maxValInsertFive(int num) {
        int nDigits = 0;
        if(num == 0)
            return 50;
        int sign = num<0?-1:1;
        int temp = Math.abs(num);
        while(temp > 0) {
            nDigits++;
            temp/=10;
        }
        num = Math.abs(num);
        int position = 1;
        int max = Integer.MIN_VALUE;
         // number of positions = number of digits+1
        for(int i=0;i<=nDigits;i++) {
            /* 
            num/position*position*10 gives value before the position
            by adding 5*position , we are inseritng 5
            num%position is the remaining number after position
            */
            int newValue = (num/position*position*10)+5*position+num%position;
            // System.out.println(newValue);
            max = Math.max(sign*newValue,max);
            position*=10;
        }
        return max;
    }

    public static void main(String[] args) {
        MaxValInsertFive sol = new MaxValInsertFive();
        System.out.println("sol for 1234: "+sol.maxValInsertFive(1234));
        System.out.println("sol for 7643: "+sol.maxValInsertFive(7643));
        System.out.println("sol for 0: "+sol.maxValInsertFive(0));
        System.out.println("sol for -661: "+sol.maxValInsertFive(-661));
    }
}