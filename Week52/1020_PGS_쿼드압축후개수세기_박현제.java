class Solution {
    private static int [][] map;
    private static int [] ans = new int [] {0, 0};
    public int[] solution(int[][] arr) {            
        map = arr;
        
        int n = arr.length;
         
        recursive(0, 0, n);
        
        return ans;
    }
    
    public static void recursive(int sx, int sy, int n){
        
        if(isValid(sx, sy, n)){
            if (map[sx][sy] == 0){
                ans[0]++;
            }else{
                ans[1]++;
            }
            
            return;
        }
        
        int halfN = n / 2;
        
        if(halfN == 0) return;
        
        recursive(sx, sy, halfN);
        recursive(sx, sy + halfN, halfN);
        recursive(sx + halfN, sy, halfN);
        recursive(sx + halfN, sy + halfN, halfN);
    }
    
    public static boolean isValid(int sx, int sy, int n){
        int start = map[sx][sy];
        
        for(int i = sx; i < sx + n; i++){
            for(int j = sy; j < sy + n; j++){
                if(map[i][j] != start) return false;
            }
        }
        
        return true;   
    }
}
