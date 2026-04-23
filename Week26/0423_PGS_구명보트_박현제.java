/**
정렬, 투포인터, O(N)
**/
import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        
        Arrays.sort(people);
        int n = people.length;
        int s = 0;
        int e = n-1;
        int cnt = 0;
        while(s <= e){
            if(people[s] + people[e] <= limit){
                s++;
            }
            cnt++;   
            e--;
        }
        
        return cnt;
    }
}
