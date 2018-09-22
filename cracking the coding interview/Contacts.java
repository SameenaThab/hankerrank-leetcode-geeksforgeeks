/*
We're going to make our own Contacts application! The application must perform two types of operations:

add name, where name is a string denoting a contact name. This must store name  as a new contact in the application.
find partial, where partial is a string denoting a partial name to search the application for. It must count the number of contacts starting with partial and print the count on a new line.
Given  sequential add and find operations, perform each operation in order.*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Trie
    {
        int count=0;
        Trie[] children=new Trie[26];
        public void add(Trie root,String s)
        {
            for(char c:s.toCharArray())
            {
                int index=c-'a';
                if(root.children[index]==null)
                {
                    Trie child=new Trie();
                    root.children[index]=child;
                }
                root.children[index].count++;
                root=root.children[index];
            }                
        }
        
        public int find(Trie root,String s)
        {
            //int index;
            for(char c:s.toCharArray())
            {
                int index=c-'a';
                if(root.children[index]==null)
                    return 0;
                else
                {
                    root=root.children[index];
                }
            }
            return root.count;
        }
    }

public class Contacts {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Trie root=new Trie();
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();                    
         if(op.equals("add"))
            root.add(root,contact);
         if(op.equals("find"))
             System.out.println(root.find(root,contact));
        }

    }
    
  
}
