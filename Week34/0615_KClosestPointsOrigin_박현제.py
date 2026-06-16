"""
O(nlonn), 정렬 / 힙 사용하면 O(klogn)
"""
class Solution(object):
    def kClosest(self, points, k):
        """
        :type points: List[List[int]]
        :type k: int
        :rtype: List[List[int]]
        """
        def get_dist(p):
            x, y = p
            return x*x + y*y

        order = []
        for p in points:
            x, y = p
            order.append((get_dist(p), x, y))

        order.sort(key=lambda x: x[0])

        ans = []
        for i in range(k):
            _, x, y = order[i] 
            ans.append([x, y])

        return ans
        
