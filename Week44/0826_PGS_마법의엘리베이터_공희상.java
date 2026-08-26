class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while (storey > 0) {
            int current = storey % 10;
            int next = (storey / 10) % 10;
            
            if (current > 5) {
                answer += 10 - current;
                storey += 10 - current;
            } else if (current < 5) {
    answer += current;
    storey -= current;
} else {
                answer += 5;
                if (next >= 5) {
                    storey += 5;
                } else {
                    storey -= 5;
                }
            }
            
            storey /= 10;
            
        }        
        return answer;
    }
}
