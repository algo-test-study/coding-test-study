class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int active = 0;
        
        int[] added = new int[24]; 
        for (int i = 0; i < 24; i++) {
            if (i - k >= 0) active -= added[i - k];
            int required = players[i] / m;
            
            if (active < required) {
                int need = required - active;   
                added[i] = need;
                active += need;
                answer += need;
            }
            
        }
        return answer;
    }
}
