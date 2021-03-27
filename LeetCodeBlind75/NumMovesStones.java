import java.util.Arrays;
// https://leetcode.com/problems/moving-stones-until-consecutive/submissions/
class NumMovesStones {
    public int[] numMovesStones(int a, int b, int c) {
        int[] ar=new int[3];
        ar[0]=a;
        ar[1]=b;
        ar[2]=c;
        Arrays.sort(ar);
        a=ar[0];
        b=ar[1];
        c=ar[2];
        int[] as=new int[2];
        /* 
        condtn #1: 1 2 9
        condtn #2: 1 7 8
        condtn #3: 4 7 9
        */
        if((b-a==1 && c-b>1)|| (c-b==1 && b-a>1) || (b-a>0 && c-b==2)){
            as[0]=1;
        }
        /* 
        condtn #4: 1 5 8
        */
        else if(b-a>2){
            as[0]=2;
        }
        /*
        condtn #5: 1 3 6
        */
        else{
            as[0]=0;
        }
        as[1]=(c-(b+1))+((b-1)-a);
        return as;       
    }
}