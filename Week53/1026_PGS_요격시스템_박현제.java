import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int cnt = 0;
        
        Arrays.sort(targets, (a, b) ->{
            if(a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        
        int prevEnd = -1;
        
        for(int [] target : targets){
            int currStart = target[0];
            int currEnd = target[1];
            
            if(currStart < prevEnd) continue;
            prevEnd = currEnd;
            cnt++;
        }
        
        return cnt;
    }
}

