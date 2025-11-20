/*
알고리즘
- 완전탐색 O(26^20000)될 수 있음 > 불가
- dp
    - dp[i] = i번쨰 문자까지의 최솟값
    - dp[i] = (dp[현재 문자 삽입 전 문자열] + 1 , 현재 dp 값)
    - 문자열 길이 내에서 유효하도록 예외 처리 > isValid
시간복잡도
- O(t.length*str.length*5)
*/
import java.util.*;
class Solution {
    public int solution(String[] strs, String t) {
        int ans = 0;
        int n = t.length();
        int [] dp = new int [n+1];
        
        int max = 20001;
        for(int i=1;i<=n; i++){
            dp[i] = max;
        }
        
        for(int i=1; i<=n ; i++){
            for(String str : strs){
                if(isValid(str, t, i)){
                    dp[i] = Math.min(dp[i-str.length()] + 1, dp[i]);
                }
            }
        }
        
        for(int i=1; i<=n; i++){
            System.out.println(dp[i]);
        }

        return dp[n] == max ? -1 : dp[n];
    }
    
    static boolean isValid(String str, String t, int now){
        if(str.length() > now){
            return false;
        }
        
        // for(int i=0; i<str.length(); i++){
        //     if(str.charAt(i) != t.charAt(now - str.length() + i)) return false;
        // }
        
        if(!t.startsWith(str, now - str.length())) return false;
        
        return true;
    }
}
