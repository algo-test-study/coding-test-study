"""
알고리즘
- newInterval과 겹치지 않는 부분만 다 넣고, 겹치면 영역 확장

시간복잡도
- O(n)
"""
class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        i = 0
        n = len(intervals)
        ans = list()
        while i < n and intervals[i][1] < newInterval[0]:
            ans.append(intervals[i])
            i += 1
        
        curr_s = curr_e = 0
        while i < n and intervals[i][0] < newInterval[1]:
            ori_s, ori_e = intervals[i]
            newInterval[0] = min(ori_s, newInterval[0])
            newInterval[1] = max(ori_e, newInterval[1])
            i += 1

        ans.append(newInterval)
        
        while i < n:
            ans.append(intervals[i])
            i += 1

        return ans
