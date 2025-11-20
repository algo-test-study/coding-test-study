/*
알고리즘
- 두 수 이어 붙였을 떄 더 큰 값을 우선으로 정렬
     - String 으로 변환
     - 수 이어 붙여 비교
     - 000... 일 경우 예외처리
 시간복잡도
 - O(nlogn*str.length)
*/
import java.util.*;
import java.util.stream.*;
class Solution {
    public String solution(int[] numbers) {     
        List<String> numStrs = new ArrayList<>();
        
        for(int num : numbers){
            numStrs.add(Integer.toString(num));
        }
        
        Collections.sort(numStrs, (a, b) -> ((b + a).compareTo(a + b)));
        
        StringBuilder sb = new StringBuilder();
        
        // "000..." 경우
        if(numStrs.get(0).equals("0")) return "0";
        
        for(String num : numStrs){
            sb.append(num);
        }
        
        return sb.toString();
    }
}
