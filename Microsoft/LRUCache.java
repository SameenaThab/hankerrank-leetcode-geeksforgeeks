import java.util.*;

class LRUCache {

    class DLinkedNode {
        int value;
        int key;
        DLinkedNode prev;
        DLinkedNode next;
    }

    Map<Integer,DLinkedNode> cache = new HashMap<Integer,DLinkedNode>();
    int capacity;
    DLinkedNode head = new DLinkedNode();
    DLinkedNode tail = new DLinkedNode();

    public LRUCache(int capacity) {
      this.capacity = capacity; 
      head = new DLinkedNode();
      tail = new DLinkedNode();
      head.next = tail;
      tail.prev = head;
    }
    
    public int get(int key) {
        if(cache.containsKey(key)) {
            DLinkedNode node = cache.get(key);
            moveToHead(node);
            return node.value;
        }
        return -1;  
    }
    
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            DLinkedNode node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            DLinkedNode node = new DLinkedNode();
            node.key=key;
            node.value = value;
            addNode(node);
            cache.put(key,node);
            if(cache.size()>capacity) {
                DLinkedNode last = popTail();
                cache.remove(last.key);
            }
        }
    }

    private DLinkedNode popTail() {
        DLinkedNode node = tail.prev;
        removeNode(node);
        return node;
    }

    public void addNode(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public void removeNode(DLinkedNode node) { 
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}