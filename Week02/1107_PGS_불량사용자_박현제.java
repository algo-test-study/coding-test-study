/*
알고리즘
- 밴 id는 set에 넣기
- dfs로 사용자 아이디 목록 확인
- set의 크기가 답

시간복잡도
- O(N^M * M)
*/
import java.util.stream.*;
import java.util.*;
class Solution {
    static Set<Set<String>> id = new HashSet<>();
    static String [] user_id;
    static String [] banned_id;
    
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        
        dfs(new HashSet<>(), 0);
        
        return id.size();
    }
    
    
    private static void dfs(Set<String> banSet, int depth){
        if(depth == banned_id.length){ 
            id.add(new HashSet<>(banSet));
            return;
        }
        
        String word = banned_id[depth];
        
        for(String user : user_id){
            if(banSet.contains(user) || !match(user, word)) continue;
            
            banSet.add(user);
            dfs(banSet, depth+1);
            banSet.remove(user); 
        }
    }
    
    
    
    private static boolean match(String user, String word){
        if(user.length() != word.length()){
            return false;
        }
        
        for(int i = 0; i < user.length(); i++){
            if(word.charAt(i) !='*' && word.charAt(i) != user.charAt(i)){
                return false;
            }  
        }
        
        return true;
    }
}
