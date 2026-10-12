import java.util.*;
import java.util.stream.*;
class Solution {

  
    static List<String> ans;
    static boolean [] visit;
    static String[][] tickets;
    
    public String[] solution(String[][] tickets) {
        this.tickets = tickets;
        
        int len = tickets.length;
        visit = new boolean[len];
        ans = new ArrayList<>();
        
        dfs("ICN","ICN", 0, len);
        
        Collections.sort(ans);
        return ans.get(0).split(" ");
    }

    private static void dfs(String curr, String path, int depth, int len){
        if(depth == len){
            ans.add(path);
            return;
        }
        
        for(int i=0; i<len; i++){
            if(!tickets[i][0].equals(curr)) continue;
            
            if(visit[i]) continue;
            
            visit[i] = true;
            dfs(tickets[i][1],path + " " + tickets[i][1], depth + 1, len);
            visit[i] = false;
        }
    }
    
}
