import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//chapter 1 Question 1.6

public class Chap1Prob6 {

	static String stringCompression(String st)
	{
		char present = st.charAt(0);
		int count=1;
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<st.length();i++)
		{
			if(st.charAt(i)==present)
				count++;
			else
			{
				sb.append(present+Integer.toString(count));
				count=1;
				present = st.charAt(i);
			}
		}
		if(sb.length()<st.length())
			return sb.toString();
		else
			return st;
	}

    public static void main(String[] args)
    {
    	System.out.print(stringCompression("aaabbca"));
	}
}