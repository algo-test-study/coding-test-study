/*
- 진행상황, 진도 퍼센트/h 일때 하루마다 몇 개 기능 배포되는지

알고리즘
- 하루 마다 speeds 만큼 다 더함
- 만약 100 이상이면 배포 개수++
- 이미 배포된 것 체크 > 인덱스로

시간복잡도
- O(n^2)
*/

import java.util.*;
import java.util.stream.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> ans = new ArrayList<>();
        
        int idx = 0;
        int n = progresses.length;
        
        while(idx<n) {
            for(int i = 0; i < n; i++) {
                progresses[i] += speeds[i];
            }
            
            int cnt = 0;
            for(int i = idx; i < n; i++) {
                if(progresses[i] >= 100) {
                    cnt++;
                    idx++;
                } else{
                    break;
                }
            }
            
            if(cnt > 0) {
                ans.add(cnt);
            }
        }
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
