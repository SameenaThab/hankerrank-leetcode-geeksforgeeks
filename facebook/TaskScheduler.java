//https://leetcode.com/problems/task-scheduler

/* 
The total number of CPU intervals we need consists of busy and idle slots. Number of busy slots is defined by the number of tasks to execute: len(tasks). The problem is to compute a number of idle slots.
Maximum possible number of idle slots is defined by the frequency of the most frequent task: idle_time <= (f_max - 1) * n.
Input: ["A","A","A","A","A",B","B","C"] n=2
A _ _ A _ _ A _ _ A _ _ A idleTime = (5-1)*2 = 8
A B _ A B _ A _ _ A _ _ A idletime = 8-min(A_freq-1,B_freq) = 8-(4,2) = 6
A B C A B _ A _ _ A _ _ A idleTime = 6-1 = 5
adding time taken for task = 5+No_of_tasks = 5+8 = 13 

Algorithm:
1.The maximum number of tasks is 26. Let's allocate an array frequencies of 26 elements to keep the frequency of each task.
2.Iterate over the input array and store the frequency of task A at index 0, the frequency of task B at index 1, etc.
3.Sort the array and retrieve the maximum frequency f_max. This frequency defines the max possible idle time: idle_time = (f_max - 1) * n.
4.Pick the elements in the descending order one by one. At each step, decrease the idle time by min(f_max - 1, f) where f is a current frequency. Remember, that idle_time is greater or equal to 0.
5.Return busy slots + idle slots: len(tasks) + idle_time.

Time: O(n). Though we use arrays.sort, n is always 26
Space = O(1). freq array size is constant 26 
*/
class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for(char task:tasks) {
            freq[task-'A']++;
        }
        Arrays.sort(freq);
        int maxFreq = freq[25];
        int idleTime = (maxFreq-1)*n;
        for(int i=24;i>=0;i--) {
            idleTime -= Math.min(maxFreq-1,freq[i]);
        }
        idleTime = Math.max(0,idleTime);
        return idleTime+tasks.length;
    }


/* 
Using Math:
Let's use some math to compute the answer. There are two possible situations:
Situation1: The most frequent task is not frequent enough to force the presence of idle slots.
[A B C D E A F A], n= 2
A [B C] A [D E] A F => B,C,D,E are in cooling period and F is not 
Situation2: The most frequent task is frequent enough to force some idle slots.
A B A A B C A A, n =2
A [B C] A B _ A _ _ A _ _ A -> lots of idle cooling period
The answer is the maximum between these two.
The first situation is straightforward because the total number of slots is defined by the number of tasks: len(tasks).
The second situation is a bit more tricky and requires to know the number n_max [no of frequencies with max freq]and the frequency f_max of the most frequent tasks.
input: A B C D B A B A A B
f_max = 4
n_max = 2 (A, B have max freq = 4)
A [B C] A [B D] A [B _] A B => [] = coolning epriod
1{A [B C]} 2{A [B D]} 3{A [B _]} 4{A B}
Group 1 = 1+n => a for task A and cooling period 2
Group 1,2,3 = (f_max-1)*(1+n) bcoz no of group will be fMax-1
Group 4 = n_max (no of task with f_max)

Algorithm:
1.The maximum number of tasks is 26. Let's allocate an array frequencies of 26 elements to keep the frequency of each task.
2.Iterate over the input array and store the frequency of task A at index 0, the frequency of task B at index 1, etc.
3.Find the maximum frequency: f_max = max(frequencies).
4.Find the number of tasks which have the max frequency: n_max = frequencies.count(f_max).
5.If the number of slots to use is defined by the most frequent task, it's equal to (f_max - 1) * (n + 1) + n_max.
6.Otherwise, the number of slots to use is defined by the overall number of tasks: len(tasks).
7.Return the maximum of these two: max(len(tasks), (f_max - 1) * (n + 1) + n_max).

Time Complexity: O(N_total). N_total is a number of tasks to execute. This time is needed to iterate over the input array tasks and to compute the array frequencies. Array frequencies contains 26 elements, and hence all operations with it takes constant time.
Space Complexity: O(1), to keep the array frequencies of 26 elements.

*/
    public int leastIntervalUsingMath(char[] tasks, int n) {
        // frequencies of the tasks
        int[] frequencies = new int[26];
        for (int t : tasks) {
            frequencies[t - 'A']++;
        }

        // max frequency
        int f_max = 0;
        for (int f : frequencies) {
            f_max = Math.max(f_max, f);
        }
        
        // count the most frequent tasks
        int n_max = 0;
        for (int f : frequencies) {
            if (f == f_max) n_max++;
        }
        
        return Math.max(tasks.length, (f_max - 1) * (n + 1) + n_max);
    }

}