/*
//방법1. 그래프 생성
알고리즘
- 그래프를 만들고
- dfs or bfs 돌림. 방문했으면 pass, 방문 안했으면 dfs 탐색 1번당 cnt++

시간복잡도
- O(n^2 + n + m) = O(n^2 n + n-1) = O(n^2)
*/
import java.util.*;
class Solution {
    static boolean [] visited;
    static Map<Integer, List<Integer>> g = new HashMap<>();
    public int solution(int n, int[][] computers) {
        visited = new boolean [n];
        
        for(int i=0; i<n; i++) g.put(i, new ArrayList<>());
        
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(computers[i][j] == 1){
                    g.get(i).add(j);
                    g.get(j).add(i);
                }
            }
        }
        int cnt = 0;
        
        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            dfs(i);
            cnt++;
        }
        
        return cnt;
    }
    
    public static void dfs(int curr){
        visited[curr] = true;
        for(int next : g.get(curr)){
            if(visited[next]) continue;
            dfs(next);
        }
    }
}
---
/*
//방법2. 배열 그대로 사용
시간복잡도
- O(n^2)
*/
import java.util.*;
class Solution {
    static boolean [] visited;
    static int [][] computers;
    static int n;
    public int solution(int n, int[][] computers) {
        int cnt = 0;
        this.computers = computers;
        this.n = n;
        visited = new boolean [n];
        for(int i=0; i<n; i++){
            if(visited[i]) continue;
            dfs(i);
            cnt++;
        }
        
        return cnt;
    }
    
    public static void dfs(int curr){
        visited[curr] = true;
        
        for(int next=0; next<n; next++){
            if(computers[curr][next] == 1 && !visited[next]){
                visited[next] = true;
                dfs(next);
            }
        }
    }
}
