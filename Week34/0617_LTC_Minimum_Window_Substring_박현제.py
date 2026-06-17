"""
O(len(s) + len(t))
"""
class Solution(object):
    def minWindow(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: str
        """
        ans = ""

        if len(t) > len(s):
            return ""

        need = Counter(t)

        window = defaultdict(int)

        req = len(need)
        cnt = 0

        l = 0
        min_len = float("inf")


        for r in range(len(s)):
            c = s[r]
            window[c] += 1

            if c in need and window[c] == need[c]:
                cnt += 1

            while cnt == req:
                if r - l + 1 < min_len:
                    min_len = r - l + 1
                    ans = s[l:r + 1]

                l_c = s[l]
                window[l_c] -= 1

                if l_c in need and window[l_c] < need[l_c]:
                    cnt -= 1

                l += 1

        return ans
