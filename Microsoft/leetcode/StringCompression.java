/* 
Let's maintain anchor, the start position of the contiguous group of characters we are currently reading.

Now, let's read from left to right. We know that we must be at the end of the block when we are at the last character, or when the next character is different from the current character.

When we are at the end of a group, we will write the result of that group down using our write head. chars[anchor] will be the correct character, and the length (if greater than 1) will be read - anchor + 1. We will write the digits of that number to the array.
*/
class StringCompression {
    public int compress(char[] chars) {
        int anchor = 0;
        int write = 0;
        for(int read=0;read<chars.length;read++) {
            //when read is at last element or not a consecutive repeated character;
            if(read+1 == chars.length || chars[read+1] != chars[read]) {
                chars[write++]=chars[read];
                if(read > anchor) {
                    // ""+(read-anchor+1) length of array in string
                    for(char ch:(""+(read-anchor+1)).toCharArray()) {
                        chars[write++]=ch;
                    }
                }
                anchor = read+1;
            }
        }
        return write;
    }
}