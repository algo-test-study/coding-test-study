"""
O(n)
"""
from collections import Counter
class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: int
        """

        c = Counter(s)

        total = 0
        is_odd = False
      
        for cnt in c.values():
            if cnt % 2 == 0:
                total += cnt
            else:
                total += (cnt - 1)
                is_odd = True
        
        if is_odd:
            return total + 1
        return total
