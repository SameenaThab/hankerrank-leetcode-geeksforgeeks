// https://leetcode.com/discuss/interview-question/891655/Postmates-or-OA-or-Rectangle-Boxes

import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class RectangleBoxes {
    // public static List<int[]> rectangles = new ArrayList<int[]>(); 
    public static int[] minRectangle = null;
    public static void main(String[] args) {
        int[][] operations = {{0, 1, 3}, {0, 4, 2}, {1, 3, 4}, {1, 3, 2}};
        for(int[] operation:operations) {
            if(operation[0] == 0) {
                int[] newRectangle = new int[2];
                newRectangle[0] = operation[1];
                newRectangle[1] = operation[2];
                if(minRectangle == null || (newRectangle[0]*newRectangle[1]) < minRectangle[0]*minRectangle[1]) {
                    minRectangle = newRectangle;
                }
                // rectangles.add(newRectangle);
            } else {
                int[] bigRectangle = new int[2];
                bigRectangle[0] = operation[1];
                bigRectangle[1] = operation[2];
                System.out.println(willFit(bigRectangle));
            }
        }
   }

    private static boolean willFit(int[] bigRectangle) {
        // System.out.println(minRectangle[0] + " " + minRectangle[1]);
        return (minRectangle[0] < bigRectangle[0] && minRectangle[1] < bigRectangle[1])
             || (minRectangle[1] < bigRectangle[0] && minRectangle[0] < bigRectangle[1]);
        // for(int[] rectangle : rectangles) {
        //     if((rectangle[0] < bigRectangle[0] && rectangle[1] < bigRectangle[1])
        //      || (rectangle[1] < bigRectangle[0] && rectangle[0] < bigRectangle[1])) {
        //          return true;
        //      }
        //      continue;
        // }
        // return false;
    }

}