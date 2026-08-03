"""
같은지 비교
# -> 백스페이스
가장 마지막에 있는 문자열을 지우면 된다 = 스택
O(len(s) + len(t))
""" 
class Solution:
    def backspaceCompare(self, s: str, t: str) -> bool:
        
        def process(strs):
            stk = []
            for curr in strs:
                if curr == '#':
                    if stk:
                        stk.pop()
                else:
                    stk.append(curr)
            return stk

        return process(s) == process(t)
