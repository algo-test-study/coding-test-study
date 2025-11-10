
/*
전선을 하나씩 끊어봄

알고리즘
- 트리 > map
- dfs 돌리기
    - 전선 끊기: 전체 wires 반복 현재 wire에 해당하면 continue
- n개의 노드 - 현재 탐색한 노드 개수 = 나머지 노드 개수

시간복잡도
- O(N^2)
*/
import java.util.*;

class Solution {
    static Map<Integer, List<Integer>> g = new HashMap<>();
    public int solution(int n, int[][] wires) {

        for(int i = 1; i <= n; i++){
            g.put(i, new ArrayList<>());
        }
        
        for(int [] wire : wires){
            int a = wire[0];
            int b = wire[1];
            
            g.get(a).add(b);
            g.get(b).add(a);
        }
        
        int min = Integer.MAX_VALUE;

        for(int [] wire : wires){
            int aCnt = dfs(1, wire[0], wire[1], new boolean[n+1]);
            int bCnt = n - aCnt;
            int cnt = Math.abs(aCnt - bCnt);
            min = Math.min(cnt, min);
        }
        
        return min;
    }
    
    static int dfs(int curr, int x, int y, boolean [] visited){
        visited[curr] = true;
        int cnt = 1;
        
        for(int next : g.get(curr)){
            if(visited[next]) continue;
            if((x == curr && y == next) || (x == next && y == curr)) continue;
            cnt += dfs(next, x, y, visited);
        }
        
        return cnt;
    }
}


