
import java.util.*;
class Solution {
    public int[] solution(String s) {
        int transCnt = 0;
        int zeroCnt = 0;
    
        while(!s.equals("1")){
            int oneCnt = 0;
            
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '0'){
                    zeroCnt++;
                }else{
                    oneCnt++;
                }
            }

            s = Integer.toBinaryString(oneCnt);
            
            transCnt++;
        }
        
        return new int []{transCnt, zeroCnt};
    }
}
