import java.util.*;
/* 
https://algo.monster/problems/day_of_week
Given current day as day of the week and an integer K, the task is to find the day of the week after K days.

Example 1:
Input:
day = “Monday”

K = 3

Output: Thursday
Example 2:
Input:
day = “Tuesday”

K = 101

Output: Friday
*/
class DayOfWeek {
    // no doing map because if key is integer, then takes same O(n) as list to find the index of currDay
    // if key is String(weekday) , takes O(n) to find weeday for index, same as list
    List<String> days;
    String dayOfWeek(String currDay, int k) {
        this.days = new ArrayList<String>(){
            {
                add("Monday");
                add("Tuesday");
                add("Wednesday");
                add("Thursday");
                add("Friday");
                add("Saturday");
                add("Sunday");
            }
        };

        int index = days.indexOf(currDay);
        return days.get((index + k) % 7);       
    }

    public static void main(String[] args) {
        DayOfWeek sol = new DayOfWeek();
        System.out.println(sol.dayOfWeek("Monday", 3));
        System.out.println(sol.dayOfWeek("Tuesday", 101));
        System.out.println(sol.dayOfWeek("Sunday", 2));
    }
}