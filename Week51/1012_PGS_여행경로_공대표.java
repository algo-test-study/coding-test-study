import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        
        for (String[] ticket : tickets) {
            graph.computeIfAbsent(ticket[0], k -> new PriorityQueue<>()).offer(ticket[1]);
        }
        
        LinkedList<String> result = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("ICN");
        
        while (!stack.isEmpty()) {
            String airport = stack.peek();
            
            if (graph.containsKey(airport) && !graph.get(airport).isEmpty()) {
                stack.push(graph.get(airport).poll());
            } else {
                result.addFirst(stack.pop());
            }
        }
        
        return result.toArray(new String[0]);
    }
}
