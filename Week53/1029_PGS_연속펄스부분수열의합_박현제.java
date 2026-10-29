class Solution {
    public long solution(int[] sequence) {
        long currMax1 = 0;
        long currMax2 = 0;
        long max = 0;
        
        int n = sequence.length;
        
        for(int i = 0; i < n; i++){
            int curr = i % 2 == 0 ? sequence[i] : -sequence[i];
            
            currMax1 = Math.max(curr, currMax1 + curr);
            currMax2 = Math.max(-curr, currMax2 - curr);
            
            max = Math.max(max, Math.max(currMax1, currMax2));
        }
        
        return max;
    }
}
