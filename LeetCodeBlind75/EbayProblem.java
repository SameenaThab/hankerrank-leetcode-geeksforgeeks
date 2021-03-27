import java.util.Arrays;
/* 
there are infinite # of boxes. One ball can occupy one box
given array of boxes that are occupied
Find min no of moves required to make balls contain in consequtive boxes
eg: 3 5 2 6 ans: 1
explantion ball at box 6 can be moved to box 4 then all boxes become consecutive
2 3 4 5

Approach we should do a sliding window (size = 3) of the array using the NumMovesStones.java method to find min moves 
for each window we upate the min
min = Math.max(min,curr_Window_Min)
*/

/* 
Similar type of problem https://leetcode.com/problems/moving-stones-until-consecutive-ii/ (used discussion solution accepted in leetcode ,login and check)
max is max=1+Math.max(A[N-1]-A[1]+1-N,A[N-2]-A[0]+1-N);  
*/
class EbayProblem {
    public int findMinMoves(int[] balls) {
        Arrays.sort(balls);
        int n = balls.length;
        if(balls[n-1]-balls[0]+1 == n) // already consecutive
            return 0;
        int window = 0;
        int min = 1;
        /* 
        for each window, element we compare are 0+window,1+window,2+window
        therefore 2+window<arr.length
        */
        while(window+2 < balls.length) { 
            int winMin = findMinInWindow(balls,window);
            min = Math.max(min,winMin);
            window++;
        }  
        return min;
    }
    
    private int findMinInWindow(int[] balls, int window) {
        int a = balls[0+window];
        int b = balls[1+window];
        int c = balls[2+window];
        /* 
        condtn #1: 1 2 9
        condtn #2: 1 7 8
        condtn #3: 4 7 9
        */
        if((b-a==1 && c-b>1)|| (c-b==1 && b-a>1) || (b-a>0 && c-b==2)){
            return 1;
        }
        /* 
        condtn #4: 1 5 8
        */
        else if(b-a>2){
            return 2;
        }
        /*
        condtn #5: 1 3 6
        */
        else{
            return b-a-1;
        }   
    }

    public static void main(String[] args) {
        EbayProblem sol = new EbayProblem();

        System.out.println("Just move 20 to 9, solution is 1: "+sol.findMinMoves(new int[]{20,10,8,7}));
        System.out.println("already consecution solution should be 0: "+sol.findMinMoves(new int[]{7,8,9}));
        System.out.println("Minimum moves must be 2: "+sol.findMinMoves(new int[]{1,11,8,14}));
        System.out.println("change 4 to 8, one move: "+sol.findMinMoves(new int[]{4,7,9}));
        System.out.println("10 to 2 one move: "+sol.findMinMoves(new int[]{1,10}));
        System.out.println("10 to 2 one move: "+sol.findMinMoves(new int[]{6,5,4,3,10}));
        System.out.println("Failed: need better solution "+sol.findMinMoves(new int[]{10,20,30,40,50,60}));
        
    }

    
}