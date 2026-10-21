
import java.util.*;
class Solution {
    public int[] solution(String s) {
        int transCnt = 0;
        int zeroCnt = 0;
        
        int n = s.length();
        
        while(!s.equals("1")){
            int cnt = 0;
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i<n; i++){
                if(s.charAt(i) == '0'){
                    cnt++;
                }else{
                    sb.append(1);
                }
            }
            s = sb.toString();

            s = Integer.toBinaryString(sb.length());
            
            transCnt++;
            zeroCnt += cnt;
            n = s.length();
        }
        
        return new int []{transCnt, zeroCnt};
    }
}
