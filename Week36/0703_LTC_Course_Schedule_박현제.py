"""
어떤 걸 먼저 수강해야 하는지가 나옴
즉, 노드 탐색에 순서가 있다

a: 위상정렬
사이클이 생기면 안됨
1. 위상 정렬을 하고
2. 사이클을 확인 -> 시작점 + visited or ind 배열에 0이 아닌 숫자가 있으면 사이클이 있는 것

t: O(m + n),n = numCourses m = prerequisites.length
"""
from collections import defaultdict, deque
class Solution(object):
    def canFinish(self, numCourses, prerequisites):
        """
        :type numCourses: int
        :type prerequisites: List[List[int]]
        :rtype: bool
        """

        n = numCourses
        
        ind = [0] * n

        g = defaultdict(list)

        for e, s in prerequisites:
            ind[e] += 1
            g[s].append(e)
    
        q = deque()

        for i, v in enumerate(ind):
            if v == 0:
                q.append((i))

        while q:
            curr = q.popleft()

            for nxt in g[curr]:
                ind[nxt] -= 1
                if ind[nxt] == 0:
                    q.append(nxt)
        

        return False if max(ind) > 0 else True



            
