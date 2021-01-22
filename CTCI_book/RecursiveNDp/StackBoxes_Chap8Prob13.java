import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.io.*;
import java.lang.reflect.Array;

public class  StackBoxes_Chap8Prob13 {

    public class Box {
        int length;
        int width;
        int height;
        Box(int i,int j,int k) {
            length=i;
            width=j;
            height=k;
        }
    }

    Box[] boxes;
    int n;

    StackBoxes_Chap8Prob13(int[] lengths, int[] widths,int[] heights,int n) {
        this.n = n;
        this.boxes = new Box[n];
        for(int i=0;i<n;i++) {
            boxes[i]=new Box(lengths[i],widths[i],heights[i]);
        }
    }

    public static void main(String[] args) {
        int[] lengths = new int[]{10,6,9,5,4};
        int[] heights = new int[]{2,5,7,4,3};
        int[] widths = new int[]{4,1,3,2,1};
        StackBoxes_Chap8Prob13 sol = new StackBoxes_Chap8Prob13(lengths,widths,heights,5);
        // sort by height       
        sol.sortBoxesByHeight();
        System.out.println("Sorted heights");
        for(Box box:sol.boxes) {
            System.out.print(box.height+" ");
        }
        System.out.println();
        // System.out.println(sol.stackBoxesIterative());
        System.out.println(sol.stackBoxes());
        System.out.println(sol.stackBoxes2());
    }

    //iterative
    /* 
    Iterative approach will not work because once a bottom is set it will not show other 
    options without that bottom
    For example 
        int[] lengths = new int[]{10,6,9,5,4};
        int[] heights = new int[]{2,5,7,4,3};
        int[] widths = new int[]{4,1,3,2,1};
        After 7, 5 is set =12
        but without 5, 7+4+3 = 14
    Hence Commented
    */
    // int stackBoxesIterative() {
    //     int maxHeight = 0;
    //     for(int i=0;i<n;i++) {  
    //         int formedHeight = boxes[i].height;
    //         int bottom = i;
    //         for(int j=i;j<n;j++) {
    //             if(valid(bottom,j)) {
    //                 bottom=j;
    //                 formedHeight+=boxes[j].height;
    //             }
    //         }
    //         maxHeight = Math.max(maxHeight,formedHeight);
    //     }
    //     return maxHeight;
    // }


    /* 
    Notes:
        heights[h1,h2,h3,h4]
        Math.max(
            if we select bottom as h1 -> h1+MaxHeight{h2,h3,h4};
                -> bottom as h2 -> h2+MaxHeight{h3,h4}
                -> bottom as h3 -> h3+MaxHeight{h4}
            if we select bottom as h2 -> h2+MaxHeight{h1,h2,h3};
            .
            .
            . )
        Among all the given heights only valid heights are selected
        for example, {h1,h2} and {h1,h3,h4} can be possible but not h2,h3
    */
    // Recursive with memorization
    int stackBoxes() {
        int maxHeight = 0;
        int[] memorization = new int[n];
        for(int bottom=0;bottom<n;bottom++) {              
            int formedHeight = helper(bottom,memorization);
            maxHeight = Math.max(maxHeight,formedHeight);
        }
        return maxHeight;        
    }

    int helper(int bottom,int[] memorization) {
        int maxHeight = 0;
        if(memorization[bottom]!=0)
            return memorization[bottom];
        for(int nextIndex=bottom+1;nextIndex<n;nextIndex++) {
            if(valid(bottom,nextIndex)) {
                int formedHeight = helper(nextIndex,memorization);
                // System.out.println("nextIndex: "+nextIndex+" formedHeight: "+formedHeight);
                maxHeight = Math.max(maxHeight,formedHeight);            
            }
        }
        maxHeight+=boxes[bottom].height;
        memorization[bottom] = maxHeight;
        return maxHeight;
    }

    /* 
    Approach 2
        {h1,h2,h3,h4}
        for each height either select as bottom or not
    */
    int stackBoxes2() {
        int[] memorization = new int[n];
        return helper2(null,0,memorization);
    }

    int helper2(Box bottom,int index,int[] memorization) {
        if(index>=n)
            return 0; //baseCase
        Box newBottom = boxes[index];
        int heightWithBox = 0;
        if(memorization[index] != 0)
            return memorization[index];
        if(bottom == null || valid(bottom,newBottom)) {
            heightWithBox = helper2(newBottom,index+1,memorization)+newBottom.height;
            memorization[index]=heightWithBox;
        }
        int heigthWithoutBox = helper2(bottom,index+1,memorization);
        return Math.max(heightWithBox,heigthWithoutBox);
    }

    boolean valid(Box bottom,Box next) {
        return bottom.width>next.width
         && bottom.length > next.length
         && bottom.width > next.width;
    }


    boolean valid(int bottom,int index) {
        return boxes[bottom].width>boxes[index].width
         && boxes[bottom].length > boxes[index].length
         && boxes[bottom].width > boxes[index].width;
    }

    void sortBoxesByHeight() {
        Arrays.sort(boxes,new Comparator<Box>() {
            @Override
            public int compare(Box b1, Box b2) {
                // descending
                return b2.height - b1.height;
            }
        });
    }
}
