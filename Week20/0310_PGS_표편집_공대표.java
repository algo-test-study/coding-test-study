import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {        
        StringBuilder answer = new StringBuilder();

        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] removed = new boolean[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        Stack<int[]> stack = new Stack<>();
        
        for (String c : cmd) {
            char type = c.charAt(0);
            
            if (type == 'U') {
                int x = Integer.parseInt(c.split(" ")[1]);
                for (int i = 0; i < x; i++) {
                    k = prev[k];
                }
            } else if (type == 'D') {
                int x = Integer.parseInt(c.split(" ")[1]);
                for (int i = 0; i < x; i++) {
                    k = next[k];
                }
            } else if (type == 'C') {
                stack.push(new int[]{prev[k], k, next[k]});
                removed[k] = true;

                if (prev[k] != -1) next[prev[k]] = next[k];
                if (next[k] != -1) prev[next[k]] = prev[k];

                if (next[k] != -1) {
                    k = next[k];
                } else {
                    k = prev[k];
                }
            } else if (type == 'Z') {
                int[] restore = stack.pop();
                
                int p = restore[0];
                int cur = restore[1];
                int n2 = restore[2];

                removed[cur] = false;

                if (p != -1) next[p] = cur;
                if (n2 != -1) prev[n2] = cur;
            }
        }        

        for (int i = 0; i < n; i++) {
            if (removed[i]) {
                answer.append("X");
            } else { 
                answer.append("O");
            }
        }

        return answer.toString();
    }
}
