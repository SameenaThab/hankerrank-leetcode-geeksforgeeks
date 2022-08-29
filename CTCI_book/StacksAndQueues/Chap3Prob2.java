import java.util.*;
//chapter 3 Question 2


public class Chap3Prob2 {

	static Stack<Integer> st=new Stack<Integer>();
   	static Stack<Integer> stmin =new Stack<Integer>();

    public static void main(String[] args)
    {
    	push(10);
    	System.out.println(min());
    	push(11);
    	push(1);
    	push(2);
    	System.out.println(min());
    	pop();
    	System.out.println(min());
    	pop();
    	System.out.println(min());
	}

	static void push(int a)
	{
		if(stmin.empty() || a<stmin.peek())
			stmin.push(a);
		st.push(a);
	}

	static void pop()
	{
		if(st.peek()==stmin.peek())
			stmin.pop();
		st.pop();
	}

	static int min()
	{
		return stmin.peek();
	}
}