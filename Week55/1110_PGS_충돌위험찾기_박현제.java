import java.util.*;
class Solution {
    static Map<Integer, int[] > curPos = new HashMap<>();
    static Map<Integer, int[] > point = new HashMap<>();
    static Map<Integer, List<Integer>> route = new HashMap<>();
    static Set<Integer> existingSet = new HashSet<>();
    static int n;
    static int x;
    static int ans = 0;
  
    public int solution(int[][] points, int[][] routes) {
        n = points.length;
        x = routes.length;       

        for(int i=1 ;i<=n;i++){
            point.put(i, points[i-1]);
        }
        
        for(int i = 1; i <= x; i++){
            route.put(i, new ArrayList<>());
            for(int j=1; j<routes[i-1].length; j++)
                route.get(i).add(routes[i-1][j]);
            }     
        }
    
        for(int i = 1; i <= x; i++){
            int startPoint = routes[i-1][0];
            int [] start = point.get(startPoint);
            curPos.put(i, start);
            existingSet.add(i);
        }
    
        checkCollision();
        
        while(existingSet.size() > 0){
            move();
            checkCollision();
        }
        
        return ans;
    }
    
    private static void move(){
        for(int node : existingSet){
            int [] cur = curPos.get(node);
            int targetPoint = route.get(node).get(0);
            int [] target = point.get(targetPoint);
            
            int cx = cur[0];
            int cy = cur[1];
            
            int tx = target[0];
            int ty = target[1];
            int nx = cx;
            int ny = cy;
            
            if(cx != tx){
                if(cx > tx)nx = cx - 1;
                else nx = cx + 1;
            }else if(cy != ty){
                if(cy > ty) ny = cy - 1;
                else ny = cy + 1;
            }
            curPos.put(node, new int []{nx, ny});
        }
    }
    
    private static void checkCollision(){
        Map<String, Integer> collisionNum = new HashMap<>();
        Set<Integer> removeSet = new HashSet<>();
        
        
         for(int node : existingSet){
             int [] cur = curPos.get(node);
             int cx = cur[0];
             int cy = cur[1];

             String key = cx + " " + cy;
             collisionNum.put(key, collisionNum.getOrDefault(key, 0) +1);

             int targetPoint = route.get(node).get(0);
             int [] target = point.get(targetPoint);
             int tx = target[0];
             int ty = target[1];
             
             if(cx == tx && cy == ty){
                if(route.get(node).size() == 1){
                    removeSet.add(node);
                }
                 
                route.get(node).remove(0);
             }
         }
        
        existingSet.removeAll(removeSet);
        
        int cnt = 0;
        for(int curCnt : collisionNum.values()){
            if(curCnt > 1){ 
                cnt++;
            }
        }
        ans += cnt;
    }
}

