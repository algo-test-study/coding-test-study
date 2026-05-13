class Solution {
    int[] numbers;
    int target;
    int answer;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        this.answer = 0;
        
        dfs(0,0);
        return answer;
    }
    
    void dfs(int index, int sum) {
        if (index == numbers.length) {
            if (sum == target) {
                answer++;   
            }
            return;
        }
        
        dfs(index + 1, sum + numbers[index]);
        dfs(index + 1, sum - numbers[index]);
    }
}
