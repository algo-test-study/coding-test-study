class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(numbers, 0, target, 0);

        return answer;
    }
    
    public static int dfs(int[] numbers, int sum, int target, int depth) {
        if (depth == numbers.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        } else {
            int count = 0;
            count += dfs(numbers, sum + numbers[depth], target, depth + 1);
            count += dfs(numbers, sum - numbers[depth], target, depth + 1);
            return count;
        }
    }   
}
