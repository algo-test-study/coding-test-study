"""
완전탐색: O(N^2) -> x
투포인터: O(N)

큰 넓이를 만들려면 낮은쪽 기둥을 옮기는 것이 유리하다.

"""
class Solution:
    def maxArea(self, height: List[int]) -> int:

        n = len(height)
        s, e = 0, n-1

        ans = 0
        while s < e:
            w = e - s
            h = min(height[e], height[s])

            area = w * h
            ans = max(ans, area)

            if height[s] < height[e]:
                s += 1
            else:
                e -= 1
        return ans
                 
            
            
            
