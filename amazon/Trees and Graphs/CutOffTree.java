//https://leetcode.com/explore/interview/card/amazon/78/trees-and-graphs/2986/
class CutOffTree {
    List<List<Integer>> forest;
    public int cutOffTree(List<List<Integer>> forest) {
        this.forest = forest;
        PriorityQueue<Pair<Integer,Integer>> pq = new PriorityQueue<Pair<Integer,Integer>>(new Comparator<Pair<Integer,Integer>>() {
            public int compare(Pair<Integer,Integer> a,Pair<Integer,Integer> b) {
                return forest.get(a.getKey()).get(a.getValue())-forest.get(b.getKey()).get(b.getValue());
            }
        });
        
        for(int i=0;i<forest.size();i++)
            for(int j=0;j<forest.get(0).size();j++){
                if(forest.get(i).get(j) > 1)
                    pq.add(new Pair(i,j));
            }
        for(Pair<Integer,Integer> pair:pq){
            int i = pair.getKey();
            int j = pair.getValue();
            System.out.println(pair.getKey()+" "+pair.getValue()+" "+forest.get(i).get(j));
        }
        
        if(forest.get(0).get(0) == 0) {
            return -1;
        }   
        int si = 0;
        int sj = 0;
        int count = 0;
        int[] adjr = new int[]{0,0,-1,1};
        int[] adjc = new int[]{1,-1,0,0};
        while(!pq.isEmpty()) {
            Pair<Integer,Integer> pair = pq.remove();
            int d = dist(si,sj,pair.getKey(),pair.getValue());
            System.out.println("source: "+si+" "+sj);
            System.out.println("destination: "+pair.getKey()+" "+pair.getValue());
            System.out.println("distance: "+d);
            if(d<0)
                return -1;
            si=pair.getKey();
            sj=pair.getValue();
            count+=d;
        }
        
        return count;
    }
    
    int dist(int si,int sj,int di,int dj) {
        int m = forest.size();
        int n = forest.get(0).size();
        int count = 0;
        int[] adjr = new int[]{0,0,-1,1};
        int[] adjc = new int[]{1,-1,0,0};
        Set<String> visited = new HashSet<String>();
        Queue<int[]> q= new LinkedList<int[]>();
        q.add(new int[]{si,sj,0});
        visited.add(si+" "+sj);
        while(!q.isEmpty()) {
            int[] dest = q.poll();
            if(dest[0] == di && dest[1] == dj)
                return dest[2];
            for(int i=0;i<4;i++) {
                int row = dest[0]+adjr[i];
                int col = dest[1]+adjc[i];
                if(row >= 0 && row < m && col >=0 && col < n && forest.get(row).get(col) != 0 && !visited.contains(row+" "+col)) {                    
                    q.add(new int[]{row,col,dest[2]+1}); 
                    visited.add(row+" "+col);
                }                   
            }       
        }
        return -1;
    }
}