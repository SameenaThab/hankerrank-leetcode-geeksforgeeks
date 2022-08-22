import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import javax.lang.model.util.ElementScanner6;

import java.io.*;

/* 
https://stackoverflow.com/questions/9141830/explain-the-use-of-a-bit-vector-for-determining-if-all-characters-are-unique
Answer 2 has a great explantion
a      =00000000000000000000000000000001
checker=00000000000000000000000000000000 ( INT = 4 BYTES, 1BYTE = 8 BITS, THERFORE 4*8 = 32 bits we use 26(#alphabets) bits)

checker='a' or checker;
// checker now becomes = 00000000000000000000000000000001
checker=00000000000000000000000000000001

a and checker=0 no dupes condition
string 'az'

checker=00000000000000000000000000000001
z      =00000010000000000000000000000000

z and checker=0 no dupes 

checker=z or checker;
// checker now becomes 00000010000000000000000000000001  

checker & index = 0 when no duplicates because all the bits are either (0 & 0) or (1& 0) or (0&1)
checker & index = 1 when duplicates present because there is one (1&1) value

bitShift = 1<<index gives the integer where bit at index of the alphabet is set ot 1
eg: index 0f b = 2, 
1 << 2 = 0001 ->shifted once-> 0010 -> second shifted -> 0100 (where index 2 is set to 1) 
*/

class DuplicateCharacter_BitVector {

    public static void main(String[] args) {
        String[] words = new String[]{"boOth","ghost","car","caAr","bar","tooth"};
        for(String word:words) {
            String result = hasDuplicates(word)? "duplicates":"no duplicates";
            System.out.println(word+" has "+result);
        }
    }

    private static boolean hasDuplicates(String word) {
        int counter = 0;
        for(char ch:word.toCharArray()){
            int index;
            if(ch >='a' && ch <='z')
                index = ch-'a';
            else // (ch >='A' && ch <='Z')
                index = ch-'A';
            int bitShift = 1<<index;    
            if((counter & bitShift) > 0) // character already exists
                return true;
            counter|=bitShift;
        }
        return false;
    }

}
