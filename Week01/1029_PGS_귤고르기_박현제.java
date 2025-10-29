/*
귤 종류를 최소화

알고리즘
- 귤 개수를 셈 > map<귤의 크기, 귤 개수>
- 가장 많은 귤 개수 내림차순 정렬 > List<귤 크기>
- 종류 수 셈

시간복잡도
- n = tangerine.length 
- O(n + nlogn + n) = O(nlogn) = 10^5*log(2^17) = 10^5*17
*/
import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> num = new ArrayList<>();
        
        for(int size : tangerine){
            if(map.containsKey(size)){
                map.put(size, map.get(size) + 1);
            }else{
                map.put(size, 1);
                num.add(size);
            }
        }
        
        Collections.sort(num, (a,b) -> (map.get(b) - map.get(a)));
        
        int cnt = 0;
        
        for(int size : num){
            int curr = map.get(size);
            k -= curr;
            cnt++;
            if(k <= 0){
                break;
            }
        }
        
        return cnt;
    }
}
