import heapq
from collections import defaultdict
class FreqStack(object):
    def __init__(self):
        self.freq = defaultdict(int)
        self.stk = defaultdict(list)
        self.max_cnt = 0
     
    def push(self, val):
        """
        :type val: int
        :rtype: None
        """
        
        self.freq[val] += 1
        pos = self.freq[val]
        self.stk[pos].append(val)
        self.max_cnt = max(pos, self.max_cnt)

    def pop(self):
        """
        :rtype: int
        """
        
        val = self.stk[self.max_cnt].pop()
        self.freq[val] -= 1
        if not self.stk[self.max_cnt]:
            self.max_cnt -= 1

        return val


# Your FreqStack object will be instantiated and called as such:
# obj = FreqStack()
# obj.push(val)
# param_2 = obj.pop()
