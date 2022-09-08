class TopKFrequent {
    class Node {
        int val;
        int freq;
        Node(int val) {
            this.val = val;
        }
        
        void incFreq() {
            this.freq++;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>(){
            public int compare(Node a,Node b) {
                return b.freq-a.freq;
            }
        });
        HashMap<Integer,Node> map = new HashMap<Integer,Node>();
        for(int num:nums) {
            Node node = map.getOrDefault(num,new Node(num));
            node.incFreq();
            map.put(num,node);
        }
        for(int num:map.keySet()) {
            pq.add(map.get(num));
        }
        int[] result = new int[k];
        int i=0;
        while(!pq.isEmpty() && i<k) {
            result[i++]=pq.poll().val;
        }
        return result;
    }
}