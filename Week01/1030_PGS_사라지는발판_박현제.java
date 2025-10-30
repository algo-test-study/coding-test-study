/*
알고리즘
- 턴을 바꿔가면서 dfs
- 지금까지 짐/이김 & 현재 짐/이김 결과로 분기

시간복잡도
- O(4^25)
*/
import java.util.*;
class Solution {
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static int n,m;
    static int [][] board;
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        this.board = board;
        
        int ans = dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
        return ans;
    }
    
    private static int dfs(int ax, int ay, int bx, int by){
        if(board[ax][ay] == 0) return 0; 

        int cx = ax;
        int cy = ay;
        int cnt = 0;
        for(int i=0; i<4; i++){
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if(oufOfBounds(nx,ny) ||  board[nx][ny] == 0) continue;
            board[cx][cy] = 0;
            int curr = dfs(bx, by, nx, ny)+1; // 상대 턴 전환
            board[cx][cy] = 1;
            
            //지금까지 짐. 이번에 이김 -> 현재 이김
            if(curr% 2 == 1 && cnt % 2 == 0) cnt = curr;
            //지금까지 짐. 이번에 짐 -> 최대한 많이 움직임
            else if(curr % 2 == 0 && cnt % 2 == 0) cnt = Math.max(curr, cnt);
            //지금까지 이김. 이번에 이김 -> 최대한 적게 움직임
            else if(curr % 2 == 1 && cnt % 2 == 1) cnt = Math.min(curr, cnt);
        }

        return cnt;   
    }
    
    private static boolean oufOfBounds(int nx, int ny){
        return nx >= n || ny >= m || nx < 0 || ny < 0;
    }
}
