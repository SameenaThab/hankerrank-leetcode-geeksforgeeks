import java.util.Scanner;

/* 
Determine whether a string is a palindrome, ignoring non-alphanumeric characters and ignore case. Examples:
Input: Do geese see God? Output: True
Input: Was it a car or a cat I saw? Output: True
Input: A brown fox jumping over Output: False


*/
class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;

        while(left<right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {  // Note 1, 2
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                return false;
            left++;
            right--;
        }
        return true;
    }

    static boolean isAlphabet(char ch) {
        return (ch>='a'&& ch<='z') || (ch>='A'&& ch<='Z') || (ch>='0'&& ch<='9');
    }

    public static void main(String[] args) {

        System.out.println((int)'9');
        System.out.println((int)'0');
        System.out.println(isAlphabet('?'));
        System.out.println(isAlphabet('a'));
        System.out.println("str: Do geese see God? "+isPalindrome("1Do geese see God?1"));
        System.out.println("str: Was it a car or a cat I saw?  "+isPalindrome("Was it a car or a cat I saw? "));
        System.out.println("str: A brown fox jumping over "+isPalindrome("A brown fox jumping over"));
    }
}