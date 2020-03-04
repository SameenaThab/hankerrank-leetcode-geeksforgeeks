class TopKFrequent {
    //https://leetcode.com/explore/interview/card/amazon/79/sorting-and-searching/2995/
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i:nums) {
            map.put(i,map.getOrDefault(i,0)+1);
        }
        
        //ascending order
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a,Integer b) {
                return map.get(a)-map.get(b);
            }
        });
        
        for(int i:map.keySet()) {
            pq.add(i);
            if(pq.size()>k)
                pq.poll(); // removing the least frequest element
        }
        
        List<Integer> result = new ArrayList<Integer>();
        while(!pq.isEmpty()) {
            result.add(pq.poll());
        }
        Collections.sort(result);
        return result;
    }
}