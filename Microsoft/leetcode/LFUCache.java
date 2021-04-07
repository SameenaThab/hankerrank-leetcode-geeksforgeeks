import java.util.*;

class LFUCache {
    //key,values
    Map<Integer,Integer> values = new HashMap<Integer,Integer>();
    // key,freq
    Map<Integer,Integer> frequencies = new HashMap<Integer,Integer>();
    // frequency,LinkedListMap of keys>
    // LinkedHashMap of keys
    Map<Integer,LinkedHashSet<Integer>> frequencyMap = new HashMap<Integer,LinkedHashSet<Integer>>();
    int capacity;
    int minFreq = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.values = new HashMap<Integer,Integer>();
        this.frequencies = new HashMap<Integer,Integer>();
        this.frequencyMap = new HashMap<Integer,LinkedHashSet<Integer>>();
        frequencyMap.put(1,new LinkedHashSet<Integer>());
    }
    
    public int get(int key) {
        if(!values.containsKey(key))
            return -1;
        int freq = frequencies.get(key);
        frequencies.put(key,freq+1);
        frequencyMap.get(freq).remove(key);
        // when cureent freq is least frequency and no elements with that frequency are present, then we need to increment minFreq
        if(freq == minFreq && frequencyMap.get(freq).size() == 0) {
            minFreq++;
        }
        if (!frequencyMap.containsKey(freq + 1))
            frequencyMap.put(freq + 1, new LinkedHashSet<>());
        frequencyMap.get(freq + 1).add(key);
        return values.get(key);
    }
    
    public void put(int key, int value) {
        if (capacity <= 0)
            return;
        if(values.containsKey(key)) {
            values.put(key,value);
            // this will set the key to right frequecy in bot frquencies and frequencyMap
            get(key);
            return;
        }
        if (values.size() >= capacity) {
            int evit = frequencyMap.get(minFreq).iterator().next();
            frequencyMap.get(minFreq).remove(evit);
            values.remove(evit);
            frequencies.remove(evit);       
        }
        // If the key is new, insert the value and current min should be 1 of course
        values.put(key,value);
        frequencies.put(key,1);
        minFreq = 1;
        frequencyMap.get(1).add(key);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */