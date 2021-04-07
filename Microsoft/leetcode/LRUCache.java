import java.util.*;

class LRUCache {
    
    class DLinkedList {
        int key;
        int value;
        DLinkedList prev;
        DLinkedList next;
    }
    
    Map<Integer,DLinkedList> cache;
    int capacity;
    DLinkedList head;
    DLinkedList tail;

    public LRUCache(int capacity) {
        this.cache = new HashMap<Integer,DLinkedList>();
        this.capacity = capacity;
        this.head = new DLinkedList();
        this.tail = new DLinkedList();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(cache.containsKey(key)){
            DLinkedList node = cache.get(key);
            moveToHead(node);  
            return node.value;
        } 
        return -1;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            DLinkedList node = cache.get(key);
            node.value = value;
            moveToHead(node);            
        } else {
            DLinkedList node = new DLinkedList();
            node.key = key;
            node.value = value;
            cache.put(key,node);
            addNode(node);
            if(cache.size()>capacity) {
                DLinkedList last = popTail();
                cache.remove(last.key);
            }
        }
    }
    
    public DLinkedList popTail() {
        DLinkedList last = tail.prev;
        removeNode(last);
        return last;
    }
    
    
    public void moveToHead(DLinkedList node) {
        removeNode(node);
        addNode(node);
    }
    
    public void addNode(DLinkedList node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    public void removeNode(DLinkedList node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
