import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
//chapter 3 Question 4


public class MyQueue_Chap3Prob4 {

	static Stack<Integer> st1=new Stack<Integer>();
   	static Stack<Integer> st2 =new Stack<Integer>();

    public static void main(String[] args)
    {

    	//System.out.println(peek());
    	add(10);
    	System.out.println(peek());
    	add(11);
    	add(1);
    	add(2);
    	System.out.println(peek());
    	remove();
    	remove();
    	System.out.println(peek());
    
	}

	static void add(int a)
	{
		st1.push(a);
	}

	static void remove()
	{
		if(!st2.empty())
			st2.pop();
		else
		{
			while(!st1.empty())
			{
				st2.push(st1.pop());
			}
			st2.pop();
		}
	}

	static int peek()
	{
		if(!st2.empty())
			return st2.peek();
		else
		{
			while(!st1.empty())
			{
				st2.push(st1.pop());
			}
			return st2.peek();
		}
	}
}