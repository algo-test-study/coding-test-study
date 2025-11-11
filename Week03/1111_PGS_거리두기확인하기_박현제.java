
/*
거리 <= 2 -> X
거리두기 ? 1 : 0

알고리즘
- 응시자 별로 거리두기 확인: 미리 p 위치 담아 for문
- dfs: visited / 파티션(X) > continue

시간복잡도
O(25*25)

*/

import java.util.*;
class Solution {
    private static final int dx[] = {0,0,-1,1};
    private static final int dy[] = {1,-1,0,0};
    static boolean isAvailable;
    static char[][] room;
    static boolean[][] visit = new boolean [5][5];
    
    
    
    public int[] solution(String[][] places) {
        int[] answer = new int [places.length];
        for(int i=0; i<places.length ; i++){
            isAvailable = false;
            String [] p = places[i];
            room = new char[5][5];
            
            for(int j=0; j< room.length; j++){
                room[j] = p[j].toCharArray();
            }
         

            for(int x=0; x<5 && !isAvailable; x++){
                for(int y=0; y<5 && !isAvailable; y++){
                    if(room[x][y] == 'P'){
                        visit[x][y] = true;
                        dfs(x, y, 0);
                        visit[x][y] = false;
                    }
                }
            }
            if(isAvailable){
                answer[i] = 0;
            }else{
                answer[i] = 1;
            }
        }
        
        return answer;
    }
    
    public static void dfs(int x, int y, int depth){
        if(depth == 2){
            return;
        }
        
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx<0 || nx>=5 || ny<0 || ny>=5 ) continue;
            if(visit[nx][ny]) continue;
            if(room[nx][ny] == 'P'){
                isAvailable = true;
                return;
            } 
            else if(room[nx][ny] == 'O') {
                visit[nx][ny] = true;
                dfs(nx, ny, depth+1);
                visit[nx][ny] = false;
            }
        }
    }
}
