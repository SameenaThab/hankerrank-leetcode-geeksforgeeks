import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;


class Chap1Prob4 {

    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("taco cat"));
        System.out.println(isPalindromePermutation("madam"));
        System.out.println(isPalindromePermutation("gguuffuv"));
        System.out.println("----------------");
        System.out.println(isPalindromePermutationBitWise("taco cat"));
        System.out.println(isPalindromePermutationBitWise("madam"));
        System.out.println(isPalindromePermutationBitWise("gguuffuv"));
        System.out.println("----------------");
    }

    // time = O(n) n is string length;
    // Space = O(26)=O(1)
    private static boolean isPalindromePermutation(String word) {
        int[] arr = new int[26];
        int oddCount = 0;
        for(char ch:word.toCharArray()) {
            int index = getAlphabetIndex(ch);
            if(index == -1)
                continue;
            arr[index]++;
            if(arr[index]%2 == 1)
                oddCount++;
            else
                oddCount--;
        }
        return oddCount<=1;
    }

     // time = O(n) n is string length;
    // Space = zero as we use bitwise operator
    /*
        Instead of using int[] array of size 26 we can use a single integer (bit size = 4*8 = 32 which less than 26) to represent character has odd/even count
        Its like using boolean[26] 
        charbit set to 1 => oddCOunt
        chaarbit set to 0 => evenCOunt
    */
    private static boolean isPalindromePermutationBitWise(String word) {
        int[] arr = new int[26];
        int checker = 0;
        for(char ch:word.toCharArray()) {
            int index = getAlphabetIndex(ch);
            if(index == -1)
                continue;
            int char_bit_index = 1 << index;
            if((checker & char_bit_index) == 0) {  // That is char bit is not already set(evenCount exists until this point)
                checker|=char_bit_index;  // setting char bit to 1(represeting oddcount)
            } else {
                checker &= ~char_bit_index; // flipping the char bit to 0 (representing evencount)
            }
        }

        return checker==0 || checkExactlyOneBitSet(checker);
    }  

    /*
    checker has more then 1 bit set to 1 => then more than one char has odd count => not a palindrome
    True eg: 000100 
        subtract 1 -> 000100-1 = 000011
        perform & between actual integer , subtract integer -> 000100 & 000011 = 000000
    False eg: 001010
        subtract 1 -> 001010-1 = 000101
        perform & between actual integer , subtract integer -> 001010 & 000101 = 001111    
    */
    private static boolean checkExactlyOneBitSet(int checker) {
        int sub = checker-1;

        return (checker & sub) == 0;
    }

    private static int getAlphabetIndex(char ch){
        if(ch >='a' && ch <='z') {
            return ch-'a';
        }
        else if(ch >='A' && ch <='Z') { 
            return ch-'A';
        } else 
            return -1; // special characters like space
    }
 

}
