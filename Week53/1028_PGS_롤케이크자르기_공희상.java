class Solution {
    public int solution(int[] toppings) {
        int answer = 0;
        
        int[] rightCount = new int[10001];
        boolean[] leftVisited = new boolean[10001];
        
        int leftTypes = 0;
        int rightTypes = 0;
        
        for (int topping : toppings) {
            if (rightCount[topping] == 0) {
                rightTypes++;
            }
            rightCount[topping]++;
        }
        
        for (int i = 0; i < toppings.length - 1; i++) {
            int topping = toppings[i];
            
            if (!leftVisited[topping]) {
                leftVisited[topping] = true;
                leftTypes++;
            }
            
            rightCount[topping]--;
            
            if (rightCount[topping] == 0) {
                rightTypes--;
            }
            
            if (leftTypes == rightTypes) {
                answer++;
            }
        }
        
        return answer;
    }
}
