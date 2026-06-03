"""
구현, O(n)

"""
class Solution(object):
    def insert(self, intervals, newInterval):
        """
        :type intervals: List[List[int]]
        :type newInterval: List[int]
        :rtype: List[List[int]]
        """
        
        lst = []

        n = len(intervals)        
        for i in range(n):
            prev_x, prev_y = intervals[i]
            curr_x, curr_y = newInterval

            if prev_y < curr_x:
                lst.append([prev_x, prev_y])
                
            elif curr_y < prev_x:
                lst.append(newInterval)
                newInterval = [prev_x, prev_y]

            else:
                newInterval = [min(prev_x, curr_x), max(prev_y, curr_y)]

        lst.append(newInterval)
        return lst

