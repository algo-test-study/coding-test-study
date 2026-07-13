# The isBadVersion API is already defined for you.
# @param version, an integer
# @return a bool
# def isBadVersion(version):

"""
이분 탐색, O(logn)
"""

class Solution(object):
    def firstBadVersion(self, n):
        """
        :type n: int
        :rtype: int
        """
        
        s = 1
        e = n

        ans = 0

        while s <= e:
            mid = (s + e) // 2
            if isBadVersion(mid):
                ans = mid
                e = mid - 1
            else:
                s = mid + 1
    
        return ans


        
