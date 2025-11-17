/*
알고리즘
- 1에서 2~N 까지 최단 거리(0, 양수): 다익스트라
- 2~n 의 최단 거리 <= K : cnt++

시간복잡도
- O(rode.length * log N)
*/
import java.util.*;
class Solution {
    public int solution(int N, int[][] road, int K) {
        int [] dist = new int[N+1];
        int INF = Integer.MAX_VALUE;
        Map<Integer, List<int []>> g = new HashMap<>();
        
        for(int i=1; i<=N; i++){
            g.put(i, new ArrayList<>());
        }
        
        for(int i=2; i<=N; i++){
            dist[i] = INF;
        }
        
        for(int i=0; i<road.length; i++){
            int [] curr = road[i];
            int s = curr[0];
            int e = curr[1];
            int w = curr[2];
            
            g.get(s).add(new int [] {e, w});
            g.get(e).add(new int [] {s, w});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        pq.add(new int [] {1, 0}); 
        
        while(!pq.isEmpty()){
            int [] currs = pq.poll();
            int curr = currs[0];
            
            for(int [] nexts : g.get(curr)){
                int next = nexts[0];
                int w = nexts[1];
                
                if(dist[next] <= dist[curr] + w) continue;
                dist[next] = dist[curr] + w;
                pq.offer(nexts);
            }
        }
        
        int cnt = 0;
        
        for(int i=1; i<=N; i++){
            if(dist[i] <= K) cnt++;
        }
        return cnt;
    }
}
