
import java.util.*;
class Solution {
    public static String [] str = {"님이 나갔습니다.", "님이 들어왔습니다."};
    
    public static Map<String, String> idMap = new HashMap<>();
    static List<String> ids = new ArrayList<>();
    static List<Integer> state = new ArrayList<>();
    
    public String[] solution(String[] record) {
        
        for(String cur : record){
            String [] opers = cur.split(" ");
            String oper = opers[0];
            
            if(oper.equals("Enter")){
                String id =opers[1];
                String nickname =opers[2];
                enter(id, nickname);
                
            }else if(oper.equals("Leave")){
                String id =opers[1];
                leave(id);
                
            }else{
                String id =opers[1];
                String nickname =opers[2];
                change(id, nickname);
                
            }
        }
        
        String [] ans = new String[state.size()];
        
        for(int i = 0; i < state.size(); i++){
            String curId = ids.get(i);
            int curState = state.get(i);
            String curNickname = idMap.get(curId);
            String result = curNickname + str[curState];
            ans[i] = result;
        }
        
        return ans;
    }
    
    private static void enter(String id, String nickname){
        idMap.put(id, nickname);
        
        ids.add(id);
        state.add(1);
    }
    
    private static void leave(String id){
        ids.add(id);
        state.add(0);
    }
    
    private static void change(String id, String nickname){
        idMap.put(id, nickname);
    }
}
