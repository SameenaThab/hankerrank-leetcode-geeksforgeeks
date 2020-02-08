//https://leetcode.com/problems/string-to-integer-atoi/
class atoi {
    public int myAtoi(String str) {      
        int i=0;
        int sign = 1;
        int num = 0;
        str = str.trim();
        int n=str.length();
        System.out.println("here");
        if(i<n && (str.charAt(i)=='+' || str.charAt(i) =='-'))
            sign = str.charAt(i++)=='+'?+1:-1;
         System.out.println(sign);
        while(i<n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                num = num*10+(str.charAt(i++)-'0');  
             System.out.println("here"+num);
             if(i<n && str.charAt(i) >= '0' && str.charAt(i) <= '9' && num > Integer.MAX_VALUE/10 || (num == Integer.MAX_VALUE/10 && str.charAt(i)>'7')) {
                 return sign == 1?Integer.MAX_VALUE:Integer.MIN_VALUE;   
             }
            }
        
            return sign*num;
    }
}