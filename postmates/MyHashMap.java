class MyHashMap {
//https://leetcode.com/problems/design-hashmap/submissions/
    /** Initialize your data structure here. */
    Integer[][] hashMap;
    public MyHashMap() {
        hashMap = new Integer[1000][1000];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int bucket = key/1000;
        int keyHash = key%1000;
        hashMap[bucket][keyHash] = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int bucket = key/1000;
        int keyHash = key%1000;
        if(hashMap[bucket] == null || hashMap[bucket][keyHash] == null)
            return -1;
        else
            return hashMap[bucket][keyHash];
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int bucket = key/1000;
        int keyHash = key%1000;
        hashMap[bucket][keyHash] = null;        
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */