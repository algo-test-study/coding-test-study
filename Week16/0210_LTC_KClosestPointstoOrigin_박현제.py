"""
알고리즘
- 완탐
시간복잡도
- n = points.len
- O(nlogn)
"""
class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        # 방법1. 
        # def get_dist(cx, cy):
        #     return (cx**2 + cy**2)**(1/2)

        # lst = []
        # for x, y in points:
        #     dist = get_dist(x,y)
        #     lst.append((dist, x, y))
        
        # lst.sort(key= lambda x : x[0])

        # ans = []
        # for i in range(k):
        #     dist, x, y = lst[i]
        #     ans.append([x, y])
        # return ans

        # 방법2. 간단
        points.sort(key=lambda x: x[0]**2 + x[1]**2)
        return points[:k]
        
