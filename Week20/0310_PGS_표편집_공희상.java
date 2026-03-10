import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmds) {
        int[] prev = new int[n];
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n-1] = -1;
        
        Stack<Integer> history = new Stack<>();
        
        for (String command : cmds) {
            if (command.startsWith("U")) {
                int times = Integer.parseInt(command.substring(2));
                while (times --> 0) k = prev[k];
            } else if (command.startsWith("D")) {
                int times = Integer.parseInt(command.substring(2));
                while (times --> 0) k = next[k];
            } else if (command.startsWith("C")) {
                history.push(k);
                
                if (prev[k] != -1) next[prev[k]] = next[k];
                if (next[k] != -1) prev[next[k]] = prev[k];
                
                k = (next[k] != -1) ? next[k] : prev[k];
            } else if (command.startsWith("Z")) {
                int undo = history.pop();
                
                if (prev[undo] != -1) next[prev[undo]] = undo;
                if (next[undo] != -1) prev[next[undo]] = undo;
            }
        }
        
        char[] answer = new char[n];
        Arrays.fill(answer, 'O');
        
        while (!history.isEmpty()) {
            answer[history.pop()] = 'X';
        }

        return new String(answer);
    }
}
