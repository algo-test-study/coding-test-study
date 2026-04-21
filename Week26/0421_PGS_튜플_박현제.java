/*
Set, 구현 / O(s_len)
*/
import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String s) {
        List<Integer> ans = new ArrayList<>();
        List<List<Integer>> nums = new ArrayList<>();
        String[] strs = s.substring(2, s.length() - 2).split("\\},\\{");
        
        
        for(String str : strs){
            String [] curr = str.split(",");
            nums.add(Arrays.stream(curr).map(i -> Integer.parseInt(i)).collect(Collectors.toList()));        
        }
        
        nums.sort((a, b) -> a.size() - b.size());
        
        
        Set<Integer> set = new HashSet<>();
        
        for(List<Integer> num : nums){
            
            for(int n : num){
                if(!set.contains(n)){
                    set.add(n);
                    ans.add(n);
                    break;
                }
            }
        
        }
        
        
    

        
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
