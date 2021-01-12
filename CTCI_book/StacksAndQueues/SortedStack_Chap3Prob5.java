import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//chapter 3 Question 4


public class SortedStack_Chap3Prob5 {

	static Stack<Integer> st=new Stack<Integer>();
   	static Stack<Integer> temp =new Stack<Integer>();

    public static void main(String[] args)
    {

    	//System.out.println(peek());
    	push(10);
    	System.out.println(st.peek());
    	push(11);
    	push(1);
    	push(2);
    	System.out.println(st.peek());
    	pop();
    	pop();
    	System.out.println(st.peek());
	}

	static void pop()
	{
		st.pop();
	}

	static void push(int a)
	{
		while(!st.empty() && a>st.peek())
		{
			temp.push(st.pop());
		}
		st.push(a);
		while(!temp.empty())
			st.push(temp.pop());
	}

}