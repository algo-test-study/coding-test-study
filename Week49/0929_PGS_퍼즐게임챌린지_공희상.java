class Solution {

    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 0;
        
        for (int diff : diffs) right = Math.max(right, diff);
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            if (canSolve(diffs, times, limit, mid)) { right = mid; } 
            else { left = mid + 1; }
        }
        
        int answer = left;
        return answer;
    }
    
    boolean canSolve(int[] diffs, int[] times, long limit, int level) {
        long totalTime = 0;
        
        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];
            int timeCur = times[i];
            
            if (diff <= level) {
                totalTime +=timeCur;
            } else {
                long timePrev = i == 0 ? 0 : times[i-1];
                totalTime += (diff - level) * (timeCur + timePrev) + timeCur;
            }
            if (totalTime > limit) return false;
        }
        return true;
    }
}
